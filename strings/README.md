# Desafio 1: Strings

## Proposta
Desenvolver um plugin para limitar o número de caracteres por linha do texto de uma mensagem de e-mail.
O plugin deve receber o texto e o limite de comprimento e retorná-lo formatado.
Na primeira parte da proposta o texto deve retornar limitado a 40 caracteres por linha, sem separar sílabas.
Na segunda parte da proposta o texto deve retornar justificado.
Em ambas partes, o texto deve ser parametrizável e é oferecida a opção extra de tonar a quantidade de caracteres por 
linha parametrizável.

## Modo de uso
Idealmente deveria ser usado da seguinte maneira:
- Executar a aplicação passando o caminho para o arquivo.txt seguido do número de caracteres desejado;
- Verificar que na pasta "home" foi gerado o arquivo outputFile.txt com o texto formatado.

## Solução
Todas as partes da proposta foram cumpridas sem que houvesse necessidade de usar o JavaTemplate.
Iniciei com o módulo de domínio, onde foram criadas três classes e seus respectivos testes:
- ParagraphWrapper: responsável por alcançar os resultados esperados na primeira parte da proposta. O seu método de 
execução recebe uma String que representa o parágrafo do texto original e retorna uma StringBuilder que representa o 
parágrafo com linhas limitadas em números de caracteres sem dividir sílabas. O método extrai as palavras da String 
recebida separadas por espaço em branco e, iterando essa lista de palavras, adiciona-as uma a uma separadas por espaço 
em branco em uma nova String, que chamamos de "wrappedLine", sempre verificando se a adição da próxima palavra não irá 
ultrapassar o limite de caracteres. Caso isso ocorra, o último espaço em branco inserido é deletado, a palavra atual é 
adicionada a String "wrappedParagraph", que representa o parágrafo formatado, e é adicionado um separador de linha. A 
String "wrappedLine" é "limpada" para que se inicie a composição de uma nova linha a partir do índice atual.
- ParagraphJustifier: responsável por alcançar os resultados esperados na segunda parte da proposta. O seu método de 
execucação recebe uma String que representa o parágrafo já limitado em caracteres e retorna uma StringBuilder que 
representa o parágrafo justificado. O método extrai as palavras da String recebida separadas por "\n" em uma lista, itera
sobre essa lista chamando o método "justifyLine" para cada e adicionando as linhas justificadas uma por uma na String
de retorno, separadas por "\n". O método "justifyLine" extrai as palavras da linha, separando por espaço em branco, e 
contabiliza o total de caracteres de todas as palavras da linha para então subtrair do número máximo de caracteres e 
descobrir quantos espaços em branco deverão ser acrescentados. O método então divide o total de espaços em branco pelo 
número de palavras para descobrir quantos espaços serão inseridos entre uma palavra e outra e o restante da divisão 
indica quantos caracteres extras deverão ser inseridos posteriormente. Iterando a lista de palavras, adiciona-se uma a 
uma à linha justificada seguida pelos espaços brancos e por um espaço extra, até que a lista de palavras chegue ao fim.
- ParagraphValidator: responsável por validar a quebra de linhas e compartilha seus métodos com ParagraphJustifier e 
ParagraphWrapper. O método "nextWordExceedsLineLimit" verifica se o tamanho da próxima palavra da lista somado ao 
tamanho da linha atual ultrapassa o limite de caracteres. Enquanto que o método "isLastWord" verifica se a palavra é a 
última palavra da lista.

Em seguida implementei o módulo de caso de uso onde criei a classe TextFormatter e sua classe de teste. Seu método 
de execução recebe uma Stream de String sobre a qual operamos uma redução ("collect") que chama o método privado 
"formatText", responsável por orquestrar a chamada do ParagraphWrapper e do ParagraphJustifier no momento adequado e 
retornar cada parágrafo formatado.

O passo seguinte foi a implementação do módulo de adaptadores(ou infraestrutura), responsável por fazer a leitura e 
escrita do arquivo de texto. Criamos a classe FileProcessor e sua respectiva classe de teste, onde o método de execução 
o caminho de input do texto original e o caminho de output do texto formatado. Ele então lê o arquivo de input, chama o
 TextFormatter passando o texto de input e repassa seu resultado para o método de escrever no arquivo.

Por fim escrevi o módulo de configuração e de aplicação. No primeiro tem-se a classe StringsConfig que instancia todos
 os objetos que serão usados na aplicação além de isolar atributos que serão reaproveitados em outras partes do sistema,
  como o número máximo de caracteres por linha e o caminho da home da máquina do usuário, onde será feito o output do 
  texto formatado. No segundo tem-se a classe com o método Main, responsável por executar a aplicação. Ela recebe os 
  argumentos passados pelo usuário, cria uma instância do StringsConfig passando para ele o limite de caracteres, chama
   a instância do FileProcessor existente no StringsConfig e seu método de execução passando o caminho do arquivo de 
   input e o caminho do arquivo de output (recuperado da StringsConfig).
   
 ## Tecnologias usadas
 - Maven 4
 - JUnit 5.5.2
 - Java 8
 - Mockito 1.10.19

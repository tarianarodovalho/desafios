# Desafios IDwall

## Propostas
Desenvolver duas aplicações, sendo a primeira um manipulador de Strings que formata um texto limitando o seu número de 
caracteres por linha e o justifica, e a segunda um crawler que coleta informações do Reddit e retorna ao usuário por meio
de um bot do Telegram. Mais detalhes no ReadMe dos projetos.

## Solução de Arquitetura
Para separar responsabilidades, da validação à persistência de informação, eu usei alguns conceitos aplicados de Clean
Architecture, como a definição de camadas:

![](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)
Camadas de Clean architecture

Isso assegura a modalidariedade do meu código em circustâncias, por exemplo, onde novas regras de negócio são adicionadas
e apenas a camada de domínio é modificada, ou ainda em caso de um novo entrypoint ser adicionado ao projeto (como uma API 
Rest) e apenas a camada de apresentação/adaptação é modificada, ou se um novo caso de uso é adicionado apenas a camada
de aplicação (UseCase) é modificada. E o mesmo pode ser visto em mudanças nas regras de validações que implicam que 
apenas a camada de domínio é afetada.

### Extras
##### Descreva o processo de resolução dos desafios;
Foquei de forma separada em cada um dos projetos na seguinte ordem:
* Strings
* Crawler

Então em ambos desenvolvi seguindo o fluxo:
1. Identifiquei quais eram os fluxos (casos de uso) pedidos na proposta do produto;
2. Identifiquei quais eram as responsabilidades pertinentes ao domínio;
3. Escrevi no código como deveria ser o fluxo de de interação entre o domínio;
4. Conforme a necessidade fui criando os serviços de domínio com a implementação apropriada;
5. Por último criei os devidos adapters para que a aplicação tenha interação externa.

Mais detalhes no readme individual de cada aplicação.

##### Descreva como utilizar a sua solução;
Idealmente a aplicação de Strings deveria ser rodada como um plugin de CLI onde você executa um comando no seu terminal
passando um arquivo .txt com o texto original e o número de caracteres desejado o plugin cria um novo arquivo .txt na home do usuário com o texto já 
formatado. Infelizmente já ao final do prazo de entrega tive alguns problemas no projeto com o maven e com o build
do IntelliJ e o mesmo parou de rodar as aplicações e não consegui nem mesmo gerar um jar para rodar o projeto pelo 
terminal.

##### Tratamento de erros e exceções. Fica a seu critério quais casos deseja tratar e como serão tratados;
Fiz alguns tratamentos de exceções, principalmente IOException e exceções específicas do Telegram: 
TelegramApiRequestException e TelegramApiException.

##### Testes unitários ou de integração;
* Testei a camada de domínio de forma unitária, isolando os métodos e verificando seus respectivos retornos;
* Testei também a camada de use cases realizando a integração dos objetos de domínio, isolando os respectivos objetos 
de domínio e validando as interações entre eles;

##### Use o Docker.
Não cheguei a gerar um container para o Docker visto que não consegui gerar os arquivos .jar.
# Desafio 2: Crawlers

## Proposta
Desenvolver uma aplicação que coleta subReddits e threads do Reddit e dispara uma mensagem para um bot do Telegram 
conforme uma lista de títulos passados pelo usuário quando esse enviar o comando \NadaPraFazer. São retornadas apenas as 
threads que possuem 5000 pontos ou mais.

## Modo de uso
Idealmente deveria ser usado da seguinte maneira:
- Rodar a aplicação;
- Acessar sua própria conta do Telegram e adicionar o bot "idwallTarianaCrawlerBot";
- Enviar o comando "/NadaPraFazer" seguido da lista de nomes de subReddits que deseja receber, como no
exemplo: "/NadaPraFazer cats;worldnews;brazil";
- O bot retornará uma mensagem com a lista de SubReddits buscados.

## Solução
Começando pela camada de domínio, criei o SubRedditGenerator, responsável por converter as informações coletadas no Reddit 
em uma lista de SubReddit com respectivas Threads. Criei também a interface SubRedditCrawler que possibilita a comunicação
da camada de domínio com a camada de apresentação/adaptação.
Em seguida implementei o caso de uso SubRedditFinder que orquestra a comunicação entre a camada de apresentação e a de 
domínio.
O próximo passo foi desenvolver a camada de adapters. Primeiro criei a SubRedditCrawlerImpl, que implementa a interface 
SubRedditCrawler, que por meio do JSoup realiza a coleta dos dados em um mapa composto pelo título do SubReddit e uma 
lista de Threads com as informações de score, título da Thread, Url dos comentários e da thread. Depois implementei a
CrawlersController, que recebe o resultado da camada de aplicação (UseCase) SubRedditFinder e o transforma em JSON para
então retornar ao TelegramBot, responsável por realizar a comunicação com o Telegram.
Por fim desenvolvi a camada "application" onde está a Main, que dá início à aplicação e registra o bot, dando início a 
ele, e também a camada de configuração que a Main acessa para recuperar informações como endpoint do Reddit e do 
TelegramBot, nome de usuário e token do bot, além de criar as instâncias necessárias para a execução de toda a aplicação.


## Tecnologias usadas
- Maven 4
- JUnit 5.5.2
- Java 8
- Mockito 1.10.19
- JSoup 1.12.1
- Gson 2.8.6
- Telegrambots 4.4.0.2
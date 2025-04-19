# Movie Indicator API

Esta aplicação lê um arquivo CSV com os vencedores da categoria "Pior Filme" do Golden Raspberry Awards e expõe uma rota REST para calcular o maior e menor intervalo entre vitórias de produtores.

## Como rodar

1. Requisitos: Java 21+ e Maven
2. Execute o comando abaixo para iniciar a aplicação:

```bash
mvn spring-boot:run
```

3. Caso queira iniciar o código sem dados, exclua o arquivo `src/main/resources/movielist.csv`.

## Endpoints

### GET `/producers/intervals`
Retorna o produtor com:
- Maior intervalo entre duas vitórias consecutivas
- Menor intervalo entre duas vitórias consecutivas

### POST `/movies/load`
Carrega novos filmes no sistema.

**Corpo da requisição (JSON):**
```json
[
  {
    "releaseYear": 1990,
    "title": "Movie One",
    "studios": "Studio A",
    "producers": "Producer X",
    "winner": true
  },
  {
    "releaseYear": 1995,
    "title": "Movie Two",
    "studios": "Studio B",
    "producers": "Producer Y",
    "winner": false
  }
]
```

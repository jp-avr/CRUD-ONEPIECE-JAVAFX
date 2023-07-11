## SOBRE

Olá. Esse projeto é uma aplicação feita em Java com o auxilio do JavaFX para interface gráfica para a cadeira de Paradigmas da programação.

## Estrutura do projeto

Temos duas pastas por padrão em nosso código, onde:

- `src`: a pasta principal
- `lib`: a pasta para manter as dependências

Os arquivos compilados vão se gerados na pasta `bin` por padrão.

> Caso queira customizar a estrutura da pasta, abra o `.vscode/settings.json` e atualize os dados.

## Arquivos

Como o projeto é feito utililizando JavaFX, sua estrutura é no modelo MVC (Model, View e Controller).
As subpastas estão divividas entre:

- `model`: Pasta onde irão encontrar os services e entities.
- `application`: Onde ficará o Main do projeto.
- `db`: Onde estará a conexão com o banco de dados e as exceções personalizadas
- `gui`: é onde estão todos os controllers e views

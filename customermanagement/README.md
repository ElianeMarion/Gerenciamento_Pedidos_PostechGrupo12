# Customer Management - API REST

## Construir a aplicação e seus containers

- Construir a aplicação
```sh
make build 
```

- Empacotar a aplicação e construir os container de aplicação e banco de dados
```sh
make docker-build
```

- Subir contaniers
```sh
make docker-start
```

- Parar contaniers
```sh
make docker-stop
```

## Execução dos Testes Regressivos

- execução dos testes unitários:
```sh
make unit-test
```

- execução dos testes integrados:
```sh
make integration-test
```

- execução dos testes de sistema
  - pré-requisito: subir os contaniers com aplicação e base de dados

```sh
make system-test
```

## Geração de Relatório dos Testes

- visualização do Relatório dos Testes (Allure)
  - (requer allure instalado)

```sh
allure serve target/allure-results
```




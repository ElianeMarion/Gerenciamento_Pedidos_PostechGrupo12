# Customer Management - API REST

## Execução dos Testes Regressivos

- para execução dos testes unitários:
```sh
make unit-test
```

- para execução dos testes integrados:
```sh
make integration-test
```

- para executar os testes de sistema
    - pré-requisito: subir os contaniers com aplicação e base de dados

```sh
make system-test
```

- Subir contaniers com aplicação e base de dados
```sh
make docker-start
```

- Parar contaniers
```sh
make docker-stop
```
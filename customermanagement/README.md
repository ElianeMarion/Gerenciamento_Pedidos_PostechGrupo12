# Customer Management - API REST

## API Reference
Need application started

[Swagger](http://localhost:8081/swagger-ui/index.html#/) 

## Build the application and its containers

- Clean project
```sh
mvn clean 
```

- Build the application
```sh
make build 
```

- Package the application and build the application and database containers
```sh
make docker-build
```

- Start contaniers
```sh
make docker-start
```

- Stop contaniers
```sh
make docker-stop
```

## Regressive testing

- executing unit tests:
```sh
make unit-test
```

- execution of integrated tests:
```sh
make integration-test
```

- execution of systems tests
  - prerequisite: start containers with application and database
```sh
make system-test
```

## Test Report Generation

- viewing the Test Report (Allure)
  - (requires allure installed)

```sh
allure serve target/allure-results
```

## Exemplos de requisição

Cadastrar um cliente
````
curl -X "POST" --location "http://localhost:8081/customer" --header "accept: */*" --header "Content-Type: application/json" --data "{\"name\":\"Maria do Rosário\",\"cpf\":\"17523716075\",\"phoneNumber\":\"11979974811\",\"address\":{\"street\":\"Rua Leblon\",\"number\":20,\"city\":\"Embu das Artes\",\"state\":\"São Paulo\",\"zipcode\":\"06826270\",\"subSector\":2}}"
````

Buscar cliente por ID
````
curl -X "GET" "http://localhost:8081/customer/1" -H "accept: */*"
````


# Customer Management - API REST

## API Reference
Need application started

[Swagger](http://localhost:8080/swagger-ui/index.html#/) 

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




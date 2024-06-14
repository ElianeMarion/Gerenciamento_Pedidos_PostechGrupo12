
build:
	mvn compile

unit-test:
	mvn test

integration-test:
	mvn test -P integration-test

system-test:
	mvn test -P system-test

package:
	mvn package

docker-build: package
	docker build -t customermanagement:dev -f ./Dockerfile .

docker-start:
	docker compose -f docker-compose.yaml up -d

docker-stop:
	docker compose -f docker-compose.yaml down


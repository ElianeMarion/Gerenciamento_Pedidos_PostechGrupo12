# Gerenciamento de Pedidos Postech - Grupo12
Challenge da Fase 4 sobre Gerenciamento de Pedidos Integrados com Spring e Microsserviços

### Start stop app

Iniciar aplicação com docker-compose na raiz do projeto
````
docker-compose -f docker-compose-app.yaml up --build
````

Finalizar aplicação com docker-compose removendo volumes
````
docker-compose -f docker-compose-app.yaml down -v
````
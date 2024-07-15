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

## Requests

### Microsserviço customer management
Cadastrar um cliente
````
curl -X "POST" --location "http://localhost:8081/customer" --header "accept: */*" --header "Content-Type: application/json" --data "{\"name\":\"Maria do Rosário\",\"cpf\":\"17523716075\",\"phoneNumber\":\"11979974811\",\"address\":{\"street\":\"Rua Leblon\",\"number\":20,\"city\":\"Embu das Artes\",\"state\":\"São Paulo\",\"zipcode\":\"06826270\",\"subSector\":2}}"
````
Buscar cliente por ID
````
curl -X "GET" "http://localhost:8081/customer/1" -H "accept: */*"
````


### Microsserviço payment
Registro de pagamento
````
curl -X "POST" --location "http://localhost:8080/payments" --header "Content-Type: application/json" --data "{\"paymentId\": \"3e4e11d1-7882-45f1-a3fb-0a8d9a613fcf\",\"orderId\": 1,\"value\": 10,\"status\": \"PROCESSING\"}"
````

Busca de pagamento
````
curl -X "GET" --location "http://localhost:8080/payments/3e4e11d1-7882-45f1-a3fb-0a8d9a613fcf"
````

### Microsserviço order

Criar Pedido
````
curl -X "POST" --location "http://localhost:8083/orders" --header "Content-Type: application/json" --data '{\
"customerId": 1,
    "deliveryDate": null,
    "status": "WAITING_SEPARATION",
    "deliveryAddressId": 1,
    "originAddressId": 1,
    "statusOrder": "WAITING_PAYMENT",
    "orderLine": [
        {
            "productId": 1,
            "quantity": 1
        }
    ]
}'
````
Listar pedidos
````
curl -X "GET" --location "http://localhost:8083/orders"
````


### Microsserviço delivery

Registro de entrega
````
curl -X "POST" --location "http://localhost:8084/delivery" --header "Content-Type: application/json" --data "{\"order\":{\"orderID\":10,\"customer\":{\"customerID\":1,\"name\":\"José Soares\",\"cpf\":\"70247079090\",\"phoneNumber\":\"11999992232\"},\"purchaseDate\":\"2024-05-12T09:11:59\",\"deliveryDate\":\"2024-05-14T09:23:35\",\"status\":\"Aguardando entrega\",\"orderLines\":[{\"orderLineID\":10,\"product\":{\"productID\": 1,\"name\": \"Paracetamol\",\"description\": \"Paracetamol 750\",\"price\": 8.99,\"quantityStock\":100},\"quantity\": 3,\"price\": 8.99}]},\"senderAddress\":{\"addressID\":1,\"street\":\"Rua Teodoro Sampaio\",\"number\":561,\"complement\":\"A\",\"city\":\"São Paulo\",\"state\":\"SP\",\"zipCode\":\"05405000\",\"subSector\":0}}"
````
Busca de entregas com status aguardando entrega por subsetor
````
curl -X "GET" --location "http://localhost:8084/delivery?subSector=0"
````
Atualização de entrega com id do entregador
````
curl -X "PUT" --location "http://localhost:8084/delivery?deliveryID=668d218e1426f7293d51defa&courierID=6670ed2561c1ca485c37ba6d"
````
Atualização de entrega para status Em trânsito que pertença ao ID de um entregador
````
curl -X "PUT" --location "http://localhost:8084/delivery/left/6670ed2561c1ca485c37ba6d"
````
Atualização de entrega para status Entrega concluída
````
curl -X "PUT" --location "http://localhost:8084/delivery/completed/668d218e1426f7293d51defa"
````
Cadastro de entregador
````
curl -X "POST" --location "http://localhost:8084/delivery/courier" --header "Content-Type: application/json" --data "{\"courierName\":\"Ed Motta\"}"
````
Listar entregadores
````
curl -X "GET" --location "http://localhost:8084/delivery/courier"
````
Atualizar status de um entregador (livre | ocupado | inativo)
````
curl -X "PUT" --location "http://localhost:8084/delivery/courier/6670ed2561c1ca485c37ba6d" --header "Content-Type: application/json" --data "{\"status\":\"ocupado\"}"
````
Busca de rota
``` 
curl -X "GET" --location "http://localhost:8084/delivery/route?addressOrigin=rua%20leblon%2C%2014%20-%20Jardim%20s%C3%A3o%20vicente%2C%20embu%20das%20artes%20-%20sp&addressDestination=rua%20teodoro%20sampaio%2C%20835%20-%20pinheiros%2C%20s%C3%A3o%20paulo%20-%20sp&bestRouteInRealTime=true&typeOfTransport=moto&avoidTols=true&avoidHighways=true"
```

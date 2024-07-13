db = db.getSiblingDB('deliverydb');

db.createCollection('courier');

db.courier.insertMany([
    {
        _id:"6670ed2561c1ca485c37ba6d",
        courierName:"João Pereira",
        status:"livre",
        lastDelivery: ISODate('2024-03-02T15:22:01.629Z')
    },
    {
        _id:"6670ed2661c1ca485c37ba6e",
        courierName:"Maria Aparecida",
        status:"livre",
        lastDelivery: ISODate('2024-03-04T16:12:25.122Z')
     }
 ]);

 db.createCollection('delivery');

 db.delivery.insertMany([
    {
       "order": {
            "orderID": 1,
            "customer": {
                "customerID": 1,
                "name": "José Soares",
                "cpf": "70247079090",
                "phoneNumber": "11999992232"
            },
            "purchaseDate": "2024-05-12T09:11:59",
            "deliveryDate": "2024-05-14T09:23:35",
            "status": "Aguardando entrega",
            "orderLines": [
                {
                    "orderLineID": 1,
                    "product": {
                        "productID": 1,
                        "name": "Paracetamol",
                        "description": "Paracetamol 750",
                        "price": 8.99,
                        "quantityStock": 10
                    },
                    "quantity": 3,
                    "price": 8.99
                }
            ]
        },
        "senderAddress": {
            "addressID": 1,
            "street": "Rua Teodoro Sampaio",
            "number": 561,
            "complement": null,
            "city": "São Paulo",
            "state": "SP",
            "zipCode": "05405000",
            "subSector": 0
        },
        _id:"668d218e1426f7293d51defa",
        "departureDate": null,
        "arrivalDate": null
    },
    {
        "order": {
            "orderID": 2,
            "customer": {
                "customerID": 1,
                "name": "José Soares",
                "cpf": "70247079090",
                "phoneNumber": "11999992232"
            },
            "purchaseDate": "2024-05-12T09:11:59",
            "deliveryDate": "2024-05-14T09:23:35",
            "status": "Aguardando entrega",
            "orderLines": [
                {
                    "orderLineID": 2,
                    "product": {
                        "productID": 1,
                        "name": "Paracetamol",
                        "description": "Paracetamol 750",
                        "price": 8.99,
                        "quantityStock": 7
                    },
                    "quantity": 1,
                    "price": 8.99
                }
            ]
        },
        "senderAddress": {
            "addressID": 1,
            "street": "Rua Teodoro Sampaio",
            "number": 561,
            "complement": null,
            "city": "São Paulo",
            "state": "SP",
            "zipCode": "05405000",
            "subSector": 0
        },
        _id:"668d218e1426f7293d51defb",
        "departureDate": null,
        "arrivalDate": null
    }
 ]);


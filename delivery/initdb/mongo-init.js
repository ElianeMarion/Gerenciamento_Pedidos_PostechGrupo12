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


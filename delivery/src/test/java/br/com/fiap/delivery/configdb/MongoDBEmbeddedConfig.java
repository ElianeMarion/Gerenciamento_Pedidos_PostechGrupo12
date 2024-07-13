package br.com.fiap.delivery.configdb;


import br.com.fiap.delivery.utils.Util;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;



//@TestConfiguration
public class MongoDBEmbeddedConfig {

    private MongoCollection<Document> collection;

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoServer mongoServer) {
        String connection = mongoServer.getConnectionString();
        return new SimpleMongoClientDatabaseFactory(connection + "/test");
    }

    @Bean(destroyMethod = "shutdown")
    public MongoServer mongoServer() {
        MongoServer mongoServer = new MongoServer(new MemoryBackend());
        mongoServer.bind();
//        setItens(mongoServer);
        return mongoServer;
    }

    private void setItens(MongoServer server) {
            String connectionString = server.bindAndGetConnectionString();
        try (MongoClient client = MongoClients.create(connectionString)){
            client.getDatabase("test").createCollection("delivery");
            collection = client.getDatabase("test").getCollection("delivery");
            String order = Util.serialize(Util.Objects.buildOrder());
            String address = Util.serialize(Util.Objects.buildAddress());
            Document obj = new Document("deliveryID", "66703ca39cf3737f9cd99af6")
                    .append("order", order)
                    .append("senderAddress", address);
            collection.insertOne(obj);
            collection.find().first();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO
        }
    }


//    private static final String CONNECTION_STRING = "mongodb://%s:%d";
//
//    @Value("${spring.data.mongodb.host:localhost}")
//    String ip;
//    @Value("${spring.data.mongodb.port:0}")
//    int port;
//
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws IOException {
//        final MongoTemplate mongoTemplate = new MongoTemplate(MongoClients
//                .create(String.format(CONNECTION_STRING, ip, port)), "delivery");
//        return mongoTemplate;
//
//    }

//    @Bean
//    public MongodExecutable mongodExecutable() throws IOException {
//        ImmutableMongodConfig mongodConfig = MongodConfig
//                .builder()
//                .isShardServer(true)
//                .version(Version.Main.V4_4)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();
//
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        return starter.prepare(mongodConfig);
//    }

}

package it.bitrock.mongodbbitrock.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@Service
public class MongoDBConfiguration {




    @Bean
    public MongoDatabase mongoDatabase(@Value("${spring.data.mongodb.uri}") String connectionString,@Value("${spring.data.mongodb.database}") String database) {
        return MongoClients.create(mongoClientSettings(connectionString)).getDatabase(database);
    }


    private MongoClientSettings mongoClientSettings(String connString) {
        ConnectionString connectionString = new ConnectionString(connString);
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();
    }


}

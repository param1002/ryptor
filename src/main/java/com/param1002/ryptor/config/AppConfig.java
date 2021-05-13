package com.param1002.ryptor.config;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Bean
    public MongoClientFactoryBean mongo() {

        final MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost(host);
        mongo.setPort(port);
        mongo.setCredential(getCredential());
        return mongo;
    }

    MongoCredential[] getCredential() {

        return new MongoCredential[]{MongoCredential.createCredential(username, database, password.toCharArray())};
    }

    @Bean
    public MongoTemplate mongoTemplate(final MongoClient mongoClient) {

        return new MongoTemplate(mongoClient, database);
    }

}

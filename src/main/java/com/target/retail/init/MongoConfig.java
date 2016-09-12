package com.target.retail.init;



import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@PropertySource("classpath:application.properties")
class MongoConfig extends AbstractMongoConfiguration {

  @Autowired
  private Environment env;

  @Override
  public MongoClient mongo() throws Exception {

    String userName = env.getProperty("mongodb.username");
    String password = env.getProperty("mongodb.password");
    String portValue = env.getProperty("mongodb.port");

    MongoClientOptions mongoClientOptions =
        MongoClientOptions.builder().connectTimeout(50000).maxWaitTime(800).serverSelectionTimeout(50000).build();

    MongoCredential mongoCredential =
        MongoCredential.createCredential(userName, getDatabaseName(), password.toCharArray());

    ServerAddress serverAddress = new ServerAddress(env.getProperty("mongodb.host"), Integer.parseInt(portValue));

    MongoClient mongoClient =
        new MongoClient(serverAddress, Collections.singletonList(mongoCredential), mongoClientOptions);

    return mongoClient;
  }

  @Override
  public MongoTemplate mongoTemplate() throws Exception {
    final MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getDatabaseName());
    mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
    return mongoTemplate;
  }

  @Override
  protected String getDatabaseName() {
    return env.getProperty("mongodb.database");
  }
  
  

}

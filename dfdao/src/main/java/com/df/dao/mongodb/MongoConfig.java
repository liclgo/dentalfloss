package com.df.dao.mongodb;


import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {
	private @Value("#{propertiesReader.mongoip}")
	String jdbcUrl;

	public @Bean
	MongoOperations abc() throws UnknownHostException {
		return new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(
				jdbcUrl), "test"));
	}

}
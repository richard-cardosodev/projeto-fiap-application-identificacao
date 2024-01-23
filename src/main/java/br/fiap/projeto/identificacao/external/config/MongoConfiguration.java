package br.fiap.projeto.identificacao.external.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.fiap.projeto.identificacao.external.repository.mongo")
public class MongoConfiguration {
}

package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.adapter.gateway.MongoClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.external.repository.mongo.MongoClientRepository;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongo")
public class BeansConfigurationMongo {
    @Bean
    public IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway(MongoClientRepository mongoClientRepository) {
        return new MongoClienteRepositoryAdapterGateway(mongoClientRepository);
    }
}

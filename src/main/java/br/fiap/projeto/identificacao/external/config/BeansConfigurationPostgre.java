package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.adapter.gateway.PostgresClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.external.repository.postgres.PostgresClienteRepository;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgre")
public class BeansConfigurationPostgre {
    @Bean
    public IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway(PostgresClienteRepository postgresClienteRepository) {
        return new PostgresClienteRepositoryAdapterGateway(postgresClienteRepository);
    }
}

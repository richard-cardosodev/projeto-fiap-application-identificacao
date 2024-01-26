package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.adapter.gateway.MongoClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.adapter.gateway.PostgresClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.external.repository.postgres.PostgresClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration @RequiredArgsConstructor @Slf4j
public class ClienteDataLoader {

    private final MongoClienteRepositoryAdapterGateway mongoGateway;

    private final PostgresClienteRepository postgresClienteRepository;

    @PostConstruct
    @SneakyThrows
    public void init() {

        PostgresClienteRepositoryAdapterGateway postgresGateway;
        List<Cliente> clientes;

        clientes = Collections.singletonList(
                new Cliente(UUID.randomUUID().toString(), "Cliente1", "01234567890", "cliente1@test.com")
        );

        postgresGateway = new PostgresClienteRepositoryAdapterGateway(postgresClienteRepository);

        for (Cliente cliente : clientes) {
            if (!mongoGateway.buscaPorCpf(cliente.getCpf().getNumero()).isPresent()) {
                log.info("Inserindo cpf {} no repositorio da classe {}", cliente.getCpf(), mongoGateway.getClass().getName());
                mongoGateway.insere(cliente);
            }
            if (!postgresGateway.buscaPorCpf(cliente.getCpf().getNumero()).isPresent()) {
                log.info("Inserindo cpf {} no repositorio da classe {}", cliente.getCpf(), postgresGateway.getClass().getName());
                postgresGateway.insere(cliente);
            }
        }
    }
}

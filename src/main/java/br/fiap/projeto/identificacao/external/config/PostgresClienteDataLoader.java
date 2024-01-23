package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration
@Profile("postgre")
public class PostgresClienteDataLoader {

    @Autowired
    private IClienteRepositoryAdapterGateway clienteRepository;

    @PostConstruct
    @SneakyThrows
    public void init() {
        List<Cliente> clientes = Collections.singletonList(
                new Cliente(UUID.randomUUID().toString(), "Cliente1", "01234567890", "cliente1@test.com")
        );
        clientes.forEach(cli -> clienteRepository.insere(cli));
    }
}

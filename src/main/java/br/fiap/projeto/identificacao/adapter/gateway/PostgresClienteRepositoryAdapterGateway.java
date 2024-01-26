package br.fiap.projeto.identificacao.adapter.gateway;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.external.repository.entity.ClienteEntity;
import br.fiap.projeto.identificacao.external.repository.postgres.PostgresClienteRepository;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostgresClienteRepositoryAdapterGateway implements IClienteRepositoryAdapterGateway {

    private static final Logger log = LoggerFactory.getLogger(PostgresClienteRepositoryAdapterGateway.class);

    private final PostgresClienteRepository postgresClienteRepository;

    public PostgresClienteRepositoryAdapterGateway(PostgresClienteRepository postgresClienteRepository) {
        this.postgresClienteRepository = postgresClienteRepository;
    }

    @Override
    public Cliente insere(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = postgresClienteRepository.save(clienteEntity);
        return clienteEntity.toCliente();
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        log.info("Atualizando {}", cliente);
        return insere(cliente);
    }

    @Override
    public void remove(Cliente cliente) {
        postgresClienteRepository.save(ClienteEntity.fromCliente(cliente));
    }

    @Override
    public Optional<Cliente> busca(String codigo) {
        Optional<ClienteEntity> clienteRecuperado = postgresClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return clienteRecuperado.map(ClienteEntity::toCliente);
    }

    @Override
    public List<Cliente> buscaTodos() {
        List<ClienteEntity> entidades = postgresClienteRepository.findAllByDataExclusaoIsNull();
        return entidades.stream().map(ClienteEntity::toCliente).collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> buscaPorCpf(String cpf) {
        Optional<ClienteEntity> entity = postgresClienteRepository.findByCpfAndDataExclusaoIsNull(cpf);
        return entity.map(ClienteEntity::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        Optional<ClienteEntity> entity = postgresClienteRepository.findByEmailAndDataExclusaoIsNull(email);
        return entity.map(ClienteEntity::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorCodigoEDataExclusaoNula(String codigo) {
        Optional<ClienteEntity> cliente = postgresClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return cliente.map(ClienteEntity::toCliente);
    }
}

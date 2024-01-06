package br.fiap.projeto.identificacao.adapter.gateway;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.external.repository.entity.ClienteEntity;
import br.fiap.projeto.identificacao.external.repository.postgres.SpringClienteRepository;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteRepositoryAdapterGateway implements IClienteRepositoryAdapterGateway {

    private final SpringClienteRepository springClienteRepository;

    public ClienteRepositoryAdapterGateway(SpringClienteRepository springClienteRepository) {
        this.springClienteRepository = springClienteRepository;
    }

    @Override
    public Cliente insere(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = springClienteRepository.save(clienteEntity);
        return clienteEntity.toCliente();
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = springClienteRepository.save(clienteEntity);
        return clienteEntity.toCliente();
    }

    @Override
    public void remove(Cliente cliente) {
        springClienteRepository.save(ClienteEntity.fromCliente(cliente));
    }

    @Override
    public Optional<Cliente> busca(String codigo) {
        Optional<ClienteEntity> clienteRecuperado = springClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return clienteRecuperado.map(ClienteEntity::toCliente);
    }

    @Override
    public List<Cliente> buscaTodos() {
        List<ClienteEntity> entidades = springClienteRepository.findAllByDataExclusaoIsNull();
        return entidades.stream().map(ClienteEntity::toCliente).collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> buscaPorCpf(String cpf) {
        Optional<ClienteEntity> entity = springClienteRepository.findByCpfAndDataExclusaoIsNull(cpf);
        return entity.map(ClienteEntity::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        Optional<ClienteEntity> entity = springClienteRepository.findByEmailAndDataExclusaoIsNull(email);
        return entity.map(ClienteEntity::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorCodigoEDataExclusaoNula(String codigo) {
        Optional<ClienteEntity> cliente = springClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return cliente.map(ClienteEntity::toCliente);
    }
}

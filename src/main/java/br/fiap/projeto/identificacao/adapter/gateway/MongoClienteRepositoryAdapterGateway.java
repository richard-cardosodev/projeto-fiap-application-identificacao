package br.fiap.projeto.identificacao.adapter.gateway;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.external.repository.mongo.MongoClienteRepository;
import br.fiap.projeto.identificacao.external.repository.mongo.collection.ClienteDocument;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MongoClienteRepositoryAdapterGateway implements IClienteRepositoryAdapterGateway {

    private MongoClienteRepository mongoClienteRepository;

    public MongoClienteRepositoryAdapterGateway(MongoClienteRepository mongoClienteRepository) {
        this.mongoClienteRepository = mongoClienteRepository;
    }

    @Override
    public Optional<Cliente> busca(String codigo) {
        Optional<ClienteDocument> clienteRecuperado = mongoClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return clienteRecuperado.map(ClienteDocument::toCliente);
    }

    @Override
    public List<Cliente> buscaTodos() {
        List<ClienteDocument> entidades = mongoClienteRepository.findAllByDataExclusaoIsNull();
        return entidades.stream().map(ClienteDocument::toCliente).collect(Collectors.toList());
    }

    @Override
    public Cliente insere(Cliente cliente) {
        ClienteDocument clienteDocument = ClienteDocument.fromCliente(cliente);
        clienteDocument = mongoClienteRepository.save(clienteDocument);
        return clienteDocument.toCliente();
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        ClienteDocument clienteDocument = ClienteDocument.fromCliente(cliente);
        clienteDocument = mongoClienteRepository.save(clienteDocument);
        return clienteDocument.toCliente();
    }

    @Override
    public void remove(Cliente cliente) {
        mongoClienteRepository.save(ClienteDocument.fromCliente(cliente));
    }

    @Override
    public Optional<Cliente> buscaPorCpf(String cpf) {
        Optional<ClienteDocument> entity = mongoClienteRepository.findByCpfAndDataExclusaoIsNull(cpf);
        return entity.map(ClienteDocument::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        Optional<ClienteDocument> entity = mongoClienteRepository.findByEmailAndDataExclusaoIsNull(email);
        return entity.map(ClienteDocument::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorCodigoEDataExclusaoNula(String codigo) {
        Optional<ClienteDocument> cliente = mongoClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return cliente.map(ClienteDocument::toCliente);
    }
}

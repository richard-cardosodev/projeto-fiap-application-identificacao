package br.fiap.projeto.identificacao.adapter.gateway;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.external.repository.mongo.MongoClientRepository;
import br.fiap.projeto.identificacao.external.repository.mongo.collection.ClienteDocument;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MongoClienteRepositoryAdapterGateway implements IClienteRepositoryAdapterGateway {

    private MongoClientRepository mongoClientRepository;

    public MongoClienteRepositoryAdapterGateway(MongoClientRepository mongoClientRepository) {
        this.mongoClientRepository = mongoClientRepository;
    }

    @Override
    public Optional<Cliente> busca(String codigo) {
        Optional<ClienteDocument> clienteRecuperado = mongoClientRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return clienteRecuperado.map(ClienteDocument::toCliente);
    }

    @Override
    public List<Cliente> buscaTodos() {
        List<ClienteDocument> entidades = mongoClientRepository.findAllByDataExclusaoIsNull();
        return entidades.stream().map(ClienteDocument::toCliente).collect(Collectors.toList());
    }

    @Override
    public Cliente insere(Cliente cliente) {
        ClienteDocument clienteDocument = ClienteDocument.fromCliente(cliente);
        clienteDocument = mongoClientRepository.save(clienteDocument);
        return clienteDocument.toCliente();
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        ClienteDocument clienteDocument = ClienteDocument.fromCliente(cliente);
        clienteDocument = mongoClientRepository.save(clienteDocument);
        return clienteDocument.toCliente();
    }

    @Override
    public void remove(Cliente cliente) {
        mongoClientRepository.save(ClienteDocument.fromCliente(cliente));
    }

    @Override
    public Optional<Cliente> buscaPorCpf(String cpf) {
        Optional<ClienteDocument> entity = mongoClientRepository.findByCpfAndDataExclusaoIsNull(cpf);
        return entity.map(ClienteDocument::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        Optional<ClienteDocument> entity = mongoClientRepository.findByEmailAndDataExclusaoIsNull(email);
        return entity.map(ClienteDocument::toCliente);
    }

    @Override
    public Optional<Cliente> buscaPorCodigoEDataExclusaoNula(String codigo) {
        Optional<ClienteDocument> cliente = mongoClientRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        return cliente.map(ClienteDocument::toCliente);
    }
}

package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;
import java.util.Optional;

public interface IClienteRepositoryAdapterGateway {

    Optional<Cliente> busca(String codigo) throws EntradaInvalidaException;

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente atualiza(Cliente cliente);

    void remove(Cliente cliente);

    Optional<Cliente> buscaPorCpf(String cpf);

    Optional<Cliente> buscaPorEmail(String email);

    Optional<Cliente> buscaPorCodigoEDataExclusaoNula(String codigo);
}

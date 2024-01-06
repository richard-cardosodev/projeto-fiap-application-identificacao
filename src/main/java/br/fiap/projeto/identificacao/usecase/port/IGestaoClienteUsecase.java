package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IGestaoClienteUsecase {

    Cliente busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente) throws EntradaInvalidaException, EntidadeNaoEncontradaException;

    Cliente edita(Cliente cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    Cliente buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException;
}

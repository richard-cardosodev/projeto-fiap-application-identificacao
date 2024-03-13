package br.fiap.projeto.identificacao.usecase.port;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

public interface ILGPDExcluiDadosUseCase {
    void excluiDados(Cliente cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException;
}

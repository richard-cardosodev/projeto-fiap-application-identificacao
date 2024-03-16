package br.fiap.projeto.identificacao.adapter.controller.port;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.ExcluiClienteRequestDTO;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

public interface ILGPDRestAdapterController {
    void excluiDados(ExcluiClienteRequestDTO cliente) throws EntradaInvalidaException, EntidadeNaoEncontradaException;
}

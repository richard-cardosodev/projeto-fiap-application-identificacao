package br.fiap.projeto.identificacao.adapter.controller.port;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IClienteRestAdapterController {

    ClienteResponseDTO busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    List<ClienteResponseDTO> buscaTodos();

    ClienteResponseDTO insere(ClienteRequestDTO cliente) throws EntradaInvalidaException, EntidadeNaoEncontradaException;

    ClienteResponseDTO atualiza(String codigo, ClienteRequestDTO cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    ClienteResponseDTO buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException;
}

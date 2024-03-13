package br.fiap.projeto.identificacao.usecase;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.ILGPDExcluiDadosUseCase;

import java.util.Optional;

public class LGPDExcluiDadosUseCase implements ILGPDExcluiDadosUseCase {

    private static final String DADOS_INVALIDOS = "Os dados enviados não correspondem ao registro, não foi possível efetuar a exclusão!";
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado!";

    private IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway;

    public LGPDExcluiDadosUseCase(IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway) {
        this.clienteRepositoryAdapterGateway = clienteRepositoryAdapterGateway;
    }

    @Override
    public void excluiDados(Cliente dadosCliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Optional<Cliente> clienteEncontrado = clienteRepositoryAdapterGateway.buscaPorEmail(dadosCliente.getEmail().getEndereco());
        if(!clienteEncontrado.isPresent()){
            throw new EntidadeNaoEncontradaException(CLIENTE_NAO_ENCONTRADO);
        }

        validaDadosExclusao(clienteEncontrado.get(), dadosCliente);

        Cliente clienteAnonimizado = clienteEncontrado.get().anonimizarDadosParaExclusao();
        clienteAnonimizado.adicionaDataDeExclusao();
        clienteRepositoryAdapterGateway.atualiza(clienteAnonimizado);
    }

    private void validaDadosExclusao(Cliente clienteEncontrado, Cliente dadosCliente) throws EntradaInvalidaException {
        if (clienteEncontrado.getEmail().getEndereco().trim().toLowerCase().equals(dadosCliente.getEmail().getEndereco().trim().toLowerCase()) &&
            clienteEncontrado.getNome().trim().toLowerCase().equals(dadosCliente.getNome().trim().toLowerCase()) &&
            clienteEncontrado.getTelefone().equals(dadosCliente.getTelefone())) {
            return;
        }
        throw new EntradaInvalidaException(DADOS_INVALIDOS);
    }
}

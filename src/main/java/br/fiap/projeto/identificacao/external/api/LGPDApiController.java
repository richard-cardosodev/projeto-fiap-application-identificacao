package br.fiap.projeto.identificacao.external.api;

import br.fiap.projeto.identificacao.adapter.controller.port.ILGPDRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.ExcluiClienteRequestDTO;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lgpd")
@Api(tags = {"Controle LGPD"})
public class LGPDApiController {

    private ILGPDRestAdapterController lgpdRestAdapterController;

    public LGPDApiController(ILGPDRestAdapterController lgpdRestAdapterController) {
        this.lgpdRestAdapterController = lgpdRestAdapterController;
    }

    @Transactional
    @DeleteMapping("/excluir")
    @ApiOperation(value = "Anonimiza e exclui", notes = "Este endpoint anonimiza os dados do cliente e efetua a exclusão logica")
    public ResponseEntity<Void> excluiDados(@RequestBody ExcluiClienteRequestDTO cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        lgpdRestAdapterController.excluiDados(cliente);
        return ResponseEntity.noContent().build();
    }
}

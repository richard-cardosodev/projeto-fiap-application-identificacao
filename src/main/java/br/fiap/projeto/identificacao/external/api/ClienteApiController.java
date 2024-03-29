package br.fiap.projeto.identificacao.external.api;

import br.fiap.projeto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Log4j2
@Api(tags = {"Identificação"})
public class ClienteApiController {

    private final IClienteRestAdapterController clienteAdapterController;

    @PostMapping
    @SneakyThrows
    @ApiOperation(value = "Insere as informações de cliente", notes = "Este endpoint insere as informações de um cliente que optou por se identificar")
    public ResponseEntity<ClienteResponseDTO> insere(@RequestBody ClienteRequestDTO cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteAdapterController.insere(cliente));
    }

    @PutMapping("/{codigo}")
    @SneakyThrows
    @ApiOperation(value = "Atualiza as informações de cliente", notes = "Este endpoint atualiza as informações de um cliente")
    public ClienteResponseDTO atualiza(@PathVariable String codigo, @RequestBody ClienteRequestDTO cliente) {
        return clienteAdapterController.atualiza(codigo, cliente);
    }

    @DeleteMapping("/{codigo}")
    @SneakyThrows
    @ApiOperation(value = "Remove o registro de cliente", notes = "Este endpoint remove o registro de um cliente")
    public ResponseEntity<Void> remove(@PathVariable String codigo) {
        clienteAdapterController.remove(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigo}")
    @SneakyThrows
    @ApiOperation(value = "Busca as informações de cliente", notes = "Este endpoint busca as informações de um cliente identificado")
    public ClienteResponseDTO busca(@PathVariable String codigo) {
        return clienteAdapterController.busca(codigo);
    }

    @GetMapping("/todos")
    @ApiOperation(value = "Busca todos os clientes", notes = "Este endpoint busca todos os clientes")
    public List<ClienteResponseDTO> buscaTodos() {
        return clienteAdapterController.buscaTodos();
    }

    @GetMapping("/cpf")
    @SneakyThrows
    @ApiOperation(value = "Busca cliente por CPF", notes = "Este endpoint busca as informações de um cliente por CPF")
    public ClienteResponseDTO buscaPorCpf(@RequestParam String cpf) {
        return clienteAdapterController.buscaPorCpf(cpf);
    }
}

package br.fiap.projeto.identificacao.bdd;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import br.fiap.projeto.util.CPFUtil;
import br.fiap.projeto.util.EmailUtil;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinitions {

    private Response response;

    private ClienteResponseDTO clienteResponseDTO;

    private final String ENDPOINT_API = "http://localhost:8080/identificacao/clientes";

    @Quando("submeter um novo cliente")
    public void submeterUmNovoCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(geraClienteRequestDTO())
                .when()
                .post(ENDPOINT_API);
        clienteResponseDTO =  response
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(ClienteResponseDTO.class);
    }

    @Então("o cliente foi registrado com sucesso")
    public void clienteRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseDTOSchema.json"));
    }

    @Dada("que um cliente ja foi registrado")
    public void clienteJaFoiRegistrado() {
        submeterUmNovoCliente();
        Assertions.assertEquals(true, Optional.ofNullable(clienteResponseDTO).isPresent());
    }

    @Quando("requisitar a alteracao do cliente")
    public void requisitarAlteracaoDaIdentificacao() {
        ClienteRequestDTO request = new ClienteRequestDTO(clienteResponseDTO.getNome(), clienteResponseDTO.getCpf(), "marioumc@gmail.com");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .put(ENDPOINT_API + "/{id}", clienteResponseDTO.getCodigo().toString());
    }

    @Então("o cliente foi alterado com sucesso")
    public void clienteFoiAlteradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseDTOSchema.json"));
    }

    @Quando("requisitar a exclusao do cliente")
    public void requisitarExclusaoDoCliente() {
        response = given()
                .when()
                .delete(ENDPOINT_API + "/{id}", clienteResponseDTO.getCodigo().toString());
    }

    @Então("o cliente eh excluido com sucesso")
    public void clienteExcluidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Quando("requisitar a busca do cliente")
    public void requisitarBuscaDoCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_API + "/{id}", clienteResponseDTO.getCodigo());
    }

    @Então("o cliente eh exibido com sucesso")
    public void clienteExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseDTOSchema.json"));
    }

    @Quando("requisitar a busca de um cliente pelo cpf")
    public void requisitarBuscaPorUmClientePeloCpf() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .queryParam("cpf", clienteResponseDTO.getCpf())
                .get(ENDPOINT_API + "/cpf");
    }

    @Quando("requisitar a lista de clientes")
    public void requisitarListaDeClientes() {
        response = given()
                .when()
                .get(ENDPOINT_API + "/todos");
    }

    @Então("os clientes sao exibidos com sucesso")
    public void clientesExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ListaClienteResponseDTOSchema.json"));
    }

    private ClienteRequestDTO geraClienteRequestDTO() {
        return ClienteRequestDTO.builder()
                .nome("Cliente1")
                .email(EmailUtil.gerarEmail())
                .cpf(CPFUtil.gerarCPFSoNumeros())
                .build();
    }
}

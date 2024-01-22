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

    @Quando("submeter uma nova identificação")
    public void submeterUmaNovaIdentificação() {
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

    @Então("a identificação foi registrada com sucesso")
    public void identificacaoFoiRegistradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseDTOSchema.json"));
    }

    @Dada("que uma identificação já foi registrada")
    public void umaIdentificacaoJaFoiRegistrada() {
        submeterUmaNovaIdentificação();
        Assertions.assertEquals(true, Optional.ofNullable(clienteResponseDTO).isPresent());
    }

    @Quando("requisitar a alteração da identificação")
    public void requisitarAlteracaoDaIdentificação() {
        ClienteRequestDTO request = new ClienteRequestDTO(clienteResponseDTO.getNome(), clienteResponseDTO.getCpf(), "marioumc@gmail.com");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .put(ENDPOINT_API + "/{id}", clienteResponseDTO.getCodigo().toString());
    }

    @Então("a identificação foi alterada com sucesso")
    public void identificacaoFoiAlteradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseDTOSchema.json"));
    }

    @Quando("requisitar a exclusão da identificação")
    public void requisitarExclusaoDaIdentificação() {
        response = given()
                .when()
                .delete(ENDPOINT_API + "/{id}", clienteResponseDTO.getCodigo().toString());
    }

    @Então("a identificação é excluida com sucesso")
    public void identificacaoExcluidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Quando("requisitar a busca da identificação")
    public void requisitarBuscaDaIdentificação() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_API + "/{id}", clienteResponseDTO.getCodigo());
    }

    @Então("a identificação é exibida com sucesso")
    public void identificacaoExibidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseDTOSchema.json"));
    }

    @Quando("requisitar a busca por uma identificação pelo cpf")
    public void requisitarBuscaPorUmaIdentificacaoPeloCpf() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .queryParam("cpf", clienteResponseDTO.getCpf())
                .get(ENDPOINT_API + "/cpf");
    }

    @Quando("requisitar a lista de identificacoes")
    public void requisitarListaDeIdentificacoes() {
        response = given()
                .when()
                .get(ENDPOINT_API + "/todos");
    }

    @Então("as identificações são exibidas com sucesso")
    public void identificacoesExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ListaClienteResponseDTOSchema.json"));
    }

    private ClienteRequestDTO geraClienteRequestDTO() {
        return ClienteRequestDTO.builder()
                .nome("Mario")
                .email(EmailUtil.gerarEmail())
                .cpf(CPFUtil.gerarCPFSoNumeros())
                .build();
    }
}

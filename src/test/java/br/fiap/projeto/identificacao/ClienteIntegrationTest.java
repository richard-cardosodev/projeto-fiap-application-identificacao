package br.fiap.projeto.identificacao;

import br.fiap.projeto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.util.CPFUtil;
import br.fiap.projeto.util.EmailUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest @AutoConfigureMockMvc
public class ClienteIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testeInserir() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/clientes")
                        .content(asJsonString(geraClienteRequestDTO()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void testeInserirCpfInvalido() throws Exception {

        ClienteRequestDTO dto = geraClienteRequestDTO();
        dto.setCpf("123");
        mvc.perform(MockMvcRequestBuilders
                        .post("/clientes")
                        .content(asJsonString(dto))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()));
    }

    @Test
    public void testeBuscaTodos() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/clientes/todos"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testeBuscaPorCpfInexistente() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/clientes/cpf")
                .queryParam("cpf", "00000000000")
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void testBuscaPorCodigoInexistente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/clientes/0000")
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testRemoverInexistente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/clientes/0000")
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    private ClienteRequestDTO geraClienteRequestDTO() {
        return ClienteRequestDTO.builder()
                .nome("Cliente1")
                .email(EmailUtil.gerarEmail())
                .cpf(CPFUtil.gerarCPFSoNumeros())
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

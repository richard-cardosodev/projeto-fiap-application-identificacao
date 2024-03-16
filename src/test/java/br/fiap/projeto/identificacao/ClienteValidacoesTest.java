package br.fiap.projeto.identificacao;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.identificacao.entity.vo.Email;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClienteValidacoesTest {

    @Test
    void testeCpfInvalido() {

        Assertions.assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", "123", "teste@teste.com", "11999998888"),
                Cpf.CPF_INVALIDO);
    }

    @Test
    void testeEmailInvalido() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", "01234567890", "teste", "11999998888"),
                Email.EMAIL_INVALIDO);
    }

    @Test
    void testeCodigoAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(null, "nome1", "01234567890", "teste@teste.com", "11999998888"),
                Cliente.CODIGO_AUSENTE);
    }

    @Test
    void testeNomeAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), null, "01234567890", "teste@teste.com", "11999998888"),
                Cliente.CPF_AUSENTE);
    }

    @Test
    void testeEmailAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", "01234567890", null, "11999998888"),
                Cliente.EMAIL_AUSENTE);
    }

    @Test
    void testeCpfAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", null, "teste@teste.com", "11999998888"),
                Cliente.CPF_AUSENTE);
    }
}

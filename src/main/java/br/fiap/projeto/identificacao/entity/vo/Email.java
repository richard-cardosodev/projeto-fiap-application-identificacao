package br.fiap.projeto.identificacao.entity.vo;

import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.regex.Pattern;

public class Email {

    public static final String EMAIL_INVALIDO = "E-mail inválido!";
    private final String endereco;

    public Email(String endereco) {
        this.endereco = endereco;
    }

    public void validar() throws EntradaInvalidaException {
        if (Pattern.matches("\\w{1,100}@\\w{1,100}\\.\\w{1,10}(\\.\\w{1,10})*\n", endereco)) {
            return;
        }
        throw new EntradaInvalidaException(EMAIL_INVALIDO);
    }

    public static Email fromString(String endereco) {
        return new Email(endereco);
    }

    public String getEndereco() {
        return endereco;
    }
}


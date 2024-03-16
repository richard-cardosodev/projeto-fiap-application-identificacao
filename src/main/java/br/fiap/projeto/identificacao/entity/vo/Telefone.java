package br.fiap.projeto.identificacao.entity.vo;

import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Telefone {
    private static final String TELEFONE_INVALIDO = "O telefone só pode conter números com mínimo de 10 e máximo de 11 digitos!";
    private String numero;

    public Telefone(String numero) throws EntradaInvalidaException {
        this.numero = numero.trim().toLowerCase();
        validar();
    }

    public static Telefone fromString(String numeroTelefone) throws EntradaInvalidaException {
        return new Telefone(numeroTelefone);
    }

    public void validar() throws EntradaInvalidaException {
        if(!Pattern.matches("\\d{10,11}", numero)) {
            throw new EntradaInvalidaException(TELEFONE_INVALIDO);
        }
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(numero, telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}

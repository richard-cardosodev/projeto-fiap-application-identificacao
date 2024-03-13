package br.fiap.projeto.identificacao.entity;

import br.fiap.projeto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.identificacao.entity.vo.Email;
import br.fiap.projeto.identificacao.entity.vo.Telefone;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.time.LocalDateTime;

public class Cliente {

    public static final String CPF_AUSENTE = "Informe o cpf!";
    public static final String EMAIL_AUSENTE = "Informe o e-mail!";
    public static final String NOME_AUSENTE = "Informe o nome!";
    public static final String ENTIDADE_NAO_ENCONTRADA = "Cliente não encontrado!";
    public static final String CPF_DUPLICADO = "Esse cpf já está cadastrado!";
    public static final String EMAIL_DUPLICADO = "Esse e-mail já está cadastrado!";
    public static final String CODIGO_AUSENTE = "Informe o código do cliente!";
    public static final String USUARIO_JA_EXCLUIDO = "Este cliente já está excluido! Não foi possível atualiza-lo.";
    public static final String TELEFONE_AUSENTE = "Informe o telefone!";
    private String nome;
    private Cpf cpf;
    private Email email;
    private Telefone telefone;
    private String codigo;
    private LocalDateTime dataExclusao;

    public Cliente(String codigo, String nome, String cpf, String email, String telefone) throws EntradaInvalidaException {
        this.codigo = codigo;
        this.nome = nome;
        validaCodigo();
        validaCpf(cpf);
        validaEmail(email);
        validaNome();
        this.cpf = Cpf.fromString(cpf);
        this.email = Email.fromString(email);
        this.telefone = Telefone.fromString(telefone);
    }

    public Cliente(String codigo, String nome, String cpf, String email, String telefone, LocalDateTime dataExclusao) throws EntradaInvalidaException {
        this.codigo = codigo;
        this.nome = nome;
        validaCodigo();
        validaCpf(cpf);
        validaEmail(email);
        validaNome();
        validaTelefone(telefone);
        this.cpf = Cpf.fromString(cpf);
        this.email = Email.fromString(email);
        this.telefone = Telefone.fromString(telefone);
        this.dataExclusao = dataExclusao;
    }

    public Cliente(String nome, String cpf, String email, String telefone) throws EntradaInvalidaException {
        this.nome = nome;
        validaCpf(cpf);
        validaEmail(email);
        validaNome();
        validaTelefone(telefone);
        this.cpf = Cpf.fromString(cpf);
        this.email = Email.fromString(email);
        this.telefone = Telefone.fromString(telefone);
    }

    public Cliente(String nome, String email, String telefone) throws EntradaInvalidaException {
        this.nome = nome;
        validaEmail(email);
        validaNome();
        validaTelefone(telefone);
        this.email = Email.fromString(email);
        this.telefone = Telefone.fromString(telefone);
    }

    public void adicionaDataDeExclusao() throws EntradaInvalidaException {
        if (this.dataExclusao != null) {
            throw new EntradaInvalidaException(USUARIO_JA_EXCLUIDO);
        }
        this.dataExclusao = LocalDateTime.now();
    }

    public Cliente anonimizarDadosParaExclusao() throws EntradaInvalidaException {
        nome = "anônimo";
        cpf = Cpf.fromString("00000000000");
        email = Email.fromString("anonimo@anonimo.anon");
        telefone = Telefone.fromString("99999999999");
        return this;
    }

    private void validaCodigo() throws EntradaInvalidaException {
        if (codigo == null) {
            throw new EntradaInvalidaException(CODIGO_AUSENTE);
        }
    }

    private void validaCpf(String cpf) throws EntradaInvalidaException {
        if (cpf == null) {
            throw new EntradaInvalidaException(CPF_AUSENTE);
        }
        Cpf.fromString(cpf).validar();
    }

    private void validaEmail(String email) throws EntradaInvalidaException {
        if (email == null) {
            throw new EntradaInvalidaException(EMAIL_AUSENTE);
        }
        Email.fromString(email).validar();
    }

    private void validaTelefone(String telefone) throws EntradaInvalidaException {
        if (telefone == null) {
            throw new EntradaInvalidaException(TELEFONE_AUSENTE);
        }
        Telefone.fromString(telefone);
    }

    private void validaNome() throws EntradaInvalidaException {
        if (nome == null || nome.length() == 0) {
            throw new EntradaInvalidaException(NOME_AUSENTE);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public LocalDateTime getDataExclusao() {
        return dataExclusao;
    }
}

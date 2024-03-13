package br.fiap.projeto.identificacao.external.repository.entity;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.identificacao.entity.vo.Email;
import br.fiap.projeto.identificacao.entity.vo.Telefone;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;


@Entity @Getter
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(name = "UN_CLIENTE", columnNames = {"codigo"}))
public class ClienteEntity {

    @Id
    private String codigo;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDateTime dataExclusao;

    public ClienteEntity() {
    }

    public ClienteEntity(String codigo, String nome, String cpf, String email, String telefone, LocalDateTime dataExclusao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataExclusao = dataExclusao;
    }

    public ClienteEntity(String codigo, String nome, Cpf cpf, Email email, Telefone telefone, LocalDateTime dataExclusao) {
        this(codigo, nome, cpf.getNumero(), email.getEndereco(), telefone.getNumero(), dataExclusao);
    }

    public ClienteEntity(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public static ClienteEntity fromCliente(Cliente cliente) {
        return new ClienteEntity(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(), cliente.getDataExclusao());
    }

    public Cliente toCliente() {
        try {
            return new Cliente(codigo, nome, cpf, email, telefone, dataExclusao);
        } catch (EntradaInvalidaException e) {
            throw new RuntimeException(e);
        }
    }

}

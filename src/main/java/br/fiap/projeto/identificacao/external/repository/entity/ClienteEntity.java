package br.fiap.projeto.identificacao.external.repository.entity;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.identificacao.entity.vo.Email;
import lombok.Getter;
import lombok.SneakyThrows;

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
    private LocalDateTime dataExclusao;

    public ClienteEntity() {
    }

    public ClienteEntity(String codigo, String nome, String cpf, String email, LocalDateTime dataExclusao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataExclusao = dataExclusao;
    }

    public ClienteEntity(String codigo, String nome, Cpf cpf, Email email, LocalDateTime dataExclusao) {
        this(codigo, nome, cpf.getNumero(), email.getEmail(), dataExclusao);
    }

    public static ClienteEntity fromCliente(Cliente cliente) {
        return new ClienteEntity(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataExclusao());
    }

    @SneakyThrows
    public Cliente toCliente() {
        return new Cliente(codigo, nome, cpf, email, dataExclusao);
    }

}

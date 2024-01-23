package br.fiap.projeto.identificacao.external.repository.mongo.collection;

import br.fiap.projeto.identificacao.entity.Cliente;
import br.fiap.projeto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.identificacao.entity.vo.Email;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "clientes")
public class ClienteDocument {
    @Id
    private String codigo;
    private String nome;
    private String cpf;
    private String email;
    private LocalDateTime dataExclusao;

    public ClienteDocument() {
    }

    public ClienteDocument(String codigo, String nome, String cpf, String email, LocalDateTime dataExclusao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataExclusao = dataExclusao;
    }

    public ClienteDocument(String codigo, String nome, Cpf cpf, Email email, LocalDateTime dataExclusao) {
        this(codigo, nome, cpf.getNumero(), email.getEndereco(), dataExclusao);
    }

    public static ClienteDocument fromCliente(Cliente cliente) {
        return new ClienteDocument(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataExclusao());
    }

    @SneakyThrows
    public Cliente toCliente() {
        return new Cliente(codigo, nome, cpf, email, dataExclusao);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataExclusao() {
        return dataExclusao;
    }
}

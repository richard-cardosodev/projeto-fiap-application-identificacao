package br.fiap.projeto.identificacao.adapter.controller.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcluiClienteRequestDTO {

    private String nome;
    private String email;
    private String telefone;

}

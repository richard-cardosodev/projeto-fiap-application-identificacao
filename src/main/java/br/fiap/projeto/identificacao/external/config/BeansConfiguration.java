package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.adapter.controller.ClienteRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.identificacao.usecase.GestaoClienteUseCase;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.IGestaoClienteUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public IGestaoClienteUsecase gestaoClienteUsecase(IClienteRepositoryAdapterGateway clienteRepository) {
        return new GestaoClienteUseCase(clienteRepository);
    }

    @Bean
    public IClienteRestAdapterController clienteRestAdapterController(IGestaoClienteUsecase gestaoClienteUsecase) {
        return new ClienteRestAdapterController(gestaoClienteUsecase);
    }
}

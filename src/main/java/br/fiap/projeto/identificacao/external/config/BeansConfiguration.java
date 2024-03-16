package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.adapter.controller.ClienteRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.LGPDRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.port.ILGPDRestAdapterController;
import br.fiap.projeto.identificacao.adapter.gateway.ClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.external.repository.postgres.SpringClienteRepository;
import br.fiap.projeto.identificacao.usecase.GestaoClienteUseCase;
import br.fiap.projeto.identificacao.usecase.LGPDExcluiDadosUseCase;
import br.fiap.projeto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.IGestaoClienteUsecase;
import br.fiap.projeto.identificacao.usecase.port.ILGPDExcluiDadosUseCase;
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

    @Bean
    public IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway(SpringClienteRepository springClienteRepository) {
        return new ClienteRepositoryAdapterGateway(springClienteRepository);
    }

    @Bean
    public ILGPDRestAdapterController lgpdRestAdapterController(ILGPDExcluiDadosUseCase lgpdExcluiDadosUseCase) {
        return new LGPDRestAdapterController(lgpdExcluiDadosUseCase);
    }

    @Bean
    public ILGPDExcluiDadosUseCase lgpdExcluiDadosUseCase(IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway) {
        return new LGPDExcluiDadosUseCase(clienteRepositoryAdapterGateway);
    }
}

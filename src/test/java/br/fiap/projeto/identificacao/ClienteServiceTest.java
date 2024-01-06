package br.fiap.projeto.identificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

// @SpringBootTest
// @TestPropertySource("classpath:application.properties")
// public class ClienteServiceTest {

// @Autowired
// private IClienteRestAdapterController clienteRestAdapterController;

// @Test
// public void testeBusca() {
// List<ClienteResponseDTO> clienteDTOS =
// clienteRestAdapterController.buscaTodos();
// assertFalse(CollectionUtils.isEmpty(clienteDTOS));
// }

// @Test
// public void testeBuscaPorCpf() throws EntidadeNaoEncontradaException,
// EntradaInvalidaException {
// String cpf = "09876543210";
// ClienteRequestDTO request = new ClienteRequestDTO("TesteBusca", cpf,
// "teste@busca.com");
// ClienteResponseDTO cliente;
// cliente = clienteRestAdapterController.insere(request);

// assertNotNull(cliente);
// assertNotNull(cliente.getCpf());
// assertEquals(cpf, cliente.getCpf());

// ClienteResponseDTO clienteBusca =
// clienteRestAdapterController.buscaPorCpf(cpf);
// assertNotNull(clienteBusca);
// assertNotNull(clienteBusca.getCpf());
// assertNotNull(clienteBusca.getCodigo());
// assertEquals(cpf, clienteBusca.getCpf());
// }

// @Test
// public void testeInsere() throws EntradaInvalidaException,
// EntidadeNaoEncontradaException {

// ClienteRequestDTO cliente = new ClienteRequestDTO("NomeTeste", "98765432109",
// "teste@teste.com");
// ClienteResponseDTO resultado = clienteRestAdapterController.insere(cliente);
// assertNotNull(resultado);

// List<ClienteResponseDTO> clienteDTOS =
// clienteRestAdapterController.buscaTodos();
// assertFalse(CollectionUtils.isEmpty(clienteDTOS));
// }

// @Test
// public void testeRemove() throws EntidadeNaoEncontradaException,
// EntradaInvalidaException {

// String codigoSaida;
// AtomicReference<ClienteRequestDTO> cliente;
// AtomicReference<ClienteResponseDTO> resultado;

// cliente = new AtomicReference<>();
// resultado = new AtomicReference<>();

// cliente.set(new ClienteRequestDTO("NomeTeste", "45678912301",
// "teste2@teste.com"));
// resultado.set(clienteRestAdapterController.insere(cliente.get()));
// codigoSaida = resultado.get().getCodigo();

// resultado.set(clienteRestAdapterController.busca(codigoSaida));

// assertNotNull(resultado);

// clienteRestAdapterController.remove(resultado.get().getCodigo());
// assertThrows(
// EntidadeNaoEncontradaException.class,
// () -> clienteRestAdapterController.busca(resultado.get().getCodigo()));
// assertThrows(
// EntidadeNaoEncontradaException.class,
// () -> clienteRestAdapterController.busca(codigoSaida));

// }
// }

package app.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uniamerica.gestor.app.entity.Cliente;
import com.uniamerica.gestor.app.repository.ClienteRepository;
import com.uniamerica.gestor.app.service.ClienteService;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository; // Criação do mock para ClienteRepository

    @InjectMocks
    private ClienteService clienteService; // Instância de ClienteService com o mock injetado

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks antes de cada teste
    }

    @Test
    public void testSalvarCliente() {
        // Mock para o cliente a ser salvo
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        
        // Define o comportamento do mock para o método save
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Chama o método a ser testado
        Cliente resultado = clienteService.salvarCliente(cliente);

        // Verifica se o resultado é o esperado
        assertEquals(cliente, resultado);
    }

    @Test
    public void testListarTodos() {
        // Mock para a lista de clientes
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");

        List<Cliente> listaClientes = Arrays.asList(cliente1, cliente2);

        // Define o comportamento do mock para o método findAll
        when(clienteRepository.findAll()).thenReturn(listaClientes);

        // Chama o método a ser testado
        List<Cliente> resultado = clienteService.listarTodos();

        // Verifica se o resultado é o esperado
        assertEquals(listaClientes, resultado);
    }

    @Test
    public void testBuscarPorId() {
        // Mock para um cliente específico
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        // Define o comportamento do mock para o método findById
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Chama o método a ser testado
        Optional<Cliente> resultado = clienteService.buscarPorId(1L);

        // Verifica se o resultado é o esperado
        assertEquals(Optional.of(cliente), resultado);
    }

    @Test
    public void testUpdateClienteExistente() {
        // Mock para o cliente a ser atualizado
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        // Define o comportamento do mock para os métodos existsById e save
        when(clienteRepository.existsById(1L)).thenReturn(true);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Chama o método a ser testado
        Cliente resultado = clienteService.update(1L, cliente);

        // Verifica se o resultado é o esperado
        assertEquals(cliente, resultado);
    }

    @Test
    public void testUpdateClienteNaoExistente() {
        // Define o comportamento do mock para o método existsById
        when(clienteRepository.existsById(1L)).thenReturn(false);

        // Verifica se o método lança a exceção esperada
        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.update(1L, new Cliente());
        });
    }

    @Test
    public void testDeletarCliente() {
        // Chama o método a ser testado
        clienteService.deletarCliente(1L);

        // Verifica se o método deleteById foi chamado com o argumento correto
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByNome() {
        // Mock para a lista de clientes com o nome "João"
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        List<Cliente> listaClientes = Arrays.asList(cliente);

        // Define o comportamento do mock para o método findByNomeContainingIgnoreCase
        when(clienteRepository.findByNomeContainingIgnoreCase("João")).thenReturn(listaClientes);

        // Chama o método a ser testado
        List<Cliente> resultado = clienteService.findByNome("João");

        // Verifica se o resultado é o esperado
        assertEquals(listaClientes, resultado);
    }

    @Test
    public void testFindByIdadeGreaterThanEqual() {
        // Mock para a lista de clientes com idade maior ou igual a 30
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setIdade(30);

        List<Cliente> listaClientes = Arrays.asList(cliente);

        // Define o comportamento do mock para o método findByIdadeGreaterThanEqual
        when(clienteRepository.findByIdadeGreaterThanEqual(30)).thenReturn(listaClientes);

        // Chama o método a ser testado
        List<Cliente> resultado = clienteService.findByIdadeGreaterThanEqual(30);

        // Verifica se o resultado é o esperado
        assertEquals(listaClientes, resultado);
    }

    @Test
    public void testFindByCpfLike() {
        // Mock para a lista de clientes com CPF que contém "123"
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf("456456789");

        List<Cliente> listaClientes = Arrays.asList(cliente);

        // Define o comportamento do mock para o método findByCpfLike
        when(clienteRepository.findByCpfLike("123")).thenReturn(listaClientes);

        // Chama o método a ser testado
        List<Cliente> resultado = clienteService.findByCpfLike("123");

        // Verifica se o resultado é o esperado
        assertEquals(listaClientes, resultado);
    }
}
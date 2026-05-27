import com.transferencia.bancaria.exception.ContaInativaException;
import com.transferencia.bancaria.exception.SaldoInsuficienteException;
import com.transferencia.bancaria.model.Cliente;
import com.transferencia.bancaria.model.Conta;
import com.transferencia.bancaria.model.StatusTransacao;
import com.transferencia.bancaria.model.Transacao;
import com.transferencia.bancaria.repository.ContaRepository;
import com.transferencia.bancaria.repository.TransacaoRepository;
import com.transferencia.bancaria.service.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferenciaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransferenciaService service;

    private Conta contaOrigem;
    private Conta contaDestino;

    @BeforeEach
    void setup() {
        Cliente pedro = new Cliente("111", "Pedro", "Rua 1", LocalDate.of(2003, 7, 25));
        Cliente claudio = new Cliente("222", "Cláudio", "Rua 2", LocalDate.of(1985, 4, 16));

        contaOrigem = new Conta("001", 5000.00, "Corrente", "Ativa", pedro);
        contaDestino = new Conta("002", 1000.00, "Corrente", "Ativa", claudio);
    }

    @Test
    void deveRealizarTransferenciaComSucesso() {
        when(contaRepository.findById("001")).thenReturn(Optional.of(contaOrigem));
        when(contaRepository.findById("002")).thenReturn(Optional.of(contaDestino));
        when(transacaoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Transacao transacao = service.realizarTransferencia("001", "002", 1000.00);

        assertEquals(StatusTransacao.APROVADA, transacao.getStatus());
        assertEquals(4000.00, contaOrigem.getSaldo());
        assertEquals(2000.00, contaDestino.getSaldo());
    }

    @Test
    void deveLancarExcecaoQuandoContaInativa() {
        contaOrigem.setStatus("Inativa");
        when(contaRepository.findById("001")).thenReturn(Optional.of(contaOrigem));
        when(contaRepository.findById("002")).thenReturn(Optional.of(contaDestino));

        assertThrows(ContaInativaException.class, () ->
                service.realizarTransferencia("001", "002", 1000.00));
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        when(contaRepository.findById("001")).thenReturn(Optional.of(contaOrigem));
        when(contaRepository.findById("002")).thenReturn(Optional.of(contaDestino));

        assertThrows(SaldoInsuficienteException.class, () ->
                service.realizarTransferencia("001", "002", 99999.00));
    }

    @Test
    void deveLancarExcecaoQuandoValorNegativo() {
        when(contaRepository.findById("001")).thenReturn(Optional.of(contaOrigem));
        when(contaRepository.findById("002")).thenReturn(Optional.of(contaDestino));

        assertThrows(IllegalArgumentException.class, () ->
                service.realizarTransferencia("001", "002", -100.00));
    }
}
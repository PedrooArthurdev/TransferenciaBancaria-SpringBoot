package com.transferencia.bancaria.service;

import com.transferencia.bancaria.exception.ContaInativaException;
import com.transferencia.bancaria.exception.SaldoInsuficienteException;
import com.transferencia.bancaria.model.Conta;
import com.transferencia.bancaria.model.StatusTransacao;
import com.transferencia.bancaria.model.Transacao;
import com.transferencia.bancaria.repository.ContaRepository;
import com.transferencia.bancaria.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferenciaService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public TransferenciaService(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao realizarTransferencia(String numeroOrigem, String numeroDestino, double valor) {
        Conta origem = contaRepository.findById(numeroOrigem)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada"));

        Conta destino = contaRepository.findById(numeroDestino)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada"));

        if (!origem.getStatus().equals("Ativa")) {
            throw new ContaInativaException("Conta de origem está inativa");
        }

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }

        if (origem.getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        contaRepository.save(origem);
        contaRepository.save(destino);

        Transacao transacao = new Transacao(origem, destino, valor, LocalDateTime.now(), StatusTransacao.APROVADA);
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> getHistorico() {
        return transacaoRepository.findAll();
    }
}
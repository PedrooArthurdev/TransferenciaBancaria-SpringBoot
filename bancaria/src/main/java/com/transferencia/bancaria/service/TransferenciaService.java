package com.transferencia.bancaria.service;

import com.transferencia.bancaria.exception.ContaInativaException;
import com.transferencia.bancaria.exception.SaldoInsuficienteException;
import com.transferencia.bancaria.model.Conta;
import com.transferencia.bancaria.model.StatusTransacao;
import com.transferencia.bancaria.model.Transacao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferenciaService {
    private List<Transacao> historico = new ArrayList<>();

    public Transacao realizarTransferencia(Conta origem, Conta destino, double valor) {
        if (!origem.getStatus().equals("Ativa")) {
            throw new ContaInativaException("Conta de origem está inativa");
        }

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }

        if (origem.getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        origem.setSaldo((origem.getSaldo() - valor));
        destino.setSaldo((destino.getSaldo() + valor));

        Transacao transacao = new Transacao(origem, destino, valor, LocalDateTime.now(), StatusTransacao.APROVADA);
        historico.add(transacao);
        return transacao;

        }

    public List<Transacao> getHistorico() {
        return historico;
    }
}




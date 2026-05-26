package com.transferencia.bancaria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "conta_origem")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino")
    private Conta contaDestino;

    private double valor;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    public Transacao(Conta contaOrigem, Conta contaDestino, double valor, LocalDateTime dataHora, StatusTransacao status) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Transacao() {
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusTransacao getStatus() {
        return status;
    }
}

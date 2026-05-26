package com.transferencia.bancaria.model;

import java.time.LocalDateTime;


public class Transacao {
    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;
    private LocalDateTime dataHora;
    private StatusTransacao status;

    public Transacao(Conta contaOrigem, Conta contaDestino, double valor, LocalDateTime dataHora, StatusTransacao status) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataHora = dataHora;
        this.status = status;
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

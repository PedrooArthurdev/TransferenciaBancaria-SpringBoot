package com.transferencia.bancaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TransferenciaRequest {

    @NotBlank(message = "Conta de origem é obrigatória")
    private String contaOrigem;

    @NotBlank(message = "Conta de destino é obrigatória")
    private String contaDestino;

    @Positive(message = "Valor deve ser positivo")
    private double valor;

    public TransferenciaRequest(String contaOrigem, String contaDestino, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
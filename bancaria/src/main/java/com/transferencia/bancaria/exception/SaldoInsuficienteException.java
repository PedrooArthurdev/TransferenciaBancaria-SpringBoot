package com.transferencia.bancaria.exception;

public class SaldoInsuficienteException extends RuntimeException{
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}

package com.transferencia.bancaria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contas")
public class Conta {
    @Id
    private String numeroConta;

    private double saldo;
    private String tipoConta;
    private String status;

    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    private Cliente cliente;

    public Conta(String numeroConta, double saldo, String tipoConta, String status, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.status = status;
        this.cliente = cliente;
    }

    public Conta() {
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
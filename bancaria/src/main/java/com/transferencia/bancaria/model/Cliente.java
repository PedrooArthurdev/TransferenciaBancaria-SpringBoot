package com.transferencia.bancaria.model;

import java.time.LocalDate;

public class Cliente {
    private String cpf;
    private String nome;
    private String endereco;
    private LocalDate dataNascimento;

    public Cliente (String cpf, String nome, String endereco, LocalDate dataNascimento){
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public Cliente() {

    }

    public String getCpf() {
        return cpf;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

package com.transferencia.bancaria.controller;

import com.transferencia.bancaria.dto.TransferenciaRequest;
import com.transferencia.bancaria.exception.ContaInativaException;
import com.transferencia.bancaria.exception.SaldoInsuficienteException;
import com.transferencia.bancaria.model.Cliente;
import com.transferencia.bancaria.model.Conta;
import com.transferencia.bancaria.model.Transacao;
import com.transferencia.bancaria.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @GetMapping("/status")
    public String status() {
        return "API de transferências funcionando!";
    }

    @PostMapping
    public String realizarTransferencia(@RequestBody TransferenciaRequest request) {
        Cliente cliente1 = new Cliente("02085375111", "Pedro", "Rua 26 norte", LocalDate.of(2003, 7, 25));
        Cliente cliente2 = new Cliente("05794865233", "Cláudio", "Avenida das palmeiras", LocalDate.of(1985, 4, 16));

        Conta origem = new Conta(request.getContaOrigem(), 5000.00, "Corrente", "Ativa", cliente1);
        Conta destino = new Conta(request.getContaDestino(), 1000.00, "Corrente", "Ativa", cliente2);

        Transacao transacao = service.realizarTransferencia(origem, destino, request.getValor());

        return "Transferência realizada! Status: " + transacao.getStatus() + " | Valor: R$ " + transacao.getValor();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ContaInativaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleContaInativa(ContaInativaException e) {
        return e.getMessage();
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSaldoInsuficiente(SaldoInsuficienteException e) {
        return e.getMessage();
    }

    @GetMapping("/historico")
    public List<Transacao> historico() {
        return service.getHistorico();
    }
}

package com.transferencia.bancaria.controller;

import com.transferencia.bancaria.dto.TransferenciaRequest;
import com.transferencia.bancaria.model.Cliente;
import com.transferencia.bancaria.model.Conta;
import com.transferencia.bancaria.model.Transacao;
import com.transferencia.bancaria.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @GetMapping("/status")
    public String status() {
        return "API de transferências funcionando!";
    }

    @PostMapping
    public Transacao realizarTransferencia(@Valid @RequestBody TransferenciaRequest request) {
        return service.realizarTransferencia(
                request.getContaOrigem(),
                request.getContaDestino(),
                request.getValor()
        );
    }

    @GetMapping("/historico")
    public List<Transacao> historico() {
        return service.getHistorico();
    }
}
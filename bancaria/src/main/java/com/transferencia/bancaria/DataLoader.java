package com.transferencia.bancaria;

import com.transferencia.bancaria.model.Cliente;
import com.transferencia.bancaria.model.Conta;
import com.transferencia.bancaria.repository.ClienteRepository;
import com.transferencia.bancaria.repository.ContaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public DataLoader(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    @Override
    public void run(String... args) {
        Cliente pedro = new Cliente("02085375111", "Pedro", "Rua 26 norte", LocalDate.of(2003, 7, 25));
        Cliente claudio = new Cliente("05794865233", "Cláudio", "Avenida das palmeiras", LocalDate.of(1985, 4, 16));

        clienteRepository.save(pedro);
        clienteRepository.save(claudio);

        contaRepository.save(new Conta("4102-8", 5000.00, "Corrente", "Ativa", pedro));
        contaRepository.save(new Conta("9487-8", 10000.00, "Corrente", "Ativa", claudio));
    }
}
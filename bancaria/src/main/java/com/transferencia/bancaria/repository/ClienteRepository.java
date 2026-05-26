package com.transferencia.bancaria.repository;

import com.transferencia.bancaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
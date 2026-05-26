package com.transferencia.bancaria.repository;

import com.transferencia.bancaria.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
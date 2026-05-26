package com.transferencia.bancaria.repository;

import com.transferencia.bancaria.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {
}
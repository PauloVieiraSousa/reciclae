package com.reciclae.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reciclae.enums.TipoTransacao;
import com.reciclae.model.Token;


public interface TokenRepository extends JpaRepository<Token, Long> {
	
	@Query(value = "SELECT COALESCE(sum(t.quantidade), 0) FROM token t WHERE t.investidor_id = :id AND t.tipo_transacao = :tipoTransacao", nativeQuery = true)
	public BigDecimal tokenByInvestidorId(@Param("id") Long id, @Param("tipoTransacao") String tipoTransacao);

}

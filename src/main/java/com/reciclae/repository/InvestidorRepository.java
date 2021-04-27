package com.reciclae.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reciclae.model.Investidor;

public interface InvestidorRepository extends JpaRepository<Investidor, Long> {

}

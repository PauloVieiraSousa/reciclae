package com.reciclae.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.reciclae.enums.TipoTransacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="resgate")
public class Resgate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private BigDecimal quantidade;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipoTransacao;

	@Column
	private Instant dataTransacao = Instant.now();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "colaboradorId", nullable = false)
	private Colaborador colaborador;
	
	public Resgate(BigDecimal quantidade, TipoTransacao tipoTransacao, Colaborador colaborador) {
		this.quantidade = quantidade;
		this.tipoTransacao = tipoTransacao;
		this.colaborador = colaborador;
	}
}

package com.reciclae.controller.output.dto;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenOutputDto {

	private Long id;
	
	private BigDecimal quantidade;

	private String dataEmissao;
}

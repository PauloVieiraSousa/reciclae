package com.reciclae.controller.input.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenInputDto {

	
	@NotEmpty(message = "Campo name obrigatório")
	private String name;

	@NotNull(message = "Campo name obrigatório")
	private BigDecimal quantidade;

}

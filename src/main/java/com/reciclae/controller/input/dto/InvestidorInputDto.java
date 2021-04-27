package com.reciclae.controller.input.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestidorInputDto {
	
	@NotEmpty(message = "Campo name obrigatório")
	private String name;

}

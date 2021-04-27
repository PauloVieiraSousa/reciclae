package com.reciclae.controller.input.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResgateInputDto {

	@NotNull(message = "Campo investidorId obrigatório")
	private Long investidorId;
	
	@NotNull(message = "Campo colaboradorId obrigatório")
	private Long colaboradorId;
	
	
	@NotNull(message = "Campo name obrigatório")
	private BigDecimal quantidade;
	
	

}

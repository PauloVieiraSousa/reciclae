package com.reciclae.controller;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.reciclae.controller.input.dto.ResgateInputDto;
import com.reciclae.controller.input.dto.TokenInputDto;
import com.reciclae.controller.output.dto.ResponseOutput;
import com.reciclae.controller.output.dto.TokenOutputDto;
import com.reciclae.enums.TipoTransacao;
import com.reciclae.model.Colaborador;
import com.reciclae.model.Investidor;
import com.reciclae.model.Resgate;
import com.reciclae.model.Token;
import com.reciclae.repository.ColaboradorRepository;
import com.reciclae.repository.InvestidorRepository;
import com.reciclae.repository.ResgateRepository;
import com.reciclae.repository.TokenRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;


@Api(value = "Token")
@AllArgsConstructor
@RequestMapping("api")
@RestController
public class TokenController {
	
	TokenRepository tokenRepository;
	InvestidorRepository investidorRepository;
	ResgateRepository resgateRepository;
	ColaboradorRepository colaboradorRepository;


	@ApiOperation(value = "Emissão de token")
	@PostMapping(path = "/token", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity emissaoToken(@Valid @RequestBody TokenInputDto tokenDto, UriComponentsBuilder uriBuilder){
		
		Investidor investidor = new Investidor(tokenDto.getName());
		Token token = new Token(tokenDto.getQuantidade(), investidor, TipoTransacao.CREDITO);
		
		Investidor investidorSave = investidorRepository.save(investidor);
		Token save = tokenRepository.save(new Token(token.getQuantidade(), investidor, TipoTransacao.CREDITO));
		URI path = uriBuilder.path("/token/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(path).body(new TokenOutputDto(save.getId(), save.getQuantidade(), save.getDataEmissao().toString()));
	}
	
	@ApiOperation(value = "Resgate de token")
	@PostMapping(path = "/token/resgate", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity resgatarToken(@Valid @RequestBody ResgateInputDto resgateDto, UriComponentsBuilder uriBuilder){
		
		Investidor investidor = investidorRepository.getOne(resgateDto.getInvestidorId());
		Colaborador colaborador = colaboradorRepository.getOne(resgateDto.getColaboradorId());
		
		BigDecimal tokensCredito = tokenRepository.tokenByInvestidorId(investidor.getId(), TipoTransacao.CREDITO.toString());
		BigDecimal TokensDebito = tokenRepository.tokenByInvestidorId(investidor.getId(), TipoTransacao.DEBITO.toString());
		
		BigDecimal totalTransacao = TokensDebito.add(resgateDto.getQuantidade());
		
		if (tokensCredito.compareTo(totalTransacao) < 0 ) {
			return  ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseOutput("Operação não permitida", 1001));
		}
		
		Token save = tokenRepository.save(new Token(resgateDto.getQuantidade(), investidor, TipoTransacao.DEBITO));
		Resgate resgateSave = resgateRepository.save(new Resgate(resgateDto.getQuantidade(), TipoTransacao.CREDITO, colaborador));

		URI path = uriBuilder.path("/token/resgate/{id}").buildAndExpand(resgateSave.getId()).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseOutput(String.format("Resgate efetuado com sucesso"), 2000));
	}
	
	
	
	
	
	
}

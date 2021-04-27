package com.reciclae.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.reciclae.controller.input.dto.ColaboradorInputDto;
import com.reciclae.controller.output.dto.ColaboradorOutputDto;
import com.reciclae.model.Colaborador;
import com.reciclae.repository.ColaboradorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;


@Api(value = "Colaborador")
@AllArgsConstructor
@RequestMapping("api")
@RestController
public class ColaboradorController {
	
	ColaboradorRepository colaboradorRepository;
	
	@ApiOperation(value = "Adiciona novo Colaborador")
	@PostMapping(path = "/colaborador", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addColaborador(@Valid @RequestBody ColaboradorInputDto colaboradorDto, UriComponentsBuilder uriBuilder){
		Colaborador colaborador = new Colaborador(colaboradorDto.getName());
		Colaborador save = colaboradorRepository.save(colaborador);
		URI path = uriBuilder.path("/colaborador/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(path).body(new ColaboradorOutputDto(save.getId(), save.getName()));
	}
}

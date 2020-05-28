package com.gabrielferreira.projeto.controller;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.gabrielferreira.projeto.entidade.Nacionalidade;
import com.gabrielferreira.projeto.entidade.dto.NacionalidadeDTO;
import com.gabrielferreira.projeto.entidade.dto.NacionalidadeInserirDTO;
import com.gabrielferreira.projeto.service.NacionalidadeService;

@RestController
@RequestMapping("/nacionalidades")
public class NacionalidadeController {
	
	@Autowired
	private NacionalidadeService nacionalidadeService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<Nacionalidade> listar(){
		return nacionalidadeService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nacionalidade> consultarPorId(@PathVariable String id) {
		Nacionalidade nacionalidade = nacionalidadeService.consultarPorId(id);
		return ResponseEntity.ok().body(nacionalidade);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public NacionalidadeDTO inserir(@Valid @RequestBody NacionalidadeInserirDTO nacionalidadeInserirDTO) {
		Nacionalidade nacionalidade = toEntityInserir(nacionalidadeInserirDTO);
		return toModel(nacionalidadeService.inserir(nacionalidade));
	}
	
	private Nacionalidade toEntityInserir(NacionalidadeInserirDTO dto) {
		return modelMapper.map(dto,Nacionalidade.class);
	}
	
	public NacionalidadeDTO toModel(Nacionalidade nacionalidade) {
		return modelMapper.map(nacionalidade,NacionalidadeDTO.class);
	}
}

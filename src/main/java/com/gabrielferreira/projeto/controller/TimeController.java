package com.gabrielferreira.projeto.controller;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.entidade.Time;
import com.gabrielferreira.projeto.entidade.dto.TimeAtualizarDTO;
import com.gabrielferreira.projeto.entidade.dto.TimeDTO;
import com.gabrielferreira.projeto.entidade.dto.TimeInserirDTO;
import com.gabrielferreira.projeto.service.TimeService;
import com.gabrielferreira.projeto.utils.URL;

@RestController
@RequestMapping("/times")
public class TimeController {
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<TimeDTO> listar(){
		return toCollectionModel(timeService.consultarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TimeDTO> consultarPorId(@PathVariable String id) {
		Time time = timeService.consultarPorId(id);
		TimeDTO timeDTO = toModel(time);
		return ResponseEntity.ok().body(timeDTO);
	}
	
	@PostMapping(value="/nome")
 	public ResponseEntity<List<Time>> procurarNome(@RequestParam(value="nome", defaultValue="") String nome) {
		nome = URL.decodeParam(nome);
		List<Time> list = timeService.procurarNome(nome);
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id){
		timeService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public TimeDTO inserir(@Valid @RequestBody TimeInserirDTO timesInserirDTO) {
		Time time = toEntityInserir(timesInserirDTO);
		return toModel(timeService.inserir(time));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public TimeAtualizarDTO atualizar(@Valid @RequestBody TimeAtualizarDTO timeAtualizarDTO,@PathVariable String id) {
		Time time = toEntityAtualizar(timeAtualizarDTO);
		return toModelAtualizar(timeService.atualizar(id, time));
	}
	
	private Time toEntityInserir(TimeInserirDTO dto) {
		return modelMapper.map(dto,Time.class);
	}
	
	private Time toEntityAtualizar(TimeAtualizarDTO dto) {
		return modelMapper.map(dto,Time.class);
	}
	
	private TimeAtualizarDTO toModelAtualizar(Time time) {
		return modelMapper.map(time,TimeAtualizarDTO.class);
	}
		
	public TimeDTO toModel(Time time) {
		return modelMapper.map(time,TimeDTO.class);
	}
	
	private List<TimeDTO> toCollectionModel(List<Time> times) {
		return times.stream()
				.map(time -> toModel(time))
				.collect(Collectors.toList());
	}
}

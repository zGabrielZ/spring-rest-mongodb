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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.entidade.Jogador;
import com.gabrielferreira.projeto.entidade.Time;
import com.gabrielferreira.projeto.entidade.dto.JogadorAtualizarDTO;
import com.gabrielferreira.projeto.entidade.dto.JogadorDTO;
import com.gabrielferreira.projeto.entidade.dto.JogadorInserirDTO;
import com.gabrielferreira.projeto.service.JogadorService;
import com.gabrielferreira.projeto.service.TimeService;
import com.gabrielferreira.projeto.utils.URL;

@RestController
public class JogadorController {
	
	@Autowired
	private JogadorService jogadorService;
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/times/{timeId}/jogadores")
	public List<JogadorDTO> listar(@PathVariable String timeId) {
		
		Time time = timeService.consultarPorId(timeId);
		
		return toCollectionModel(time.getJogadores());
	}
	
	@GetMapping("/jogadores/{id}")
	public ResponseEntity<JogadorDTO> consultarPorId(@PathVariable String id) {
		Jogador jogador = jogadorService.consultarPorId(id);
		jogador.setTime(timeService.consultarPorId(jogador.getTime().getId()));
		JogadorDTO jogadorDTO = toModel(jogador);
		return ResponseEntity.ok().body(jogadorDTO);
	}
	
	@DeleteMapping("/jogadores/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id){
		jogadorService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/times/{timeId}/jogadores")
	@ResponseStatus(value = HttpStatus.CREATED)
	public JogadorDTO inserir(@PathVariable String timeId,@Valid @RequestBody JogadorInserirDTO jogadorDTO) {
		Jogador jogador = toEntityInserir(jogadorDTO);
		return toModel(jogadorService.inserir(timeId, jogador));
	}
	
	@PutMapping("/jogadores/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public JogadorDTO atualizar(@Valid @RequestBody JogadorAtualizarDTO jogadorDTO,
			@PathVariable String id) {
		Jogador jogador = toEntityAtualizar(jogadorDTO);
		return toModel(jogadorService.atualizar(id, jogador));
	}
	
	@GetMapping("/jogadores/nomes")
 	public ResponseEntity<List<Jogador>> procurarNome(@RequestParam(value="nome", defaultValue="") String nome) {
		nome = URL.decodeParam(nome);
		List<Jogador> list = jogadorService.procurarNome(nome);
		return ResponseEntity.ok().body(list);
	}
	
	
	private Jogador toEntityInserir(JogadorInserirDTO dto) {
		return modelMapper.map(dto,Jogador.class);
	}
	
	private Jogador toEntityAtualizar(JogadorAtualizarDTO dto) {
		return modelMapper.map(dto,Jogador.class);
	}
		
	public JogadorDTO toModel(Jogador jogador) {
		return modelMapper.map(jogador,JogadorDTO.class);
	}
	
	private List<JogadorDTO> toCollectionModel(List<Jogador> jogadores) {
		return jogadores.stream()
				.map(jogador -> toModel(jogador))
				.collect(Collectors.toList());
	}
}

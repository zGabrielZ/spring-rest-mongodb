package com.gabrielferreira.projeto.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabrielferreira.projeto.entidade.Nacionalidade;
import com.gabrielferreira.projeto.service.NacionalidadeService;

@RestController
@RequestMapping("/nacionalidades")
public class NacionalidadeController {
	
	@Autowired
	private NacionalidadeService nacionalidadeService;

	@GetMapping
	public List<Nacionalidade> listar(){
		return nacionalidadeService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nacionalidade> consultarPorId(@PathVariable String id) {
		Nacionalidade nacionalidade = nacionalidadeService.consultarPorId(id);
		return ResponseEntity.ok().body(nacionalidade);
	}
}

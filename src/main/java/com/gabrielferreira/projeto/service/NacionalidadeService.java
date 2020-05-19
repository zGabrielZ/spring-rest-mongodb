package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.entidade.Nacionalidade;
import com.gabrielferreira.projeto.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.repositorio.NacionalidadeRepositorio;

@Service
public class NacionalidadeService {
	
	@Autowired
	private NacionalidadeRepositorio nacionalidadeRepositorio;

	public List<Nacionalidade> consultarTodos(){
		return nacionalidadeRepositorio.findAll();
	}
	
	public Nacionalidade consultarPorId(String id) {
		Optional<Nacionalidade> nacionalidade = nacionalidadeRepositorio.findById(id);
		return nacionalidade.orElseThrow(() -> new EntidadeNotFoundException("Nacionalidade não encontrado"));
	}

}

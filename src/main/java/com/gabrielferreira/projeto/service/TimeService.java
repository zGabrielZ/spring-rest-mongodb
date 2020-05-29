package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.entidade.Nacionalidade;
import com.gabrielferreira.projeto.entidade.Time;
import com.gabrielferreira.projeto.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.repositorio.NacionalidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.TimeRepositorio;


@Service
public class TimeService {
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	@Autowired
	private NacionalidadeRepositorio nacionalidadeRepositorio;

	public List<Time> consultarTodos(){
		return timeRepositorio.findAll();
	}
	
	public Time consultarPorId(String id) {
		Optional<Time> time = timeRepositorio.findById(id);
		return time.orElseThrow(() -> new EntidadeNotFoundException("Time não encontrado"));
	}
	
	public void deletar(String id) {
		Optional<Time> time = timeRepositorio.findById(id);
		if(!time.isPresent()) {
			throw new EntidadeNotFoundException("Time não encontrado");
		}
		timeRepositorio.deleteById(id);
	}
	
	public Time inserir(Time time) {		

		Optional<Nacionalidade> nacionalidade = nacionalidadeRepositorio.findById(time.getNacionalidade().getId());
		if(!nacionalidade.isPresent()) {
			throw new EntidadeNotFoundException("Nacionalidade não encontrado");
		}
		time.setNacionalidade(nacionalidade.get());
		return timeRepositorio.save(time);
	}
	
	public Time atualizar(String id,Time time) {			
		Optional<Time> entidade = timeRepositorio.findById(id);
		if(!entidade.isPresent()) {
			throw new EntidadeNotFoundException("Time não encontrado");
		}
		updateData(entidade.get(),time);
		return timeRepositorio.save(entidade.get());
	}
	
	
	private void updateData(Time entidade,Time time) {
		entidade.setNome(time.getNome());
		entidade.setDataFundacao(time.getDataFundacao());
	}
	
	public List<Time> procurarNome(String nome) {
		return timeRepositorio.procurarNome(nome);
	}

}

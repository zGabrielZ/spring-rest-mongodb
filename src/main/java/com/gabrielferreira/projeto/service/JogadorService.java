package com.gabrielferreira.projeto.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.entidade.Jogador;
import com.gabrielferreira.projeto.entidade.Nacionalidade;
import com.gabrielferreira.projeto.entidade.Time;
import com.gabrielferreira.projeto.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.repositorio.JogadorRepositorio;
import com.gabrielferreira.projeto.repositorio.NacionalidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.TimeRepositorio;

@Service
public class JogadorService {
	
	@Autowired
	private JogadorRepositorio jogadorRepositorio;
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	@Autowired
	private NacionalidadeRepositorio nacionalidadeRepositorio;
	

	public List<Jogador> consultarTodos(String idTime){
		return jogadorRepositorio.findAll();
	}
	
	public Jogador consultarPorId(String id) {
		Optional<Jogador> jogador = jogadorRepositorio.findById(id);
		return jogador.orElseThrow(() -> new EntidadeNotFoundException("Jogador não encontrado"));
	}
	
	public void deletar(String id) {
		Optional<Jogador> jogador = jogadorRepositorio.findById(id);
		if(!jogador.isPresent()) {
			throw new EntidadeNotFoundException("Jogador não encontrado");
		}
		jogadorRepositorio.deleteById(id);
	}
	
	public Jogador inserir(String timeId,Jogador jogador) {
		
		Optional<Time> time = timeRepositorio.findById(timeId);
		
		if(!time.isPresent()) {
			throw new EntidadeNotFoundException("Time não encontrado");
		}
		
		Optional<Nacionalidade> nacionalidade = nacionalidadeRepositorio.findById(jogador.getNacionalidade().getId());
		
		if(!nacionalidade.isPresent()) {
			throw new EntidadeNotFoundException("Nacionalidade não encontrado");
		}
		
		jogador.setTime(time.get());
		jogador.setNacionalidade(nacionalidade.get());
		
		jogadorRepositorio.save(jogador);
		
		time.get().getJogadores().add(jogador);
		
		timeRepositorio.save(time.get());
		
		return jogadorRepositorio.save(jogador);
	}
	
	public Jogador atualizar(String id,Jogador jogador) {
		
		Optional<Jogador> entidade = jogadorRepositorio.findById(id);
		
		if(!entidade.isPresent()) {
			throw new EntidadeNotFoundException("Jogador não encontrado");
		}
	
		updateData(entidade.get(),jogador);
		
		return jogadorRepositorio.save(entidade.get());
	}
	
	private void updateData(Jogador entidade,Jogador jogador) {
		entidade.setNome(jogador.getNome());
		entidade.setTime(jogador.getTime());
		entidade.setNumeroDaCamisa(jogador.getNumeroDaCamisa());
	}
	
	public List<Jogador> procurarNome(String nome) {
		return jogadorRepositorio.procurarNome(nome);
	}

}

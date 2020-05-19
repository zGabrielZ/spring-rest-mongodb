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
import com.gabrielferreira.projeto.repositorio.TimeRepositorio;

@Service
public class JogadorService {
	
	@Autowired
	private JogadorRepositorio jogadorRepositorio;
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	@Autowired
	private NacionalidadeService nacionalidadeService;

	public List<Jogador> consultarTodos(String idTime){
		return jogadorRepositorio.findAll();
	}
	
	public Jogador consultarPorId(String id) {
		Optional<Jogador> jogador = jogadorRepositorio.findById(id);
		return jogador.orElseThrow(() -> new EntidadeNotFoundException("Jogador não encontrado"));
	}
	
	public void deletar(String id) {
		consultarPorId(id);
		jogadorRepositorio.deleteById(id);
	}
	
	public Jogador inserir(String timeId,Jogador jogador) {
		
		
		Time time = timeService.consultarPorId(timeId);
		
		Nacionalidade nacionalidade = nacionalidadeService.consultarPorId(jogador.getNacionalidade().getId());

		jogador.setTime(time);
		jogador.setNacionalidade(nacionalidade);
		
		jogadorRepositorio.save(jogador);
		
		time.getJogadores().add(jogador);
		
		timeRepositorio.save(time);
		
		return jogadorRepositorio.save(jogador);
	}
	
	public Jogador atualizar(String timeId,String id,Jogador jogador) {
		Jogador entidade = consultarPorId(id);
		
		Time time = timeService.consultarPorId(timeId);
		
		updateData(entidade,jogador);

		entidade.setTime(time);
		
		timeRepositorio.save(time);
		
		return jogadorRepositorio.save(entidade);
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

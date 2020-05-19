package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.entidade.Jogador;

@Repository
public interface JogadorRepositorio extends MongoRepository<Jogador,String>{

	@Query("{ 'nome': { $regex: ?0, $options: 'i' } }")
	List<Jogador> procurarNome(String nome);
}

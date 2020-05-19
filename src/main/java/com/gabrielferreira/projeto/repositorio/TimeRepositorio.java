package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.entidade.Time;

@Repository
public interface TimeRepositorio extends MongoRepository<Time,String>{

	@Query("{ 'nome': { $regex: ?0, $options: 'i' } }")
	List<Time> procurarNome(String nome);
}

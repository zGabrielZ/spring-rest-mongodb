package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.entidade.Nacionalidade;

@Repository
public interface NacionalidadeRepositorio extends MongoRepository<Nacionalidade,String>{

	boolean existsByNome(String nome);
}

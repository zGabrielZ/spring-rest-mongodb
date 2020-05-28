package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NacionalidadeInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nome;
}

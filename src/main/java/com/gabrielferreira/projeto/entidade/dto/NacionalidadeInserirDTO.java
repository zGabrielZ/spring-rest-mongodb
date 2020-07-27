package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NacionalidadeInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nome;
}

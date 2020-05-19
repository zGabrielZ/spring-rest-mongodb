package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NacionalidadeNovoId implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Nacionalidade id não pode ser nulo")
	private String id;
}

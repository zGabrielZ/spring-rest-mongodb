package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;


import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NacionalidadeNovoId implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Nacionalidade id não pode ser nulo")
	private String id;
}

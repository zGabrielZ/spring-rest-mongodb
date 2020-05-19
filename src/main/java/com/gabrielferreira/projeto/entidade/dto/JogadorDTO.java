package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JogadorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private Long numeroDaCamisa;
	private NacionalidadeDTO nacionalidade;

}

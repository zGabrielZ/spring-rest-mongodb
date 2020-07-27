package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private Long numeroDaCamisa;
	private NacionalidadeDTO nacionalidade;

}

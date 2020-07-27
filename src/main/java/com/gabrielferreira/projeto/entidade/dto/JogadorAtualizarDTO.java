package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogadorAtualizarDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nome;
	
	@NotNull(message = "Numero da camisa não pode ser vazio")
	@Max(value = 100,message = "Nao pode passar do numero 100")
	private Long numeroDaCamisa;

}

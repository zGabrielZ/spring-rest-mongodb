package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class JogadorInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nome;
	
	@NotNull(message = "Numero da camisa não pode ser vazio")
	@Max(value = 100,message = "Nao pode passar do número 100")
	@Min(value = 0,message = "Nao pode ser menor do que 0")
	private Long numeroDaCamisa;

	@Valid
	@NotNull(message = "Nacionalidade não pode ser nulo")
	private NacionalidadeNovoId nacionalidade;

}

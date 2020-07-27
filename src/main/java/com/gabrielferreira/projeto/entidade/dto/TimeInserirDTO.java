package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nome;
	
	@NotNull(message = "Data não pode ser vazio")
	@JsonFormat(timezone = MY_TIME_ZONE)
	private Date dataFundacao;
	
	@Valid
	@NotNull(message = "Nacionalidade não pode ser nulo")
	private NacionalidadeNovoId nacionalidade;

}

package com.gabrielferreira.projeto.entidade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFundacao;
	private NacionalidadeDTO nacionalidade;
	private List<JogadorDTO> jogadores = new ArrayList<JogadorDTO>();

}

package com.gabrielferreira.projeto.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Time implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";
	
	@Id
	private String id;
	private String nome;
	@JsonFormat(timezone = MY_TIME_ZONE)
	private Date dataFundacao;
	
	private Nacionalidade nacionalidade;
	
	@DBRef(lazy = true)
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	public Time() {}

	public Time(String id, String nome, Date dataFundacao) {
		this.id = id;
		this.nome = nome;
		this.dataFundacao = dataFundacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}

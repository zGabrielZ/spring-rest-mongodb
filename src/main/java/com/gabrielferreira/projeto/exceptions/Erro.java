package com.gabrielferreira.projeto.exceptions;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Erro implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String titulo;
	private OffsetDateTime data;
	
	private List<Campo> campos = new ArrayList<Erro.Campo>();
	
	@Getter
	@Setter
	public static class Campo{
		private String nome;
		private String mensagem;
		
		public Campo(String nome, String mensagem) {
			this.nome = nome;
			this.mensagem = mensagem;
		}
		
		
	}
	
	public Erro() {}

	public Erro(Integer status, String titulo, OffsetDateTime data) {
		this.status = status;
		this.titulo = titulo;
		this.data = data;
	}
	
	
	
}

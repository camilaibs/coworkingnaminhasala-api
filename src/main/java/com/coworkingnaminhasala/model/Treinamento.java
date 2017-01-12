package com.coworkingnaminhasala.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Treinamento {

	private Long id;
	private String titulo;
	private Date inicio;
	private Date fim;
	private String onde;
	private List<String> emails;
	
	public Treinamento(String titulo, Date inicio, Date fim, String onde) {
		this.titulo = titulo;
		this.inicio = inicio;
		this.fim = fim;
		this.onde = onde;
	}
	
}

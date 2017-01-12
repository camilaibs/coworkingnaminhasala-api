package com.coworkingnaminhasala.model;

import lombok.Data;

@Data
public class Usuario {

	private String email;
	private String senha;
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
}

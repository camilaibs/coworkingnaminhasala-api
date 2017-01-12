package com.coworkingnaminhasala.controller;

import lombok.Data;

@Data
public class UsuarioPayload implements Validable {

	private String email;
	private String senha;
	
	public boolean isValid() {
		return email != null && !email.isEmpty() && senha != null && !senha.isEmpty();
	}
	
}

package com.coworkingnaminhasala.controller;

import lombok.Data;

@Data
public class TreinamentoEmailPayload implements Validable {

	private String endereco;
	
	public boolean isValid() {
		return endereco != null && !endereco.isEmpty();
	}
	
}

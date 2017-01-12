package com.coworkingnaminhasala.controller;

import java.util.Date;

import lombok.Data;

@Data
public class TreinamentoPayload implements Validable {

	private String titulo;
	private Date inicio;
	private Date fim;
	private String onde;
	
	public boolean isValid() {
		return titulo != null && !titulo.isEmpty() && inicio != null && fim != null && onde != null && !onde.isEmpty();
	}
	
}

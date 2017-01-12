package com.coworkingnaminhasala.controller;

import com.coworkingnaminhasala.AbstractRequestHandler;
import com.coworkingnaminhasala.Answer;
import com.coworkingnaminhasala.dao.UsuarioDao;
import com.coworkingnaminhasala.dao.UsuarioDaoImpl;
import com.coworkingnaminhasala.model.Usuario;

import spark.Request;

public class GetUsuariosHandler extends AbstractRequestHandler<UsuarioPayload> {

	private UsuarioDao usuarioDao;
	
	public GetUsuariosHandler() {
		super(UsuarioPayload.class);
		this.usuarioDao = new UsuarioDaoImpl();
	}

	@Override
	protected Answer processImpl(UsuarioPayload json, Request request) {		
		Usuario usuario = usuarioDao.listaPorEmailESenha(json.getEmail(), json.getSenha());
		return Answer.ok(dataToJson(usuario));
	}

}

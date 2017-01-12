package com.coworkingnaminhasala.controller;

import com.coworkingnaminhasala.AbstractRequestHandler;
import com.coworkingnaminhasala.Answer;
import com.coworkingnaminhasala.dao.TreinamentoDao;
import com.coworkingnaminhasala.dao.TreinamentoDaoImpl;

import spark.Request;

public class PutTreinamentosEmailsHandler extends AbstractRequestHandler<TreinamentoEmailPayload> {
	
	private TreinamentoDao treinamentoDao;
	
	public PutTreinamentosEmailsHandler() {
		super(TreinamentoEmailPayload.class);
		this.treinamentoDao = new TreinamentoDaoImpl();
	}

	@Override
	protected Answer processImpl(TreinamentoEmailPayload json, Request request) {
		Long id = Long.parseLong(request.params("id"));
		
		if(!treinamentoDao.existeTreinamentoCom(id)) {
			return new Answer(404);
		}
		
		treinamentoDao.adicionaEmail(id, json.getEndereco());
		
		return new Answer(200);
	}

}

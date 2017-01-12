package com.coworkingnaminhasala.controller;

import com.coworkingnaminhasala.AbstractRequestHandler;
import com.coworkingnaminhasala.Answer;
import com.coworkingnaminhasala.dao.TreinamentoDao;
import com.coworkingnaminhasala.dao.TreinamentoDaoImpl;

import spark.Request;

public class PutTreinamentosHandler extends AbstractRequestHandler<TreinamentoPayload> {
	
	private TreinamentoDao treinamentoDao;
	
	public PutTreinamentosHandler() {
		super(TreinamentoPayload.class);
		this.treinamentoDao = new TreinamentoDaoImpl();
	}

	@Override
	protected Answer processImpl(TreinamentoPayload json, Request request) {
		Long id = Long.parseLong(request.params("id"));
		
		if(!treinamentoDao.existeTreinamentoCom(id)) {
			return new Answer(404);
		}
		
		treinamentoDao.atualizaTreinamento(id, json.getTitulo(), json.getInicio(), json.getFim(), json.getOnde());
		
		return new Answer(200);
	}

}

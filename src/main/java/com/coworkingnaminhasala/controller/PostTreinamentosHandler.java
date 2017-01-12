package com.coworkingnaminhasala.controller;

import com.coworkingnaminhasala.AbstractRequestHandler;
import com.coworkingnaminhasala.Answer;
import com.coworkingnaminhasala.dao.TreinamentoDao;
import com.coworkingnaminhasala.dao.TreinamentoDaoImpl;
import com.coworkingnaminhasala.model.Treinamento;

import spark.Request;

public class PostTreinamentosHandler extends AbstractRequestHandler<TreinamentoPayload> {

	private TreinamentoDao treinamentoDao;
	
	public PostTreinamentosHandler() {
		super(TreinamentoPayload.class);
		this.treinamentoDao = new TreinamentoDaoImpl();
	}

	@Override
	protected Answer processImpl(TreinamentoPayload json, Request request) {		
		Treinamento treinamento = treinamentoDao.criaTreinamento(
			json.getTitulo(), 
			json.getInicio(),
			json.getFim(),
			json.getOnde()
		);
		
		return new Answer(201, dataToJson(treinamento));
	}

}

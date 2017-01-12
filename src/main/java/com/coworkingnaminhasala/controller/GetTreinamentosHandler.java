package com.coworkingnaminhasala.controller;

import java.util.List;

import com.coworkingnaminhasala.AbstractRequestHandler;
import com.coworkingnaminhasala.Answer;
import com.coworkingnaminhasala.dao.TreinamentoDao;
import com.coworkingnaminhasala.dao.TreinamentoDaoImpl;
import com.coworkingnaminhasala.model.Treinamento;

import spark.Request;

public class GetTreinamentosHandler extends AbstractRequestHandler<EmptyPayload> {

	private TreinamentoDao treinamentoDao;
	
	public GetTreinamentosHandler() {
		super(EmptyPayload.class);
		this.treinamentoDao = new TreinamentoDaoImpl();
	}

	@Override
	protected Answer processImpl(EmptyPayload value, Request request) {		
		List<Treinamento> treinamentos = treinamentoDao.lista();
		String json = dataToJson(treinamentos);
		return Answer.ok(json);
	}

}

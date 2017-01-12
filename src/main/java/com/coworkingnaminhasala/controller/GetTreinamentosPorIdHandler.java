package com.coworkingnaminhasala.controller;

import com.coworkingnaminhasala.AbstractRequestHandler;
import com.coworkingnaminhasala.Answer;
import com.coworkingnaminhasala.dao.TreinamentoDao;
import com.coworkingnaminhasala.dao.TreinamentoDaoImpl;
import com.coworkingnaminhasala.model.Treinamento;

import spark.Request;

public class GetTreinamentosPorIdHandler extends AbstractRequestHandler<EmptyPayload> {

	private TreinamentoDao treinamentoDao;
	
	public GetTreinamentosPorIdHandler() {
		super(EmptyPayload.class);
		this.treinamentoDao = new TreinamentoDaoImpl();
	}

	@Override
	protected Answer processImpl(EmptyPayload value, Request request) {
		Long id = Long.parseLong(request.params("id"));
		
		if (!treinamentoDao.existeTreinamentoCom(id)) {
			return new Answer(404, dataToJson("Este treinamento não foi encontrado."));
		}
		
		Treinamento treinamento = treinamentoDao.listaPorId(id);
		String json = dataToJson(treinamento);
		return Answer.ok(json);
	}

}

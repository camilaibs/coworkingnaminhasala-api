package com.coworkingnaminhasala;


import java.io.IOException;

import com.coworkingnaminhasala.controller.EmptyPayload;
import com.coworkingnaminhasala.controller.Validable;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractRequestHandler<V extends Validable> implements RequestHandler<V>, Route {

	private ObjectMapper mapper = new ObjectMapper();
	private Class<V> valueClass;

	private static final int HTTP_BAD_REQUEST = 400;

	public AbstractRequestHandler(Class<V> valueClass) {
		this.valueClass = valueClass;
	}

	public String dataToJson(Object data) {
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.setSerializationInclusion(Include.NON_NULL);
			return mapper.writeValueAsString(data);
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter?");
		}
	}
	
	@SuppressWarnings("hiding")
	public <V> V jsonToData(String json, Class<V> valueClass) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, valueClass);
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		try {
			V value = null;
			if (valueClass != EmptyPayload.class) {
				value = jsonToData(request.body(), valueClass);
			}
			
			Answer answer = process(value, request);
			
			response.type("application/json");
			response.status(answer.getCode());
			response.body(answer.getBody());
			return answer.getBody();
		} catch (JsonMappingException e) {
			response.status(HTTP_BAD_REQUEST);
			response.body(e.getMessage());
			return e.getMessage();
		} finally {
			request.session().invalidate();
		}
	}

	public final Answer process(V value, Request request) {
		if (value != null && !value.isValid()) {
			return new Answer(400);
		} else {
			return processImpl(value, request);
		}
	}

	protected abstract Answer processImpl(V value, Request request);

}

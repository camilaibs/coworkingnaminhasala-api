package com.coworkingnaminhasala;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import com.beust.jcommander.JCommander;
import com.coworkingnaminhasala.controller.GetTreinamentosHandler;
import com.coworkingnaminhasala.controller.GetTreinamentosPorIdHandler;
import com.coworkingnaminhasala.controller.GetUsuariosHandler;
import com.coworkingnaminhasala.controller.PostTreinamentosHandler;
import com.coworkingnaminhasala.controller.PutTreinamentosHandler;

public class Service {

	public static void main(String[] args) {
		Configuration configuracao = new Configuration();
		new JCommander(configuracao, args);

		port(configuracao.servicePort);
		
		exception(Exception.class, (exception, request, response) -> {
			response.status(500);
			response.body("Não foi possível realizar esta ação, comunique a equipe de tecnologia");
		});
		
		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
		});
		
		options("/*", (request, response) -> {
			response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, X-Requested-With, X-Access-Token");
			return "ok";
        });
		
		post("/", new GetUsuariosHandler());
		post("/treinamentos", new PostTreinamentosHandler());
		get("/treinamentos", new GetTreinamentosHandler());
		get("/treinamentos/:id", new GetTreinamentosPorIdHandler());
		put("/treinamentos/:id", new PutTreinamentosHandler());
		put("/treinamentos/:id/emails", new PutTreinamentosHandler());
	}
}
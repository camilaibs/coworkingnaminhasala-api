package com.coworkingnaminhasala;


import com.beust.jcommander.Parameter;

public class Configuration {

	@Parameter(names = "--debug")
	public boolean debug = false;

	@Parameter(names = { "--service-port" })
	public Integer servicePort = 8080;

	@Parameter(names = { "--database" })
	public String database = "coworking_na_minha_sala";

	@Parameter(names = { "--db-host" })
	public String dbHost = "localhost";

	@Parameter(names = { "--db-username" })
	public String dbUsername = "root";

	@Parameter(names = { "--db-password" })
	public String dbPassword = "";

	@Parameter(names = { "--db-port" })
	public Integer dbPort = 3306;
	
}

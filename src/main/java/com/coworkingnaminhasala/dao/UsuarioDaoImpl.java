package com.coworkingnaminhasala.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.coworkingnaminhasala.Configuration;
import com.coworkingnaminhasala.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDaoImpl implements UsuarioDao {

	private Sql2o sql2o;

	public UsuarioDaoImpl() {
		Configuration options = new Configuration();
		this.sql2o = new Sql2o(
				"jdbc:mysql://" + options.dbHost + ":" + options.dbPort + 
				"/" + options.database + "?useUnicode=true&characterEncoding=UTF-8",
				options.dbUsername, 
				options.dbPassword
		);
	}
	
	@Override
	public Usuario listaPorEmailESenha(String email, String senha) {
		try (Connection conexao = sql2o.open()) {
            String comando = "SELECT email, senha "+
            				 "FROM usuario "+
        				 	 "WHERE email = :email AND senha = :senha";
			
            Usuario usuario = conexao.createQuery(comando)
            		.addParameter("email", email)
            		.addParameter("senha", senha)
            		.executeAndFetchFirst(Usuario.class);
            
            return usuario;
		}
	}

}

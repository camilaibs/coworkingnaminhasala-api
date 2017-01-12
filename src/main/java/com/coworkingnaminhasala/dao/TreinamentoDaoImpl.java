package com.coworkingnaminhasala.dao;

import java.util.Date;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.coworkingnaminhasala.Configuration;
import com.coworkingnaminhasala.model.Treinamento;

import lombok.Data;

@Data
public class TreinamentoDaoImpl implements TreinamentoDao {

	private Sql2o sql2o;

	public TreinamentoDaoImpl() {
		Configuration options = new Configuration();
		this.sql2o = new Sql2o(
				"jdbc:mysql://" + options.dbHost + ":" + options.dbPort + 
				"/" + options.database + "?useUnicode=true&characterEncoding=UTF-8",
				options.dbUsername, 
				options.dbPassword
		);
	}

	@Override
	public Treinamento criaTreinamento(String titulo, Date inicio, Date fim, String onde) {
		try (Connection conexao = sql2o.open()) {
			Treinamento treinamento = new Treinamento(titulo, inicio, fim, onde);
			
			String comando = "INSERT INTO treinamento (titulo, inicio, fim, onde) "+
						 	 "VALUES (:titulo, :inicio, :fim, :onde)";
			
			treinamento.setId(
				conexao.createQuery(comando, true)
			    .addParameter("titulo", treinamento.getTitulo())
			    .addParameter("inicio", treinamento.getInicio())
			    .addParameter("fim", treinamento.getFim())
			    .addParameter("onde", treinamento.getOnde())
			    .executeUpdate()
				.getKey(Long.class)
			);
			
			return treinamento;
		}
	}

	@Override
	public List<Treinamento> lista() {
		try (Connection conexao = sql2o.open()) {
            String comando = "SELECT id, titulo, inicio, fim, onde "+
	            		     "FROM treinamento";
//	            		     "WHERE fim > NOW()";
			
            List<Treinamento> teams = conexao.createQuery(comando).executeAndFetch(Treinamento.class);
            
            return teams;
        } 
	}

	@Override
	public boolean existeTreinamentoCom(Long id) {
		try (Connection conexao = sql2o.open()) {
			String comando = "SELECT COUNT(*) FROM treinamento WHERE id = :id";
        	Integer contagem = conexao.createQuery(comando)
			    .addParameter("id", id)
			    .executeScalar(Integer.class);
        
       		return contagem > 0;
		}
	}

	@Override
	public Treinamento listaPorId(Long id) {
		try (Connection conexao = sql2o.open()) {
            String comando = "SELECT id, titulo, inicio, fim, onde "+
            				 "FROM treinamento "+
        				 	 "WHERE id = :id";
			
            Treinamento treinamento = conexao.createQuery(comando)
            		.addParameter("id", id)
            		.executeAndFetchFirst(Treinamento.class);
            
            comando = "SELECT endereco "+
		 			  "FROM treinamento_email "+
 			     	  "WHERE treinamento_id = :treinamento_id";
            
            treinamento.setEmails(
        		conexao.createQuery(comando)
        		.addParameter("treinamento_id", id)
        		.executeScalarList(String.class)
    		);
            
            return treinamento;
		}
	}

	@Override
	public void atualizaTreinamento(Long id, String titulo, Date inicio, Date fim, String onde) {
		try (Connection conexao = sql2o.open()) {
			String comando = "UPDATE treinamento "+
						     "SET titulo = :titulo, inicio = :inicio, fim = :fim, onde = :onde "+
						     "WHERE id = :id";
			
			conexao.createQuery(comando)
		    .addParameter("titulo", titulo)
		    .addParameter("inicio", inicio)
		    .addParameter("fim", fim)
		    .addParameter("onde", onde)
		    .addParameter("id", id)
		    .executeUpdate();
		}
	}

	@Override
	public void adicionaEmail(Long id, String endereco) {
		try (Connection conexao = sql2o.open()) {
			String comando = "INSERT INTO treinamento_email (treinamento_id, endereco) "+
						 	 "VALUES (:treinamento_id, :endereco)";
			
			conexao.createQuery(comando)
		    .addParameter("treinamento_id", id)
		    .addParameter("endereco", endereco)
		    .executeUpdate();
		}
	}

}

package com.coworkingnaminhasala.dao;

import java.util.Date;
import java.util.List;

import com.coworkingnaminhasala.model.Treinamento;

public interface TreinamentoDao {

	Treinamento criaTreinamento(String titulo, Date inicio, Date fim, String onde);

	List<Treinamento> lista();

	boolean existeTreinamentoCom(Long id);

	Treinamento listaPorId(Long id);

	void atualizaTreinamento(Long id, String titulo, Date inicio, Date fim, String onde);

	void adicionaEmail(Long id, String endereco);

}
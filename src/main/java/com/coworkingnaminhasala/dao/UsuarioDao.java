package com.coworkingnaminhasala.dao;

import com.coworkingnaminhasala.model.Usuario;

public interface UsuarioDao {

	Usuario listaPorEmailESenha(String email, String senha);

}
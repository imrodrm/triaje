package com.spring.modelo.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.spring.modelo.entidades.PersonalUrgencias;

public interface PersonalUrgenciasLoginDAO {

	public List<PersonalUrgencias> login(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	public void cambiarContrasenia(String username, String newpwd);
}

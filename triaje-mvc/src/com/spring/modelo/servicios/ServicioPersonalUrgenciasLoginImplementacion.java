package com.spring.modelo.servicios;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.modelo.dao.PersonalUrgenciasLoginDAO;
import com.spring.modelo.entidades.PersonalUrgencias;

@Service
public class ServicioPersonalUrgenciasLoginImplementacion implements ServicioPersonalUrgenciasLogin {

	@Autowired
	private PersonalUrgenciasLoginDAO dao;
	
	@Transactional
	public PersonalUrgencias login(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		List<PersonalUrgencias> resultado = dao.login(username, pwd);
		if(resultado.isEmpty()) {
			return null;
		}
		return resultado.get(0);
	}

	@Transactional
	public void cambiarContrasenia(String username, String newpwd) {
		// TODO Auto-generated method stub

	}

}

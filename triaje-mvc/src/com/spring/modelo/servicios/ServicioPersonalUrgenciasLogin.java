package com.spring.modelo.servicios;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.spring.modelo.entidades.PersonalUrgencias;

@Service
public interface ServicioPersonalUrgenciasLogin {

	public PersonalUrgencias login(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	public void cambiarContrasenia(String username, String newpwd);
}

package com.spring.modelo.entidades;

/**
 * @author Ima
 * Esta clase pretende mapear al Personal de Urgencias que realiza la evaluaci�n en el proceso de triaje
 * En la base de datos MySQL la tabla a la que corresponde es "personal_urgencias", por ello le ponemos en @Table ese nombre.
 * Tambi�n le a�adimos el @Entity, puesto que es una entidad
 */
public class PersonalUrgenciasLogin {
	
	private String usuario;
	
	private String contrasenia;
	
	public PersonalUrgenciasLogin() {}
	
	public PersonalUrgenciasLogin(String username, String pwd) {
		this.usuario = username;
		
		this.contrasenia = pwd;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(String user) {
		this.usuario = user;
	}
	
	public String getContrasenia() {
		return this.contrasenia;
	}
	
	public void setContrasenia(String pwd) {
		this.contrasenia = pwd;
	}
}

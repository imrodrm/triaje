package com.spring.modelo.dao;

import java.util.List;

import com.spring.modelo.entidades.Paciente;

/**
 * @author Ima Interfaz que usaremos para acceder a los Pacientes de la base de
 *         datos Por eso se llama DAO (Data Access Object). Esta es la interfaz
 *         que usaremos en el servicio para acceder a los datos y as�, si
 *         cambiamos la implementaci�n, no tenemos que cambiar el servicio
 */
public interface PacienteDAO {

	/**
	 * Devuelve los pacientes de la BD
	 * 
	 * @return lista de pacientes de la base de datos
	 */
	public List<Paciente> getPacientes();

	/**
	 * A�ade un paciente a la BD
	 * 
	 * @param guardar Paciente a guardar
	 */
	public void savePaciente(Paciente guardar);

	/**
	 * M�todo que devuelve un paciente seg�n su N�mero de la Seguridad Social
	 * 
	 * @param nss del Paciente a recuperar
	 * @return el Paciente con el NSS pasado por par�metro
	 */
	public Paciente getPacientePorNSS(String nss);

	/**
	 * Actualiza el paciente por la Base de datos
	 * 
	 * @param update Paciente a actualizar
	 */
	public void updatePaciente(Paciente update);

	/**
	 * Borra el paciente con el nss pasado por par�metro de la BD
	 * 
	 * @param nss del paciente a borrar
	 */
	public void deletePaciente(String nss);

	/**
	 * Devuelve los pacientes con el nombre  que se pasa por par�meto
	 * (LIKE)
	 * 
	 * @param nombre que queremos comparar en la BD
	 * @return Lista de pacientes con el nombre pasado por par�metro
	 */
	public List<Paciente> getPacientesPorNombreOApellido(String nombre);
	
	/**
	 * Devuelve el Paciente con el DNI que se pasa por par�metro
	 * @param dni del paciente que queremos conseguir
	 * @return el Paciente con el DNI que se pasa por par�metro
	 */
	public List<Paciente> getPacientePorNombreYDomicilio(String nombre, String casa);
}

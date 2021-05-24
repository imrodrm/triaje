package com.spring.modelo.dao;

import java.util.List;

import com.spring.modelo.entidades.PersonalUrgencias;

/**
 * @author Ima Interfaz que usaremos para acceder al PersonalDeUrgencias de la
 *         base de datos Por eso se llama DAO (Data Access Object). Esta es la
 *         interfaz que usaremos en el servicio para acceder a los datos y así,
 *         si cambiamos la implementación, no tenemos que cambiar el servicio
 */
public interface PersonalUrgenciasDAO {

	/**
	 * Método que devuelve todo el personal de urgencias
	 * 
	 * @return una lista con todo el personal de urgencias
	 */
	public List<PersonalUrgencias> getPersonalUrgencias();

	/**
	 * Añade la evaluadora o el evaluador a la base de datos
	 * 
	 * @param pu PersonalUrgencias a guardar
	 */
	public void savePersonalUrgencias(PersonalUrgencias pu);

	/**
	 * Método que devuelve el PersonalUrgencias con un id
	 * 
	 * @param id del PersonalUrgencias que queremos devolver
	 * @return el PersonalUrgencias con el id dicho
	 */
	public PersonalUrgencias getPersonalUrgenciasPorId(int id);

	/**
	 * Método que actualiza el PersonalUrgencias pasado por parámetro
	 * 
	 * @param update PersonalUrgencias a actualizar
	 */
	public void updatePersonalUrgencias(PersonalUrgencias update);

	/**
	 * Método que borra el PersonalUrgencias con el id que se pasa por parámetro
	 * 
	 * @param id PersonalUrgencias a borrar
	 */
	public void deletePersonalUrgencias(int id);

	/**
	 * Devuelve una lista de PersonalUrgencias que tengan el nombre o apellido que
	 * contengan la string que se pasa por parámetro
	 * 
	 * @param nombre que queremos comparar
	 * @return Lista de PersonalUrgencias con ese nombre
	 */
	public List<PersonalUrgencias> getPersonalUrgenciasPorNombreOApellido(String nombre);
	
}

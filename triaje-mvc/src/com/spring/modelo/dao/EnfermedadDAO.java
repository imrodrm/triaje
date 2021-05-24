package com.spring.modelo.dao;

import java.util.List;

import com.spring.modelo.entidades.Enfermedad;

/**
 * @author Ima Interfaz que usaremos para acceder a las Enfermedades de la base
 *         de datos Por eso se llama DAO (Data Access Object). Esta es la
 *         interfaz que usaremos en el servicio para acceder a los datos y as�,
 *         si cambiamos la implementaci�n, no tenemos que cambiar el servicio
 */
public interface EnfermedadDAO {

	/**
	 * M�todo para traernos TODAS las enfermedades ordenadas por nombre
	 */
	public List<Enfermedad> getEnfermedades();

	/**
	 * M�todo para a�adir una enfermedad a la base de datos
	 */
	public void addEnfermedad(Enfermedad enf);

	/**
	 * M�todo para traer una enfermedad seg�n su ID
	 */
	public Enfermedad getEnfermedadPorId(int id);

	/**
	 * M�todo para devolver una enfermedad seg�n su nombre
	 */
	public List<Enfermedad> getEnfermedadPorNombre(String nombre);

	/**
	 * M�todo para actualizar la informaci�n de una enfermedad S�lo deber�amos
	 * intentar modificar la descripci�n o el nombre, no el ID
	 */
	public void updateEnfermedad(Enfermedad update);

	/**
	 * M�todo para borrar una enfermedad seg�n su id
	 */
	public void deleteEnfermedad(int id);
}

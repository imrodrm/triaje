package com.spring.modelo.dao;

import java.util.List;

import com.spring.modelo.entidades.Enfermedad;

/**
 * @author Ima Interfaz que usaremos para acceder a las Enfermedades de la base
 *         de datos Por eso se llama DAO (Data Access Object). Esta es la
 *         interfaz que usaremos en el servicio para acceder a los datos y así,
 *         si cambiamos la implementación, no tenemos que cambiar el servicio
 */
public interface EnfermedadDAO {

	/**
	 * Método para traernos TODAS las enfermedades ordenadas por nombre
	 */
	public List<Enfermedad> getEnfermedades();

	/**
	 * Método para añadir una enfermedad a la base de datos
	 */
	public void addEnfermedad(Enfermedad enf);

	/**
	 * Método para traer una enfermedad según su ID
	 */
	public Enfermedad getEnfermedadPorId(int id);

	/**
	 * Método para devolver una enfermedad según su nombre
	 */
	public List<Enfermedad> getEnfermedadPorNombre(String nombre);

	/**
	 * Método para actualizar la información de una enfermedad Sólo deberíamos
	 * intentar modificar la descripción o el nombre, no el ID
	 */
	public void updateEnfermedad(Enfermedad update);

	/**
	 * Método para borrar una enfermedad según su id
	 */
	public void deleteEnfermedad(int id);
}

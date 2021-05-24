package com.spring.modelo.dao;

import java.util.Date;
import java.util.List;

import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PersonalUrgencias;

/**
 * @author Ima Interfaz que usaremos para acceder a las Evalucaiones de la base
 *         de datos Por eso se llama DAO (Data Access Object). Esta es la
 *         interfaz que usaremos en el servicio para acceder a los datos y as�,
 *         si cambiamos la implementaci�n, no tenemos que cambiar el servicio
 */
public interface EvaluacionDAO {

	/**
	 * @return Todas las evaluaciones de la BD
	 */
	public List<Evaluacion> getEvaluaciones();

	/**
	 * M�todo para guardar una evaluaci�n en la Base de Datos
	 * 
	 * @param e es la evaluaci�n a guardar
	 */
	public void saveEvaluacion(Evaluacion e);

	/**
	 * M�todo que nos devuelve una evaluaci�n seg�n su ID
	 * 
	 * @param id de la evaluaci�n para traer de la BD
	 * @return Evaluaci�n con el id pasado por par�metro
	 */
	public Evaluacion getEvaluacionPorId(String id);

	/**
	 * M�todo que actualiza una evaluaci�n
	 * 
	 * @param e evaluaci�n a actualizar
	 */
	public void updateEvaluacion(Evaluacion e);

	/**
	 * M�todo que borra una evaluaci�n por su id
	 * 
	 * @param id id de la evaluaci�n a borrar
	 */
	public void deleteEvaluacion(String id);

	/**
	 * M�todo que devuelve las evaluaciones de un paciente dado
	 * 
	 * @param p paciente del que queremos saber las evaluaciones
	 * @return evaluaciones del paciente pasado por par�metro
	 */
	public List<Evaluacion> getEvaluacionesPorPaciente(Paciente p);

	/**
	 * M�todo que devuelve las evaluaciones de un evaluador dado
	 * 
	 * @param pu Evaluador/a (PersonalUrgencias) del que queremos saber las
	 *           evaluaciones
	 * @return evaluaciones del evaluador/a pasado por par�metro
	 */
	public List<Evaluacion> getEvaluacionesPorEvaluador(PersonalUrgencias pu);

	/**
	 * Devuelve las evaluaciones a partir de una fecha (y hora) dada
	 * 
	 * @param d fecha a partir de la que queremos las evaluaciones
	 * @return lista de evaluaciones a partir de dicha fecha
	 */
	public List<Evaluacion> getEvaluacionesAPartirFecha(Date d);
}

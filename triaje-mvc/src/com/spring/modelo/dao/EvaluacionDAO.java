package com.spring.modelo.dao;

import java.util.Date;
import java.util.List;

import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PersonalUrgencias;

/**
 * @author Ima Interfaz que usaremos para acceder a las Evalucaiones de la base
 *         de datos Por eso se llama DAO (Data Access Object). Esta es la
 *         interfaz que usaremos en el servicio para acceder a los datos y así,
 *         si cambiamos la implementación, no tenemos que cambiar el servicio
 */
public interface EvaluacionDAO {

	/**
	 * @return Todas las evaluaciones de la BD
	 */
	public List<Evaluacion> getEvaluaciones();

	/**
	 * Método para guardar una evaluación en la Base de Datos
	 * 
	 * @param e es la evaluación a guardar
	 */
	public void saveEvaluacion(Evaluacion e);

	/**
	 * Método que nos devuelve una evaluación según su ID
	 * 
	 * @param id de la evaluación para traer de la BD
	 * @return Evaluación con el id pasado por parámetro
	 */
	public Evaluacion getEvaluacionPorId(String id);

	/**
	 * Método que actualiza una evaluación
	 * 
	 * @param e evaluación a actualizar
	 */
	public void updateEvaluacion(Evaluacion e);

	/**
	 * Método que borra una evaluación por su id
	 * 
	 * @param id id de la evaluación a borrar
	 */
	public void deleteEvaluacion(String id);

	/**
	 * Método que devuelve las evaluaciones de un paciente dado
	 * 
	 * @param p paciente del que queremos saber las evaluaciones
	 * @return evaluaciones del paciente pasado por parámetro
	 */
	public List<Evaluacion> getEvaluacionesPorPaciente(Paciente p);

	/**
	 * Método que devuelve las evaluaciones de un evaluador dado
	 * 
	 * @param pu Evaluador/a (PersonalUrgencias) del que queremos saber las
	 *           evaluaciones
	 * @return evaluaciones del evaluador/a pasado por parámetro
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

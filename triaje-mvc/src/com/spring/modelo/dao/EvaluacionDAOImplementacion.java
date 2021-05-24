package com.spring.modelo.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PersonalUrgencias;

/**
 * @author Ima Implementación que usaremos para acceder a las Evalucaiones de la
 *         base de datos Por eso se llama DAO (Data Access Object). Repository
 *         es una anotación que es una especialización de @Component y significa
 *         que la clase es un repositorio Un repositorio es un mecanismo que
 *         encapsula CRUD. Aquí también se implementan otras operaciones aparte
 *         de las típicas CRUD, pero mientras no se implemente lógica, está
 *         bien.
 */
@Repository
public class EvaluacionDAOImplementacion implements EvaluacionDAO {

	/**
	 * La factoría de la que cogemos las sesiones La anotación de @Autowired sirve
	 * para inyectar dependencias
	 */
	@Autowired
	private SessionFactory factoria;

	/**
	 * @return Todas las evaluaciones de la BD ordenadas por fecha
	 */
	@Override
	public List<Evaluacion> getEvaluaciones() {
		Session sesion = factoria.getCurrentSession();

		Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion ORDER BY fecha", Evaluacion.class);

		List<Evaluacion> evaluaciones = query.getResultList();

		return evaluaciones;
	}

	/**
	 * Método para guardar una evaluación en la Base de Datos
	 * 
	 * @param e es la evaluación a guardar
	 */
	@Override
	public void saveEvaluacion(Evaluacion e) {
		Session sesion = factoria.getCurrentSession();

		sesion.save(e);
	}

	/**
	 * Método que nos devuelve una evaluación según su ID
	 * 
	 * @param id de la evaluación para traer de la BD
	 * @return Evaluación con el id pasado por parámetro
	 */
	@Override
	public Evaluacion getEvaluacionPorId(String id) {
		Session sesion = factoria.getCurrentSession();

		Evaluacion ev = sesion.get(Evaluacion.class, id);

		return ev;
	}

	/**
	 * Método que actualiza una evaluación
	 * 
	 * @param e evaluación a actualizar
	 */
	@Override
	public void updateEvaluacion(Evaluacion e) {
		Session sesion = factoria.getCurrentSession();

		sesion.update(e);
	}

	/**
	 * Método que borra una evaluación por su id
	 * 
	 * @param id id de la evaluación a borrar
	 */
	@Override
	public void deleteEvaluacion(String id) {
		Session sesion = factoria.getCurrentSession();

		Evaluacion ev = sesion.get(Evaluacion.class, id);

		sesion.delete(ev);
	}

	/**
	 * Método que devuelve las evaluaciones de un paciente dado ordenadas por fecha
	 * 
	 * @param p paciente del que queremos saber las evaluaciones
	 * @return evaluaciones del paciente pasado por parámetro
	 */
	@Override
	public List<Evaluacion> getEvaluacionesPorPaciente(Paciente p) {
		Session sesion = factoria.getCurrentSession();

		Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion WHERE nssPaciente = :nss ORDER BY fecha",
				Evaluacion.class);
		query.setParameter("nss", p.getNSS());

		List<Evaluacion> evaluaciones = query.getResultList();

		return evaluaciones;
	}

	/**
	 * Método que devuelve las evaluaciones de un evaluador dado ordenado por fecha
	 * 
	 * @param pu Evaluador/a (PersonalUrgencias) del que queremos saber las
	 *           evaluaciones
	 * @return evaluaciones del evaluador/a pasado por parámetro
	 */
	@Override
	public List<Evaluacion> getEvaluacionesPorEvaluador(PersonalUrgencias pu) {
		Session sesion = factoria.getCurrentSession();

		Query<Evaluacion> query = sesion.createQuery(
				"FROM Evaluacion WHERE idPersonal_Urgencias = :idUrgencias ORDER BY fecha", Evaluacion.class);
		query.setParameter("idUrgencias", pu.getId());

		List<Evaluacion> evaluaciones = query.getResultList();

		return evaluaciones;
	}

	/**
	 * Devuelve las evaluaciones a partir de una fecha (y hora) dada, ordenados por
	 * su prioridad
	 * 
	 * @param d fecha a partir de la que queremos las evaluaciones
	 * @return lista de evaluaciones a partir de dicha fecha
	 */
	@Override
	public List<Evaluacion> getEvaluacionesAPartirFecha(Date d) {
		Session sesion = factoria.getCurrentSession();

		Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion WHERE fecha >= :date ORDER BY prioridad",
				Evaluacion.class);
		query.setParameter("date", d);

		List<Evaluacion> evaluaciones = query.getResultList();

		return evaluaciones;
	}

}

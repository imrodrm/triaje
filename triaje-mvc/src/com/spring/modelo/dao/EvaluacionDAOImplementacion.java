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
 * @author Ima Implementaci�n que usaremos para acceder a las Evalucaiones de la
 *         base de datos Por eso se llama DAO (Data Access Object). Repository
 *         es una anotaci�n que es una especializaci�n de @Component y significa
 *         que la clase es un repositorio Un repositorio es un mecanismo que
 *         encapsula CRUD. Aqu� tambi�n se implementan otras operaciones aparte
 *         de las t�picas CRUD, pero mientras no se implemente l�gica, est�
 *         bien.
 */
@Repository
public class EvaluacionDAOImplementacion implements EvaluacionDAO {

	/**
	 * La factor�a de la que cogemos las sesiones La anotaci�n de @Autowired sirve
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
	 * M�todo para guardar una evaluaci�n en la Base de Datos
	 * 
	 * @param e es la evaluaci�n a guardar
	 */
	@Override
	public void saveEvaluacion(Evaluacion e) {
		Session sesion = factoria.getCurrentSession();

		sesion.save(e);
	}

	/**
	 * M�todo que nos devuelve una evaluaci�n seg�n su ID
	 * 
	 * @param id de la evaluaci�n para traer de la BD
	 * @return Evaluaci�n con el id pasado por par�metro
	 */
	@Override
	public Evaluacion getEvaluacionPorId(String id) {
		Session sesion = factoria.getCurrentSession();

		Evaluacion ev = sesion.get(Evaluacion.class, id);

		return ev;
	}

	/**
	 * M�todo que actualiza una evaluaci�n
	 * 
	 * @param e evaluaci�n a actualizar
	 */
	@Override
	public void updateEvaluacion(Evaluacion e) {
		Session sesion = factoria.getCurrentSession();

		sesion.update(e);
	}

	/**
	 * M�todo que borra una evaluaci�n por su id
	 * 
	 * @param id id de la evaluaci�n a borrar
	 */
	@Override
	public void deleteEvaluacion(String id) {
		Session sesion = factoria.getCurrentSession();

		Evaluacion ev = sesion.get(Evaluacion.class, id);

		sesion.delete(ev);
	}

	/**
	 * M�todo que devuelve las evaluaciones de un paciente dado ordenadas por fecha
	 * 
	 * @param p paciente del que queremos saber las evaluaciones
	 * @return evaluaciones del paciente pasado por par�metro
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
	 * M�todo que devuelve las evaluaciones de un evaluador dado ordenado por fecha
	 * 
	 * @param pu Evaluador/a (PersonalUrgencias) del que queremos saber las
	 *           evaluaciones
	 * @return evaluaciones del evaluador/a pasado por par�metro
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

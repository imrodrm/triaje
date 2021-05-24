package com.spring.modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.modelo.entidades.PersonalUrgencias;

/**
 * @author Ima Implementación que usaremos para acceder al PersonalDeUrgencias
 *         de la base de datos Por eso se llama DAO (Data Access Object).
 */
public class PersonalUrgenciasDAOImplementacion implements PersonalUrgenciasDAO {

	/**
	 * La factoría de la que cogemos las sesiones La anotación de @Autowired sirve
	 * para inyectar dependencias
	 */
	@Autowired
	private SessionFactory factoria;

	/**
	 * Método que devuelve todo el personal de urgencias ordenado por ID
	 * 
	 * @return una lista con todo el personal de urgencias
	 */
	@Override
	public List<PersonalUrgencias> getPersonalUrgencias() {
		Session sesion = factoria.getCurrentSession();

		Query<PersonalUrgencias> query = sesion.createQuery("FROM PersonalUrgencias ORDER BY id",
				PersonalUrgencias.class);

		List<PersonalUrgencias> personal = query.getResultList();

		return personal;
	}

	/**
	 * Añade la evaluadora o el evaluador a la base de datos
	 * 
	 * @param pu PersonalUrgencias a guardar
	 */
	@Override
	public void savePersonalUrgencias(PersonalUrgencias pu) {
		Session sesion = factoria.getCurrentSession();

		sesion.save(pu);
	}

	/**
	 * Método que devuelve el PersonalUrgencias con un id
	 * 
	 * @param id del PersonalUrgencias que queremos devolver
	 * @return el PersonalUrgencias con el id dicho
	 */
	@Override
	public PersonalUrgencias getPersonalUrgenciasPorId(int id) {
		Session sesion = factoria.getCurrentSession();

		return sesion.get(PersonalUrgencias.class, id);
	}

	/**
	 * Método que actualiza el PersonalUrgencias pasado por parámetro
	 * 
	 * @param update PersonalUrgencias a actualizar
	 */
	@Override
	public void updatePersonalUrgencias(PersonalUrgencias update) {
		Session sesion = factoria.getCurrentSession();

		sesion.update(update);
	}

	/**
	 * Método que borra el PersonalUrgencias con el id que se pasa por parámetro
	 * 
	 * @param id PersonalUrgencias a borrar
	 */
	@Override
	public void deletePersonalUrgencias(int id) {
		Session sesion = factoria.getCurrentSession();

		PersonalUrgencias pu = sesion.get(PersonalUrgencias.class, id);

		sesion.delete(pu);
	}

	/**
	 * Devuelve una lista de PersonalUrgencias que tengan el nombre o apellido que
	 * contengan la string que se pasa por parámetro ordenados por nombre
	 * 
	 * @param nombre que queremos comparar
	 * @return Lista de PersonalUrgencias con ese nombre
	 */
	@Override
	public List<PersonalUrgencias> getPersonalUrgenciasPorNombreOApellido(String nombre) {
		Session sesion = factoria.getCurrentSession();

		// Comparamos con lower para cubrirnos en caso de todas mayúsculas o algo así
		// Los % % es para que la query coja todo lo que contenga la string que se pasa
		// por parámetro
		Query<PersonalUrgencias> query = sesion.createQuery(
				"FROM PersonalUrgencias WHERE (lower(nombre) LIKE lower(:name)) OR (lower(apellidos) LIKE lower(:apellido)) ORDER BY nombre",
				PersonalUrgencias.class);
		;
		query.setParameter("name", "%" + nombre + "%");
		query.setParameter("apellido", "%" + nombre + "%");

		List<PersonalUrgencias> personal = query.getResultList();

		return personal;
	}

}

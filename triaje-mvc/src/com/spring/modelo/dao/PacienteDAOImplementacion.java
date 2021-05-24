package com.spring.modelo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.modelo.entidades.Paciente;

/**
 * @author Ima Implementación de la interfaz que usaremos para acceder a los
 *         Pacientes de la base de datos Por eso se llama DAO (Data Access
 *         Object). Repository es una anotación que es una especialización
 *         de @Component y significa que la clase es un repositorio Un
 *         repositorio es un mecanismo que encapsula CRUD. Aquí también se
 *         implementan otras operaciones aparte de las típicas CRUD, pero
 *         mientras no se implemente lógica, está bien.
 */
@Repository
public class PacienteDAOImplementacion implements PacienteDAO {

	/**
	 * La factoría de la que cogemos las sesiones La anotación de @Autowired sirve
	 * para inyectar dependencias
	 */
	@Autowired
	private SessionFactory factoria;

	/**
	 * Devuelve los pacientes de la BD ordenados por su Número de la Seguridad
	 * Social
	 * 
	 * @return lista de pacientes de la base de datos
	 */
	@Override
	public List<Paciente> getPacientes() {
		Session sesion = factoria.getCurrentSession();

		Query<Paciente> query = sesion.createQuery("FROM Paciente ORDER BY nss", Paciente.class);

		List<Paciente> pacientes = query.getResultList();

		return pacientes;
	}

	/**
	 * Añade un paciente a la BD
	 * 
	 * @param guardar Paciente a guardar
	 */
	@Override
	public void savePaciente(Paciente guardar) {
		Session sesion = factoria.getCurrentSession();

		sesion.save(guardar);
	}

	/**
	 * Método que devuelve un paciente según su Número de la Seguridad Social
	 * 
	 * @param nss del Paciente a recuperar
	 * @return el Paciente con el NSS pasado por parámetro
	 */
	@Override
	public Paciente getPacientePorNSS(String nss){
		Session sesion = factoria.getCurrentSession();

		return sesion.get(Paciente.class, nss);
	}

	/**
	 * Actualiza el paciente por la Base de datos
	 * 
	 * @param update Paciente a actualizar
	 */
	@Override
	public void updatePaciente(Paciente update) {
		Session sesion = factoria.getCurrentSession();

		sesion.update(update);
	}

	/**
	 * Borra el paciente con el nss pasado por parámetro de la BD
	 * 
	 * @param nss del paciente a borrar
	 */
	@Override
	public void deletePaciente(String nss) {
		Session sesion = factoria.getCurrentSession();

		Paciente borrar = sesion.get(Paciente.class, nss);

		sesion.delete(borrar);
	}

	/**
	 * Devuelve los pacientes con el nombre parecido (LIKE) al que se pasa por parámeto
	 * ordenados por nombre
	 * 
	 * @param nombre que queremos comparar en la BD
	 * @return Lista de pacientes con el nomrbe pasado por parámetro
	 */
	@Override
	public List<Paciente> getPacientesPorNombreOApellido(String nombre) {
		Session sesion = factoria.getCurrentSession();

		// Comparamos con lower para cubrirnos en caso de todas mayúsculas o algo así
		// Los % % es para que la query coja todo lo que contenga la string que se pasa
		// por parámetro
		Query<Paciente> query = sesion.createQuery(
				"FROM Paciente WHERE (lower(nombre) LIKE lower(:name)) ORDER BY nombre",
				Paciente.class);
		;
		query.setParameter("name", "%" + nombre + "%");

		List<Paciente> pacientes = query.getResultList();

		return pacientes;
	}

	/**
	 * Devuelve una lista (que tendrá o 0 o 1 elemento) con el dni que se pasa por parámeto
	 * ordenados por nombre
	 * 
	 * @param dni que queremos comparar en la BD
	 * @return Lista de pacientes con el dni pasado por parámetro
	 */
	@Override
	public List<Paciente> getPacientePorNombreYDomicilio(String nombre, String casa) {
		Session sesion = factoria.getCurrentSession();
		Query<Paciente> query = sesion.createQuery(
				"FROM Paciente WHERE nombre = :name AND "
				+ "lower(domicilio) LIKE lower(:casa) "
				+ "ORDER BY nombre",
				Paciente.class);
		;
		query.setParameter("name", nombre);
		System.out.println(casa);
		query.setParameter("casa", "%" + casa + "%");
		
        
		List<Paciente> pacientes = query.getResultList();

		return pacientes;
	}
}

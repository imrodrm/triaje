package com.spring.modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.modelo.entidades.Enfermedad;

/**
 * @author Ima Implementación de la interfaz para acceder a los datos es una
 *         anotación que es una especialización de @Component y significa que la
 *         clase es un repositorio Un repositorio es un mecanismo que encapsula
 *         CRUD. Aquí también se implementan otras operaciones aparte de las
 *         típicas CRUD, pero mientras no se implemente lógica, está bien.
 */
@Repository
public class EnfermedadDAOImplementacion implements EnfermedadDAO {

	/**
	 * La factoría de la que cogemos las sesiones La anotación de @Autowired sirve
	 * para inyectar dependencias
	 */
	@Autowired
	private SessionFactory factoria;

	/**
	 * Método para traernos TODAS las enfermedades ordenadas por nombre
	 */
	@Override
	public List<Enfermedad> getEnfermedades() {
		Session sesion = factoria.getCurrentSession();

		Query<Enfermedad> query = sesion.createQuery("FROM Enfermedad ORDER BY nombre", Enfermedad.class);

		List<Enfermedad> enfermedades = query.getResultList();

		return enfermedades;
	}

	/**
	 * Método para añadir una enfermedad a la base de datos
	 */
	@Override
	public void addEnfermedad(Enfermedad enf) {
		Session sesion = factoria.getCurrentSession();

		sesion.save(enf);
	}

	/**
	 * Método para traer una enfermedad según su ID
	 */
	@Override
	public Enfermedad getEnfermedadPorId(int id) {
		Session sesion = factoria.getCurrentSession();

		Enfermedad e = sesion.get(Enfermedad.class, id);

		return e;
	}

	/**
	 * Método para devolver una enfermedad según su nombre
	 */
	@Override
	public List<Enfermedad> getEnfermedadPorNombre(String nombre) {
		Session sesion = factoria.getCurrentSession();

		// Comparamos con lower para cubrirnos en caso de todas mayúsculas o algo así
		// Los % % es para que la query coja todo lo que contenga la string que se pasa
		// por parámetro
		Query<Enfermedad> query = sesion
				.createQuery("FROM Enfermedad WHERE lower(nombre) LIKE lower(:name) ORDER BY nombre", Enfermedad.class);
		;
		query.setParameter("name", "%" + nombre + "%");

		List<Enfermedad> enfermedades = query.getResultList();

		return enfermedades;
	}

	/**
	 * Método para actualizar la información de una enfermedad Sólo deberíamos
	 * intentar modificar la descripción o el nombre, no el ID
	 */
	@Override
	public void updateEnfermedad(Enfermedad update) {
		Session sesion = factoria.getCurrentSession();

		sesion.update(update);
	}

	/**
	 * Método para borrar una enfermedad según su id
	 */
	@Override
	public void deleteEnfermedad(int id) {
		Session sesion = factoria.getCurrentSession();

		Enfermedad e = sesion.get(Enfermedad.class, id);

		sesion.delete(e);

	}
}

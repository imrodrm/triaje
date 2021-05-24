package com.spring.modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.modelo.entidades.Enfermedad;

/**
 * @author Ima Implementaci�n de la interfaz para acceder a los datos es una
 *         anotaci�n que es una especializaci�n de @Component y significa que la
 *         clase es un repositorio Un repositorio es un mecanismo que encapsula
 *         CRUD. Aqu� tambi�n se implementan otras operaciones aparte de las
 *         t�picas CRUD, pero mientras no se implemente l�gica, est� bien.
 */
@Repository
public class EnfermedadDAOImplementacion implements EnfermedadDAO {

	/**
	 * La factor�a de la que cogemos las sesiones La anotaci�n de @Autowired sirve
	 * para inyectar dependencias
	 */
	@Autowired
	private SessionFactory factoria;

	/**
	 * M�todo para traernos TODAS las enfermedades ordenadas por nombre
	 */
	@Override
	public List<Enfermedad> getEnfermedades() {
		Session sesion = factoria.getCurrentSession();

		Query<Enfermedad> query = sesion.createQuery("FROM Enfermedad ORDER BY nombre", Enfermedad.class);

		List<Enfermedad> enfermedades = query.getResultList();

		return enfermedades;
	}

	/**
	 * M�todo para a�adir una enfermedad a la base de datos
	 */
	@Override
	public void addEnfermedad(Enfermedad enf) {
		Session sesion = factoria.getCurrentSession();

		sesion.save(enf);
	}

	/**
	 * M�todo para traer una enfermedad seg�n su ID
	 */
	@Override
	public Enfermedad getEnfermedadPorId(int id) {
		Session sesion = factoria.getCurrentSession();

		Enfermedad e = sesion.get(Enfermedad.class, id);

		return e;
	}

	/**
	 * M�todo para devolver una enfermedad seg�n su nombre
	 */
	@Override
	public List<Enfermedad> getEnfermedadPorNombre(String nombre) {
		Session sesion = factoria.getCurrentSession();

		// Comparamos con lower para cubrirnos en caso de todas may�sculas o algo as�
		// Los % % es para que la query coja todo lo que contenga la string que se pasa
		// por par�metro
		Query<Enfermedad> query = sesion
				.createQuery("FROM Enfermedad WHERE lower(nombre) LIKE lower(:name) ORDER BY nombre", Enfermedad.class);
		;
		query.setParameter("name", "%" + nombre + "%");

		List<Enfermedad> enfermedades = query.getResultList();

		return enfermedades;
	}

	/**
	 * M�todo para actualizar la informaci�n de una enfermedad S�lo deber�amos
	 * intentar modificar la descripci�n o el nombre, no el ID
	 */
	@Override
	public void updateEnfermedad(Enfermedad update) {
		Session sesion = factoria.getCurrentSession();

		sesion.update(update);
	}

	/**
	 * M�todo para borrar una enfermedad seg�n su id
	 */
	@Override
	public void deleteEnfermedad(int id) {
		Session sesion = factoria.getCurrentSession();

		Enfermedad e = sesion.get(Enfermedad.class, id);

		sesion.delete(e);

	}
}

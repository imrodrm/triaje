package com.spring.modelo.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 * @author Ima
 * Esta clase pretende representar lo que es un paciente en nuestra aplicación
 * Un paciente es una persona a la que se le realiza evaluaciones en el proceso de triaje
 * tomándole distintos datos y viendo cuales son sus dolencias
 * Está mapeada con la tabla paciente de la BD MySQL, por eso las anotaciones
 */
@Entity
@Table(name="paciente")
public class Paciente {

	//CAMPOS
	
	/**
	 * Identificador del paciente. Será su número de la seguridad social
	 * Su contraparte en la base de datos es el atributo nss
	 */
	@Id
	@Column(name="nss")
	private String NSS;
	
	/**
	 * Nombre del paciente con apellidos
	 * Tiene un tamaño máximo de 100
	 * Su contraparte en la base de datos es el atributo nombre
	 */
	@Size(max = 100, message = "El número máximo de caracteres para el nombre es de {max}")
	@Column(name="nombre")
	private String nombre;
	
	
	/**
	 * Fecha de nacimiento del paciente
	 * Tiene como restricción que ha de ser pasada o presente
	 * Su contrapartida en la BD es f_nac
	 */
	@PastOrPresent
	@Column(name="f_nac")
	private Date f_nac;
	
	/**
	 * Domicilio (Tipo de vía, número, piso/puerta...)
	 * Tiene como restricción que ha de ser de 100 caracteres o menos
	 * Su contrapartida en la BD es domicilio
	 */
	@Size(max = 100, message = "El número máximo de caracteres para el domicilio es de {max}")
	@Column(name="domicilio")
	private String domicilio;
	
	/**
	 * Población en la que vive el Paciente
	 * Restricción: menos de 50 caracteres
	 * Su contrapartida en la BD es poblacion
	 */
	@Size(max = 50, message = "El número máximo de caracteres para la población es de {max}")
	@Column(name="poblacion")
	private String poblacion;
	
	/**
	 * Teléfono del Paciente
	 * Ha de tener 9 y únicamente 9 caracteres
	 * Su contrapartida en la BD es telefono
	 */
	@Size(min = 9, max = 9, message = "El número de teléfono ha de tener {min} caracteres")
	@Column(name="telefono")
	private String telefono;
	
	/**
	 * Género del paciente (Mujer, hombre, no binario...)
	 * Su contrapartida en la BD es genero
	 */
	@Column(name="genero")
	private String genero;
	
	/**
	 * Enfermedades del paciente
	 * Tiene un fetch LAZY para así no traernos las enfermedades a no ser que lo queramos
	 * Tiene un cascade de todo tipo menos delete para no borrar la enfermedad si borramos el paciente
	 * Este campo está mapeado por el campo Paciente en la tabla PacienteEnfermedad en la BD 
	 */
	@OneToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
			mappedBy = "paciente")
	private Set<PacienteEnfermedad> enfermedades = new HashSet<PacienteEnfermedad>();
	
	/**
	 * Evaluaciones del paciente
	 * Tiene un fetch LAZY para así no traernos las evaluaciones a no ser que lo queramos
	 * Tiene un cascade de todo tipo menos delete para no borrar la evaluacion si borramos el paciente
	 * Este campo está mapeado por el campo Paciente en la tabla Evaluacion en la BD 
	 */
	@OneToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
			mappedBy="paciente")
	private List<Evaluacion> evaluaciones;
	
	//CONSTRUCTORES
	
	
	/**
	 * Constructor vacío que necesita Hibernate 
	 */
	public Paciente() {}

	/**
	 * Constructor con parámetros
	 * @param nss Número de la SS del paciente
	 * @param nombre Nombre del paciente
	 * @param apellidos del paciente
	 * @param f_nac fecha de nacimiento del paciente
	 * @param domicilio del paciente
	 * @param poblacion del paciente
	 * @param telefono del paciente
	 * @param genero del paciente
	 */
	public Paciente(String nss, String nombre, Date f_nac, String domicilio, String poblacion,
			String telefono, String genero) {
		this.NSS = nss;
		this.nombre = nombre;
		this.f_nac = f_nac;
		this.domicilio = domicilio;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.genero = genero;
	}
	
	//GETTERS Y SETTERS
	
	/**
	 * Devuelve el NSS del paciente
	 * @return el NSS del paciente
	 */
	public String getNSS() {
		return NSS;
	}

	/**
	 * Devuelve el nombre del paciente
	 * @return el nombre del paciente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre del paciente
	 * @param nombre nuevo nombre del paciente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la fecha de nacimiento del paciente
	 * @return fecha de nacimiento del paciente
	 */
	public Date getF_nac() {
		return f_nac;
	}

	/**
	 * Devuelve el domicilio del paciente
	 * @return el domicilio del paciente
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Modifica el domicilio  del paciente
	 * @param domicilio nuevo domicilio del paciente
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Devuelve la población del paciente
	 * @return la población del paciente
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Modifica la población del paciente
	 * @param poblacion nueva población del paciente
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	/**
	 * Devuelve la población del paciente
	 * @return la población del paciente
	 */
	public String getDomicilioEntero() {
		return this.getDomicilio() + ". " + this.getPoblacion() + ".";
	}

	/**
	 * Devuelve el telefono del paciente
	 * @return el telefono del paciente
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Modifica el telefono del paciente
	 * @param nuevo telefono del paciente
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Devuelve el genero del paciente
	 * @return el genero del paciente
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Modifica el genero del paciente
	 * @param genero nuevo género del paciente
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	/**
	 * Devuelve las enfermedades del paciente
	 * Como tiene un Fetch LAZY esto seguramente acarree una llamada a la BD
	 * @return Un Set de PacienteEnfermedad dónde están las enfermedades con sus "temporalidades" del paciente
	 */
	public Set<PacienteEnfermedad> getEnfermedades() {
		return enfermedades;
	}

	/**
	 * Modifica las enfermedades del paciente
	 * @param enfermedades Set de PacienteEnfermedad nuevo
	 */
	public void setEnfermedades(Set<PacienteEnfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	/**
	 * Devuelve las evaluaciones del paciente
	 * Como tiene un Fetch LAZY esto seguramente acarree una llamada a la BD
	 * @return Una lista de evaliaciones del paciente
	 */
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	/**
	 * Modifica las evaluaciones del paciente
	 * @param evaluaciones Lista de evaluaciones nueva
	 */
	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
	
	/**
	 * Devuelve la edad del paciente
	 * @return la edad del paciente
	 */
	public int edad() {
		if(this.f_nac != null) {
			LocalDate hoy = LocalDate.now();
			LocalDate nacimiento = this.f_nac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return Period.between(nacimiento, hoy).getYears();
		}
		return -1;
	}
	
	//ToString
	/**
	 * Devuelve una String con datos del paciente
	 */
	public String toString() {
		return "Paciente " + this.nombre + ". Con NSS: " + this.NSS;
	}
}

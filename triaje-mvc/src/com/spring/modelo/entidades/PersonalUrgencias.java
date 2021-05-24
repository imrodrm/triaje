package com.spring.modelo.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;



/**
 * @author Ima
 * Esta clase pretende mapear al Personal de Urgencias que realiza la evaluación en el proceso de triaje
 * En la base de datos MySQL la tabla a la que corresponde es "personal_urgencias", por ello le ponemos en @Table ese nombre.
 * También le añadimos el @Entity, puesto que es una entidad
 */
@Entity
@Table(name="personal_urgencias")
public class PersonalUrgencias {
	
	//CAMPOS
	
	/**
	 * El id del personal de urgencias será único, y será autogenerado cuando se inserte en la base de datos. 
	 * En la BD es el campo id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/**
	 * El nombre de pila del PersonalUrgencias
	 * Tiene un tamaño máximo de 45
	 * Su contraparte en la base de datos es el atributo nombre
	 */
	@Size(max = 45, message = "El número máximo de caracteres para el nombre es de {max}")
	@Column(name="nombre")
	private String nombre;
	
	/**
	 * Los apellidos del personal de urgencias
	 * Tiene un tamaño máximo de caracteres 50
	 * Su contraparte en la base de datos es el atributo apellidos
	 */
	@Size(max = 50, message = "El número máximo de caracteres para los apellidos es de {max}")
	@Column(name="apellidos")
	private String apellidos;
	
	/**
	 * La fecha de nacimiento del personal de urgencias
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
	 * Población en la que vive el  Personal de Urgencias
	 * Restricción: menos de 50 caracteres
	 * Su contrapartida en la BD es poblacion
	 */
	@Size(max = 50, message = "El número máximo de caracteres para la población es de {max}")
	@Column(name="poblacion")
	private String poblacion;
	
	/**
	 * Número de teléfono del Personal de Urgencias
	 * Ha de tener 9 y únicamente 9 caracteres
	 * Su contrapartida en la BD es telefono
	 */
	@Size(min = 9, max = 9, message = "El número de teléfono ha de tener {min} caracteres")
	@Column(name="telefono")
	private String telefono;
	
	/**
	 * Género del personal de urgencias (Mujer, hombre, no binario...)
	 * Su contrapartida en la BD es genero
	 */
	@Column(name="genero")
	private String genero;
	
	/**
	 * Nombre de usuario del PersonalUrgencias en el sistema
	 * Se autogenerará de la siguiente forma:
	 * 		primeros 2 caracteres del nombre, 1º apellido entero y, en caso de tener 2º apellido, las 2 primeras letras de este
	 * En la BD es el campo usuario
	 */
	@Size(max = 20, message = "El número de caracteres del usuario ha de tener {max} caracteres como mucho")
	@Column(name="usuario")
	private String usuario;
	
	/**
	 * El correo será nombredeusuario@riojasalud.es
	 * En la BD es el campo usuario
	 */
	@Size(max = 50, message = "El número de caracteres del usuario ha de tener {max} caracteres como mucho")
	@Column(name="correo")
	private String correo;
	
	//
	/**
	 * Lista de las evaluaciones que ha realizado el Personal de Urgencias
	 * Poner el FetchType a LAZY significa que no nos traemos las evaluaciones del Personal de Urgencias A NO SER que lo queramos hacer explícitamente
	 * En cascada están todas las acciones menor borrar, ya que si borramos una evaluación no queremos borrar su evaluador ni viceversa
	 */
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy="evaluador", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Evaluacion> evaluaciones;
	
	//CONSTRUCTORES
	
	/**
	 * Constructor vacío, necesario para Hibernate
	 */
	public PersonalUrgencias() {}	

	//
	/**
	 * Constructor con varios argumentos
	 * @param nombre nombre del PersonalUrgencias
	 * @param apellidos apellidos del PersonalUrgencias
	 * @param f_nac echa de nacimiento del personalUrgencias
	 * @param domicilio domicilio del PersonalUrgencias
	 * @param poblacion Población del PersonalUrgencias
	 * @param telefono teléfono del PersonalUrgencias
	 * @param genero género del PersonalUrgencias
	 */
	public PersonalUrgencias(
			@Size(max = 45, message = "El número máximo de caracteres para el nombre es de {max}") String nombre,
			@Size(max = 50, message = "El número máximo de caracteres para los apellidos es de {max}") String apellidos,
			@PastOrPresent Date f_nac,
			@Size(max = 100, message = "El número máximo de caracteres para el domicilio es de {max}") String domicilio,
			@Size(max = 50, message = "El número máximo de caracteres para la población es de {max}") String poblacion,
			@Size(min = 9, max = 9, message = "El número de teléfono ha de tener {min} caracteres") String telefono,
			@Size(max = 20, message = "El número máximo de caracteres para el género es de {max}") String genero) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.f_nac = f_nac;
		this.domicilio = domicilio;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.genero = genero;
		this.usuario = this.nombre.substring(0, 2) + this.apellidos.split(" ")[0];
		if(this.apellidos.length()>1) {
			this.usuario += this.apellidos.split(" ")[1].substring(0, 2);
		}
		this.correo = this.usuario + "@riojasalud.es";
	}

	//GETTERS Y SETTERS
	
	/**
	 * Devuelve el id del PersonalUrgencias
	 * @return el id del PersonalUrgencias
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve el nombre del PersonalUrgencias
	 * @return el nombre del PersonalUrgencias
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre del PersonalUrgencias
	 * @param nombre nuevo nombre del PersonalUrgencias
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve los apellidos del PersonalUrgencias
	 * @return los apellidos del PersonalUrgencias
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Modifica los apellidos del PersonalUrgencias
	 * @param apellidos nuevos apellidos del PersonalUrgencias
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Devuelve la fecha de nacimiento del PersonalUrgencias
	 * @return fecha de nacimiento del PersonalUrgencias
	 */
	public Date getF_nac() {
		return f_nac;
	}

	/**
	 * Devuelve el domicilio del PersonalUrgencias
	 * @return el domicilio del PersonalUrgencias
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Modifica el domicilio  del PersonalUrgencias
	 * @param domicilio nuevo domicilio del PersonalUrgencias
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Devuelve la población del PersonalUrgencias
	 * @return la población del PersonalUrgencias
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Modifica la población del PersonalUrgencias
	 * @param poblacion nueva población del PersonalUrgencias
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * Devuelve la población del PersonalUrgencias
	 * @return la población del PersonalUrgencias
	 */
	public String getDomicilioEntero() {
		return this.getDomicilio() + ". " + this.getPoblacion() + ".";
	}
	
	/**
	 * Devuelve el telefono del PersonalUrgencias
	 * @return el telefono del PersonalUrgencias
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Modifica el telefono del PersonalUrgencias
	 * @param nuevo telefono del PersonalUrgencias
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Devuelve el genero del PersonalUrgencias
	 * @return el genero del PersonalUrgencias
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Modifica el genero del PersonalUrgencias
	 * @param genero nuevo género del PersonalUrgencias
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Devuelve el usuario del PersonalUrgencias
	 * @return el usuario del PersonalUrgencias
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Modifica el usuario del PersonalUrgencias
	 * @param usuario nuevo usuario del PersonalUrgencias
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Devuelve el correo del PersonalUrgencias
	 * @return el correo del PersonalUrgencias
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Modifica el correo del PersonalUrgencias
	 * @param correo nuevo correo del PersonalUrgencias
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Devuelve las evaluaciones del PersonalUrgencias
	 * @return las evaluaciones del PersonalUrgencias
	 */
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	/**
	 * Modifica las evaluaciones del PersonalUrgencias
	 * @param evaluaciones nuevas evaluaciones del PersonalUrgencias
	 */
	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	//MÉTODOS
	
	/**
	 * Método para añadir una evaluación
	 * @param ev nueva evaluación
	 */
	public void addEvaluacion (Evaluacion ev) {
		if(this.evaluaciones == null)
			this.evaluaciones = new ArrayList<Evaluacion>();
		this.evaluaciones.add(ev);
	}

	/**
	 * Devuelve la edad del Personal de Urgencias
	 * @return edad del PersonalUrgencias
	 */
	public int edad() {
		if(this.f_nac != null) {
			LocalDate hoy = LocalDate.now();
			LocalDate nacimiento = this.f_nac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return Period.between(nacimiento, hoy).getYears();
		}
		return -1;
	}
	
	//toString
	/**
	 * Devuelve una String con datos del PersonalUrgencias
	 */
	@Override
	public String toString() {
		return "Personal de Urgencias con id: " + this.id + ". Nombre: " + this.apellidos + ", " + this.nombre;
	}
}

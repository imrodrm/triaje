package com.spring.modelo.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Ima
 * Esta clase pretende representar lo que es una enfermedad en nuestra aplicación
 * Está mapeada con la tabla enfermedad de la base de datos MySQL, por eso tenemos las anotaciones @Entity y @Table
 */
@Entity
@Table(name="enfermedad")
public class Enfermedad {

	//CAMPOS
	
	/**
	 * El id de la enfermedad, para diferenciarlo de otras
	 * Su valor es autogenerado, por ello en el constructor no se pide el id.
	 * Su contraparte en la base de datos es el atributo id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	/**
	 * Nombre de la enfermedad
	 * Tiene como restricciones que no puede tener un número de caracteres mayor a 45
	 * Su contraparte en la base de datos es homónima
	 */
	@Size(max = 45, message = "El número máximo de caracteres para el nombre es de {max}")
	@Column(name="nombre")
	private String nombre;
	
	/**
	 * Corta descripción de la enfermedad. Como es una aplicación dedicada al triaje, sería interesante que lo que se cuente aquí tenga que ver con efectos que pueda tener para
	 * que el paciente acuda a urgencias
	 * El número máximo de caracteres es de 200
	 * Su contraparte en la base de datos es homónima
	 */
	@Size(max = 200, message = "El número máximo de caracteres para la descripción es de {max}")
	@Column(name="descripcion")
	private String descripcion;
	
	/**
	 * Un set con los pacientes que sufren una enfermedad. Recordemos que PacienteEnfermedad es una tabla generada como consecuencia de la relación N a N entre Paciente y Enfermedad
	 * El tipo de FETCH es Lazy, ya que no nos interesará, normalmente, traernos los pacientes de una enfermedad, ya que ralentizaría el funcionamiento del sistema
	 * El cascade es de todo tipo menos de DELETE, ya que no queremos borrar los pacientes si borramos la enfermedad
	 * Está mapeado por el atributo enfermedad en la tabla PacienteEnfermedad
	 */
	@OneToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
			mappedBy = "enfermedad")
	private Set<PacienteEnfermedad> pacientes = new HashSet<PacienteEnfermedad>();
	
	//CONSTRUCTORES
	
	/**
	 * Constructor sin parámetros necesario para Hibernate
	 */
	public Enfermedad() {}
	
	/**
	 * Constructor con parámetros en caso de ncesitarlo
	 * @param nombre nombre de la enfermedad
	 * @param descripcion descripción de la enfermedad
	 */
	public Enfermedad(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	//GETTERS Y SETTERS
	
	/**
	 * Devuelve el id de la enfermedad
	 * @return id de la enfermedad
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Devuelve el nombre de la enfermedad
	 * @return el nombre de la enfermedad
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre de la enfermedad
	 * @param nombre nuevo de la enfermedad
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la descripción de la enfermedad
	 * @return la descripción de la enfermedad
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Cambia la descripción de la enfermedad
	 * @param descripcion nueva de la enfermedad
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve los pacientes que padecen esta enfermedad
	 * @return los pacientes que padecen la enfermedad
	 */
	public Set<PacienteEnfermedad> getPacienteEnfermedad(){
		return this.pacientes;
	}
	
	/**
	 * Cambia el set de los pacientes de la enfermedad. Normalmente no lo usaremos
	 * @param pacienteEnfermedad el nuevo set de PacienteEnfermedad
	 */
	public void setPacienteEnfermedad(Set<PacienteEnfermedad> pacienteEnfermedad) {
		this.pacientes = pacienteEnfermedad;
	}
	
	//ToString
	
	/**
	 * Devuelve una explicación de la Evaluación
	 */
	@Override
	public String toString() {
		return "Evaluación con id: " + this.id + ". " + this.nombre;
	}
	
}

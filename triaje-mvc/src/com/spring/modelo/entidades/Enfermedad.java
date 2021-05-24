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
 * Esta clase pretende representar lo que es una enfermedad en nuestra aplicaci�n
 * Est� mapeada con la tabla enfermedad de la base de datos MySQL, por eso tenemos las anotaciones @Entity y @Table
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
	 * Tiene como restricciones que no puede tener un n�mero de caracteres mayor a 45
	 * Su contraparte en la base de datos es hom�nima
	 */
	@Size(max = 45, message = "El n�mero m�ximo de caracteres para el nombre es de {max}")
	@Column(name="nombre")
	private String nombre;
	
	/**
	 * Corta descripci�n de la enfermedad. Como es una aplicaci�n dedicada al triaje, ser�a interesante que lo que se cuente aqu� tenga que ver con efectos que pueda tener para
	 * que el paciente acuda a urgencias
	 * El n�mero m�ximo de caracteres es de 200
	 * Su contraparte en la base de datos es hom�nima
	 */
	@Size(max = 200, message = "El n�mero m�ximo de caracteres para la descripci�n es de {max}")
	@Column(name="descripcion")
	private String descripcion;
	
	/**
	 * Un set con los pacientes que sufren una enfermedad. Recordemos que PacienteEnfermedad es una tabla generada como consecuencia de la relaci�n N a N entre Paciente y Enfermedad
	 * El tipo de FETCH es Lazy, ya que no nos interesar�, normalmente, traernos los pacientes de una enfermedad, ya que ralentizar�a el funcionamiento del sistema
	 * El cascade es de todo tipo menos de DELETE, ya que no queremos borrar los pacientes si borramos la enfermedad
	 * Est� mapeado por el atributo enfermedad en la tabla PacienteEnfermedad
	 */
	@OneToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
			mappedBy = "enfermedad")
	private Set<PacienteEnfermedad> pacientes = new HashSet<PacienteEnfermedad>();
	
	//CONSTRUCTORES
	
	/**
	 * Constructor sin par�metros necesario para Hibernate
	 */
	public Enfermedad() {}
	
	/**
	 * Constructor con par�metros en caso de ncesitarlo
	 * @param nombre nombre de la enfermedad
	 * @param descripcion descripci�n de la enfermedad
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
	 * Devuelve la descripci�n de la enfermedad
	 * @return la descripci�n de la enfermedad
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Cambia la descripci�n de la enfermedad
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
	 * Devuelve una explicaci�n de la Evaluaci�n
	 */
	@Override
	public String toString() {
		return "Evaluaci�n con id: " + this.id + ". " + this.nombre;
	}
	
}

package com.spring.modelo.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author Ima
 * Esta clase en un principio no tendría que existir, pero al añadir el campo TEMPORALIDAD, no puede mapearse como una @ManyToMany
 * Lo que pretende mapear es la relación entre una enfermedad y un paciente y su "temporalidad"
 * A pesar de "no tener que existir", tiene una tabla en la Base de Datos.
 */
@Entity
@Table(name = "Paciente_enfermedad")
public class PacienteEnfermedad {

	//CAMPOS 
	
	/**
	 * Este id ha tenido que ser añadido para poder mapear la clase correctamente, si no, no podría mapearse correctamente, por lo tanto, es relativamente "inútil", a la par que necesario
	 * Su contraparte es pacienteEnfermedadID en la BD
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pacienteEnfermedadID")
	private long id;
	
	/**
	 * Paciente que padece la enfermedad. 
	 * Tiene un fetch LAZY porque no siempre vamos a querer saber quién padece la enfermedad (por ejemplo, simplemente contar)
	 * El cascade, igual que siempre, es de todo tipo menos DELETE, para no borrar al paciente de la BD
	 * En la base de datos es el campo nssPaciente en la tabla Paciente_Enfermedad
	 */
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "nssPaciente")
	private Paciente paciente;
	
	/**
	 * Enfermedad que sufre el paciente. 
	 * Tiene un fetch LAZY porque no siempre vamos a querer saber qué enfermeda padece el Paciente (contar, por ejemplo)
	 * El cascade, igual que siempre, es de todo tipo menos DELETE, para no borrar la enfermedad de la BD
	 * En la base de datos es el campo idEnfermedad en la tabla Paciente_Enfermedad
	 */
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "idEnfermedad")
	private Enfermedad enfermedad;
	
	/**
	 * Temporalidad de la enfermedad del paciente
	 * Los valores deberían ser: CRÓNICA, AGUDA, POSIBLEREINCIDENTE...
	 * En la base de datos es el campo temporalidad en la tabla Paciente_Enfermedad
	 */
	@Column(name="temporalidad")
	private String temporalidad;
	
	//GETTERS Y SETTERS
	
	/**
	 * @return el id de PacienteEnfermedad
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return la enfermedad del Paciente
	 */
	@Transient
	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}
	
	/**
	 * Enfermedad a modificar del paciente.
	 * @param enfermedad nueva enfemerdad
	 */
	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
	
	/**
	 * @return devuelve el Paciente qe sufre la enfermedad
	 */
	@Transient
	public Paciente getPaciente() {
		return this.paciente;
	}
	
	/**
	 * Modifica el paciente que sufre la enfermerdad
	 * @param paciente nuevo paciente
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	/**
	 * @return Devuelve la temporalidad de la enfermedad
	 */
	public String getTemporalidad() {
		return temporalidad;
	}
	
	/**
	 * Modifica la temporalidad de la enfermedad
	 * @param temporalidad
	 */
	public void setTemporalidad(String temporalidad) {
		this.temporalidad = temporalidad;
	}
}

package com.spring.modelo.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;


/**
 * @author Ima
 * Esta clase pretende representar lo que es una evaluaci�n en nuestra aplicaci�n
 * Una evaluaci�n es el proceso por el cual el personal de urgencias realiza el triaje de un paciente,
 * tom�ndole distintos datos y viendo cuales son sus dolencias
 * Est� mapeada con la tabla Evaluaci�n de la BD MySQL, por eso las anotaciones
 */
@Entity
@Table(name = "evaluacion")
public class Evaluacion {

	//CAMPOS
	
	/**
	 * Identificador de la evaluaci�n
	 * El id de una evaluacion estar� tomado por el NSS del paciente y
	 * el acumulado de las evaluaciones que este tiene
	 * Su contraparte en la base de datos es el atributo id
	 */
	@Id
	@Column(name = "id")
	private String id;
	
	
	/**
	 * Fecha y hora de la evaluaci�n. Debe de ser pasada o presente
	 * Su contraparte en la base de datos es el atributo fecha
	 */
	@PastOrPresent
	@Column(name = "fecha", insertable = false)
	private Date fecha;
	
	
	/**
	 * La prioridad del triaje es un n�mero que va desde 1 hasta 5. Al menos, el SET-MAT es as� (usado en espa�a). Los niveles son:
	 * Nivel 1: resucitaci�n. En este nivel hay un riesgo vital inmediato que requiere resucitaci�n
	 * Nivel 2: emergencia. Riesgo vital previsible cuya resoluci�n depende del tiempo
	 * Nivel 3: urgencia. Potencial riesgo vital
	 * Nivel 4: menos urgencia. Situaci�n compleja, pero sin riesgo vital elevado
	 * Nivel 5. No urgente- Situaciones que permiten una demora o programaci�n de la atenci�n sin riesgo para el paciente
	 * 
	 * Fuente: https://es.wikipedia.org/wiki/Triaje#MANCHESTER_TRIAGE_SYSTEM_(MTS)_Y_SISTEMA_ESPA%C3%91OL_DE_TRIAJE_(SET)
	 * 
	 * Su contraparte en la base de datos es el atributo prioridad
	 */
	@Column(name = "prioridad")
	private int prioridad;
	
	
	/**
	 * Temperatura corporal del paciente, tiene como m�nimo 32 y m�ximo 45
	 * 
	 * Su contraparte en la base de datos es el atributo temperatura
	 */
	@Min(32)
	@Max(45)
	@Column(name = "temperatura")
	private Double temperatura;
	
	
	/**
	 * Peso del paciente
	 * 
	 * Su contraparte en la base de datos es el atributo peso
	 */
	@Column(name = "peso")
	private Double peso;
	
	
	/**
	 * Altura del paciente
	 * Su contraparte en la base de datos es el atributo altura
	 */
	@Column(name = "altura")
	private Double altura;
	
	
	/**
	 * Dolencia: aqu� es d�nde se explicar� de qu� adolece el paciente
	 * Tiene como restricciones que como mucho ha de tener 1000 caracteres
	 * 
	 * Esto es lo que pretenderemos rellenar con Amazon Lex
	 * Su contraparte en la base de datos es el atributo dolencia
	 */
	@Size(min=0, max = 1000, message = "El n�mero de caracteres para la dolencia ha de estar entre {min} y {max}")
	@Column (name = "dolencia")
	private String dolencia;
	

	/**
	 * Paciente al cual se le realiza la evaluaci�n
	 * Es una clave externa hacia la tabla Paciente
	 * El fetch en este caso es LAZY porque puede que no queramos saber de qui�n es la evaluaci�n (ejemplo, un listado r�pido)
	 * El cascade es de todo menos de DELETE, porque no queremos borrar el paciente cuando se borre la evaluaci�n
	 * 
	 * En JoinColumn se pone el nombre de la columna que tiene la clave for�nea en la base de datos en la tabla Evaluacion
	 * 
	 * Su contraparte en la base de datos es el atributo nssPaciente (N�mero de la Seguridad Social del Paciente)
	 */
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "nssPaciente")
	private Paciente paciente;
	

	/**
	 * Personal que realiza la evaluaci�n
	 * Es una clave externa hacia la tabla Personal_Urgencias
	 * El fetch en este caso es LAZY porque puede que no queramos saber de qui�n es la evaluaci�n (ejemplo, un listado r�pido)
	 * El cascade es de todo menos de DELETE, porque no queremos borrar el personal cuando se borre la evaluaci�n
	 * En JoinColumn se pone el nombre de la columna que tiene la clave for�nea en la base de datos en la tabla Evaluacion
	 * 
	 * Su contraparte en la base de datos es el atributo idPersonal_Urgencias (id del personal de urgencias
	 */
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "idPersonal_Urgencias")
	private PersonalUrgencias evaluador;

	
	//CONSTRUCTORES
	
	/**
	 * Constructor sin par�metros que necesita Hibernate
	 */
	public Evaluacion() {}
	

	/**
	 * Constructor con los par�metros que necesita una evaluaci�n
	 * El id no se le pasa, ya que como hemos dicho, es generado a partir del NSS del paciente y el n�mero de evaluaciones que ha precisado
	 * @param fecha normalmente ser� la fecha del momento en el que se cree (now)
	 * @param prioridad este campo deber� rellenarse a la hora de realizar la evaluaci�n, no es obligatorio rellenarla
	 * @param temperatura del paciente en el momento, no es obligatorio rellenarla
	 * @param peso del paciente en el momento, no es obligatorio rellenarlo
	 * @param altura del paciente en el momento, no es obligatorio rellenarlo
	 * @param dolencia del paciente en el momento, no es obligatoria rellenarla
	 * @param paciente al que se le realiza la evaluaci�n
	 * @param evaluador que realiza la evaluaci�n
	 */
	public Evaluacion(@PastOrPresent Date fecha, int prioridad, @Min(32) @Max(45) double temperatura,
			double peso, double altura,
			@Size(min = 0, max = 1000, message = "El n�mero de caracteres para la dolencia ha de estar entre {min} y {max}") String dolencia,
			Paciente paciente, PersonalUrgencias evaluador) {
		super();
		int i = this.paciente.getEvaluaciones().size() + 1;
		this.id = this.paciente.getNSS() + "-" + Integer.toString(i);
		this.fecha = fecha;
		this.prioridad = prioridad;
		this.temperatura = temperatura;
		this.peso = peso;
		this.altura = altura;
		this.dolencia = dolencia;
		this.paciente = paciente;
		this.evaluador = evaluador;
	}

	//GETTERS y SETTERS
	
	/**
	 * Devuelve el id de la evaluaci�n
	 * @return id de la evaluaci�n
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Devuelve el id de la evaluaci�n
	 * @return id de la evaluaci�n
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Devuelve la fecha de la evaluaci�n
	 * @return fecha de la evaluaci�n
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha de la evaluaci�n
	 * @param fecha. Nueva fecha de la evaluaci�n
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve la prioridad de la evaluaci�n
	 * @return la prioridad de la evaluaci�n
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Modifica la prioridad de la evaluaci�n
	 * @param prioridad nueva de la evaluaci�n
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * devuelve la temperatura del paciente en un momento dado
	 * @return la temperatura del paciente para esta evaluaci�n
	 */
	public Double getTemperatura() {
		return temperatura;
	}

	/**
	 * Modifica la temperatura del paciente para la evaluaci�n
	 * @param temperatura Nueva temperatura
	 */
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	/**
	 * Devuelve el peso del paciente para la evaluaci�n
	 * @return el peso del paciente para esta evaluaci�n
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * Modifica el peso del paciente para la evaluaci�n
	 * @param peso Nuevo peso
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * Devuelve la altura del paciente para la evaluaci�n
	 * @return la altura del paciente para esta evaluaci�n
	 */
	public Double getAltura() {
		return altura;
	}

	/**
	 * Modifica la altura del paciente para la evaluaci�n
	 * @param altura Nueva altura
	 */
	public void setAltura(Double altura) {
		this.altura = altura;
	}

	/**
	 * Devuelve la dolencia del paciente para la evaluaci�n
	 * @return la dolencia del paciente para esta evaluaci�n
	 */
	public String getDolencia() {
		return dolencia;
	}

	/**
	 * Modifica la dolencia del paciente para la evaluaci�n
	 * @param dolencia Nueva dolencia
	 */
	public void setDolencia(String dolencia) {
		this.dolencia = dolencia;
	}

	/**
	 * Devuelve el paciente de la evaluaci�n
	 * @return el paciente de la evaluaci�n
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * Modifica el paciente de la evaluaci�n
	 * @param paciente Nuevo paciente
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	/**
	 * Devuelve el PersonalUrgencias de la evaluaci�n
	 * @return el PersonalUrgencias de la evaluaci�n
	 */
	public PersonalUrgencias getEvaluador() {
		return evaluador;
	}

	/**
	 * Modifica el PersonalUrgencias de la evaluaci�n
	 * @param evaluador Nuevo PersonalUrgencias
	 */
	public void setEvaluador(PersonalUrgencias evaluador) {
		this.evaluador = evaluador;
	}
	
	//ToString
	
	/**
	 * Devuelve una String explicando la evaluaci�n
	 */
	@Override
	public String toString() {
		String base = "Evaluaci�n con ID: " + this.id + ", del " + this.paciente + " realizada por: " + this.evaluador + " en el momento " + this.fecha.toString() + 
				". Paciente adolec�a de: " + this.dolencia + ".";
		return base;
	}

}

package com.spring.controladores;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PacienteBusquedaNSS;
import com.spring.modelo.entidades.PacienteBusquedaNombreYDomicilio;
import com.spring.modelo.entidades.PersonalUrgencias;
import com.spring.modelo.entidades.PersonalUrgenciasLogin;
import com.spring.modelo.servicios.ServicioEvaluacion;
import com.spring.modelo.servicios.ServicioPaciente;

@Controller
@RequestMapping("/evaluacion")
public class EvaluacionController {
	
	private ServicioEvaluacion service;
	
	private ServicioPaciente servicePaciente;
	
	@Autowired
	public void setServicioEvaluacion(ServicioEvaluacion service) {
		this.service = service;
	}
	
	@Autowired
	public void setServicioPaciente(ServicioPaciente service) {
		this.servicePaciente = service;
	}
	
	
	/**
	 * Método para empezar la evaluación.
	 * Si no estamos logueados, nos lleva a la página de loguear
	 * Si estamos logueados, devuelve el JSP para rellenar el NSS
	 * @param model
	 * @return el JSP 
	 */
	@GetMapping("/nueva")
	public String getPacienteNSS(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		model.addAttribute("paciente", new Paciente());
		return "nuevaEvaluacionPaciente";
	}
	
	
	/**
	 * Método que procesa el formulario en el que metemos el NSS del Paciente
	 * Si está bien, pasamos al formulario de la evaluación
	 * Si no, volvemos al formulario del NSS
	 * @param p Paciente con NSS
	 * @param model
	 * @return el JSP al que redirige
	 */
	@PostMapping("/nueva")
	public String postPacienteNSS(@ModelAttribute("paciente") PacienteBusquedaNSS p, Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		System.out.println(p.getNSS());
		
		if(p != null && p.getNSS() != null && p.getNSS() != "") {
			Paciente pbd = this.servicePaciente.getPacientePorNSS(p.getNSS());
			if(pbd != null) {
				sesion.removeAttribute("errores");
				sesion.setAttribute("paciente", pbd);
				return "redirect:/evaluacion/nuevaForm";
			}
			String errores = "No hay un paciente con ese NSS en la Base de Datos";
			sesion.setAttribute("errores", errores);
		}
		return "nuevaEvaluacionPaciente";
	}
	
	
	/**
	 * Método para empezar la evaluación.
	 * Si no estamos logueados, nos lleva a la página de loguear
	 * Si estamos logueados, devuelve el JSP para rellenar el Nombre del Paciente
	 * @param model
	 * @return el JSP 
	 */
	@GetMapping("/nuevaNombre")
	public String getPacienteNombreYDomicilio(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		model.addAttribute("paciente", new PacienteBusquedaNombreYDomicilio());
		return "nuevaEvaluacionPacienteNombre";
	}
	
	/**
	 * Método que procesa el formulario en el que metemos el nombre y el domicilio del Paciente
	 * Si está bien, pasamos al formulario de la evaluación
	 * Si no, volvemos al formulario del nombre y el domicilio
	 * @param p PacienteBusquedaNombreYDomicilio
	 * @param model
	 * @return el JSP al que redirige
	 */
	@PostMapping("/nuevaNombre")
	public String postPacienteNombreYDomicilio(@ModelAttribute("paciente") PacienteBusquedaNombreYDomicilio p, Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		if(p != null && p.getNombre() != null && p.getNombre() != "" && p.getDomicilio() != null && p.getDomicilio() != "") {
			System.out.println(p.getNombre());
			System.out.println(p.getDomicilio());
			List<Paciente> pbd = this.servicePaciente.getPacientePorNombreYDomicilio(p.getNombre(), p.getDomicilio());
			if(pbd.size() > 0) {
				sesion.removeAttribute("errores");
				sesion.setAttribute("paciente", pbd.get(0));
				return "redirect:/evaluacion/nuevaForm";
			}
			String errores = "No hay un paciente con ese nombre y esa fecha de nacimiento en la Base de Datos";
			sesion.setAttribute("errores", errores);
		}
		return "nuevaEvaluacionPacienteNombre";
	}
	
	/**
	 * Método para seguir la evaluación una vez ya tenemos el paciente.
	 * Si no estamos logueados, nos lleva a la página de loguear
	 * Si estamos logueados, devuelve el JSP para rellenar el formulario de la evaluación
	 * @param model
	 * @return el JSP 
	 */
	@GetMapping("/nuevaForm")
	public String evaluacionForm(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		Evaluacion ev = new Evaluacion();
		//ev.setAltura(0.0);
		//ev.setPeso(0.0);
		//ev.setTemperatura(0.0);
		model.addAttribute("evaluacion", ev);
		return "nuevaEvaluacion";
	}
	
	/**
	 * Método para procesar la evaluación.
	 * Si no estamos logueados, nos lleva a la página de loguear
	 * Si no hay un paciente introducido, nos lleva a la página de introducir un paciente
	 * Si estamos logueados, procesa la evaluación y nos lleva al índice
	 * @param model
	 * @return el JSP 
	 */
	@PostMapping("/nuevaForm")
	public String nuevaEvaluacion(Evaluacion ev, Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		Paciente p = (Paciente) sesion.getAttribute("paciente");
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		if (p==null) {
			return "redirect:/nueva";
		}
		ev.setPaciente(p);
		ev.setEvaluador(logueado);
		int i = p.getEvaluaciones().size() + 1;
		ev.setId(p.getNSS() + "-" + Integer.toString(i));
		sesion.removeAttribute("paciente");
		this.service.saveEvaluacion(ev);
		return "redirect:/";
	}
	
	/**
	 * Método creado para AJAX en el que, con una cadena que corresponde al nombre, devuelve un JSON formado por los nombres y los domicilios de los pacientes
	 * @param nombre del pacietne para buscar en la BD
	 * @return JSON
	 */
	@PostMapping("/buscarPacientes")
	public @ResponseBody String buscarPacientes(@RequestParam("nombre") String nombre) {
		System.out.println(nombre);
		if(nombre!=null && nombre!="") {
			List<Paciente> pacientes = this.servicePaciente.getPacientesPorNombreOApellido(nombre);
			List<String> nombres = new ArrayList<String>();
			for(Paciente p: pacientes) {
				nombres.add(p.getNombre() + "-" + p.getDomicilio());
			}
			Gson g = new Gson();
			String json = g.toJson(nombres);
			return json;
		}
		return null;
	}
	
	//A PARTIR DE AQUÍ SON LOS MÉTODOS PARA MOSTRAR EVALUACIONES, NO PARA HACERLAS
	@GetMapping("/verHoy")
	public String verEvaluacionesHoy(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		
		LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
		LocalDateTime startofDay = localDateTime.with(LocalTime.MIN);
		Date date = Date.from(startofDay.atZone(ZoneId.systemDefault()).toInstant());	
		
		List<Evaluacion> evaluaciones = this.service.getEvaluacionesAPartirFecha(date);
		for(Evaluacion ev: evaluaciones) {
			System.out.println(ev.getId());
		}
		//sesion.setAttribute("evaluaciones", evaluaciones);
		model.addAttribute("evaluaciones", evaluaciones);
		
		return "verEvaluaciones";
	}
	
	/**
	 * Método creado para AJAX en el que, con una cadena que corresponde al nombre, devuelve un JSON formado por los nombres y los domicilios de los pacientes
	 * @param nombre del pacietne para buscar en la BD
	 * @return JSON
	 */
	@PostMapping("/verDolencia")
	public @ResponseBody String verDolencia(@RequestParam("id") String id) {
		System.out.println(id);
		if(id!=null && id!="") {
			Evaluacion eval = this.service.getEvaluacionPorId(id);
			Gson g = new Gson();
			String json = g.toJson(eval.getDolencia());
			return json;
		}
		return null;
	}
}

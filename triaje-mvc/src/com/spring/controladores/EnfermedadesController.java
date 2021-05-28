package com.spring.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.modelo.entidades.PersonalUrgencias;
import com.spring.modelo.entidades.PersonalUrgenciasLogin;
import com.spring.modelo.servicios.ServicioEnfermedad;

@Controller
@RequestMapping("/enfermedad")
public class EnfermedadesController {

	private ServicioEnfermedad servicioEnfermedad;
	
	@Autowired
	public void ServicioEnfermedad(ServicioEnfermedad service) {
		this.servicioEnfermedad = service;
	}
	
	@GetMapping("verTodas")
	public String verPacientes(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if (logueado == null) {
			// Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			return "redirect:/";
		}
		model.addAttribute("enfermedades", this.servicioEnfermedad.getEnfermedades());
		return "verEnfermedades";
	}
}

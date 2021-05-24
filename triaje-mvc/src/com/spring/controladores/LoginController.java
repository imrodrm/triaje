package com.spring.controladores;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.modelo.entidades.PersonalUrgencias;
import com.spring.modelo.entidades.PersonalUrgenciasLogin;
import com.spring.modelo.servicios.ServicioPersonalUrgenciasLogin;

@Controller
public class LoginController {
	
	private ServicioPersonalUrgenciasLogin service;

	@Autowired
	public void setServicioPersonalUrgenciasLogin( ServicioPersonalUrgenciasLogin service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado = (PersonalUrgencias) sesion.getAttribute("logueado");
		if(logueado == null) {
			//Añadimos el PersonalUrgenciasLogin al modelo
			model.addAttribute("login", new PersonalUrgenciasLogin());
			
			return "login";
		}else {
			model.addAttribute("login", logueado);
			return "index";
		}
	}
	
	@PostMapping("login")
	public String login(@ModelAttribute("login") PersonalUrgenciasLogin login, BindingResult br) {
		if(br.hasErrors()) {
			return "login";
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		PersonalUrgencias logueado;
		try {
			logueado = this.service.login(login.getUsuario(), login.getContrasenia());
			if(logueado == null) {
				sesion.setAttribute("erroresLogin", "Error al iniciar sesión: el usuario o la contraseña son incorrectos");
				return "redirect:/";
			}
			sesion.removeAttribute("erroresLogin");
			sesion.setAttribute("logueado", logueado);
			return "redirect:/";
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("cerrarSesion")
	public String cerrarSesion() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession sesion = attr.getRequest().getSession(true);
		sesion.removeAttribute("logueado");
		return "redirect:/";
	}
}

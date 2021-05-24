package com.spring.modelo.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.modelo.entidades.Enfermedad;

@Service
public interface ServicioEnfermedad {
	
	public List<Enfermedad> getEnfermedades();
	
	public void addEnfermedad(Enfermedad enf);
	
	public Enfermedad getEnfermedadPorId(int id);
	
	public List<Enfermedad> getEnfermedadPorNombre(String nombre);
	
	public void updateEnfermedad(Enfermedad update);
	
	public void deleteEnfermedad(int id);
}

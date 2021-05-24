package com.spring.modelo.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.modelo.entidades.Paciente;

@Service
public interface ServicioPaciente {

	public List<Paciente> getPacientes();
	
	public void savePaciente(Paciente save);
	
	public Paciente getPacientePorNSS(String NSS);
	
	public void updatePaciente(Paciente update);
	
	public void deletePaciente(String nss);
	
	public List<Paciente> getPacientesPorNombreOApellido (String nombre);
	
	public List<Paciente> getPacientePorNombreYDomicilio(String nombre, String hogar);
}

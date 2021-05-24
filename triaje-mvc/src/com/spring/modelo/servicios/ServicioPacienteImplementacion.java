package com.spring.modelo.servicios;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.modelo.dao.PacienteDAO;
import com.spring.modelo.entidades.Paciente;

@Service
public class ServicioPacienteImplementacion implements ServicioPaciente {

	@Autowired
	private PacienteDAO dao;

	@Transactional
	public List<Paciente> getPacientes() {
		// TODO Auto-generated method stub
		return this.dao.getPacientes();
	}

	@Transactional
	public void savePaciente(Paciente save) {
		this.dao.savePaciente(save);
	}

	@Transactional
	public Paciente getPacientePorNSS(String NSS) {
		return this.dao.getPacientePorNSS(NSS);
	}

	@Transactional
	public void updatePaciente(Paciente update) {
		this.dao.updatePaciente(update);
	}

	@Transactional
	public void deletePaciente(String nss) {
		this.dao.deletePaciente(nss);
	}

	@Transactional
	public List<Paciente> getPacientesPorNombreOApellido(String nombre) {
		return this.dao.getPacientesPorNombreOApellido(nombre);
	}

	@Transactional
	public List<Paciente> getPacientePorNombreYDomicilio(String nombre, String hogar) {
		return this.dao.getPacientePorNombreYDomicilio(nombre, hogar);
	}

}

package com.spring.modelo.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.modelo.dao.EnfermedadDAO;
import com.spring.modelo.entidades.Enfermedad;

@Service
public class ServicioEnfermedadImplementacion implements ServicioEnfermedad{

	@Autowired
	EnfermedadDAO dao;
	
	@Transactional
	public List<Enfermedad> getEnfermedades() {
		return this.dao.getEnfermedades();
	}

	@Transactional
	public void addEnfermedad(Enfermedad enf) {
		this.dao.addEnfermedad(enf);
	}

	@Transactional
	public Enfermedad getEnfermedadPorId(int id) {
		return this.dao.getEnfermedadPorId(id);
	}

	@Transactional
	public List<Enfermedad> getEnfermedadPorNombre(String nombre) {
		return this.dao.getEnfermedadPorNombre(nombre);
	}

	@Transactional
	public void updateEnfermedad(Enfermedad update) {
		this.dao.updateEnfermedad(update);
	}

	@Transactional
	public void deleteEnfermedad(int id) {
		this.dao.deleteEnfermedad(id);
	}

}

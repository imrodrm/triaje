package com.spring.modelo.servicios;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.modelo.dao.EvaluacionDAO;
import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PersonalUrgencias;

@Service
public class ServicioEvaluacionImplementacion implements ServicioEvaluacion {

	@Autowired
	private EvaluacionDAO dao;
	
	@Transactional
	public List<Evaluacion> getEvaluaciones() {
		return this.dao.getEvaluaciones();
	}

	@Transactional
	public void saveEvaluacion(Evaluacion e) {
		this.dao.saveEvaluacion(e);
	}

	@Transactional
	public Evaluacion getEvaluacionPorId(String id) {
		return this.dao.getEvaluacionPorId(id);
	}

	@Transactional
	public void updateEvaluacion(Evaluacion e) {
		this.dao.updateEvaluacion(e);
	}

	@Transactional
	public void deleteEvaluacion(String id) {
		this.dao.deleteEvaluacion(id);
	}

	@Transactional
	public List<Evaluacion> getEvaluacionesPorPaciente(Paciente p) {
		return this.dao.getEvaluacionesPorPaciente(p);
	}

	@Transactional
	public List<Evaluacion> getEvaluacionesPorEvaluador(PersonalUrgencias pu) {
		return this.dao.getEvaluacionesPorEvaluador(pu);
	}

	@Transactional
	public List<Evaluacion> getEvaluacionesAPartirFecha(Date d) {
		return this.dao.getEvaluacionesAPartirFecha(d);
	}

}

package com.spring.modelo.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PersonalUrgencias;

@Service
public interface ServicioEvaluacion {

	public List<Evaluacion> getEvaluaciones();
	
	public void saveEvaluacion (Evaluacion e);
	
	public Evaluacion getEvaluacionPorId(String id);
	
	public void updateEvaluacion(Evaluacion e);
	
	public void deleteEvaluacion(String id);
	
	public List<Evaluacion> getEvaluacionesPorPaciente(Paciente p);
	
	public List<Evaluacion> getEvaluacionesPorEvaluador(PersonalUrgencias pu);
	
	public List<Evaluacion> getEvaluacionesAPartirFecha(Date d);
}

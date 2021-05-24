package com.spring.modelo.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.modelo.entidades.PersonalUrgencias;

@Service
public interface ServicioPersonalUrgencias {

	public List<PersonalUrgencias> getPersonalUrgencias();
	
	public void savePersonalUrgencias(PersonalUrgencias pu);
	
	public PersonalUrgencias getPersonalUrgenciasPorId(int id);
	
	public void updatePersonalUrgencias(PersonalUrgencias update);
	
	public void deletePersonalUrgencias(int id);
	
	public List<PersonalUrgencias> getPersonalUrgenciasPorNombreOApellido(String nombre);
}

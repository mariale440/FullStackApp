package com.test.spring.boot.backend.apirest.models.services;

import java.util.List;
import java.util.Optional;

import com.test.spring.boot.backend.apirest.models.entity.Tarea;

public interface ITareaService {
	
	public Tarea saveTarea(Tarea cat) throws Exception;        
    
    public Tarea findOne(int id);
    
    public void deleteTarea(int id) throws Exception;
    
    public List<Tarea> getAllTareas();

}

package com.test.spring.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.spring.boot.backend.apirest.models.entity.Tarea;

public interface ITareaDao extends CrudRepository<Tarea, Integer>{
	
	@Query(value = "SELECT t FROM Tarea t ")
    public List<Tarea> findAll();

}

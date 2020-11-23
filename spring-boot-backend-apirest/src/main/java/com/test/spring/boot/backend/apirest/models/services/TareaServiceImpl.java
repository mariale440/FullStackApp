package com.test.spring.boot.backend.apirest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.spring.boot.backend.apirest.models.dao.ITareaDao;
import com.test.spring.boot.backend.apirest.models.entity.Tarea;

@Service
public class TareaServiceImpl implements ITareaService{
	
	@Autowired
	private ITareaDao tareaDao;
	
	@Transactional
	@Override
    public Tarea saveTarea(Tarea cat) throws Exception {
        if (cat==null) {
            throw new Exception("Tarea no puede ser nulo");
        }
        return tareaDao.save(cat);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Tarea findOne(int id) {
        return tareaDao.findById(id).orElse(null);
    }
    
    @Transactional
    @Override
    public void deleteTarea(int id) throws Exception {
    	Tarea tarea = findOne(id);
        if (tarea==null) {
            throw new Exception("Error al eliminar tarea");
        }
        tareaDao.deleteById(id);
    }
    
    @Override
    public List<Tarea> getAllTareas() {
        return (List<Tarea>) tareaDao.findAll();
    }

}

package com.test.spring.boot.backend.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.backend.apirest.models.entity.Tarea;

import com.test.spring.boot.backend.apirest.models.services.ITareaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Servicio Rest - Tareas")
public class TareaRestController {

	@Autowired
	ITareaService tareaService;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/tasks")
	@ApiOperation(value = "Listar Tareas", notes = "Retorna la lista de todas las tareas")
	public ResponseEntity<Object> getTasks() {
		return new ResponseEntity<>(tareaService.getAllTareas(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/tasks/{id}")
	@ApiOperation(value = "Buscar Tarea", notes = "Retorna una tarea por ID")
	public ResponseEntity<Object> getTask(@PathVariable("id") int id) {
		return new ResponseEntity<>(tareaService.findOne(id), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crear Tarea", notes = "Crea una nueva tarea")
	public ResponseEntity<Object> createTask(@RequestBody Tarea tarea) {
		try {
			if (tarea != null && tarea.getDescripcion() != null && !tarea.getDescripcion().equals("")
					&& tarea.getVigencia() != null) {

				tareaService.saveTarea(tarea);
			} else {
				return new ResponseEntity<>("Error parametros de entrada", HttpStatus.BAD_REQUEST);
			}
			tareaService.saveTarea(tarea);
		} catch (Exception e) {
			return new ResponseEntity<>("Error en guardar tarea", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tarea creada exitosamente", HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/tasks/{id}")
	@ApiOperation(value = "Modificar Tarea", notes = "Actualiza los datos de una tarea por ID")
	public ResponseEntity<Object> updateTask(@PathVariable("id") int id, @RequestBody Tarea tarea) {
		try {
			if (tarea != null && tarea.getDescripcion() != null && !tarea.getDescripcion().equals("")
					&& tarea.getVigencia() != null) {
				Tarea t = tareaService.findOne(id);

				if (t != null) {
					t.setDescripcion(tarea.getDescripcion());
					t.setVigencia(tarea.getVigencia());
					tareaService.saveTarea(t);
				} else {
					return new ResponseEntity<>("Error en obtener tarea por ID", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>("Error parametros de entrada", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Error en modificar tarea", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tarea modificada exitosamente", HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping(value = "/tasks/{id}")
	@ApiOperation(value = "Eliminar Tarea", notes = "Elimina una tarea por ID")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		try {
			tareaService.deleteTarea(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Error en eliminar tarea", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tarea eliminada exitosamente", HttpStatus.NO_CONTENT);
	}

}

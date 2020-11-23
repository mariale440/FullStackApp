package com.test.spring.boot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tbl_tarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT l FROM Tarea l")})
public class Tarea implements Serializable{
           
    @Id 
    @SequenceGenerator(name="SEQ_GEN", sequenceName="TAREA_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "descripcion",  length = 200)
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "vigencia")
    private Integer vigencia; 
	
	@Basic(optional = false)
    @Column(name = "fechaCreacion")
	@Temporal(TemporalType.DATE)
    private Date fechaCreacion;
	
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getVigencia() {
		return vigencia;
	}

	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rj.gonzalez10
 */
public class IteracionDTO implements Serializable {
    //numbre de la iteración
    private String nombre;
    //descripción de la iteración
    private String descripcion;
    //fecha de inicio de la iteración
    private Date fechaInicio;
   //fecha de fin de la iteración 
    private Date fechaFin;
    //identificador de la iteracion
    private Long id;
    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    } 
    
    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }


    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public IteracionEntity toEntity()
    { IteracionEntity entidad = new IteracionEntity();
      entidad.setDescripcion(descripcion);
      entidad.setId(id);
      entidad.setFechaInicio(fechaInicio);
      entidad.setFechaFin(fechaFin);
      entidad.setNombre(nombre);
      return entidad;     
    }
    public IteracionDTO(IteracionEntity pIteracionEntity)
    {
        this.setDescripcion(pIteracionEntity.getDescripcion());
        this.setFechaFin(pIteracionEntity.getFechaFin());
        this.setFechaInicio(pIteracionEntity.getFechaInicio());
        this.setId(pIteracionEntity.getId());
        this.setNombre(pIteracionEntity.getNombre());
    }
    public IteracionDTO(){
        
    }
}

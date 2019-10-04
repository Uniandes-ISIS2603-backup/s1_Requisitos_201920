/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.adapters.DateAdapter;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Maria Alejandra Escalante
 */
public class ModificacionesDTO implements Serializable {
    
    private Long id;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaModificacion;
    
    private String descripcion;

    public ModificacionesDTO (){
        
    }
    
    public ModificacionesDTO(ModificacionesEntity mod){
        setId(mod.getId());
        setDescripcion(mod.getDescripcion());
        setFechaModificacion(mod.getFechaModificacion());
    }
    
    public ModificacionesEntity toEntity(){
        ModificacionesEntity nueva= new ModificacionesEntity();
        nueva.setId(this.id);
        nueva.setDescripcion(this.descripcion);
        nueva.setFechaModificacion(this.fechaModificacion);
        return nueva;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

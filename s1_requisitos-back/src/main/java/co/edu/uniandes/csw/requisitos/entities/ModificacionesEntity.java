/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import co.edu.uniandes.csw.bookstore.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Entity
public class ModificacionesEntity extends BaseEntity implements Serializable{
    //declaraciones necesarias para Date
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    //variable de la fecha en la que se creo la modificacion
    private Date fechaModificacion;
    //variable que describe la modificacion que fue realizada
    private String descripcion;

    //relacion con la entidad persona
    @PodamExclude
    @ManyToOne(
    cascade = CascadeType.ALL
    )
    private PersonaEntity persona;
   
    //relacion con casos de uso
    @ManyToOne(
    cascade = CascadeType.ALL
    )
    private CasoDeUsoEntity casoModificaciones;
    
    //relacion con requisitos
    @PodamExclude
    @ManyToOne(
    cascade = CascadeType.ALL
    )
    private RequisitosEntity modificacionesRequisito;

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

    /**
     * @return the persona
     */
    public PersonaEntity getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    /**
     * @return the casoModificaciones
     */
    public CasoDeUsoEntity getCasoModificaciones() {
        return casoModificaciones;
    }

    /**
     * @param casoModificaciones the casoModificaciones to set
     */
    public void setCasoModificaciones(CasoDeUsoEntity casoModificaciones) {
        this.casoModificaciones = casoModificaciones;
    }

    /**
     * @return the modificacionesRequisito
     */
    public RequisitosEntity getModificacionesRequisito() {
        return modificacionesRequisito;
    }

    /**
     * @param modificacionesRequisito the modificacionesRequisito to set
     */
    public void setModificacionesRequisito(RequisitosEntity modificacionesRequisito) {
        this.modificacionesRequisito = modificacionesRequisito;
    }

    
 
   

  
}

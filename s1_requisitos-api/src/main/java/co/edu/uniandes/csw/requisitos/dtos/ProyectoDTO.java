/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan Martinez
 */
public class ProyectoDTO implements Serializable{
    
    private String nombre;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicial;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFinal;

    public ProyectoDTO(){
    
    }
    
    public ProyectoDTO(ProyectoEntity proy){
        if(proy!=null){
            this.nombre = proy.getNombre();
            this.fechaFinal = proy.getFechaFinal();
            this.fechaInicial = proy.getFechaInicial();
        }
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    public ProyectoEntity toEntity() {
        ProyectoEntity proyectoEntity = new ProyectoEntity();
        proyectoEntity.setNombre(this.nombre);
        proyectoEntity.setFechaFinal(this.fechaFinal);
        proyectoEntity.setFechaInicial(this.fechaInicial);
        return proyectoEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
}

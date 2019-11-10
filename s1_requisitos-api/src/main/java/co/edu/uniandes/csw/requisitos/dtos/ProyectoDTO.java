/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.adapters.DateAdapter;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * @author Juan Martinez
 */
public class ProyectoDTO implements Serializable{
    
    /**
     * Nombre del proyecto
     */
    private String nombre;
    
    /**
     * Fecha inicial del proyecto
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicial;
    
    /**
     * Fecha final del proyecto
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFinal;

    /**
     * Identificador del proyecto
     */
    private Long id;
    
    private EquipoDesarrolloEntity equipo;
    
    public ProyectoDTO(){
    
    }
    
    /**
     * Constructor entidad a DTO
     * @param proy entidad a convertir
     */
    public ProyectoDTO(ProyectoEntity proy){
        if(proy!=null){
            this.nombre = proy.getNombre();
            this.fechaFinal = proy.getFechaFinal();
            this.fechaInicial = proy.getFechaInicial();
            this.equipo = proy.getEquipo();
            this.id = proy.getId();
        }
    }
    
    /**
     * Constructor DTO a entidad
     * @return entidad a partir del DTO
     */
    public ProyectoEntity toEntity() {
        ProyectoEntity proyectoEntity = new ProyectoEntity();
        proyectoEntity.setNombre(this.nombre);
        proyectoEntity.setFechaFinal(this.fechaFinal);
        proyectoEntity.setFechaInicial(this.fechaInicial);
        proyectoEntity.setId(this.id);
        return proyectoEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
     * @return the equipo
     */
    public EquipoDesarrolloEntity getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(EquipoDesarrolloEntity equipo) {
        this.equipo = equipo;
    }

    
    
    
    
}

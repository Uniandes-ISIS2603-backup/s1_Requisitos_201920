/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Martinez
 * corregido por: Juan Felipe Rubio
 */
public class EquipoDesarrolloDTO implements Serializable{

  
    
    /**
     *  id del equipo de desarrollo
     */
    private Long id;
    
    /**
     * equipo de desarrollo
     */
    private String equipoDesarrollo;
    
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
     * @return the equipoDesarrollo
     */
    public String getEquipoDesarrollo() {
        return equipoDesarrollo;
    }

    /**
     * @param equipoDesarrollo the equipoDesarrollo to set
     */
    public void setEquipoDesarrollo(String equipoDesarrollo) {
        this.equipoDesarrollo = equipoDesarrollo;
    }
    
    /**
     * constructor vació de equipo desarrollo DTO
     */
    public EquipoDesarrolloDTO(){
        
    }
    
    public EquipoDesarrolloDTO(EquipoDesarrolloEntity equipo){
        if(equipo!=null){
        this.id = equipo.getId();
        this.equipoDesarrollo=equipo.getEquipoDesarrollo();
        }
    }
    
    public EquipoDesarrolloEntity toEntity(){
        EquipoDesarrolloEntity equipoEntity= new EquipoDesarrolloEntity();
        equipoEntity.setId(this.getId());
        equipoEntity.setEquipoDesarrollo(this.equipoDesarrollo);
        return equipoEntity;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}

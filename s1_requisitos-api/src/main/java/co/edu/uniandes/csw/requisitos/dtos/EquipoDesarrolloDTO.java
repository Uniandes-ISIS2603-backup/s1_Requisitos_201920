/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Martinez
 */
public class EquipoDesarrolloDTO {
    
    /**
     * 
     */
    private Long id;

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
    
    public EquipoDesarrolloDTO(){
        
    }
    
    public EquipoDesarrolloDTO(EquipoDesarrolloEntity equipo){
        this.id = equipo.getId();
    }
    
    public EquipoDesarrolloEntity toEntity(){
        EquipoDesarrolloEntity equipoEntity= new EquipoDesarrolloEntity();
        equipoEntity.setId(this.id);
        return equipoEntity;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}

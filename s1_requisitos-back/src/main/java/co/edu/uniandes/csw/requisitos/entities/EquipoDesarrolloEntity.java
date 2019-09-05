/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Juan Martinez
 */
@Entity
public class EquipoDesarrolloEntity extends BaseEntity implements Serializable{
    
    private String equipoDesarrollo;

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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Juan Rubio
 */
@Entity
public class DescripcionEntity extends BaseEntity implements Serializable {

    /**
     * Caso de uso entidades
     */
    @ManyToOne
    private CasoDeUsoEntity casoEntidades;
    
     /**
     * Caso de uso caminos
     */
    @ManyToOne
    private CasoDeUsoEntity casoCaminos;
    
     /**
     * Caso de uso pos condiciones
     */
    @ManyToOne
    private CasoDeUsoEntity casoPosCondiciones;
    
     /**
     * Caso de uso precondiciones
     */
    @ManyToOne
    private CasoDeUsoEntity casoPreCondiciones;
    
    /**
     * Caso de uso alterno
     */
    @ManyToOne
    private CasoDeUsoEntity casoAlterno;
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
    
    private String descripcion;
}

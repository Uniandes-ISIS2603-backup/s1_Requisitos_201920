/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Entidad que representa un desarrollador
 *
 * @author Nicolás Tobo
 */
@Entity
public class DesarrolladorEntity extends BaseEntity implements Serializable {

    /**
     * String que representa el tipo del desarrollador
     */
    private String tipo;

    /**
     * retorna el tipo de la prueba.
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * reescribe el valor de tipo del objeto.
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import java.util.List;

/**
 *
 * @author Nicolas Tobo
 */
public class DesarrolladorDTO 
{

      /**
     * String que representa el tipo del desarrollador
     */
    private String tipo;
    /**
     * Constructor del detailDto
     */
    public DesarrolladorDTO(){}
    /**
     * BLOQUE GETTER AND SETTER
     */
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

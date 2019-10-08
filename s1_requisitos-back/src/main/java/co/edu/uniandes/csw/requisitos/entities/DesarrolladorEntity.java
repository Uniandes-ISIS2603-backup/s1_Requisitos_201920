/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa un desarrollador
 *
 * @author Nicolás Tobo
 */
@Entity
public class DesarrolladorEntity extends PersonaEntity implements Serializable {

    /**
     * String que representa el tipo del desarrollador
     */
    private String tipo;
    /**
     * Lista de requisitos que tiene un desarrollador
     */
    @PodamExclude
    @OneToMany(mappedBy = "desarrollador",fetch=FetchType.LAZY)
    private List<RequisitosEntity> requisitos = new ArrayList<>();

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
    /**
     * Retorna los requisitos en los que trabaja el desarrollador
     * @return lista de requisitos
     */
    public List<RequisitosEntity> getRequisitos() {
        return requisitos;
    }
    /**
     * Define los requisitos en los que trabaja el desarrollador
     * @param requisitos 
     */
    public void setRequisitos(List<RequisitosEntity> requisitos) {
        this.requisitos = requisitos;
    }   
}


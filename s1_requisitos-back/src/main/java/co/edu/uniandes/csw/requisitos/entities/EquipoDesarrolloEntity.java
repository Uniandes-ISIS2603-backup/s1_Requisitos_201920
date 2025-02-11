/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Martinez
 * corregido por: Juan Rubio
 */
@Entity
public class EquipoDesarrolloEntity extends BaseEntity implements Serializable{


    private String equipoDesarrollo;

    @PodamExclude
    @OneToMany(mappedBy = "equipoDesarrollo",cascade = CascadeType.PERSIST)
    private List<DesarrolladorEntity> integrantes = new ArrayList<>();
    
    @PodamExclude
    @OneToMany (mappedBy = "equipo",cascade = CascadeType.PERSIST)
    private List<ProyectoEntity> proyectos;
    
       
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
     * @return the integrantes
     */
    public List<DesarrolladorEntity> getIntegrantes() {
        return integrantes;
    }

    /**
     * @param integrantes the integrantes to set
     */
    public void setIntegrantes(List<DesarrolladorEntity> integrantes) {
        this.integrantes = integrantes;
    }

    /**
     * @return the proyectos
     */
    public List<ProyectoEntity> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(List<ProyectoEntity> proyectos) {
        this.proyectos = proyectos;
    }
    
}

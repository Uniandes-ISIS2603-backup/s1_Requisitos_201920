/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import co.edu.uniandes.csw.requisitos.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan Martinez
 */
@Entity
public class ProyectoEntity extends BaseEntity implements Serializable{
    
    /**
     * Nombre del proyecto
     */
    private String nombre;
    
     @PodamExclude
     @ManyToOne
     private EquipoDesarrolloEntity equipo;
    
     @PodamExclude
     @OneToMany(
    mappedBy = "iteracionProyecto",
            cascade = CascadeType.ALL
     )
       private List<IteracionEntity> iteraciones = new ArrayList<>();
     
    
     
    @Temporal(TemporalType.DATE)
    /**
     * FechaInicial del proyecto
     */
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicial;
    
    /**
     * FechaFinal del proyecto
     */
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    
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

    /**
     * @return the iteraciones
     */
    public List<IteracionEntity> getIteraciones() {
        return iteraciones;
    }

    /**
     * @param iteraciones the iteraciones to set
     */
    public void setIteraciones(List<IteracionEntity> iteraciones) {
        this.iteraciones = iteraciones;
    }
    
    
}

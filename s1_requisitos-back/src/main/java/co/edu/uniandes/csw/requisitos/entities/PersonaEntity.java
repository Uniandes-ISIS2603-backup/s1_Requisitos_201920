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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Rubio
 */
@Entity
public class PersonaEntity extends BaseEntity implements Serializable {

    /**
     * @return the modificaciones
     */
    public List<ModificacionesEntity> getModificaciones() {
        return modificaciones;
    }

    /**
     * @param modificaciones the modificaciones to set
     */
    public void setModificaciones(List<ModificacionesEntity> modificaciones) {
        this.modificaciones = modificaciones;
    }

    /**
     * @return the equipoDesarrollo
     */
    public EquipoDesarrolloEntity getEquipoDesarrollo() {
        return equipoDesarrollo;
    }

    /**
     * @param equipoDesarrollo the equipoDesarrollo to set
     */
    public void setEquipoDesarrollo(EquipoDesarrolloEntity equipoDesarrollo) {
        this.equipoDesarrollo = equipoDesarrollo;
    }

    @PodamExclude
    @OneToMany(
        mappedBy = "persona", 
        fetch = javax.persistence.FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    private List<ModificacionesEntity> modificaciones=new ArrayList<>();
    @PodamExclude
    @ManyToOne
    private EquipoDesarrolloEntity equipoDesarrollo;
     
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the cedula
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    /**
     * nombre de la persona
     */
    private String nombre;
    /**
     * Correo de la persona
     */
    private String correo;
    
    /**
     * cedula de la perosna
     */
    private int cedula;

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
    
}

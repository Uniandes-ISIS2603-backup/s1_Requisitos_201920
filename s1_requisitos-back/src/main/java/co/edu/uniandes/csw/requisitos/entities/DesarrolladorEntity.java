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
 * Entidad que representa un desarrollador
 *
 * @author Nicolás Tobo
 */
@Entity
public class DesarrolladorEntity extends BaseEntity implements Serializable {

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

    public enum TipoDesarrollador {
        REPRESENTANTEDELCLIENTE, RESPONSABLE, COMUN
    }
    /*
    /**
     * String que representa el tipo del desarrollador
     */
    private TipoDesarrollador tipo;
    private String nombre;
    private String correo;
    private Integer edad;
    private Integer cedula;
    /**
     * Lista de requisitos que tiene un desarrollador
     */
    @PodamExclude
    @OneToMany(mappedBy = "desarrollador", cascade = CascadeType.ALL)
    private List<RequisitosEntity> requisitos = new ArrayList<>();

    @PodamExclude
    @OneToMany(
            mappedBy = "responsable",
            cascade = CascadeType.ALL
    )
    private List<CasoDeUsoEntity> casosDeUsoResponsable = new ArrayList<>();

    @PodamExclude
    @OneToMany(
            mappedBy = "representanteDelCliente",
            cascade = CascadeType.ALL
    )
    private List<CasoDeUsoEntity> casosDeUsoRepresentante = new ArrayList<>();

    @PodamExclude
    @OneToMany(
            mappedBy = "desarrolladorModificaciones",
            cascade = CascadeType.ALL
    )
    private List<ModificacionesEntity> modificaciones = new ArrayList<>();

    @PodamExclude
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private EquipoDesarrolloEntity equipoDesarrollo;

    /**
     * @return the tipo
     */
    public TipoDesarrollador getTipo() {
        return tipo;
    }
    public String getTipoString(){
  switch (tipo) {
            case REPRESENTANTEDELCLIENTE:
                return "RepresentanteDelCliente";
            case RESPONSABLE:
                return "Responsable";
            default:
                return "Comun.";
        }
    }
            

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoDesarrollador tipo) {
        this.tipo = tipo;
    }

    public void setTipoString(String tipo) {
        switch (tipo) {
            case "RepresentanteDelCliente":
                this.tipo = TipoDesarrollador.REPRESENTANTEDELCLIENTE;
                break;
            case "Responsable":
                this.tipo = TipoDesarrollador.RESPONSABLE;
                break; 
            case "Comun":
                this.tipo = TipoDesarrollador.COMUN;
                break;
            default:
                break;
          
        }

    }


    /**
     * @return the requisitos
     */
    public List<RequisitosEntity> getRequisitos() {
        return requisitos;
    }

    /**
     * @param requisitos the requisitos to set
     */
    public void setRequisitos(List<RequisitosEntity> requisitos) {
        this.requisitos = requisitos;
    }

    /**
     * @return the casosDeUsoResponsable
     */
    public List<CasoDeUsoEntity> getCasosDeUsoResponsable() {
        return casosDeUsoResponsable;
    }

    /**
     * @param casosDeUsoResponsable the casosDeUsoResponsable to set
     */
    public void setCasosDeUsoResponsable(List<CasoDeUsoEntity> casosDeUsoResponsable) {
        this.casosDeUsoResponsable = casosDeUsoResponsable;
    }

    /**
     * @return the casosDeUsoRepresentante
     */
    public List<CasoDeUsoEntity> getCasosDeUsoRepresentante() {
        return casosDeUsoRepresentante;
    }

    /**
     * @param casosDeUsoRepresentante the casosDeUsoRepresentante to set
     */
    public void setCasosDeUsoRepresentante(List<CasoDeUsoEntity> casosDeUsoRepresentante) {
        this.casosDeUsoRepresentante = casosDeUsoRepresentante;
    }

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
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the cedula
     */
    public Integer getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

}

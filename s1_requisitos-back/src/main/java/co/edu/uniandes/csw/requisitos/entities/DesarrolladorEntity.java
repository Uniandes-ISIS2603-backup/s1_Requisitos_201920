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
import javax.persistence.FetchType;
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
     * String que representa el tipo del desarrollador
     */
    private String tipo;
    private String nombre;
    private String correo;
    private Integer edad;
    private Integer cedula;
    /**
     * Lista de requisitos que tiene un desarrollador
     */
    @PodamExclude
    @OneToMany(mappedBy = "desarrollador",fetch=FetchType.LAZY)
    private List<RequisitosEntity> requisitos = new ArrayList<>();

    @PodamExclude
    @OneToMany(
    mappedBy = "responsable",
    fetch = javax.persistence.FetchType.LAZY
    )
    private List<CasoDeUsoEntity> casosDeUsoResponsable= new ArrayList<>();
    
    @PodamExclude
    @OneToMany(
    mappedBy = "representanteDelCliente",
    fetch = javax.persistence.FetchType.LAZY
    )
    private List<CasoDeUsoEntity> casosDeUsoRepresentante= new ArrayList<>();
    
    @PodamExclude
    @OneToMany(
    mappedBy = "desarrolladorModificaciones",
    fetch = javax.persistence.FetchType.LAZY
    )
    private List <ModificacionesEntity> modificaciones=new ArrayList<>();

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public List <ModificacionesEntity> getModificaciones() {
        return modificaciones;
    }

    /**
     * @param modificaciones the modificaciones to set
     */
    public void setModificaciones(List <ModificacionesEntity> modificaciones) {
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
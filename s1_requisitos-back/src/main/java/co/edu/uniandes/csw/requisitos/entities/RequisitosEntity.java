/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 * Entidad que representa un requisito funcional
 * @author Nicolas Tobo
 */
@Entity
public class RequisitosEntity extends BaseEntity implements Serializable 
{   
    /**
     * Desarrollador del requisito
     */
    @ManyToOne
    DesarrolladorEntity desarrollador;
    /**
     * Fuente de donde se escribe el requisito
     */
    private String fuente;
    /**
     * Autor del requisito
     */
    private String autor;
    /**
     * Informacion que tiene el requisito
     */
    private String descripcion;
    /**
     * Numero que representa la importancia del requisito
     */
    @PodamIntValue(minValue=0,maxValue=Integer.MAX_VALUE)
    private Integer importancia;
    /**
     * Boolena que representa si el requisito es estable(fijo)
     */
    private Boolean estabilidad;
    /**
     * Comentarios adicionales sobre el requisito
     */
    private String comentariosAdicionales;

    /**
     * @return the fuente
     */
    public String getFuente() {
        return fuente;
    }

    /**
     * @param fuente the fuente to set
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

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

    /**
     * @return the importancia
     */
    public Integer getImportancia() {
        return importancia;
    }

    /**
     * @param importancia the importancia to set
     */
    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    /**
     * @return the estabilidad
     */
    public Boolean getEstabilidad() {
        return estabilidad;
    }

    /**
     * @param estabilidad the estabilidad to set
     */
    public void setEstabilidad(Boolean estabilidad) {
        this.estabilidad = estabilidad;
    }

    /**
     * @return the comentariosAdicionales
     */
    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    /**
     * @param comentariosAdicionales the comentariosAdicionales to set
     */
    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }  
}

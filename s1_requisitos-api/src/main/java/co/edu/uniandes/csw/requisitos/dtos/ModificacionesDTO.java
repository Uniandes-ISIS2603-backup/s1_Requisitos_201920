/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.adapters.DateAdapter;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;

import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
  * ModificacionDTO Objeto de transferencia de datos de Modificacion. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "descripcion": string,
 *      "fechaModificacion ": Date
 *   }
 * </pre> Por ejemplo una  Modificacion se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *       "id": 2,
 *      "descripcion": "se cambio el nombre del usuario",
 *      "fechaModificacion ": "2014-01-01T23:28:56.782Z"
 *   }
 *
 * </pre>
 * @author Maria Alejandra Escalante
 */
public class ModificacionesDTO implements Serializable {

    /*
    atributos de modificacion
     */
    private Long id;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaModificacion;

    //variable que describe la modificacion que fue realizada
    private String descripcion;

    //relacion con la entidad persona

    private DesarrolladorEntity desarrolladorModificaciones;
   
    //relacion con casos de uso

    private CasoDeUsoEntity casoModificaciones;

    private RequisitosEntity modificacionesRequisito;
    /*
    constructor vacio
     */
    public ModificacionesDTO() {

    }

    /*
    constructor que convierte de entidades a DTO
     */
    public ModificacionesDTO(ModificacionesEntity mod) {
        this.id= mod.getId();
        this.fechaModificacion= mod.getFechaModificacion();
        this.casoModificaciones=mod.getCasoModificaciones();
        this.desarrolladorModificaciones=mod.getDesarrolladorModificaciones();
        this.descripcion=mod.getDescripcion();
        this.modificacionesRequisito=mod.getModificacionesRequisito();        
    }

    /*
    metodo que convierte de DTO a entidades
     */
    public ModificacionesEntity toEntity() {
        ModificacionesEntity nueva = new ModificacionesEntity();
        nueva.setId(this.getId());
        nueva.setDescripcion(this.getDescripcion());
        nueva.setFechaModificacion(this.getFechaModificacion());
        nueva.setCasoModificaciones(this.getCasoModificaciones());
        nueva.setModificacionesRequisito(this.getModificacionesRequisito());
        nueva.setDesarrolladorModificaciones(this.getDesarrolladorModificaciones());
        return nueva;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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
     * @return the desarrolladorModificaciones
     */
    public DesarrolladorEntity getDesarrolladorModificaciones() {
        return desarrolladorModificaciones;
    }

    /**
     * @param desarrolladorModificaciones the desarrolladorModificaciones to set
     */
    public void setDesarrolladorModificaciones(DesarrolladorEntity desarrolladorModificaciones) {
        this.desarrolladorModificaciones = desarrolladorModificaciones;
    }

    /**
     * @return the casoModificaciones
     */
    public CasoDeUsoEntity getCasoModificaciones() {
        return casoModificaciones;
    }

    /**
     * @param casoModificaciones the casoModificaciones to set
     */
    public void setCasoModificaciones(CasoDeUsoEntity casoModificaciones) {
        this.casoModificaciones = casoModificaciones;
    }

    /**
     * @return the modificacionesRequisito
     */
    public RequisitosEntity getModificacionesRequisito() {
        return modificacionesRequisito;
    }

    /**
     * @param modificacionesRequisito the modificacionesRequisito to set
     */
    public void setModificacionesRequisito(RequisitosEntity modificacionesRequisito) {
        this.modificacionesRequisito = modificacionesRequisito;
    }
}

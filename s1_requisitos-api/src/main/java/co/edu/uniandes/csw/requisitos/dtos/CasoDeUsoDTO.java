/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import java.io.Serializable;

/**
 *
 * CasoDeUsoDTO Objeto de transferencia de datos de CasoDeUso. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "servicios": string,
 *      "documentacion": string,
 *      "pruebas": boolean
 *   }
 * </pre> Por ejemplo un casoDeUso se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "servicios": "Login",
 *      "documentacion": "realiza el logn de usuario",
 *      "prubas": "false"
 *   }
 *
 * </pre>
 * @author Maria Alejandra Escalante
 */
public class CasoDeUsoDTO implements Serializable {
    /*
    se crean las variables del tipo caso de uso
    */
    private Long id;
    
    private String servicios;
    private String documentacion;
    private Boolean pruebas;
    private DesarrolladorEntity responsable;
/*
    consructor vacio
    */
    public CasoDeUsoDTO (){
        
    }
    /*
    constructor que convierte de entidades a DTOS
    */
      public CasoDeUsoDTO ( CasoDeUsoEntity entidad){
          setId(entidad.getId());
          setDocumentacion(entidad.getDocumentacion());
          setPruebas(entidad.getPruebas());
          setServicios(entidad.getServicios());
          setResponsable(entidad.getResponsable());
    }
    
    /*
      metodo que convierte DTO a entidades
      */
    public CasoDeUsoEntity toEntity(){
        CasoDeUsoEntity entidad= new CasoDeUsoEntity();
        entidad.setId(this.getId());
        entidad.setDocumentacion(this.getDocumentacion());
        entidad.setServicios(this.getServicios());
        entidad.setPruebas(this.getPruebas());
        entidad.setResponsable(this.getResponsable());
        return entidad;
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
     * @return the servicios
     */
    public String getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the documentacion
     */
    public String getDocumentacion() {
        return documentacion;
    }

    /**
     * @param documentacion the documentacion to set
     */
    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    /**
     * @return the pruebas
     */
    public Boolean getPruebas() {
        return pruebas;
    }

    /**
     * @param pruebas the pruebas to set
     */
    public void setPruebas(Boolean pruebas) {
        this.pruebas = pruebas;
    }

    /**
     * @return the responsable
     */
    public DesarrolladorEntity getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(DesarrolladorEntity responsable) {
        this.responsable = responsable;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import java.io.Serializable;

/**
 *
 * @author Maria Alejandra Escalante
 */
public class CasoDeUsoDTO implements Serializable {
    private Long id;
    
    private String servicios;
    private PersonaEntity responsable;
    private String documentacion;
    private Boolean pruebas;

    public CasoDeUsoDTO (){
        
    }
      public CasoDeUsoDTO ( CasoDeUsoEntity entidad){
          setId(entidad.getId());
          setDocumentacion(entidad.getDocumentacion());
          setPruebas(entidad.getPruebas());
          setServicios(entidad.getServicios());
    }
    
    
    public CasoDeUsoEntity toEntity(){
        CasoDeUsoEntity entidad= new CasoDeUsoEntity();
        entidad.setId(this.id);
        entidad.setDocumentacion(this.documentacion);
        entidad.setServicios(this.servicios);
        entidad.setPruebas(this.pruebas);
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
     * @return the responsable
     */
    public PersonaEntity getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(PersonaEntity responsable) {
        this.responsable = responsable;
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
}

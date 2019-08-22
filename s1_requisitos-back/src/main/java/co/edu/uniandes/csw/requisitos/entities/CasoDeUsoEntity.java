/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Entity
public class CasoDeUsoEntity extends BaseEntity implements Serializable {
  
    private String servicios;
    private PersonaEntity responsable;
    private String documentacion;
    private Boolean pruebas;

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

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
    public boolean isPruebas() {
        return pruebas;
    }

    /**
     * @param pruebas the pruebas to set
     */
    public void setPruebas(boolean pruebas) {
        this.pruebas = pruebas;
    }
}

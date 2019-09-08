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
 * @author Nicole Bahamon
 */
@Entity
public class DesempenoEntity extends BaseEntity implements Serializable {
    private String desempeno;

   

    /**
     * @return the desempeno
     */
    public String getDesempeno() {
        return desempeno;
    }

    /**
     * @param desempeno the desempeno to set
     */
    public void setDesempeno(String desempeno) {
        this.desempeno = desempeno;
    }
    
}

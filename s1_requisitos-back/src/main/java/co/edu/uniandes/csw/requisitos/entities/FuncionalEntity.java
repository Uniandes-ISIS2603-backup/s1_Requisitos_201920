/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nicole Bahamon
 */
@Entity
public class FuncionalEntity extends RequisitosEntity implements Serializable 
{
    @PodamExclude
    @ManyToOne
    private CasoDeUsoEntity casoFuncional;

    
    public CasoDeUsoEntity getCasoFuncional() {
        return casoFuncional;
    }

    public void setCasoFuncional(CasoDeUsoEntity casoFuncional) {
        this.casoFuncional = casoFuncional;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
    /**
     * Casos funcionales
     */
    @PodamExclude
    @ManyToOne//(cascade = CascadeType.PERSIST)
    private CasoDeUsoEntity casoFuncional;

    /**
     * Retorna el casoFuncional
     * @return 
     */
    public CasoDeUsoEntity getCasoFuncional() {
        return casoFuncional;
    }
    /**
     * Define el caso funcional
     * @param casoFuncional 
     */
    public void setCasoFuncional(CasoDeUsoEntity casoFuncional) {
        this.casoFuncional = casoFuncional;
    }

    /**
     * Retorna las modificaciones del requisito Funcional
     * @return 
     */
    public List<ModificacionesEntity> getModificaciones() 
    {
        return modificaciones;
    }
    /**
     * define el conjunto de modificaciones 
     * @param modificaciones 
     */
    public void setModificaciones(List<ModificacionesEntity> modificaciones) {
        this.modificaciones = modificaciones;
    }
    
    
}

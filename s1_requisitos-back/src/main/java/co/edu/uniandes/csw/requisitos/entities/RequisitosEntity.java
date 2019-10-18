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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 * Entidad que representa un requisito funcional
 * @author Nicolas Tobo
 */
@Entity
public  class RequisitosEntity extends BaseEntity implements Serializable 
{   
    /**
     * Desarrollador del requisito
     */
    @PodamExclude
    @ManyToOne
    private DesarrolladorEntity desarrollador;
    
    @PodamExclude
    @OneToMany(
        mappedBy = "modificacionesRequisito", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<ModificacionesEntity> modificaciones=new ArrayList<>();
    
    @PodamExclude
    @ManyToOne
    private CasoDeUsoEntity requisitosFuncionalesCaso;

    /**
     * @return the desarrollador
     */
    public DesarrolladorEntity getDesarrollador() {
        return desarrollador;
    }

    /**
     * @param desarrollador the desarrollador to set
     */
    public void setDesarrollador(DesarrolladorEntity desarrollador) {
        this.desarrollador = desarrollador;
    }

    /**
     * @return the modificaciones
     */
    public List<ModificacionesEntity> getModificaciones() {
        return modificaciones;
    }

    /**
     * @param modificaciones the modificaciones to set
     */
    public void setModificaciones(List<ModificacionesEntity> modificaciones) {
        this.modificaciones = modificaciones;
    }

    /**
     * @return the requisitosFuncionalesCaso
     */
    public CasoDeUsoEntity getRequisitosFuncionalesCaso() {
        return requisitosFuncionalesCaso;
    }

    /**
     * @param requisitosFuncionalesCaso the requisitosFuncionalesCaso to set
     */
    public void setRequisitosFuncionalesCaso(CasoDeUsoEntity requisitosFuncionalesCaso) {
        this.requisitosFuncionalesCaso = requisitosFuncionalesCaso;
    }
    
   
}

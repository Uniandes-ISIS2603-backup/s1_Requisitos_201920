/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jf.rubio
 */
public class EquipoDesarrolloDetailDTO extends EquipoDesarrolloDTO implements Serializable{

 
    
    /**
     * Lista de DesarrolladorDTO que puede tener un equipo
     */
    private List<DesarrolladorDTO> integrantes;
    /**
     * Lista de ProyectoEn
    */
    
    private List<ProyectoDTO> proyectos;
    
    /**
     * 
     */
    public EquipoDesarrolloDetailDTO()
    {
        super();
    }
    
    public EquipoDesarrolloDetailDTO(EquipoDesarrolloEntity equipoDesarrollo)
    {
        super(equipoDesarrollo);
        if(equipoDesarrollo !=null)
        {
            if(equipoDesarrollo.getIntegrantes()!= null)
            {
            integrantes = new ArrayList<>();
            for(DesarrolladorEntity entityDes :equipoDesarrollo.getIntegrantes() )
            {
                integrantes.add(new DesarrolladorDTO(entityDes));
            }
            }
            if(equipoDesarrollo.getProyectos()!= null)
            {
                proyectos = new ArrayList<>();
            for(ProyectoEntity entityPro :equipoDesarrollo.getProyectos() )
                    {
                        proyectos.add(new ProyectoDTO(entityPro));
                    }
            }
        }
    }
    
      /**
     * Convierte un objeto DesarrolldorDetailDTO a EquipoDesarrolloEntity incluyendo los
     * atributos de EquipoDesarrolloDTO.
     *
     * @return Nueva objeto EquipoDesarrolloEntity.
     *
     */
    @Override
    public EquipoDesarrolloEntity toEntity() {
        EquipoDesarrolloEntity equipoDesarrolloEntity = super.toEntity();
        if (getIntegrantes() != null) {
            List<DesarrolladorEntity> reqsEntity = new ArrayList<>();
            for (DesarrolladorDTO dtoReq : getIntegrantes()) {
                try {
                    reqsEntity.add(dtoReq.toEntity());
                } catch (Exception ex) {
                    Logger.getLogger(EquipoDesarrolloDetailDTO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            equipoDesarrolloEntity.setIntegrantes(reqsEntity);
        }
        if (getProyectos() != null) {
            List<ProyectoEntity> reqsEntity = new ArrayList<>();
            for (ProyectoDTO dtoReq : getProyectos()) {
                reqsEntity.add(dtoReq.toEntity());
            }
            equipoDesarrolloEntity.setProyectos(reqsEntity);
        }
        return equipoDesarrolloEntity;
    }
    
       /**
     * @return the integrantes
     */
    public List<DesarrolladorDTO> getIntegrantes() {
        return integrantes;
    }

    /**
     * @param integrantes the integrantes to set
     */
    public void setIntegrantes(List<DesarrolladorDTO> integrantes) {
        this.integrantes = integrantes;
    }

    /**
     * @return the proyectos
     */
    public List<ProyectoDTO> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(List<ProyectoDTO> proyectos) {
        this.proyectos = proyectos;
    }
    
}

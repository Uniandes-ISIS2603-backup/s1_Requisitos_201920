/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolás Tobo
 */
public class RequisitosDetailDTO extends RequisitosDTO 
{
    /**
     * Lista de ModificacionesDTO que puede tener el requisito
     */
    protected List<ModificacionesDTO> modificaciones;
    
    /**
     * Constructor por defecto del detailDto
     */
    public RequisitosDetailDTO()
    {
        super();
    }
    /**
     * Crea un objeto RequisitosDetailDTO a partir de un objeto RequisitosEntity
     * incluyendo los atributos de RequisitoDTO.
     *
     * @param requisitoEntity Entidad RequisitosEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public RequisitosDetailDTO(RequisitosEntity requisitoEntity) 
    {
         super(requisitoEntity);
         if (requisitoEntity != null&&requisitoEntity.getModificaciones()!=null) 
         {
            modificaciones = new ArrayList<>();
            for (ModificacionesEntity entityMod : requisitoEntity.getModificaciones()) 
            {
                modificaciones.add(new ModificacionesDTO(entityMod));
            }
         }
    }
    
    /**
     * Convierte un objeto RequisitosDetailDTO a RequisitosEntity incluyendo los
     * atributos de RequisitosDTO.
     *
     * @return Nueva objeto RequisitosEntity.
     *
     */
    @Override
    public RequisitosEntity toEntity() {
        RequisitosEntity desarrolladorEntity = super.toEntity();
        if (modificaciones != null) {
            List<ModificacionesEntity> modsEntity = new ArrayList<>();
            for (ModificacionesDTO dtoReq : modificaciones) {
                modsEntity.add(dtoReq.toEntity());
            }
            desarrolladorEntity.setModificaciones(modsEntity);
        }
        return desarrolladorEntity;
    }

    //Bloque getters and setters
    
    public List<ModificacionesDTO> getModificaciones() {
        return modificaciones;
    }

    public void setModificaciones(List<ModificacionesDTO> modificaciones) {
        this.modificaciones = modificaciones;
    }
    
    /**
     * Define las modificaciones de un requisito a partir de una lista de modificacionesEntity
     * @param modificacionesEnt
     */
    public void setModificacionesEntity(List<ModificacionesEntity> modificacionesEnt) 
    {
        modificaciones = new ArrayList<>();
        for (ModificacionesEntity entityMod :modificacionesEnt) 
        {
            modificaciones.add(new ModificacionesDTO(entityMod));
        }
    }
}


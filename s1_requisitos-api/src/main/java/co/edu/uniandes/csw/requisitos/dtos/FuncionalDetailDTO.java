/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la relacion entre un requisito funcional y sus modificaciones
 * @author Nicolas Tobo
 */
public class FuncionalDetailDTO extends FuncionalDTO
{
    /**
     * Lista de ModificacionesDTO que puede tener el requisito Funcional
     */
    protected List<ModificacionesDTO> modificaciones;
    
    /**
     * Constructor por defecto del detailDto
     */
    public FuncionalDetailDTO()
    {
        super();
    }
    /**
     * Crea un objeto FuncionalDetailDTO a partir de un objeto FuncionalEntity
     * incluyendo los atributos de FuncionalDTO.
     *
     * @param funcionalEntity Entidad FuncionalEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public FuncionalDetailDTO(FuncionalEntity funcionalEntity) 
    {
         super(funcionalEntity);
         if (funcionalEntity != null&&funcionalEntity.getModificaciones()!=null) 
         {
            modificaciones = new ArrayList<>();
            for (ModificacionesEntity entityMod : funcionalEntity.getModificaciones()) 
            {
                modificaciones.add(new ModificacionesDTO(entityMod));
            }
         }
    }
    
    /**
     * Convierte un objeto FuncionalDetailDTO a FuncionalEntity incluyendo los
     * atributos de FuncionalDTO.
     *
     * @return Nuevo objeto FuncionalEntity.
     *
     */
    @Override
    public FuncionalEntity toEntity() {
        FuncionalEntity funcionalEntity = super.toEntity();
        if (modificaciones != null) {
            List<ModificacionesEntity> modsEntity = new ArrayList<>();
            for (ModificacionesDTO dtoReq : modificaciones) {
                modsEntity.add(dtoReq.toEntity());
            }
            funcionalEntity.setModificaciones(modsEntity);
        }
        return funcionalEntity;
    }

    //Bloque getters and setters
    /**
     * Retorna la lista de modificaciones del requisito funcional
     * @return lista de modificaciones del requisito funcional
     */
    public List<ModificacionesDTO> getModificaciones() 
    {
        return modificaciones;
    }
     /**
     * Define la lista de modificaciones del requisito funcional
     * @param modificaciones
     */
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

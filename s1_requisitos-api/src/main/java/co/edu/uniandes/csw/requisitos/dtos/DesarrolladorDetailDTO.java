/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Na.tobo
 */
public class DesarrolladorDetailDTO extends DesarrolladorDTO
{
           /**
     * Lista de ModificacionesDTO que puede tener el requisito
     */
    private List<RequisitosDTO> requisitos;
    
     /**
     * Constructor del detailDto por defecto
     */
    public DesarrolladorDetailDTO()
    {
        super();
    }
    /**
     * Crea un objeto DesarrolladorDetailDTO a partir de un objeto DesarrolladorEntity
     * incluyendo los atributos de DesarrolladorDTO.
     *
     * @param desarrolladorEntity Entidad DesarrolladorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public DesarrolladorDetailDTO(DesarrolladorEntity desarrolladorEntity) 
    {
        super(desarrolladorEntity);
         if (desarrolladorEntity != null) 
         {
            requisitos = new ArrayList<>();
            for (RequisitosEntity entityReq : desarrolladorEntity.getRequisitos()) 
            {
                requisitos.add(new RequisitosDTO(entityReq));
            }
         }
    }
    
    /**
     * Convierte un objeto DesarrolldorDetailDTO a DesarrolladorEntity incluyendo los
     * atributos de DesarrolladorDTO.
     *
     * @return Nueva objeto DesarrolladorEntity.
     *
     */
    @Override
    public DesarrolladorEntity toEntity() {
        DesarrolladorEntity desarrolladorEntity = super.toEntity();
        if (requisitos != null) {
            List<RequisitosEntity> reqsEntity = new ArrayList<>();
            for (RequisitosDTO dtoReq : requisitos) {
                reqsEntity.add(dtoReq.toEntity());
            }
            desarrolladorEntity.setRequisitos(reqsEntity);
        }
        return desarrolladorEntity;
    }
    
    //Bloque getters y setters

    public List<RequisitosDTO> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<RequisitosDTO> requisitos) {
        this.requisitos = requisitos;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representacion relaciones de desarrollador
 * @author Nicolás Tobo
 */
public class DesarrolladorDetailDTO extends DesarrolladorDTO implements Serializable
{
     /**
     * Lista de ModificacionesDTO que puede tener el requisito
     */
    private List<RequisitosDTO> requisitos;
    private List<CasoDeUsoDTO> casosDeUsoRepresentante;
    private List<CasoDeUsoDTO> casosDeUsoResponsable;
    private List<ModificacionesDTO> modificaciones;
    
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
         if (desarrolladorEntity != null) {

            if (desarrolladorEntity.getModificaciones() != null) {
                modificaciones = new ArrayList<>();
                desarrolladorEntity.getModificaciones().forEach(lis -> 
                    modificaciones.add(new ModificacionesDTO(lis)));
              
            }
            if (desarrolladorEntity.getRequisitos() != null) {
                requisitos = new ArrayList<>();
                desarrolladorEntity.getRequisitos().forEach(lis -> 
                    requisitos.add(new RequisitosDTO(lis)));
            }
             if (desarrolladorEntity.getCasosDeUsoRepresentante() != null) {
                casosDeUsoRepresentante = new ArrayList<>();
                desarrolladorEntity.getCasosDeUsoRepresentante().forEach(lis -> 
                    casosDeUsoRepresentante.add(new CasoDeUsoDTO(lis)));
            }
              if (desarrolladorEntity.getCasosDeUsoResponsable() != null) {
                casosDeUsoResponsable = new ArrayList<>();
                desarrolladorEntity.getCasosDeUsoResponsable().forEach(lis ->
                    casosDeUsoResponsable.add(new CasoDeUsoDTO(lis)));            }

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
    public DesarrolladorEntity toEntity()   {
           DesarrolladorEntity desarrolladorEntity = super.toEntity();
        if (modificaciones!=null){
            List <ModificacionesEntity> modificacionesEntity= new ArrayList<>();
            modificaciones.forEach(dto ->
                modificacionesEntity.add(dto.toEntity()));
            desarrolladorEntity.setModificaciones(modificacionesEntity);
        }
     
        if (requisitos != null) {
            List<RequisitosEntity> reqsEntity = new ArrayList<>();
            requisitos.forEach(dtoReq ->
                reqsEntity.add(dtoReq.toEntity()));
            desarrolladorEntity.setRequisitos(reqsEntity);
        }
           if (casosDeUsoRepresentante != null) {
            List<CasoDeUsoEntity> casoDeUsoEntity = new ArrayList<>();
            casosDeUsoRepresentante.forEach(dtoCasoDeUso ->
                casoDeUsoEntity.add(dtoCasoDeUso.toEntity()));       
            desarrolladorEntity.setCasosDeUsoRepresentante(casoDeUsoEntity);
        }
                if (casosDeUsoResponsable != null) {
            List<CasoDeUsoEntity> casoDeUsoEntity = new ArrayList<>();
            casosDeUsoResponsable.forEach(dtoCasoDeUso ->
                casoDeUsoEntity.add(dtoCasoDeUso.toEntity()));         
            desarrolladorEntity.setCasosDeUsoResponsable(casoDeUsoEntity);
        }
        
        return desarrolladorEntity;
    }
    
    
    /**
     * Retorna los requisitos en que trabaja un desarrollador
     * @return 
     */
    public List<RequisitosDTO> getRequisitos() {
        return requisitos;
    }
    /**
     * Define los requisitos en que trabaja un desarrollador
     * @param requisitos 
     */
    public void setRequisitos(List<RequisitosDTO> requisitos) {
        this.requisitos = requisitos;
    }

    /**
     * @return the casosDeUsoRepresentante
     */
    public List<CasoDeUsoDTO> getCasosDeUsoRepresentante() {
        return casosDeUsoRepresentante;
    }

    /**
     * @param casosDeUsoRepresentante the casosDeUsoRepresentante to set
     */
    public void setCasosDeUsoRepresentante(List<CasoDeUsoDTO> casosDeUsoRepresentante) {
        this.casosDeUsoRepresentante = casosDeUsoRepresentante;
    }

    /**
     * @return the casosDeUsoResponsable
     */
    public List<CasoDeUsoDTO> getCasosDeUsoResponsable() {
        return casosDeUsoResponsable;
    }

    /**
     * @param casosDeUsoResponsable the casosDeUsoResponsable to set
     */
    public void setCasosDeUsoResponsable(List<CasoDeUsoDTO> casosDeUsoResponsable) {
        this.casosDeUsoResponsable = casosDeUsoResponsable;
    }

    /**
     * @return the modificaciones
     */
    public List<ModificacionesDTO> getModificaciones() {
        return modificaciones;
    }

    /**
     * @param modificaciones the modificaciones to set
     */
    public void setModificaciones(List<ModificacionesDTO> modificaciones) {
        this.modificaciones = modificaciones;
    }
    
    
}
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
    private List<RequisitosDTO> requisitos= new ArrayList<>();
    private List<ModificacionesDTO> modificaciones=  new ArrayList<>();
    private List<CasoDeUsoDTO> casosdeusoResponsable = new ArrayList<>();
    private List<CasoDeUsoDTO> casosdeusoRepresentante = new ArrayList<>();

    
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
              if (desarrolladorEntity.getModificaciones() != null) {
                modificaciones = new ArrayList<>();
                for (ModificacionesEntity lis : desarrolladorEntity.getModificaciones()) {
                    modificaciones.add(new ModificacionesDTO(lis));
                }
            }
              if (desarrolladorEntity.getRequisitos() != null) {
                requisitos = new ArrayList<>();
                for (RequisitosEntity lis : desarrolladorEntity.getRequisitos()) {
                    requisitos.add(new RequisitosDTO(lis));
                }
            }
             if (desarrolladorEntity.getCasosDeUsoRepresentante()!= null) {
                casosdeusoRepresentante = new ArrayList<>();
                for (CasoDeUsoEntity lis : desarrolladorEntity.getCasosDeUsoRepresentante()) {
                    casosdeusoRepresentante.add(new CasoDeUsoDTO(lis));
                }
            }    
              if (desarrolladorEntity.getCasosDeUsoResponsable()!= null) {
                casosdeusoResponsable = new ArrayList<>();
                for (CasoDeUsoEntity lis : desarrolladorEntity.getCasosDeUsoResponsable()) {
                    casosdeusoResponsable.add(new CasoDeUsoDTO(lis));
                }
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

 

   

    /**
     * @return the casosdeusoResponsable
     */
    public List<CasoDeUsoDTO> getCasosdeusoResponsable() {
        return casosdeusoResponsable;
    }

    /**
     * @param casosdeusoResponsable the casosdeusoResponsable to set
     */
    public void setCasosdeusoResponsable(List<CasoDeUsoDTO> casosdeusoResponsable) {
        this.casosdeusoResponsable = casosdeusoResponsable;
    }

    /**
     * @return the casosdeusoRepresentante
     */
    public List<CasoDeUsoDTO> getCasosdeusoRepresentante() {
        return casosdeusoRepresentante;
    }

    /**
     * @param casosdeusoRepresentante the casosdeusoRepresentante to set
     */
    public void setCasosdeusoRepresentante(List<CasoDeUsoDTO> casosdeusoRepresentante) {
        this.casosdeusoRepresentante = casosdeusoRepresentante;
    }
    
    
}
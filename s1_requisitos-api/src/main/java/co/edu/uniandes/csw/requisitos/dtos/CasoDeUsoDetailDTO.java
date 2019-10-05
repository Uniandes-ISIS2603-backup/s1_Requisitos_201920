/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Alejandra Escalante
 */
public class CasoDeUsoDetailDTO extends CasoDeUsoDTO implements Serializable {

    private List<ModificacionesDTO> modificaciones;
    private List<FuncionalDTO> requisitosFuncionales;
    private List<DescripcionDTO> entidades;
    private List<DescripcionDTO> caminosExcepcion;
    private List<DescripcionDTO> posCondiciones;
    private List<DescripcionDTO> preCondiciones;
    private List<DescripcionDTO> caminoAlterno;
    private List<DesarrolladorDTO> responsables;

    
      public CasoDeUsoDetailDTO() {
        super();
    }
    
    public CasoDeUsoDetailDTO(CasoDeUsoEntity entidad) {
        super(entidad);
        /*
        if (entidad!=null){
            if (entidad.getCaminosExcepcion() !=null){
                caminosExcepcion=new ArrayList<>();
                for (DescripcionEntity desc:entidad.getCaminosExcepcion()){
                    caminosExcepcion.add(new DescripcionDTO(desc));
                }
            }
            if (entidad.getFuncional()!=null){
                requisitosFuncionales=new ArrayList<>();
                for (FuncionalEntity funcional:entidad.getFuncional()){
                    requisitosFuncionales.add(new FuncionalDTO(funcional));
                }
            }
            
            if (entidad.getEntidades()!=null){
                entidades=new ArrayList<>();
                for (DescripcionEntity desc:entidad.getEntidades()){
                    entidades.add(new DescripcionDTO(desc));
                }
            }
            if (entidad.getPosCondiciones()!=null){
                posCondiciones=new ArrayList<>();
                for (DescripcionEntity desc:entidad.getPosCondiciones()){
                    posCondiciones.add(new DescripcionDTO(desc));
                }
            }
            if (entidad.getPreCondiciones()!=null){
                preCondiciones=new ArrayList<>();
                for (DescripcionEntity desc:entidad.getPreCondiciones()){
                    preCondiciones.add(new DescripcionDTO(desc));
                }
            }
            if (entidad.getPreCaminoAlterno()!=null){
                caminoAlterno=new ArrayList<>();
                for (DescripcionEntity desc:entidad.getPreCaminoAlterno()){
                    caminoAlterno.add(new DescripcionDTO(desc));
                }
            }

        }
    }
      
     
    }
    @Override
     public CasoDeUsoEntity toEntity(){
         
     }
        */
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
     * @return the requisitosFuncionales
     */
    public List<FuncionalDTO> getRequisitosFuncionales() {
        return requisitosFuncionales;
    }

    /**
     * @param requisitosFuncionales the requisitosFuncionales to set
     */
    public void setRequisitosFuncionales(List<FuncionalDTO> requisitosFuncionales) {
        this.requisitosFuncionales = requisitosFuncionales;
    }

    /**
     * @return the entidades
     */
    public List<DescripcionDTO> getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(List<DescripcionDTO> entidades) {
        this.entidades = entidades;
    }

    /**
     * @return the caminosExcepcion
     */
    public List<DescripcionDTO> getCaminosExcepcion() {
        return caminosExcepcion;
    }

    /**
     * @param caminosExcepcion the caminosExcepcion to set
     */
    public void setCaminosExcepcion(List<DescripcionDTO> caminosExcepcion) {
        this.caminosExcepcion = caminosExcepcion;
    }

    /**
     * @return the posCondiciones
     */
    public List<DescripcionDTO> getPosCondiciones() {
        return posCondiciones;
    }

    /**
     * @param posCondiciones the posCondiciones to set
     */
    public void setPosCondiciones(List<DescripcionDTO> posCondiciones) {
        this.posCondiciones = posCondiciones;
    }

    /**
     * @return the preCondiciones
     */
    public List<DescripcionDTO> getPreCondiciones() {
        return preCondiciones;
    }

    /**
     * @param preCondiciones the preCondiciones to set
     */
    public void setPreCondiciones(List<DescripcionDTO> preCondiciones) {
        this.preCondiciones = preCondiciones;
    }

    /**
     * @return the preCaminoAlterno
     */
    public List<DescripcionDTO> getCaminoAlterno() {
        return caminoAlterno;
    }

    /**
     * @param preCaminoAlterno the preCaminoAlterno to set
     */
    public void setCaminoAlterno(List<DescripcionDTO> preCaminoAlterno) {
        this.caminoAlterno = preCaminoAlterno;
    }

    /**
     * @return the desarrollador
     */
    public List<DesarrolladorDTO> getResponsable() {
        return responsables;
    }

    /**
     * @param desarrollador the desarrollador to set
     */
    public void setResponsable(List<DesarrolladorDTO> desarrollador) {
        this.responsables = desarrollador;
    }

  
     
}

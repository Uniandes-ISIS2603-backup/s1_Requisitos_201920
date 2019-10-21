/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Alejandra Escalante
 */
public class CasoDeUsoDetailDTO extends CasoDeUsoDTO implements Serializable {

    /*
    listas con las relaciones 1 a muchos que tiene caso de uso
     */
    private List<ModificacionesDTO> modificaciones = new ArrayList<>();
    private List<RequisitosDTO> funcionales = new ArrayList<>();


    /*
    constructor vacio
     */
    public CasoDeUsoDetailDTO() {
        super();
    }

    /*
      constructor que convierte de entidad a DTO
     */
    public CasoDeUsoDetailDTO(CasoDeUsoEntity entidad) {
        super(entidad);

        if (entidad != null) {

            if (entidad.getModificaciones() != null) {
                modificaciones = new ArrayList<>();
                for (ModificacionesEntity lis : entidad.getModificaciones()) {
                    modificaciones.add(new ModificacionesDTO(lis));
                }
            }
            if (entidad.getFuncionales() != null) {
                funcionales = new ArrayList<>();
                for (RequisitosEntity lis : entidad.getFuncionales()) {
                    funcionales.add(new RequisitosDTO(lis));
                }
            }

        }

    }
/*
    @Override
    public CasoDeUsoEntity toEntity() {
        CasoDeUsoEntity entity = super.toEntity();
        
    }
*/
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
     * @return the funcionales
     */
    public List<RequisitosDTO> getFuncionales() {
        return funcionales;
    }

    /**
     * @param funcionales the funcionales to set
     */
    public void setFuncionales(List<RequisitosDTO> funcionales) {
        this.funcionales = funcionales;
    }

   
}

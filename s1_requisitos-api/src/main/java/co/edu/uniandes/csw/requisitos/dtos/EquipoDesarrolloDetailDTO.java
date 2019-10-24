/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jf.rubio
 */
public class EquipoDesarrolloDetailDTO extends EquipoDesarrolloDTO{

 
    
    /**
     * Lista de DesarrolladorDTO que puede tener un equipo
     */
    private List<DesarrolladorDTO> integrantes;
    
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
            integrantes = new ArrayList<>();
            for(DesarrolladorEntity entityDes :equipoDesarrollo.getIntegrantes() )
            {
                integrantes.add(new DesarrolladorDTO(entityDes));
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
                reqsEntity.add(dtoReq.toEntity());
            }
            equipoDesarrolloEntity.setIntegrantes(reqsEntity);
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
    
}

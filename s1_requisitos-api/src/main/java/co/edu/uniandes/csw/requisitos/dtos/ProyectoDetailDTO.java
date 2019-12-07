/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rj.gonzalez10
 */
public class ProyectoDetailDTO extends ProyectoDTO {
    private List<IteracionDTO> iteraciones  = new ArrayList<>();
     /*
    constructor vacio
     */
    public ProyectoDetailDTO() {
        super();
    }
      public ProyectoDetailDTO(ProyectoEntity proyecto) {
        super(proyecto);

            if (proyecto != null && proyecto.getIteraciones() != null) {
                iteraciones = new ArrayList<>();
                for (IteracionEntity lis : proyecto.getIteraciones()) {
                    iteraciones.add(new IteracionDTO(lis));
                }
            }
        
    
    
}
       @Override
    public ProyectoEntity toEntity() {
        ProyectoEntity entity = super.toEntity();
        if (getIteraciones()!=null){
            List <IteracionEntity> iteracionesEntity= new ArrayList<>();
            for (IteracionDTO dto: getIteraciones() ){
                iteracionesEntity.add(dto.toEntity());
            }
                entity.setIteraciones(iteracionesEntity);
        }
        return entity;
    }

    /**
     * @return the iteraciones
     */
    public List<IteracionDTO> getIteraciones() {
        return iteraciones;
    }

    /**
     * @param iteraciones the iteraciones to set
     */
    public void setIteraciones(List<IteracionDTO> pIteraciones) {
        this.iteraciones = pIteraciones;
    }
}

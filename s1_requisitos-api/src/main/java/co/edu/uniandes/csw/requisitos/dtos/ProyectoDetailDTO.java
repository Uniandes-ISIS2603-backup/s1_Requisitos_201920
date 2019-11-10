/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Martinez
 */
public class ProyectoDetailDTO extends ProyectoDTO implements Serializable{
   
    /*
    listas con las relaciones 1 a muchos que tiene proyecto
     */
    private List<IteracionDTO> iteraciones = new ArrayList<>();


    /*
    constructor vacio
     */
    public ProyectoDetailDTO() {
        super();
    }

    /*
      constructor que convierte de entidad a DTO
     */
    public ProyectoDetailDTO(ProyectoEntity entidad) {
        super(entidad);

        if (entidad != null) {

            if (entidad.getIteraciones() != null) {
                iteraciones = new ArrayList<>();
                for (IteracionEntity lis : entidad.getIteraciones()) {
                    iteraciones.add(new IteracionDTO(lis));
                }
            }

        }

    }

    /**
     *  Constructor que convierte a entidad
     * @return 
     */
    @Override
    public ProyectoEntity toEntity() {
        ProyectoEntity entity = super.toEntity();
        if (iteraciones!=null){
            List <IteracionEntity> iteracionesEntity= new ArrayList<>();
            for (IteracionDTO dto: iteraciones ){
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
    public void setIteraciones(List<IteracionDTO> iteraciones) {
        this.iteraciones = iteraciones;
    }
    
}

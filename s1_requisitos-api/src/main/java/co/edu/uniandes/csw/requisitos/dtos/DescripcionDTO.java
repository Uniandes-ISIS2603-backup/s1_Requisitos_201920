/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;

/**
 *
 * @author jf.rubio
 */
public class DescripcionDTO {

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * descripcion del caso
     */
    private String descripcion;
    
    /**
     * id de la descripcion
     */
    private Long id;
    
    /**
     * metodo que convierte el DTO de la descripción a una entidad
     * @return entidad que la ingormación del DTO
     */
    public DescripcionEntity toEntity(){
        DescripcionEntity descripcion= new DescripcionEntity();
        descripcion.setDescripcion(this.getDescripcion());
        descripcion.setId(this.getId());
        return descripcion;
    }
    
    /**
     * constructor vacio de la descripcionDTO
     */
    public DescripcionDTO(){
        
    }
    
    /**
     * Constructor de descriociónDTO mediante una entidad
     * @param descripcion entidad de la descripcion de la cual se hará el DTO
     */
    public DescripcionDTO(DescripcionEntity descripcion)
    {
        setDescripcion(descripcion.getDescripcion());
        setId(descripcion.getId());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import java.io.Serializable;

/**
 *
 * @author rj.gonzalez10
 */
public class RepresentanteDelClienteDTO implements Serializable {
    
    private Long id;
    
    private String nombre;
    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
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
    public RepresentanteDelClienteEntity toEntity()
    {
        RepresentanteDelClienteEntity representante = new RepresentanteDelClienteEntity();
        representante.setNombre(getNombre());
        representante.setId(id);
        return representante;
    }
    
    public RepresentanteDelClienteDTO(RepresentanteDelClienteEntity representante)
    {
        this.setNombre(representante.getNombre());
        this.setId(representante.getId());
    }
    public RepresentanteDelClienteDTO()
    {
        
    }

   

    
}

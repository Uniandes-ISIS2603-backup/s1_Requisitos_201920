/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;

/**
 *
 * @author rj.gonzalez10
 */
public class RepresentanteDelClienteDetailDTO extends RepresentanteDelClienteDTO {
    
    public RepresentanteDelClienteDetailDTO(RepresentanteDelClienteEntity entidad)
    {
        super(entidad);
    }
     public RepresentanteDelClienteDetailDTO()
    {
        
    }
    
}

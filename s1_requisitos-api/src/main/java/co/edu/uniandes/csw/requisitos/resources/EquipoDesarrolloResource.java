/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.EquipoDesarrolloDTO;
import co.edu.uniandes.csw.requisitos.ejb.EquipoDesarrolloLogic;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Martinez
 */
@Path("/equipoDesarrollo")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoDesarrolloResource {
    
    @Inject
    EquipoDesarrolloLogic proyLogic;
    
    private static final Logger LOGGER = Logger.getLogger(EquipoDesarrolloResource.class.getName());
    
    @POST
    public EquipoDesarrolloDTO createEquipo( EquipoDesarrolloDTO equipo) throws BusinessLogicException {
        LOGGER.info("ProyectoResource createProyecto: input:" +equipo.toString());
        EquipoDesarrolloEntity equipoEntity = equipo.toEntity();
        
        EquipoDesarrolloEntity nuevoEquipoEntity = proyLogic.createEquipoDesarrollo(equipoEntity);
        
        EquipoDesarrolloDTO nuevoEquipoDTO = new EquipoDesarrolloDTO(nuevoEquipoEntity);
        LOGGER.info("ProyectoResource createProyecto: output:" +nuevoEquipoEntity.toString());
        return nuevoEquipoDTO;
    }
    
    
}

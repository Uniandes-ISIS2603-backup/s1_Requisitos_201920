/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.ProyectoDTO;
import co.edu.uniandes.csw.requisitos.ejb.ProyectoLogic;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Martinez
 */
@Path("/proyecto")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProyectoResource {
    
    @Inject
    ProyectoLogic proyLogic;
    
    private static final Logger LOGGER = Logger.getLogger(ProyectoResource.class.getName());
    
    @POST
    public ProyectoDTO createProyecto(ProyectoDTO proy) throws BusinessLogicException {
        LOGGER.info("ProyectoResource createProyecto: input:" +proy.toString());
        ProyectoEntity proyectoEntity = proy.toEntity();
        
        ProyectoEntity nuevoProyectoEntity = proyLogic.createProyecto(proyectoEntity);
        
        ProyectoDTO nuevoProyectoDTO = new ProyectoDTO(nuevoProyectoEntity);
        LOGGER.info("ProyectoResource createProyecto: output:" +nuevoProyectoEntity.toString());
        return nuevoProyectoDTO;
    }
    
}

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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
        LOGGER.info("EquipoDesarrolloResource createEquipoDesarrollo: input:" +equipo.toString());
        EquipoDesarrolloEntity equipoEntity = equipo.toEntity();
        
        EquipoDesarrolloEntity nuevoEquipoEntity = proyLogic.createEquipoDesarrollo(equipoEntity);
        
        EquipoDesarrolloDTO nuevoEquipoDTO = new EquipoDesarrolloDTO(nuevoEquipoEntity);
        LOGGER.info("EquipoDesarrolloResource createEquipoDesarrollo: output:" +nuevoEquipoEntity.toString());
        return nuevoEquipoDTO;
    }
    
    /*
     retorna una lista de equipos como EquipoDesarrolloDTO
    */
    @GET
    public List<EquipoDesarrolloDTO> getEquipos(){
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource getEquipos: input:void");
        List<EquipoDesarrolloDTO> lista= listEntity2DTO(proyLogic.getEquipos());
        return lista;
    }
    
    /*
    retorna un EquipoDesarrolloDTO dado un id
    */
    @GET 
    @Path("{equipoId:\\d+}")
    public EquipoDesarrolloDTO getEquipo(@PathParam("equipoId") Long id){
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource getEquipo: input:{0}", id);
        EquipoDesarrolloEntity a= proyLogic.getEquipo(id);
        if (a==null){
            throw new WebApplicationException("El recurso /equipoDesarrollo/" + id + " no existe.", 404);
        }
        EquipoDesarrolloDTO dto= new EquipoDesarrolloDTO(a);
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource getEquipo: output:{0}", dto);
        return dto;
        
    }
    
    /*
    actualiza el equipo DTO
    */
    @PUT
    @Path("{equipoId: \\d+}")
    public EquipoDesarrolloDTO updateEquipo(@PathParam("equipoId") Long modId, EquipoDesarrolloDTO mod)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource updateEquipo: input: id: {0} , caso: {1}", new Object[]{modId, mod});
        mod.setId(modId);
        if (proyLogic.getEquipo(modId)==null){
            throw new WebApplicationException("El recurso /equipoDesarrollo/" + modId + " no existe.", 404);
        }
        EquipoDesarrolloDTO nuevo= new EquipoDesarrolloDTO(proyLogic.updateEquipo(mod.toEntity()));
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource updateEquipo: output:{0}", nuevo);
        return nuevo;
        
    }
    
    /*
    borra una modificacionDTO dado un id
    */
    @Path("{proyectoId: \\d+}")
    @DELETE
    public void deleteEquipo(@PathParam ("equipoId") Long modId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource deleteEquipo: input: {0}", modId);
        EquipoDesarrolloEntity nuevo = proyLogic.getEquipo(modId);
        if (nuevo == null) {
            throw new WebApplicationException("El recurso /equipo/" + modId + " no existe.", 404);
        }
        
        LOGGER.info("EquipoDesarrolloResource deleteEquipo: output: void");
        proyLogic.deleteEquipo(modId);
        
    }
    
    private List<EquipoDesarrolloDTO> listEntity2DTO(List<EquipoDesarrolloEntity> entityList) {
        List<EquipoDesarrolloDTO> list = new ArrayList<>();
        for (EquipoDesarrolloEntity entity : entityList) {
            list.add(new EquipoDesarrolloDTO(entity));
        }
        return list;
    }
    
}

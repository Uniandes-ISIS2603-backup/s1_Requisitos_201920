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
       
        ProyectoEntity proyectoEntity = proy.toEntity();
        
        ProyectoEntity nuevoProyectoEntity = proyLogic.createProyecto(proyectoEntity);
        
        ProyectoDTO nuevoProyectoDTO = new ProyectoDTO(nuevoProyectoEntity);
        
        return nuevoProyectoDTO;
    }
    /*
     retorna una lista de modificaciones como ModificacionesDTO
    */
    @GET
    public List<ProyectoDTO> getProyectos(){
        LOGGER.log(Level.INFO, "ProyectoResource getProyectos: input:void");
        return listEntity2DTO(proyLogic.getProyectos());
    }
    
    /*
    retorna una modificacionDTO dado un id
    */
    @GET 
    @Path("{proyectoId:\\d+}")
    public ProyectoDTO getProyecto(@PathParam("proyectoId") Long id){
        LOGGER.log(Level.INFO, "ModificacionesResource getModificacion: input:{0}", id);
        ProyectoEntity a= proyLogic.getProyecto(id);
        if (a==null){
            throw new WebApplicationException("El resource /proyecto/" + id + " no se encuentra.", 404);
        }
        ProyectoDTO dto= new ProyectoDTO(a);
        LOGGER.log(Level.INFO, "ProyectoResource getProyecto: output:{0}", dto);
        return dto;
        
    }
    
    /*
    actualiza la modificacion DTO
    */
    @PUT
    @Path("{proyectoId: \\d+}")
    public ProyectoDTO updateProyecto(@PathParam("proyectoId") Long modId, ProyectoDTO mod)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ProyectoResource updateProyecto: input: id: {0} , caso: {1}", new Object[]{modId, mod});
        mod.setId(modId);
        if (proyLogic.getProyecto(modId)==null){
            throw new WebApplicationException("Recurso /proyecto/" + modId + " no existe.", 404);
        }
        ProyectoDTO nuevo= new ProyectoDTO(proyLogic.updateProyecto(mod.toEntity()));
        LOGGER.log(Level.INFO, "ProyectoResource updateProyecto: output:{0}", nuevo);
        return nuevo;
        
    }
    
    /*
    borra una modificacionDTO dado un id
    */
    @Path("{proyectoId: \\d+}")
    @DELETE
    public void deleteProyecto(@PathParam ("proyectoId") Long modId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ProyectoResource deleteProyecto: input: {0}", modId);
        ProyectoEntity nuevo = proyLogic.getProyecto(modId);
        if (nuevo == null) {
            throw new WebApplicationException("El recurso /proyecto/" + modId + " no esta presente.", 404);
        }
        
        LOGGER.info("ProyectoResource deleteProyecto: output: void");
        proyLogic.deleteProyecto(modId);
        
    }
    
    private List<ProyectoDTO> listEntity2DTO(List<ProyectoEntity> entityList) {
        List<ProyectoDTO> list = new ArrayList<>();
        for (ProyectoEntity entity : entityList) {
            list.add(new ProyectoDTO(entity));
        }
        return list;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.ModificacionesDTO;
import co.edu.uniandes.csw.requisitos.ejb.ModificacionesLogic;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
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
 * @author Maria Alejandra Escalante
 */
//declaraciones necesarios para la conversion a json y para las pruebas de postman
@Path("modificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModificacionesResource {
    
        private static final Logger LOGGER = Logger.getLogger(ModificacionesResource.class.getName());
        //String ruta
         private static final String RUTA ="El recurso /modificaciones/";
        //String no existe
        private static final String NOEXISTE=" no existe.";
        @Inject
        private ModificacionesLogic logica;
        /**
     * Crea una modificacion con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param mod
     * @return JSON {@link EditorialDTO} - La requisito guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la requisito.
     */
    @POST
    public ModificacionesDTO createModificacion(ModificacionesDTO mod) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ModificacionesResource createModificacioens:input:{0}",mod );
        ModificacionesEntity nuevo=mod.toEntity();
        nuevo= logica.createModificaciones(nuevo);
        ModificacionesDTO nuevo2=new ModificacionesDTO(nuevo);
        LOGGER.log (Level.INFO, "ModificacionesResource createModificaciones:output:{0}", nuevo2);
        return nuevo2;
    }
    
    /*
    retorna una lista de modificaciones como ModificacionesDTO
    */
    @GET
    public List<ModificacionesDTO> getModificaciones(){
        LOGGER.log(Level.INFO, "ModificacionesResource getModificaciones: input:void");
        return listEntity2DTO(logica.getModificaciones());
        
    }
    
    /*
    retorna una modificacionDTO dado un id
    */
    @GET 
    @Path("{modificacionesId:\\d+}")
    public ModificacionesDTO getModificacion(@PathParam("modificacionesId") Long id){
        LOGGER.log(Level.INFO, "ModificacionesResource getModificacion: input:{0}", id);
        ModificacionesEntity a= logica.getModificacion(id);
        if (a==null){
            throw new WebApplicationException(RUTA + id + NOEXISTE, 404);
        }
        ModificacionesDTO dto= new ModificacionesDTO(a);
        LOGGER.log(Level.INFO, "ModificacionesResource getModificacion: output:{0}", dto);
        return dto;
        
    }
    
    /*
    actualiza la modificacion DTO
    */
    @PUT
    @Path("{modificacionesId: \\d+}")
    public ModificacionesDTO updateModificaciones(@PathParam("modificacionesId") Long modId, ModificacionesDTO mod)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ModificacioensResource updateModificaciones: input: id: {0} , caso: {1}", new Object[]{modId, mod});
        mod.setId(modId);
        if (logica.getModificacion(modId)==null){
            throw new WebApplicationException("El resource /modificaciones/" + modId + " no se encuentra, no existe.", 404);
        }
        ModificacionesDTO nuevo= new ModificacionesDTO(logica.updateModificaciones(mod.toEntity()));
        LOGGER.log(Level.INFO, "ModificacionesResource updateModificaciones: output:{0}", nuevo);
        return nuevo;
        
    }
    
    /*
    borra una modificacionDTO dado un id
    */
    @Path("{modificacionesId: \\d+}")
    @DELETE
    public void deleteModificacion(@PathParam ("modificacionesId") Long modId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ModificacionesResource deleteModificacion: input: {0}", modId);
        ModificacionesEntity nuevo = logica.getModificacion(modId);
        if (nuevo == null) {
            throw new WebApplicationException("Recurso /modificaciones/" + modId + "  no existe.", 404);
        }
        
        LOGGER.info("ModificacionesResource deleteModificacion: output: void");
        logica.deleteModificaciones(modId);
        
    }
                
                
    private List<ModificacionesDTO> listEntity2DTO(List<ModificacionesEntity> entityList) 
    {
        LOGGER.info("ModificacionesResource listEntity2DTO: input: void");
        List<ModificacionesDTO> list = new ArrayList<>();
        for (ModificacionesEntity entity : entityList) {
            list.add(new ModificacionesDTO(entity));
        }
        LOGGER.info("ModificacionesResource listEntity2DTO: output: void");
        return list;
    }
    
    /**
     * Conexión con el servicio casos de uso a un desarrollador
     * {@link PrizeAuthorResource}
     *
     * Este método conecta la ruta de /prizes con las rutas de /author que
     * dependen del premio, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga del autor del premio.
     *
     * @param modificacionesId El ID de la editorial con respecto a la cual se accede al
     * servicio.
     * @return El servicio de autor para este premio en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que s genera cuando no se el premio.
     */
    @Path("{modificacionesId: \\d+}/casos")
    public Class<ModificacionesCasoDeUsoResource> getModificacionesCasoDeUsoResource(@PathParam("modificacionesId") Long modificacionesId) {
    
        if (logica.getModificacion(modificacionesId) == null) {
            throw new WebApplicationException(RUTA + modificacionesId + NOEXISTE, 404);
        }
        return ModificacionesCasoDeUsoResource.class;
    }
    /**
     * @param modificacionesId El ID de la  con respecto a la cual se accede al
     * servicio.
     * @return El servicio de desarrollador para esta modificacion en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la modificacion.
     */
    @Path("{modificacionesId: \\d+}/desarrolladorb")
    public Class<ModificacionesDesarrolladorResource> getModificacionesDesarrolladorResource(@PathParam("modificacionesId") Long modificacionesId) {
    
        if (logica.getModificacion(modificacionesId)== null) {
            throw new WebApplicationException(RUTA + modificacionesId + NOEXISTE, 404);
        }
        return ModificacionesDesarrolladorResource.class;
    }
  
}
    

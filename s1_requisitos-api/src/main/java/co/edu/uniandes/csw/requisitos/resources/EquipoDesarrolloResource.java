/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.EquipoDesarrolloDTO;
import co.edu.uniandes.csw.requisitos.dtos.EquipoDesarrolloDetailDTO;
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
 * corregido por: Juan rubio
 */
@Path("/equipoDesarrollo")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoDesarrolloResource {
    
    /**
     * Atributo lógica
     */
    @Inject
    EquipoDesarrolloLogic proyLogic;
    
    private static final Logger LOGGER = Logger.getLogger(EquipoDesarrolloResource.class.getName());
    
      /**
     * Crea una nuevo equipo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param equipo {@link DesarrolladorDTO} - La iteracion que se desea guardar.
     * @return JSON {@link DesarrolladorDTO} El desarrollador guardado con el atributo
     * id autogenerado.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando no existe el tipo del desarrollador.
     */
    @POST
    public EquipoDesarrolloDTO createEquipo( EquipoDesarrolloDTO equipo) throws BusinessLogicException {
        LOGGER.info("EquipoDesarrolloResource createEquipoDesarrollo: input:" +equipo.toString());
       EquipoDesarrolloDTO nuevoEquipoDTO = new EquipoDesarrolloDTO(proyLogic.createEquipoDesarrollo(equipo.toEntity()));
        LOGGER.info("EquipoDesarrolloResource createEquipoDesarrollo: output:" +equipo.toString());
        return nuevoEquipoDTO;
    }
    
    /**
     * Busca y devuelve todos los equipo que existen en la aplicacion.
     *
     * @return JSONArray {@link EquipoDesarrolladorDetailDTO} - Los equipos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EquipoDesarrolloDetailDTO> getEquipos(){
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource getEquipos: input:void");
        List<EquipoDesarrolloDetailDTO> lista= listEntity2DetailDTO(proyLogic.getEquipos());
        return lista;
    }
    
    /**
     * Busca el equipo con el id asociado recibido en la URL y lo devuelve.
     * @param equipoId Identificador del equipo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DesarrolaldorDetailDTO} - el desarrollador
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el desarrollador.
     */
    @GET 
    @Path("{equipoId:\\d+}")
    public EquipoDesarrolloDetailDTO getEquipo(@PathParam("equipoId") Long equipoId){
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource getEquipo: input:{0}", equipoId);
        EquipoDesarrolloEntity equipoDesarrolloEntity= proyLogic.getEquipo(equipoId);
        if (equipoDesarrolloEntity==null){
            throw new WebApplicationException("El recurso /equipoDesarrollo/" + equipoId + " no existe.", 404);
        }
        EquipoDesarrolloDetailDTO dto= new EquipoDesarrolloDetailDTO(equipoDesarrolloEntity);
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource getEquipo: output:{0}", dto);
        return dto;
        
    }
    
      /**
     * Actualiza  con la información que se recibe en el cuerpo de la petición,
     * el desarrollador con el id recibido en la URL.
     *
     * @param equipoDesarrolloId Identificador del desarrollador que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param equipo {@link desarrolladorDTO} El desarrollador que se desea guardar.
     * @return JSON {@link desarrolladorDetailDTO} - el desarrollador guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el desarrollador a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar rl desarrollador.
     */
    @PUT
    @Path("{equipoId: \\d+}")
    public EquipoDesarrolloDTO updateEquipo(@PathParam("equipoId") Long equipoDesarrolloId, EquipoDesarrolloDTO equipo)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource updateEquipo: input: id: {0} , caso: {1}", new Object[]{equipoDesarrolloId, equipo});
        equipo.setId(equipoDesarrolloId);
        if (proyLogic.getEquipo(equipoDesarrolloId)==null){
            throw new WebApplicationException("El recurso /equipoDesarrollo/" + equipoDesarrolloId + " no existe.", 404);
        }
        EquipoDesarrolloDTO nuevo= new EquipoDesarrolloDTO(proyLogic.updateEquipo(equipo.toEntity()));
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource updateEquipo: output:{0}", nuevo);
        return nuevo;
        
    }
    
   /**
     * Borra el equipo con el id asociado recibido en la URL.
     *
     * @param equipoId Identificador del desarrollador que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el desarrollador.
     */
    @Path("{proyectoId: \\d+}")
    @DELETE
    public void deleteEquipo(@PathParam ("equipoId") Long equipoId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "EquipoDesarrolloResource deleteEquipo: input: {0}", equipoId);
        EquipoDesarrolloEntity nuevo = proyLogic.getEquipo(equipoId);
        if (nuevo == null) {
            throw new WebApplicationException("El recurso /equipo/" + equipoId + " no existe.", 404);
        }
        
        LOGGER.info("EquipoDesarrolloResource deleteEquipo: output: void");
        proyLogic.deleteEquipo(equipoId);
        
    }
    
       /**
     * Convierte una lista de entidades a DetailDTO.
     *
     * Este método convierte una lista de objetos DesarrolladorEntity a una lista de
     * objetos DesarrolladorDetailDTO (json)
     * @param entityList corresponde a la lista de desarrolladores de tipo Entity que
     * vamos a convertir a DetailDTO.
     * @return la lista de Desarrollador en forma DetailDTO (json)
     */
    private List<EquipoDesarrolloDetailDTO> listEntity2DetailDTO(List<EquipoDesarrolloEntity> entityList) {
        
        List<EquipoDesarrolloDetailDTO> list = new ArrayList<>();
        for (EquipoDesarrolloEntity entity : entityList) {
            list.add(new EquipoDesarrolloDetailDTO(entity));
        }
        return list;
    }
    
}

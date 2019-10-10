/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DescripcionDTO;
import co.edu.uniandes.csw.requisitos.ejb.DescripcionLogic;
import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
 * @author jf.rubio
 */
@Path("/descripcion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DescripcionResource {
    
    /**
     * Atributo logica de descripcion
     */
    @Inject
    private DescripcionLogic dl;
    
         private static final Logger LOGGER = Logger.getLogger(DescripcionResource.class.getName());

     /**
      * Crea una nueva descripcion con la información que se recibe en el cuerpo de la
      * petición y se regresa un objeto idéntico con un id auto-generado por la
      * base de datos.
      * 
      * @param descripcion (@link DescripcionDTO) - La descripcion que se desea guardar.
      * @return JSON (@link DescripcionDTO) - La iteracion guardada con el atributo id
      * autogenerado.
      * @throws BusinessLogicException  (@link BusinessLogicExceptionMapper) -
      * Error de la lógica que se genera cuando ya existe la iteración.
      */
      @POST
    public DescripcionDTO createDescripcion(DescripcionDTO descripcion) throws BusinessLogicException     
    {
        LOGGER.log(Level.INFO, "DescripcionResource createDescripcion: input: {0}");
        DescripcionDTO nuevaDescripcionDTO = new DescripcionDTO(dl.createDescripcion(descripcion.toEntity()));
        LOGGER.log(Level.INFO, "DescripcionResource createDescripcion: output: {0}");
        return nuevaDescripcionDTO;
    }
    
     /**
     * Busca y devuelve todas las Descripcions que existen en la aplicacion.
     * 
     * @return JSONArray (@link DescripcionDTO) -Las descripcions encontradas en la 
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<DescripcionDTO> getDescripcions() {
        LOGGER.info("DescripcionResource getDescripcions: input: void");
        List<DescripcionDTO> listaDescripcions = listEntity2DTO(dl.getDescripcions());
        LOGGER.log(Level.INFO, "DescripcionResource getDescripcions: output: {0}", listaDescripcions);
        
        return listaDescripcions;
    }
    /**
     * Busca la descripcion con el id asociado recibido en la URL y lo devuelve.
     *
     * @param descripcionId Identificador de la descripcion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DescripcionDTO} - la descripcion
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la descripcion.
     */
    @GET
    @Path("{descripcionId: \\d+}")
    public DescripcionDTO getDescripcion(@PathParam("descripcionId") Long descripcionId) {
        LOGGER.log(Level.INFO, "DescripcionResource getDescripcion: input: {0}", descripcionId);
        DescripcionEntity descripcionEntity = dl.getDescripcion(descripcionId);
        if (descripcionEntity == null) {
            throw new WebApplicationException("El recurso /descripcion/" + descripcionId + " no existe.", 404);
        }
        DescripcionDTO descripcionDTO = new DescripcionDTO(descripcionEntity);
        LOGGER.log(Level.INFO, "DescripcionResource getDescripcion: output: {0}", descripcionDTO);
        return descripcionDTO;
    }

    /**
     * Actualiza la descripcion con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param descripcionId Identificador de la descripcion que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param descripcion {@link descripcionDTO} La descripcion que se desea guardar.
     * @return JSON {@link descripcionDTO} - la descripcion guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la descripcion a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la descripcion.
     */
    @PUT
    @Path("{descripcionId: \\d+}")
    public DescripcionDTO updateDescripcion(@PathParam("descripcionId") Long descripcionId, DescripcionDTO descripcion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DescripcionResource updateDescripcion: input: id: {0} , book: {1}", new Object[]{descripcionId, descripcion});
        descripcion.setId(descripcionId);
        if (dl.getDescripcion(descripcionId) == null) {
            throw new WebApplicationException("El recurso /descripcion/" + descripcionId + " no existe.", 404);
        }
        DescripcionDTO detailDTO = new DescripcionDTO(dl.updateDescripcion( descripcion.toEntity()));
        LOGGER.log(Level.INFO, "DescripcionResource updateDescripcion: output: {0}", detailDTO);
        return detailDTO;
    }

       /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos DescripcionEntity a una lista de
     * objetos DescripcionDTO (json)
     *
     * @param entityList corresponde a la lista de iteraciones de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Descripciones en forma DTO (json)
     */
    private List<DescripcionDTO> listEntity2DTO(List<DescripcionEntity> entityList) {
        List<DescripcionDTO> list = new ArrayList<>();
        for (DescripcionEntity entity : entityList) {
            list.add(new DescripcionDTO(entity));
        }
        return list;
    }
    
       /**
     * Borra la descripcion con el id asociado recibido en la URL.
     * @param descripcionId
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     */
    @DELETE
    @Path("{descripcionId: \\d+}")
    public void deleteDescripcion(@PathParam("descripcionId") Long descripcionId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "DescripcionResource deleteDescripcion: input: {0}", descripcionId);
        DescripcionEntity entity = dl.getDescripcion(descripcionId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /descripcion/" + descripcionId + " no existe.", 404);
        }
        dl.deleteDescripcion(descripcionId);
        LOGGER.info("DescripcionResource deleteDescripcion output: void");
    } 
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.IteracionDTO;
import co.edu.uniandes.csw.requisitos.dtos.IteracionDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.IteracionLogic;
import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
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
 * @author rj.gonzalez10
 */
@Path("iteracion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class IteracionResource {

    private static final Logger LOGGER = Logger.getLogger(IteracionResource.class.getName());

    @Inject
    private IteracionLogic iteracionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    
    /**
     * Crea una nueva iteracion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param iteracion {@link IteracionDTO} - La iteracion que se desea guardar.
     * @return JSON {@link IteracionDTO} - La iteracion guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la iteracion.
     */
    @POST
    public IteracionDTO createIteracion(IteracionDTO iteracion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IteracionResource createIteracion: input: {0}", iteracion);
        IteracionDTO nuevaIteracionDTO = new IteracionDTO(iteracionLogic.createIteracion(iteracion.toEntity()));
        LOGGER.log(Level.INFO, "IteracionResource createIteracion: output: {0}", nuevaIteracionDTO);
        return nuevaIteracionDTO;
    }

    /**
     * Busca y devuelve todas las Iteraciones que existen en la aplicacion.
     *
     * @return JSONArray {@link IteracionDetailDTO} - Las iteraciones encontrados en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<IteracionDetailDTO> getIteraciones() {
        LOGGER.info("IteracionResource getIteraciones: input: void");
        List<IteracionDetailDTO> listaIteraciones = listEntity2DetailDTO(iteracionLogic.getIteraciones());
        LOGGER.log(Level.INFO, "IteracionResource getIteraciones: output: {0}", listaIteraciones);
        return listaIteraciones;
    }

    /**
     * Busca la iteracion con el id asociado recibido en la URL y lo devuelve.
     *
     * @param iteracionId Identificador de la iteracion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link IteracionDetailDTO} - la iteracion
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la iteracion.
     */
    @GET
    @Path("{iteracionId: \\d+}")
    public IteracionDetailDTO getIteracion(@PathParam("iteracionId") Long iteracionId) {
        LOGGER.log(Level.INFO, "IteracionResource getIteracion: input: {0}", iteracionId);
        IteracionEntity iteracionEntity = iteracionLogic.getIteracion(iteracionId);
        if (iteracionEntity == null) {
            throw new WebApplicationException("El recurso /iteracion/" + iteracionId + " no existe.", 404);
        }
        IteracionDetailDTO iteracionDetailDTO = new IteracionDetailDTO(iteracionEntity);
        LOGGER.log(Level.INFO, "IteracionResource getIteracion: output: {0}", iteracionDetailDTO);
        return iteracionDetailDTO;
    }

    /**
     * Actualiza la iteracion con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param iteracionId Identificador de la iteracion que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param iteracion {@link iteracionDTO} La iteracion que se desea guardar.
     * @return JSON {@link iteracionDetailDTO} - la iteracion guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la iteracion a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la iteracion.
     */
    @PUT
    @Path("{iteracionId: \\d+}")
    public IteracionDetailDTO updateIteracion(@PathParam("iteracionId") Long iteracionId, IteracionDetailDTO iteracion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IteracionResource updateIteracion: input: id: {0} , book: {1}", new Object[]{iteracionId, iteracion});
        iteracion.setId(iteracionId);
        if (iteracionLogic.getIteracion(iteracionId) == null) {
            throw new WebApplicationException("El recurso /iteracion/" + iteracionId + " no existe.", 404);
        }
        IteracionDetailDTO detailDTO = new IteracionDetailDTO(iteracionLogic.updateIteracion( iteracion.toEntity()));
        LOGGER.log(Level.INFO, "IteracionResource updateIteracion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el libro con el id asociado recibido en la URL.
     *
     * @param iteracionId Identificador de la iteracion que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{iteracionId: \\d+}")
    public void deleteIteracion(@PathParam("iteracionId") Long iteracionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IteracionResource deleteIteracion: input: {0}", iteracionId);
        IteracionEntity entity = iteracionLogic.getIteracion(iteracionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /iteracion/" + iteracionId + " no existe.", 404);
        }
        //TODO recordar que cuando se terminen las asociaciones borrarlas antes de hacer el delete
        iteracionLogic.deleteRequisito(iteracionId);
        LOGGER.info("IteracionResource deleteIteracion: output: void");
    }



    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos IteracionEntity a una lista de
     * objetos IteracionDetailDTO (json)
     *
     * @param entityList corresponde a la lista de iteraciones de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Iteraciones en forma DTO (json)
     */
    private List<IteracionDetailDTO> listEntity2DetailDTO(List<IteracionEntity> entityList) {
        List<IteracionDetailDTO> list = new ArrayList<>();
        for (IteracionEntity entity : entityList) {
            list.add(new IteracionDetailDTO(entity));
        }
        return list;
    }
}

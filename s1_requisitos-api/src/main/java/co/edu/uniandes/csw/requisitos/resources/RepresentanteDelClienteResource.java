/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.RepresentanteDelClienteDTO;
import co.edu.uniandes.csw.requisitos.dtos.RepresentanteDelClienteDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.RepresentanteDelClienteLogic;
import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
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
@Path("representanteDelCliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RepresentanteDelClienteResource {
    private static final Logger LOGGER = Logger.getLogger(RepresentanteDelClienteResource.class.getName());

    @Inject
    private RepresentanteDelClienteLogic representanteDelClienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    
    /**
     * Crea una nueva representanteDelCliente con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param representanteDelCliente {@link RepresentanteDelClienteDTO} - La representanteDelCliente que se desea guardar.
     * @return JSON {@link RepresentanteDelClienteDTO} - La representanteDelCliente guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la representanteDelCliente.
     */
    @POST
    public RepresentanteDelClienteDTO createRepresentanteDelCliente(RepresentanteDelClienteDTO representanteDelCliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource createRepresentanteDelCliente: input: {0}", representanteDelCliente);
        RepresentanteDelClienteDTO nuevaRepresentanteDelClienteDTO = new RepresentanteDelClienteDTO(representanteDelClienteLogic.createRepresentanteDelCliente(representanteDelCliente.toEntity()));
        LOGGER.log(Level.INFO, "RepresentanteDelClenteResource createRepresentanteDelCliente: output: {0}", nuevaRepresentanteDelClienteDTO);
        return nuevaRepresentanteDelClienteDTO;
    }

    /**
     * Busca y devuelve todos los representantesDelCliente que existen en la aplicacion.
     *
     * @return JSONArray {@link RepresentanteDelClienteDetailDTO} - Los representanteDelCliente encontrados en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<RepresentanteDelClienteDetailDTO> getRepresentantesDelCliente() {
        LOGGER.info("RepresentanteDelClienteResource getRepresentantesDelCliente: input: void");
        List<RepresentanteDelClienteDetailDTO> listaRepresentantes = listEntity2DetailDTO(representanteDelClienteLogic.getRepresentantes());
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource getRepresentantesDelCliente: output: {0}", listaRepresentantes);
        return listaRepresentantes;
    }

    /**
     * Busca la representanteDelCliente con el id asociado recibido en la URL y lo devuelve.
     *
     * @param representanteDelClienteId Identificador de la representanteDelCliente que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link RepresentanteDelClienteDetailDTO} - la representanteDelCliente
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la representanteDelCliente.
     */
    @GET
    @Path("{representanteDelClienteId: \\d+}")
    public RepresentanteDelClienteDetailDTO getRepresentanteDelCliente(@PathParam("representanteDelClienteId") Long representanteDelClienteId) {
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource getRepresentanteDelCliente: input: {0}", representanteDelClienteId);
        RepresentanteDelClienteEntity representanteDelClienteEntity = representanteDelClienteLogic.getRepresentante(representanteDelClienteId);
        if (representanteDelClienteEntity == null) {
            throw new WebApplicationException("El recurso /representanteDelCliente/" + representanteDelClienteId + " no existe.", 404);
        }
        RepresentanteDelClienteDetailDTO representanteDelClienteDetailDTO = new RepresentanteDelClienteDetailDTO(representanteDelClienteEntity);
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource getIteracion: output: {0}", representanteDelClienteDetailDTO);
        return representanteDelClienteDetailDTO;
    }

    /**
     * Actualiza la representanteDelCliente con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param representanteDelClienteId Identificador de la representanteDelCliente que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param representanteDelCliente {@link representanteDelClienteDTO} La representanteDelCliente que se desea guardar.
     * @return JSON {@link representanteDelClienteDetailDTO} - la representanteDelCliente guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la representanteDelCliente a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la representanteDelCliente.
     */
    @PUT
    @Path("{representanteDelClienteId: \\d+}")
    public RepresentanteDelClienteDetailDTO updateRepresentanteDelCliente(@PathParam("representanteDelClienteId") Long representanteDelClienteId, RepresentanteDelClienteDetailDTO representanteDelCliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource updateRepresentanteDelCliente: input: id: {0} , book: {1}", new Object[]{representanteDelClienteId, representanteDelCliente});
        representanteDelCliente.setId(representanteDelClienteId);
        if (representanteDelClienteLogic.getRepresentante(representanteDelClienteId) == null) {
            throw new WebApplicationException("El recurso /representanteDelCliente/" + representanteDelClienteId + " no existe.", 404);
        }
        RepresentanteDelClienteDetailDTO detailDTO = new RepresentanteDelClienteDetailDTO(representanteDelClienteLogic.updateRepresentante(representanteDelCliente.toEntity()));
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource updateRepresentanteDelCliente: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el libro con el id asociado recibido en la URL.
     *
     * @param representanteDelClienteId Identificador de la representanteDelCliente que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{representanteDelClienteId: \\d+}")
    public void deleteRepresentanteDelCliente(@PathParam("representanteDelClienteId") Long representanteDelClienteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RepresentanteDelClienteResource deleteRepresentanteDelCliente: input: {0}", representanteDelClienteId);
        RepresentanteDelClienteEntity entity = representanteDelClienteLogic.getRepresentante(representanteDelClienteId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /representanteDelCliente/" + representanteDelClienteId + " no existe.", 404);
        }
        //TODO recordar que cuando se terminen las asociaciones borrarlas antes de hacer el delete
        representanteDelClienteLogic.deleteRequisito(representanteDelClienteId);
        LOGGER.info("RepresentanteDelClienteResource deleteIteracion: output: void");
    }



    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos RepresentanteDelClienteEntity a una lista de
     * objetos RepresentanteDelClienteDetailDTO (json)
     *
     * @param entityList corresponde a la lista de representanteDelClientees de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Iteraciones en forma DTO (json)
     */
    private List<RepresentanteDelClienteDetailDTO> listEntity2DetailDTO(List<RepresentanteDelClienteEntity> entityList) {
        List<RepresentanteDelClienteDetailDTO> list = new ArrayList<>();
        for (RepresentanteDelClienteEntity entity : entityList) {
            list.add(new RepresentanteDelClienteDetailDTO(entity));
        }
        return list;
    }
    
}

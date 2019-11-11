/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDTO;
import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.ejb.ModificacionesDesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "requisito/{id}/desarrollador".
 * @author Nicole Bahamon Martìnez
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModificacionesDesarrolladorResource {


    private static final Logger LOGGER = Logger.getLogger(ModificacionesDesarrolladorResource.class.getName());

    @Inject
    private ModificacionesDesarrolladorLogic modificacionesDesarrolladorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private DesarrolladorLogic desarrolladorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un desarrollador dentro de una modificaciòn con la informacion que recibe el la
     * URL.
     *
     * @param modificacionesId Identificador de la modificacion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param desarrolladorId Identificador del desarrollador que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DesarrolladorDTO} - El desarrollador guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDTO addAuthor(@PathParam("modificacionesId") Long modificacionesId, @PathParam("desarrolladorId") Long desarrolladorId) {
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource addAuthor: input: modificacionesId: {0} , desarrolladorId: {1}", new Object[]{modificacionesId, desarrolladorId});
        if (desarrolladorLogic.getDesarrollador(desarrolladorId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desarrolladorId + " no existe.", 404);
        }
        DesarrolladorDTO authorDTO = new DesarrolladorDTO(modificacionesDesarrolladorLogic.addAuthor( modificacionesId,desarrolladorId));
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource addAuthor: output: {0}", authorDTO);
        return authorDTO;
    }

    /**
     * Busca el desarrollador dentro de la modificacion con id asociado.
     *
     * @param modificacionesId Identificador de la modificacion que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link DesarrolladorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public DesarrolladorDetailDTO getAuthor(@PathParam("modificacionesId") Long modificacionesId){
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource getAuthor: input: {0}", modificacionesId);
        DesarrolladorEntity authorEntity = modificacionesDesarrolladorLogic.getDesarrollador(modificacionesId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /modificaciones/" + modificacionesId + "/desarrollador no existe.", 404);
        }
        DesarrolladorDetailDTO authorDetailDTO = new DesarrolladorDetailDTO(authorEntity);
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource getAuthor: output: {0}", authorDetailDTO);
        return authorDetailDTO;
    }

    /**
     * Remplaza la instancia de desarrollador asociada a una instancia de requisito
     *
     * @param modificacionesId Identificador de la modificacion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param desarrolladorId Identificador de el desarrollador que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDetailDTO replaceAuthor(@PathParam("modificacionesId") Long modificacionesId, @PathParam("desarrolladorId") Long desarrolladorId) {
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource replaceAuthor: input: modificacionesId: {0} , desarrolladorId: {1}", new Object[]{modificacionesId, desarrolladorId});
        if (desarrolladorLogic.getDesarrollador(desarrolladorId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desarrolladorId + " no existe.", 404);
        }
        DesarrolladorDetailDTO authorDetailDTO = new DesarrolladorDetailDTO(modificacionesDesarrolladorLogic.replaceAuthor(modificacionesId, desarrolladorId));
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource replaceAuthor: output: {0}", authorDetailDTO);
        return authorDetailDTO;
    }

    /**
     * Elimina la conexión entre el desarrollador y la modificacion recibido en la URL.
     *
     * @param modificacionesId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @DELETE
    public void removeAuthor(@PathParam("modificacionesId") Long modificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModificacionesDesarrolladorResource removeAuthor: input: {0}", modificacionesId);
        modificacionesDesarrolladorLogic.removeDesarrollador(modificacionesId);
        LOGGER.info("ModificacionesDesarrolladorResource removeAuthor: output: void");
    }
}

    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDTO;
import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.ejb.RequisitosDesarrolladorLogic;
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
public class RequisitosDesarrolladorResource {


    private static final Logger LOGGER = Logger.getLogger(RequisitosDesarrolladorResource.class.getName());

    @Inject
    private RequisitosDesarrolladorLogic requisitosDesarrolladorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private DesarrolladorLogic desarrolladorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un desarrollador dentro de un requisito con la informacion que recibe el la
     * URL.
     *
     * @param requisitosId Identificador del requisito que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param desarrolladorId Identificador del desarrollador que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DesarrolladorDTO} - El desarrollador guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDTO addAuthor(@PathParam("requisitosId") Long requisitosId, @PathParam("desarrolladorId") Long desarrolladorId) {
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource addAuthor: input: requisitosId: {0} , desarrolladorId: {1}", new Object[]{requisitosId, desarrolladorId});
        if (desarrolladorLogic.getDesarrollador(desarrolladorId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desarrolladorId + " no existe.", 404);
        }
        DesarrolladorDTO authorDTO = new DesarrolladorDTO(requisitosDesarrolladorLogic.addAuthor( requisitosId,desarrolladorId));
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource addAuthor: output: {0}", authorDTO);
        return authorDTO;
    }

    /**
     * Busca el desarrollador dentro de el requisito con id asociado.
     *
     * @param requisitosId Identificador de el requisito que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link DesarrolladorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public DesarrolladorDetailDTO getAuthor(@PathParam("requisitosId") Long requisitosId){
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource getAuthor: input: {0}", requisitosId);
        DesarrolladorEntity authorEntity = requisitosDesarrolladorLogic.getDesarrollador(requisitosId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /requisitos/" + requisitosId + "/desarrollador no existe.", 404);
        }
        DesarrolladorDetailDTO authorDetailDTO = new DesarrolladorDetailDTO(authorEntity);
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource getAuthor: output: {0}", authorDetailDTO);
        return authorDetailDTO;
    }

    /**
     * Remplaza la instancia de desarrollador asociada a una instancia de requisito
     *
     * @param requisitosId Identificador de el requisito que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param desarrolladorId Identificador de el desarrollador que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDetailDTO replaceAuthor(@PathParam("requisitosId") Long requisitosId, @PathParam("desarrolladorId") Long desarrolladorId) {
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource replaceAuthor: input: requisitosId: {0} , desarrolladorId: {1}", new Object[]{requisitosId, desarrolladorId});
        if (desarrolladorLogic.getDesarrollador(desarrolladorId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desarrolladorId + " no existe.", 404);
        }
        DesarrolladorDetailDTO authorDetailDTO = new DesarrolladorDetailDTO(requisitosDesarrolladorLogic.replaceAuthor(requisitosId, desarrolladorId));
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource replaceAuthor: output: {0}", authorDetailDTO);
        return authorDetailDTO;
    }

    /**
     * Elimina la conexión entre el desarrollador y el requisito recibido en la URL.
     *
     * @param requisitosId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @DELETE
    public void removeAuthor(@PathParam("requisitosId") Long requisitosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RequisitosDesarrolladorResource removeAuthor: input: {0}", requisitosId);
        requisitosDesarrolladorLogic.removeDesarrollador(requisitosId);
        LOGGER.info("RequisitosDesarrolladorResource removeAuthor: output: void");
    }
}

    


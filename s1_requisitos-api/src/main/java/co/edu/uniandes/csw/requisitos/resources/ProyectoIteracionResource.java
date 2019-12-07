/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.ProyectoDTO;
import co.edu.uniandes.csw.requisitos.dtos.ProyectoDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.ProyectoIteracionLogic;
import co.edu.uniandes.csw.requisitos.ejb.ProyectoLogic;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
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
 *
 * @author rj.gonzalez10
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProyectoIteracionResource {
     private static final Logger LOGGER = Logger.getLogger(ProyectoIteracionResource .class.getName());
      @Inject
    private ProyectoIteracionLogic iteracionProyectoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ProyectoLogic proyectoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    /**
     * Guarda un proyecto dentro de una iteracion con la informacion que recibe el la
     * URL.
     *
     * @param iteracionId Identificador de la iteracion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param proyectoId Identificador del proyecto que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ProyectoDTO} - El proyecto guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{proyectoId: \\d+}")
    public ProyectoDTO addProyecto(@PathParam("iteracionId") Long iteracionId, @PathParam("proyectoId") Long proyectoId) {
        LOGGER.log(Level.INFO, "IteracionProyectoResource addproyecto: input: iteracionId: {0} , proyectoId: {1}", new Object[]{iteracionId, proyectoId});
        if (proyectoLogic.getProyecto(proyectoId) == null) 
        {
            throw new WebApplicationException("El recurso /casodeuso/" + proyectoId + " no existe.", 404);
        }
        ProyectoDTO proyDTO = new ProyectoDTO(iteracionProyectoLogic.addProyecto(iteracionId,proyectoId));
        LOGGER.log(Level.INFO, "RequisitosProyectoResource addproyecto: output: {0}", proyDTO);
        return proyDTO;
    }
    /**
     * Busca el casodeuso dentro de el requisito con id asociado.
     *
     * @param iteracionId Identificador de el requisito que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ProyectoDetailDTO} - El proyecto buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el requisito no tiene autor
     */
    @GET
    public ProyectoDetailDTO getProyecto(@PathParam("iteracionId") Long iteracionId)
    {
        LOGGER.log(Level.INFO, "RequisitosProyectoResource getProyecto: input: {0}", iteracionId);
        ProyectoEntity authorEntity = iteracionProyectoLogic.getProyecto(iteracionId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /iteracion/" + iteracionId + "/casodeuso no existe.", 404);
        }
        ProyectoDetailDTO ProyectoDetailDTO = new ProyectoDetailDTO(authorEntity);
        LOGGER.log(Level.INFO, "RequisitosProyectoResource getProyecto: output: {0}", ProyectoDetailDTO);
        return ProyectoDetailDTO;
    }
    /**
     * Elimina la conexión entre el proyecto y la iteracion recibido en la URL.
     *
     * @param iteracionId El ID de la iteracion al cual se le va a desasociar el Proyecto
     * @throws co.edu.uniandes.csw.iteracion.exceptions.BusinessLogicException
     */
    @DELETE
    public void removeProyecto(@PathParam("iteracionId") Long iteracionId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "RequisitosCasoDeUsoResource removeproyecto: input: {0}", iteracionId);
        iteracionProyectoLogic.removeProyecto(iteracionId);
        LOGGER.info("ProyectoIteracionResource removeproyecto: output: void");
    }
     /**
     * Remplaza la instancia de casodeuso asociada a una instancia de requisito
     *
     * @param iteracionId Identificador de el requisito que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param casodeusoId Identificador de el casodeuso que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link proyectoDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{proyectoId: \\d+}")
    public ProyectoDetailDTO replaceProyecto(@PathParam("iteracionId") Long iteracionId, @PathParam("proyectoId") Long proyectoId) {
        LOGGER.log(Level.INFO, "Inicia ProyectoIteracionResource replaceproyecto: input: iteracionId: {0} , proyectoId: {1}", new Object[]{iteracionId, proyectoId});
        if (proyectoLogic.getProyecto(proyectoId) == null) {
            throw new WebApplicationException("El recurso /proyecto/" + proyectoId + " no existe.", 404);
        }
        ProyectoDetailDTO proyectoDetailDTO = new ProyectoDetailDTO(iteracionProyectoLogic.replaceProyecto(iteracionId, proyectoId));
        LOGGER.log(Level.INFO, "Termina ProyectoIteracionResource replaceproyecto: output: {0}", proyectoDetailDTO);
        return proyectoDetailDTO;
    }

}

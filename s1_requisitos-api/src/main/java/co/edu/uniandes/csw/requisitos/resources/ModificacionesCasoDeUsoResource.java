/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;
import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDTO;
import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.ejb.ModificacionesCasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jf.rubio
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModificacionesCasoDeUsoResource {
    
    private static final Logger LOGGER = Logger.getLogger(ModificacionesCasoDeUsoResource.class.getName());

    @Inject
    private ModificacionesCasoDeUsoLogic modificacionesCasoDeUsoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CasoDeUsoLogic casoDeUsoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un casoDeUso dentro de un premio con la informacion que recibe el la
     * URL.
     *
     * @param modificacionessId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param casoDeUsosId Identificador del autor que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CasoDeUsoDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{casoDeUsosId: \\d+}")
    public CasoDeUsoDTO addCasoDeUso(@PathParam("modificacionessId") Long modificacionessId, @PathParam("casoDeUsosId") Long casoDeUsosId) {
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource addCasoDeUso: input: modificacionessID: {0} , casoDeUsosId: {1}", new Object[]{modificacionessId, casoDeUsosId});
        if (casoDeUsoLogic.getCaso(casoDeUsosId) == null) {
            throw new WebApplicationException("El recurso /casoDeUsos/" + casoDeUsosId + " no existe.", 404);
        }
        CasoDeUsoDTO casoDeUsoDTO = new CasoDeUsoDTO(modificacionesCasoDeUsoLogic.addCasoDeUso(casoDeUsosId, modificacionessId));
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource addCasoDeUso: output: {0}", casoDeUsoDTO);
        return casoDeUsoDTO;
    }

    /**
     * Busca el autor dentro de el premio con id asociado.
     *
     * @param modificacionessId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link CasoDeUsoDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public CasoDeUsoDetailDTO getCasoDeUso(@PathParam("modificacionessId") Long modificacionessId) {
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource getCasoDeUso: input: {0}", modificacionessId);
        CasoDeUsoEntity casoDeUsoEntity = modificacionesCasoDeUsoLogic.getCasoDeUso(modificacionessId);
        if (casoDeUsoEntity == null) {
            throw new WebApplicationException("El recurso /modificacioness/" + modificacionessId + "/casoDeUso no existe.", 404);
        }
        CasoDeUsoDetailDTO casoDeUsoDetailDTO = new CasoDeUsoDetailDTO(casoDeUsoEntity);
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource getCasoDeUso: output: {0}", casoDeUsoDetailDTO);
        return casoDeUsoDetailDTO;
    }

    /**
     * Remplaza la instancia de CasoDeUso asociada a una instancia de Modificaciones
     *
     * @param modificacionessId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param casoDeUsosId Identificador de el casoDeUso que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link CasoDeUsoDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{casoDeUsosId: \\d+}")
    public CasoDeUsoDetailDTO replaceCasoDeUso(@PathParam("modificacionessId") Long modificacionessId, @PathParam("casoDeUsosId") Long casoDeUsosId) {
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource replaceCasoDeUso: input: modificacionessId: {0} , casoDeUsosId: {1}", new Object[]{modificacionessId, casoDeUsosId});
        if (casoDeUsoLogic.getCaso(casoDeUsosId) == null) {
            throw new WebApplicationException("El recurso /casoDeUsos/" + casoDeUsosId + " no existe.", 404);
        }
        CasoDeUsoDetailDTO casoDeUsoDetailDTO = new CasoDeUsoDetailDTO(modificacionesCasoDeUsoLogic.replaceCasoDeUso(modificacionessId, casoDeUsosId));
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource replaceCasoDeUso: output: {0}", casoDeUsoDetailDTO);
        return casoDeUsoDetailDTO;
    }

    /**
     * Elimina la conexión entre el autor y el premio recibido en la URL.
     *
     * @param modificacionessId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removeCasoDeUso(@PathParam("modificacionessId") Long modificacionessId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModificacionesCasoDeUsoResource removeCasoDeUso: input: {0}", modificacionessId);
        modificacionesCasoDeUsoLogic.removeCasoDeUso(modificacionessId);
        LOGGER.info("ModificacionesCasoDeUsoResource removeCasoDeUso: output: void");
    }
}

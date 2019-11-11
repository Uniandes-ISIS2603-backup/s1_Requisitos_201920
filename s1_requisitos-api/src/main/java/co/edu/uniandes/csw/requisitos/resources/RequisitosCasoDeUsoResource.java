/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDTO;
import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.ejb.RequisitosCasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
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
 * Clase que implementa el recurso "requisitos/{id}/casoDeUso".
 * @author Nicolás Tobo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequisitosCasoDeUsoResource 
{
   private static final Logger LOGGER = Logger.getLogger(RequisitosCasoDeUsoResource .class.getName());

    @Inject
    private RequisitosCasoDeUsoLogic requisitosCasoDeUsoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CasoDeUsoLogic casoDeUsoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
     /**
     * Guarda un casodeuso dentro de un requisito con la informacion que recibe el la
     * URL.
     *
     * @param requisitosId Identificador del requisito que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param casodeusoId Identificador del casodeuso que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CasoDeUsoDTO} - El casodeuso guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{casodeusoId: \\d+}")
    public CasoDeUsoDTO addCasoDeUso(@PathParam("requisitosId") Long requisitosId, @PathParam("casodeusoId") Long casodeusoId) {
        LOGGER.log(Level.INFO, "RequisitosCasoDeUsoResource addcasoDeUso: input: requisitosId: {0} , casodeusoId: {1}", new Object[]{requisitosId, casodeusoId});
        if (casoDeUsoLogic.getCaso(casodeusoId) == null) 
        {
            throw new WebApplicationException("El recurso /casodeuso/" + casodeusoId + " no existe.", 404);
        }
        CasoDeUsoDTO casoDTO = new CasoDeUsoDTO(requisitosCasoDeUsoLogic.addCasoDeUso(requisitosId,casodeusoId));
        LOGGER.log(Level.INFO, "RequisitosCasoDeUsoResource addcasoDeUso: output: {0}", casoDTO);
        return casoDTO;
    }
    
    /**
     * Busca el casodeuso dentro de el requisito con id asociado.
     *
     * @param requisitosId Identificador de el requisito que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link CasoDeUsoDetailDTO} - El casodeuso buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el requisito no tiene autor
     */
    @GET
    public CasoDeUsoDetailDTO getCasoDeUso(@PathParam("requisitosId") Long requisitosId)
    {
        LOGGER.log(Level.INFO, "RequisitosCasoDeUsoResource getcasoDeUso: input: {0}", requisitosId);
        CasoDeUsoEntity authorEntity = requisitosCasoDeUsoLogic.getCasoDeUso(requisitosId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /requisitos/" + requisitosId + "/casodeuso no existe.", 404);
        }
        CasoDeUsoDetailDTO casoDeUsoDetailDTO = new CasoDeUsoDetailDTO(authorEntity);
        LOGGER.log(Level.INFO, "RequisitosCasoDeUsoResource getcasoDeUso: output: {0}", casoDeUsoDetailDTO);
        return casoDeUsoDetailDTO;
    }
    
    /**
     * Elimina la conexión entre el casodeuso y el requisito recibido en la URL.
     *
     * @param requisitosId El ID del requisito al cual se le va a desasociar el Casodeuso
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @DELETE
    public void removeCasoDeUso(@PathParam("requisitosId") Long requisitosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "RequisitosCasoDeUsoResource removecasoDeUso: input: {0}", requisitosId);
        requisitosCasoDeUsoLogic.removeCasoDeUso(requisitosId);
        LOGGER.info("RequisitosCasoDeUsoResource removecasoDeUso: output: void");
    }
    
    /**
     * Remplaza la instancia de casodeuso asociada a una instancia de requisito
     *
     * @param requisitosId Identificador de el requisito que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param casodeusoId Identificador de el casodeuso que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link casoDeUsoDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{casodeusoId: \\d+}")
    public CasoDeUsoDetailDTO replaceCasoDeUso(@PathParam("requisitosId") Long requisitosId, @PathParam("casodeusoId") Long casodeusoId) {
        LOGGER.log(Level.INFO, "Inicia RequisitosCasoDeUsoResource replacecasoDeUso: input: requisitosId: {0} , casodeusoId: {1}", new Object[]{requisitosId, casodeusoId});
        if (casoDeUsoLogic.getCaso(casodeusoId) == null) {
            throw new WebApplicationException("El recurso /casodeuso/" + casodeusoId + " no existe.", 404);
        }
        CasoDeUsoDetailDTO casoDeUsoDetailDTO = new CasoDeUsoDetailDTO(requisitosCasoDeUsoLogic.replaceCasoDeUso(requisitosId, casodeusoId));
        LOGGER.log(Level.INFO, "Termina RequisitosCasoDeUsoResource replacecasoDeUso: output: {0}", casoDeUsoDetailDTO);
        return casoDeUsoDetailDTO;
    }

    
}

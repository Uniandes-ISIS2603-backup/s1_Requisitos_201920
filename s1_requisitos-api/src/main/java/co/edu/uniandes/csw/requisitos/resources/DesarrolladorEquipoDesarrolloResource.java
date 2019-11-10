/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.EquipoDesarrolloDTO;
import co.edu.uniandes.csw.requisitos.dtos.EquipoDesarrolloDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorEquipoDesarrolloLogic;
import co.edu.uniandes.csw.requisitos.ejb.EquipoDesarrolloLogic;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jf.rubio
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DesarrolladorEquipoDesarrolloResource {
    
    @Inject
    private DesarrolladorEquipoDesarrolloLogic desarrolladorEquipoDesarrolloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private EquipoDesarrolloLogic equipoDesarrolloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un equipoDesarrollo dentro de un premio con la informacion que recibe el la
     * URL.
     *
     * @param desarrolladorsId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param equipoDesarrollosId Identificador del autor que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link EquipoDesarrolloDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{equipoDesarrollosId: \\d+}")
    public EquipoDesarrolloDTO addEquipoDesarrollo(@PathParam("desarrolladorsId") Long desarrolladorsId, @PathParam("equipoDesarrollosId") Long equipoDesarrollosId) {
        if (equipoDesarrolloLogic.getEquipo(equipoDesarrollosId) == null) {
            throw new WebApplicationException("El recurso /equipoDesarrollos/" + equipoDesarrollosId + " no existe.", 404);
        }
        EquipoDesarrolloDTO equipoDesarrolloDTO = new EquipoDesarrolloDTO(desarrolladorEquipoDesarrolloLogic.addEquipoDesarrollo(equipoDesarrollosId, desarrolladorsId));
        return equipoDesarrolloDTO;
    }

    /**
     * Busca el autor dentro de el premio con id asociado.
     *
     * @param desarrolladorsId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link EquipoDesarrolloDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public EquipoDesarrolloDetailDTO getEquipoDesarrollo(@PathParam("desarrolladorsId") Long desarrolladorsId) {
        EquipoDesarrolloEntity equipoDesarrolloEntity = desarrolladorEquipoDesarrolloLogic.getEquipoDesarrollo(desarrolladorsId);
        if (equipoDesarrolloEntity == null) {
            throw new WebApplicationException("El recurso /desarrolladors/" + desarrolladorsId + "/equipoDesarrollo no existe.", 404);
        }
        EquipoDesarrolloDetailDTO equipoDesarrolloDetailDTO = new EquipoDesarrolloDetailDTO(equipoDesarrolloEntity);
        return equipoDesarrolloDetailDTO;
    }

    /**
     * Remplaza la instancia de EquipoDesarrollo asociada a una instancia de Desarrollador
     *
     * @param desarrolladorsId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param equipoDesarrollosId Identificador de el equipoDesarrollo que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link EquipoDesarrolloDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{equipoDesarrollosId: \\d+}")
    public EquipoDesarrolloDetailDTO replaceEquipoDesarrollo(@PathParam("desarrolladorsId") Long desarrolladorsId, @PathParam("equipoDesarrollosId") Long equipoDesarrollosId) {
        if (equipoDesarrolloLogic.getEquipo(equipoDesarrollosId) == null) {
            throw new WebApplicationException("El recurso /equipoDesarrollos/" + equipoDesarrollosId + " no existe.", 404);
        }
        EquipoDesarrolloDetailDTO equipoDesarrolloDetailDTO = new EquipoDesarrolloDetailDTO(desarrolladorEquipoDesarrolloLogic.replaceEquipoDesarrollo(desarrolladorsId, equipoDesarrollosId));
        return equipoDesarrolloDetailDTO;
    }

    /**
     * Elimina la conexión entre el autor y el premio recibido en la URL.
     *
     * @param desarrolladorsId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removeEquipoDesarrollo(@PathParam("desarrolladorsId") Long desarrolladorsId) throws BusinessLogicException {
        desarrolladorEquipoDesarrolloLogic.removeEquipoDesarrollo(desarrolladorsId);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.RequisitosDTO;
import co.edu.uniandes.csw.requisitos.dtos.RequisitosDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.ModificacionesRequisitoLogic;
import co.edu.uniandes.csw.requisitos.ejb.RequisitoLogic;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Martinez
 */
public class ModificacionesRequisitoResource {
    
    @Inject
    private ModificacionesRequisitoLogic modificacionesRequisitoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private RequisitoLogic requisitoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un requisito dentro de una modificacion con la informacion que recibe el URL
     * @param modificacionsId id de la modificacion que se esta actualizando
     * @param requisitosId id del requisito que se desea guardar
     * @return 
     */
    @POST
    @Path("{requisitosId: \\d+}")
    public RequisitosDTO addRequisito(@PathParam("modificacionsId") Long modificacionsId, @PathParam("requisitosId") Long requisitosId) {
        if (requisitoLogic.getRequisito(requisitosId) == null) {
            throw new WebApplicationException("El recurso /requisitos/" + requisitosId + " no existe.", 404);
        }
        RequisitosDTO requisitoDTO = new RequisitosDTO(modificacionesRequisitoLogic.addRequisito(requisitosId, modificacionsId));
        return requisitoDTO;
    }

    /**
     * Busca el autor dentro de el premio con id asociado.
     *
     * @param modificacionsId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link EquipoDesarrolloDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public RequisitosDetailDTO getRequisito(@PathParam("modificacionsId") Long modificacionsId) {
        RequisitosEntity reqEntity = modificacionesRequisitoLogic.getRequisito(modificacionsId);
        if (reqEntity == null) {
            throw new WebApplicationException("El recurso /modificacions/" + modificacionsId + "/requisito no existe.", 404);
        }
        RequisitosDetailDTO reqDetailDTO;
        reqDetailDTO = new RequisitosDetailDTO(reqEntity);
        return reqDetailDTO;
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
    @Path("{requisitosId: \\d+}")
    public RequisitosDetailDTO replaceRequisito(@PathParam("modificacionsId") Long modificacionsId, @PathParam("requisitosId") Long requisitosId) {
        if (requisitoLogic.getRequisito(requisitosId) == null) {
            throw new WebApplicationException("El recurso /requisitos/" + requisitosId + " no existe.", 404);
        }
        RequisitosDetailDTO reqDetailDTO = new RequisitosDetailDTO(modificacionesRequisitoLogic.replaceRequisito(modificacionsId, requisitosId));
        return reqDetailDTO;
    }

    /**
     * Elimina la conexión entre el autor y el premio recibido en la URL.
     *
     * @param desarrolladorsId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removeEquipoDesarrollo(@PathParam("modificacionsId") Long modificacionsId) throws BusinessLogicException {
        modificacionesRequisitoLogic.removeRequisito(modificacionsId);
    }
    
}

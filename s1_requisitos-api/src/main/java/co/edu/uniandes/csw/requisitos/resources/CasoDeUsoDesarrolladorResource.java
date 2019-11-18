/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoDesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
 * @author Maria Alejandra Escalante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CasoDeUsoDesarrolladorResource {
    
     private static final Logger LOGGER = Logger.getLogger(CasoDeUsoDesarrolladorResource.class.getName());
     
     //String ruta
     private static final String RUTA ="El recurso /desarrollador/";
     //String no existe
     private static final String NOEXISTE=" no existe.";
    @Inject
    private CasoDeUsoDesarrolladorLogic cdLogic; //variable para la ligoca
    
    @Inject
    private DesarrolladorLogic desLogic;
    
    /**
     * Guarda un desarrollador dentro de un casodeuso con la informacion que recibe el la
     * URL.
     *
     * @param casoId Identificador de el caso que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param desId Identificador del desarrollador que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{desarrolladorId: \\d+}/{tipo:\\d+}")
    public DesarrolladorDTO addResponsableRepresentante(@PathParam("casosId") Long casosId, @PathParam("desarrolladorId") Long desId, @PathParam ("tipo") int tipo) throws BusinessLogicException {
     
             
       DesarrolladorDTO desDTO= new DesarrolladorDTO();
       if (tipo==1){
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addResponsable: input: casoID: {0} , desId: {1}", new Object[]{casosId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException(RUTA + desId + NOEXISTE, 404);
        }
         desDTO = new DesarrolladorDTO(cdLogic.addResponsable(desId, casosId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addResponsable: output: {0}", desDTO);
       }
        
        
   
        if (tipo==2){
             LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addRepresentante: input: casoID: {0} , desId: {1}", new Object[]{casosId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException(RUTA + desId + NOEXISTE, 404);
        }
        desDTO = new DesarrolladorDTO(cdLogic.addRepresentante(desId, casosId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addRepresentante: output: {0}", desDTO);
      
    }
   
        return desDTO;
    }
    
     /**
     * Busca el autor dentro de el premio con id asociado.
     *
     * @param casoDeUsoId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    @Path("{tipo:\\d+}")
    public DesarrolladorDTO getResponsableRepresentante(@PathParam("casosId") Long casosId, @PathParam("tipo")int tipo) {
        DesarrolladorDTO desDTO= new DesarrolladorDTO();
        if (tipo==1){
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource get: input: {0}", casosId);
        DesarrolladorEntity desEntity =cdLogic.getResponsable(casosId);
        if (desEntity == null) {
            throw new WebApplicationException("El recurso /casos/" + casosId + "/desarrollador no existe.", 404);
        }
        desDTO = new DesarrolladorDTO(desEntity);
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource get: output: {0}", desDTO);
        return desDTO;
       }
       if (tipo==2){
           LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource get: input: {0}", casosId);
        DesarrolladorEntity desEntity =cdLogic.getRepresentante(casosId);
        if (desEntity == null) {
            throw new WebApplicationException("El recurso /casos/" + casosId + "/desarrollador no existe.", 404);
        }
        desDTO = new DesarrolladorDTO(desEntity);
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource get: output: {0}", desDTO);
      
       }
         return desDTO;
    }

    /**
     * Remplaza la instancia de desarrollador asociada a una instancia de casosDeUso
     *
     * @param casosId Identificador de el caso que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param desarrolladorId Identificador de el desarollador que se esta remplazando. Este
     * debe ser una cadxena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{desId: \\d+}/{tipo:\\d+}")
    public DesarrolladorDTO replaceAuthor(@PathParam("casosId") Long casosId, @PathParam("desId") Long desId, @PathParam ("tipo") int tipo) throws BusinessLogicException {
        DesarrolladorDTO desDTO= new DesarrolladorDTO();
        if (tipo==1){
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource replaceDesarrollador: input: casosId: {0} , desd: {1}", new Object[]{casosId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException(RUTA + desId + NOEXISTE, 404);
        }
         desDTO = new DesarrolladorDTO(cdLogic.cambiarResponsable(desId, casosId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource replaceDesarrollador: output: {0}", desDTO);
        }
        if (tipo==2){
            LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource replaceDesarrollador: input: casosId: {0} , desd: {1}", new Object[]{casosId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException(RUTA + desId + NOEXISTE, 404);
        }
         desDTO = new DesarrolladorDTO(cdLogic.cambiarRepresentante(desId, casosId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource replaceDesarrollador: output: {0}", desDTO);
        }
        return desDTO;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoDesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDTO addResponsable(@PathParam("casoId") Long casoId, @PathParam("desId") Long desId) throws BusinessLogicException {
        
       // DesarrolladorDTO desDTO= new DesarrolladorDTO();
  
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addResponsable: input: casoID: {0} , desId: {1}", new Object[]{casoId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desId + " no existe.", 404);
        }
        DesarrolladorDTO desDTO = new DesarrolladorDTO(cdLogic.addResponsable(desId, casoId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addResponsable: output: {0}", desDTO);
       
        
        
    /*
        if (tipo==2){
             LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addRepresentante: input: casoID: {0} , desId: {1}", new Object[]{casoId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desId + " no existe.", 404);
        }
        desDTO = new DesarrolladorDTO(cdLogic.addRepresentante(desId, casoId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addRepresentante: output: {0}", desDTO);
      
    }
    */
        return desDTO;
    }
    
    
    
     /**
     * Guarda un desarrollador  de tipo respresentante dentro de un casodeuso con la informacion que recibe el la
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
    /*
    @POST
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDTO addRepresentante(@PathParam("casoId") Long casoId, @PathParam("desId") Long desId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addRepresentante: input: casoID: {0} , desId: {1}", new Object[]{casoId, desId});
        if (desLogic.getDesarrollador(desId) == null) {
            throw new WebApplicationException("El recurso /desarrollador/" + desId + " no existe.", 404);
        }
        DesarrolladorDTO desDTO = new DesarrolladorDTO(cdLogic.addRepresentante(desId, casoId));
        LOGGER.log(Level.INFO, "CasoDeUsoDesarrolladorResource addRepresentante: output: {0}", desDTO);
        return desDTO;
    }
*/
    
    
    
}

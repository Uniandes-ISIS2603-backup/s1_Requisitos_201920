/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.RequisitosDTO;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Nicolas Tobo
 */
@Path("requisitos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RequisitosResource {
    
        private static final Logger LOGGER = Logger.getLogger(RequisitosResource.class.getName());
        
        /**
     * Crea una nuevorequisito con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param requisito
     * @return JSON {@link EditorialDTO} - La requisito guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la requisito.
     */
    @POST
    public RequisitosDTO createEditorial(RequisitosDTO requisito) throws BusinessLogicException 
    {
        return requisito;
    }
    
}

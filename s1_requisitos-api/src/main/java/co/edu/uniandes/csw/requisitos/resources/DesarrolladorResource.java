/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDTO;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Clase que representa el servicio Rest para requisitos
 * @author Nicolas Tobo
 */
@Path("/desarrollador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DesarrolladorResource 
{
    private static final Logger LOGGER = Logger.getLogger(DesarrolladorResource.class.getName());
    /**
      * Atributo logica
      */
     @Inject
     private DesarrolladorLogic dl;
    
    
     /**
     * Crea una nuevo desarrollador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @return JSON {@link EditorialDTO} - El requisito guardado con el atributo
     * id autogenerado.
     */
    @POST
    public DesarrolladorDTO createDesarrollador(DesarrolladorDTO desarrollador) throws BusinessLogicException     
    {
        LOGGER.log(Level.INFO, "DesarrolaldorResource createDesarrollador: input: {0}", desarrollador);
        DesarrolladorDTO nuevoDesarrolladorDTO = new DesarrolladorDTO(dl.createDesarrollador(desarrollador.toEntity()));
        LOGGER.log(Level.INFO, "DesarrolaldorResource createDesarrollador: output: {0}", nuevoDesarrolladorDTO);
        return nuevoDesarrolladorDTO;
    }
}

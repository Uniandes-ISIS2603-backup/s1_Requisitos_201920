/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.FuncionalDTO;
import co.edu.uniandes.csw.requisitos.dtos.RequisitosDTO;
import co.edu.uniandes.csw.requisitos.ejb.FuncionalLogic;
import co.edu.uniandes.csw.requisitos.ejb.RequisitoLogic;
import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Clase que representa el servicio Rest para requisitos
 * @author Nicolas Tobo
 */
@Path("/requisitosFuncionales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RequisitosFuncionalesResource {
    
     private static final Logger LOGGER = Logger.getLogger(RequisitosFuncionalesResource.class.getName()); 
     /**
      * Atributo logica
      */
     @Inject
     private FuncionalLogic rl;
        /**
     * Crea una nuevo requisito con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param requisito
     * @return JSON {@link EditorialDTO} -El requisito guardado con el atributo
     * id autogenerado.
     */
    @POST
    public RequisitosDTO createRequisitoFuncional(FuncionalDTO requisito) throws BusinessLogicException     
    {
        FuncionalEntity requisitoEntity=requisito.toEntity();
        requisitoEntity=rl.createFuncional(requisitoEntity);
        return new RequisitosDTO(requisitoEntity);
    }
}

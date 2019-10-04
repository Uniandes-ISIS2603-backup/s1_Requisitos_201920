/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.PersonaDTO;
import co.edu.uniandes.csw.requisitos.ejb.PersonaLogic;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author jf.rubio
 */
@Path("/persona")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PersonaResource {
    
      /**
      * Atributo logica
      */
     @Inject
     private PersonaLogic rl;
     
      @POST
    public PersonaDTO createRequisito(PersonaDTO persona) throws BusinessLogicException     
    {
        //RequisitosEntity requisitoEntity=requisito.toEntity();
        //requisitoEntity=rl.createRequisito(requisitoEntity);
        //return new RequisitosDTO(requisitoEntity);
        return persona;
    }
}

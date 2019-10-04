/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDTO;
import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDetailDTO;
import co.edu.uniandes.csw.requisitos.dtos.RequisitosDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Path("casos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CasoDeUsoResource {
    
        private static final Logger LOGGER = Logger.getLogger(CasoDeUsoResource.class.getName());
        
        @Inject
        private CasoDeUsoLogic logica;
        
    
    
    @POST
    public CasoDeUsoDTO createCasodeUSo(CasoDeUsoDTO caso) throws BusinessLogicException 
    {
        
        CasoDeUsoEntity entidadCaso = caso.toEntity();
        entidadCaso=logica.crearCasoDeUso(entidadCaso);
        return new CasoDeUsoDTO(entidadCaso);

    }
    /*
    @GET
    @Path("{casosId:\\id+}")
    public CasoDeUsoDetailDTO getCasoDeUso (@PathParam("casosId") Long casosId){
        
    }
    */
}

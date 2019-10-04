/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ma.Escalante
 */
@Path("casosDeUso")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CasoDeUsoResource 
{
    private static final Logger LOGGER = Logger.getLogger(CasoDeUsoResource.class.getName()); 
     /**
      * Atributo logica
      */
     @Inject
     private CasoDeUsoLogic cl;
       /**
     * Crea una nuevo caso de uso con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param casoDeUso
     * @return JSON {@link CasoDeUsoDTO} -El requisito guardado con el atributo
     * id autogenerado.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @POST
    public CasoDeUsoDTO createRequisitoFuncional(CasoDeUsoDTO casoDeUso) throws BusinessLogicException     
    {
        CasoDeUsoEntity casoDeUsoEntity=casoDeUso.toEntity();
        casoDeUsoEntity=cl.crearCasoDeUso(casoDeUsoEntity);
        return new CasoDeUsoDTO(casoDeUsoEntity);
    }

    
}

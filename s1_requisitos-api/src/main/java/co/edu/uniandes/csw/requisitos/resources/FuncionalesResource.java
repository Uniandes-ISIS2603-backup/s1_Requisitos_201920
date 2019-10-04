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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que representa el servicio Rest para requisitos
 * @author Nicolas Tobo
 */
@Path("reqFuncionales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FuncionalesResource {
    
     private static final Logger LOGGER = Logger.getLogger(FuncionalesResource.class.getName()); 
     /**
      * Atributo logica
      */
     @Inject
     private FuncionalLogic fl;
        /**
     * Crea una nuevo requisito con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param requisito
     * @return JSON {@link RequisitoDTO} -El requisito guardado con el atributo
     * id autogenerado.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @POST
    public FuncionalDTO createReqFuncional(FuncionalDTO requisito) throws BusinessLogicException     
    {
        LOGGER.log(Level.INFO, "RequisitosFuncionalesResource createReqFuncional: input: {0}", requisito);
        FuncionalDTO nuevoFuncionalDTO = new FuncionalDTO(fl.createFuncional(requisito.toEntity()));
        LOGGER.log(Level.INFO, "IteracionResource createRequncional: output: {0}", nuevoFuncionalDTO);
        return nuevoFuncionalDTO;
    }
    
    /**
     * Busca el requisito funcional con el id asociado recibido en la URL y lo devuelve.
     * @param requisitoId Identificador del requisito funcional que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link RequisitoFuncionalDTO} - requisito funcional
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la iteracion.
     */
    @GET
    @Path("{requisitoId: \\d+}")
    public FuncionalDTO getReqFuncional(@PathParam("requisitoId") Long requisitoId) 
    {
        LOGGER.log(Level.INFO, "FuncionalesResource getReqFuncional: input: {0}", requisitoId);
        FuncionalEntity funcionalEntity = fl.getFuncional(requisitoId);
        if (funcionalEntity == null) {
            throw new WebApplicationException("El recurso /Funcional/" + requisitoId + " no existe.", 404);
        }
        FuncionalDTO funcionalDTO = new FuncionalDTO(funcionalEntity);
        LOGGER.log(Level.INFO, "FuncionalResource getIteracion: output: {0}", funcionalDTO);
        return funcionalDTO;
    }
    /**
     * Busca y devuelve todos los requisitos funcionales que existen en la aplicacion.
     *
     * @return JSONArray {@link  FuncionalDTO} - Las iteraciones encontrados en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FuncionalDTO> getReqFuncionales() 
    {
        LOGGER.info("IteracionResource getRequisitosFuncionales: input: void");
        List<FuncionalDTO> listaIteraciones = listEntity2DTO(fl.getFuncionales());
        LOGGER.log(Level.INFO, "IteracionResource getIteraciones: output: {0}", listaIteraciones);
        return listaIteraciones;
    }
    
    /**
     * Actualiza el requisito funcional con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param requisitoId Identificador del requisito que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param funcional {@link funcionalDTO} La requisitoFuncional que se desea guardar.
     * @return JSON {@link FuncionalDTO} - el requisitoFuncional guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito funcional a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el requisito funcional.
     */
    @PUT
    @Path("{requisitoId: \\d+}")
    public FuncionalDTO updateReqFuncional(@PathParam("requisitoId") Long requisitoId, FuncionalDTO funcional) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "FuncionalResource updateReqFuncional: input: id: {0} , funcional: {1}", new Object[]{requisitoId, funcional});
        funcional.setId(requisitoId);
        if (fl.getFuncional(requisitoId) == null) {
            throw new WebApplicationException("El recurso /funcional/" + requisitoId + " no existe.", 404);
        }
        FuncionalDTO funDTO = new FuncionalDTO(fl.updateFuncional(funcional.toEntity()));
        LOGGER.log(Level.INFO, "IteracionResource updateIteracion: output: {0}", funDTO);
        return funDTO;
    }

    
    
    
    
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos FuncionalEntity a una lista de
     * objetos FuncionalDTO (json)
     * @param entityList corresponde a la lista de requisitos funcionales de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Iteraciones en forma DTO (json)
     */
    private List<FuncionalDTO> listEntity2DTO(List<FuncionalEntity> entityList) {
        List<FuncionalDTO> list = new ArrayList<>();
        for (FuncionalEntity entity : entityList) {
            list.add(new FuncionalDTO(entity));
        }
        return list;
    }
}

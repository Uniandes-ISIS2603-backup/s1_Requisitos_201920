/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.AtributoCalidadDTO;
import co.edu.uniandes.csw.requisitos.ejb.AtributoCalidadLogic;
import co.edu.uniandes.csw.requisitos.entities.AtributoCalidadEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author n.bahamon
 */
@Path("atributoscalidad")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AtributoCalidadResource {
        private static final Logger LOGGER = Logger.getLogger(AtributoCalidadResource.class.getName()); 
     /**
      * Atributo logica
      */
     @Inject
     private AtributoCalidadLogic fl;
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
    public AtributoCalidadDTO createAtributoCalidad(AtributoCalidadDTO atributoCalidad) throws BusinessLogicException     
    {
        LOGGER.log(Level.INFO, "AtributoCalidadResource createAtributoCalidad: input: {0}", atributoCalidad);
       
        AtributoCalidadEntity acEntity = atributoCalidad.toEntity();
        AtributoCalidadEntity nuevoacEntity = fl.createAtributoCalidad(acEntity);
        AtributoCalidadDTO nuevoACDTO = new AtributoCalidadDTO(nuevoacEntity);
        LOGGER.log(Level.INFO, "AtributoCalidadResource createAtributoCalidad: output: {0}", nuevoACDTO);
        return nuevoACDTO;
    }
    /**
     * Busca el requisito funcional con el id asociado recibido en la URL y lo devuelve.
     * @param requisitoId Identificador del requisito funcional que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link RequisitoFuncionalDetailDTO} - requisito funcional
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la iteracion.
     */
       @GET
    @Path("{atributosCalidadId: \\d+}")
    public AtributoCalidadDTO getAtributoCalidad(@PathParam("atributosCalidadId") Long atributosCalidadId) 
   {    LOGGER.log(Level.INFO, "AtributoCalidadResource getAtributoCalidad: input: {0}", atributosCalidadId);
        AtributoCalidadEntity acEntity = fl.getAtributoCalidad(atributosCalidadId);
        if (acEntity == null) {
            throw new WebApplicationException("El recurso /atributosCalidad/" + atributosCalidadId + " no existe.", 404);
        }
        AtributoCalidadDTO acDTO = new AtributoCalidadDTO(acEntity);
        LOGGER.log(Level.INFO, "AtributoCalidadResource getAtributoCalidad: output: {0}", acDTO);
        return acDTO;
    }
    
    /**
     * Busca y devuelve todos los requisitos funcionales que existen en la aplicacion.
     *
     * @return JSONArray {@link  FuncionalDTO} - Las iteraciones encontrados en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<AtributoCalidadDTO> getAtributosCalidad() 
    {
        LOGGER.info("AtributoCalidadResource getAtributosCalidad: input: void");
        List<AtributoCalidadDTO> listaIteraciones = (List<AtributoCalidadDTO>) listEntity2DetailDTO(fl.getAtributosCalidad());
        LOGGER.log(Level.INFO, "FuncionalResource getReqFuncionales: output: {0}", listaIteraciones);
        return listaIteraciones;
    }
    
    /**
     * Actualiza el requisito funcional con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param requisitoId Identificador del requisito que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param funcional {@link FuncionalDetailDTO} La requisitoFuncional que se desea guardar.
     * @return JSON {@link FuncionalDetailDTO} - el requisitoFuncional guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito funcional a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el requisito funcional.
     */
    @PUT
    @Path("{atributosCalidadId: \\d+}")
    public AtributoCalidadDTO updateAtributoCalidad(@PathParam("atributosCalidadId") Long atributoCalidadID,AtributoCalidadDTO ACDTO) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AtributoCalidadResource updateAtributoCalidad: input: id: {0} , ACDTO: {1}", new Object[]{atributoCalidadID, ACDTO});
        ACDTO.setId(atributoCalidadID);
        if (fl.getAtributoCalidad(atributoCalidadID) == null) {
            throw new WebApplicationException("El recurso /atributocalidad/" + atributoCalidadID + " no existe.", 404);
        }
        AtributoCalidadDTO acCDTO = new AtributoCalidadDTO(fl.updateAtributoCalidad(ACDTO.toEntity()));
        LOGGER.log(Level.INFO, "AtributoCalidadResource updateAtributoCalidad: output: {0}", acCDTO);
        return acCDTO;
    }
      
  /**
     * Borra el requisitoFuncional con el id asociado recibido en la URL.
     * @param requisitoId
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito a eliminar.
     */
    @DELETE
    @Path("{atributoCalidadId: \\d+}")
    public void deleteAtributoCalidad(@PathParam("atributoCalidadId") Long atributoCalidadId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AtributoCalidadResource deleteAtributoCalidad: input: {0}", atributoCalidadId);
        AtributoCalidadEntity entity = fl.getAtributoCalidad(atributoCalidadId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /atributocalidad/" + atributoCalidadId + " no existe.", 404);
        }
        //TODO recordar que cuando se terminen las asociaciones borrarlas antes de hacer el delete
        fl.deleteAtributoCalidad(atributoCalidadId);
        LOGGER.info("FuncionalResource deleteReqFuncional output: void");
    }    
     /**
     * Convierte una lista de entidades a DetailDTO.
     *
     * Este método convierte una lista de objetos FuncionalEntity a una lista de
     * objetos FuncionalDetailDTO (json)
     * @param entityList corresponde a la lista de requisitos funcionales de tipo Entity que
     * vamos a convertir a DetDTO.
     * @return la lista de requisitos Funcionales en forma DetailDTO (json)
     */
    private List<AtributoCalidadDTO> listEntity2DetailDTO(List<AtributoCalidadEntity> entityList) {
        List<AtributoCalidadDTO> list = new ArrayList<>();
        for (AtributoCalidadEntity entity : entityList) {
            list.add(new AtributoCalidadDTO(entity));
        }
        return list;
    }
}

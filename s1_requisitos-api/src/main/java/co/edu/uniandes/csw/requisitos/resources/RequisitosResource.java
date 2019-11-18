/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.RequisitosDTO;
import co.edu.uniandes.csw.requisitos.dtos.RequisitosDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.ejb.RequisitoLogic;
import co.edu.uniandes.csw.requisitos.ejb.RequisitosCasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
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
 * Clase que representa el servicio Rest para requisitos
 *
 * @author Nicolas Tobo
 */
@Path("requisitos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RequisitosResource {

    private static final Logger LOGGER = Logger.getLogger(RequisitosResource.class.getName());
    //Ruta 1
    private static final String RUTA1 = "El recurso /requisitos/";
    //Ruta 2
    private static final String RUTA2="El recurso /requisito/";
    //No existe 1
    private static final String NOEXISTE1=" no existe";
    //No existe 2
    private static final String NOEXISTE2=" no existe.";
    /**
     * Atributo logica requisito, se inyecta la dependencia
     */
    @Inject
    private RequisitoLogic requisitoLogic;
    /**
     * Atributo logica caso de uso , se inyecta la dependencia
     */
    @Inject
    private CasoDeUsoLogic casoDeUsoLogic;
    /**
     * Atributo logica relacion casoDeUso y requisito , se inyecta la
     * dependencia
     */
    @Inject
    private RequisitosCasoDeUsoLogic requisitosCasoDeUsoLogic;

    /**
     * Crea una nuevo requisito con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param requisito {@link RequisitoDTO} - EL requisito que se desea
     * guardar.
     * @return JSON {@link RequisitoDTO} -El requisito guardado con el atributo
     * id autogenerado.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * Cuando se presenta un Error de lógica (puede ser nombre repetido o algo
     * mas especifico).
     */
    @POST
    public RequisitosDTO createRequisito(RequisitosDTO requisito) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RequisitoResource createRequisito: input: {0}", requisito);
        RequisitosDTO nuevoRequisitoDTO = new RequisitosDTO(requisitoLogic.createRequisito(requisito.toEntity()));
        LOGGER.log(Level.INFO, "RequisitoResource createRequisito: output: {0}", nuevoRequisitoDTO);
        return nuevoRequisitoDTO;
    }

    /**
     * Busca el requisito con el id asociado recibido en la URL y lo devuelve.
     *
     * @param requisitoId Identificador del requisito que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link RequisitoDetailDTO} - requisito
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito.
     */
    @GET
    @Path("{requisitoId: \\d+}")
    public RequisitosDetailDTO getRequisito(@PathParam("requisitoId") Long requisitoId) {
        LOGGER.log(Level.INFO, "RequisitoResource getRequisito: input: {0}", requisitoId);
        RequisitosEntity entity = requisitoLogic.getRequisito(requisitoId);
        if (entity == null) {
            throw new WebApplicationException(RUTA1 + requisitoId + NOEXISTE1, 404);
        }
        RequisitosDetailDTO dto = new RequisitosDetailDTO(entity);
        LOGGER.log(Level.INFO, "RequisitoResource getRequisito: output: {0}", dto);
        return dto;
    }

    /**
     * Busca y devuelve todos los requisitos es que existen en la aplicacion.
     *
     * @return JSONArray {@link  RequisitoDTO} - Las iteraciones encontrados en
     * la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<RequisitosDetailDTO> getRequisito() {
        LOGGER.info("RequisitoResource getRequisitos: input: void");
        List<RequisitosDetailDTO> listaRequisitos = listEntity2DetailDTO(requisitoLogic.getRequisitos());
        LOGGER.log(Level.INFO, "RequisitoResource getRequisito: output: {0}", listaRequisitos);
        return listaRequisitos;
    }

    /**
     * Actualiza el requisito con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param requisitoId Identificador del requisito que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param r
     * @param {@link RequisitoDetailDTO} La requisitoRequisito que se desea
     * guardar.
     * @return JSON {@link RequisitoDetailDTO} - el requisitoRequisito guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el requisito
     * .
     */
    @PUT
    @Path("{requisitoId: \\d+}")
    public RequisitosDetailDTO updateRequisito(@PathParam("requisitoId") Long requisitoId, RequisitosDetailDTO r) throws BusinessLogicException {
         r.setId(requisitoId);
        if (requisitoLogic.getRequisito(requisitoId) == null) 
        {
            throw new WebApplicationException(RUTA2 + requisitoId + NOEXISTE2, 404);
        }
        return new RequisitosDetailDTO(requisitoLogic.updateRequisito(r.toEntity()));
    }

    /**
     * Borra el requisito con el id asociado recibido en la URL.
     *
     * @param requisitoId
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * - si se imcumple alguna norma de la logica
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito a
     * eliminar.
     */
    @DELETE
    @Path("{requisitoId: \\d+}")
    public void deleteRequisito(@PathParam("requisitoId") Long requisitoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RequisitoesResource deleteRequisito: input: {0}", requisitoId);
        RequisitosEntity entity = requisitoLogic.getRequisito(requisitoId);
        if (entity == null) {
            throw new WebApplicationException(RUTA1 + requisitoId + NOEXISTE2, 404);
        }
        if (entity.getRequisitosFuncionalesCaso() != null) {
            requisitosCasoDeUsoLogic.removeCasoDeUso(requisitoId);
        }
        requisitoLogic.deleteRequisito(requisitoId);
        LOGGER.info("RequisitoResource deleteRequisito output: void");
    }

    /**
     * Convierte una lista de entidades a DetailDTO.
     *
     * Este método convierte una lista de objetos RequisitoEntity a una lista de
     * objetos RequisitoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de requisitos es de tipo Entity
     * que vamos a convertir a DetDTO.
     * @return la lista de requisitos Requisitoes en forma DetailDTO (json)
     */
    private List<RequisitosDetailDTO> listEntity2DetailDTO(List<RequisitosEntity> entityList) {
        List<RequisitosDetailDTO> list = new ArrayList<>();
        for (RequisitosEntity entity : entityList) {
            list.add(new RequisitosDetailDTO(entity));
        }
        return list;
    }
    /**
     * @param requisitosId El ID de la editorial con respecto a la cual se accede al
     * servicio.
     * @return El servicio de autor para este premio en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que s genera cuando no se el premio.
     */
    @Path("{requisitosId: \\d+}/desarrolladorb")
    public Class<RequisitosDesarrolladorResource> getRequisitosDesarrolladoroResource(@PathParam("requisitosId") Long requisitosId) {
    
        if (requisitoLogic.getRequisito(requisitosId)== null) {
            throw new WebApplicationException(RUTA2 + requisitosId + NOEXISTE2, 404);
        }
        return RequisitosDesarrolladorResource.class;
    }
     /**
     * @param requisitosId El ID de la  con respecto a la cual se accede al
     * servicio.
     * @return El servicio de casoDeUso para este requisito en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el requisito.
     */
    @Path("{requisitosId: \\d+}/casodeuso")
    public Class<RequisitosCasoDeUsoResource> getRequisitosCasoDeUsoResource(@PathParam("requisitosId") Long requisitosId) {
    
        if (requisitoLogic.getRequisito(requisitosId)== null) {
            throw new WebApplicationException(RUTA2 + requisitosId + NOEXISTE2, 404);
        }
 
        return RequisitosCasoDeUsoResource.class;
    }
    
}

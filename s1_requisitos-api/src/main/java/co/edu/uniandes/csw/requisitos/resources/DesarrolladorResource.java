/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDTO;
import co.edu.uniandes.csw.requisitos.dtos.DesarrolladorDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
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
 * Clase que representa el servicio Rest para desarrollador.
 * @author Nicolas Tobo
 */
@Path("desarrollador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DesarrolladorResource 
{  
   private static final Logger LOGGER = Logger.getLogger(DesarrolladorResource.class.getName());   
   private static final String PATH1 = "El recurso /desarrollador/";
   private static final String PATH2 = "no existe";
  
    /**
      * Atributo logica
      */
     @Inject
     private DesarrolladorLogic dl;

     
    
     /**
      * 
     * Crea una nuevo desarrollador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param desarrollador {@link DesarrolladorDTO} - La iteracion que se desea guardar.
     * @return JSON {@link DesarrolladorDTO} El desarrollador guardado con el atributo
     * id autogenerado.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando no existe el tipo del desarrollador.
     */
    @POST
    public DesarrolladorDTO createDesarrollador(DesarrolladorDTO desarrollador) throws BusinessLogicException  
    {
        LOGGER.log(Level.INFO, "DesarrolladorResource createDesarrollador: input: {0}", desarrollador);
        DesarrolladorDTO nuevoDesarrolladorDTO = new DesarrolladorDTO(dl.createDesarrollador(desarrollador.toEntity()));
        LOGGER.log(Level.INFO, "DesarrolladorResource createDesarrollador: output: {0}", nuevoDesarrolladorDTO);
        return nuevoDesarrolladorDTO;
    }

    


    /**
     * Busca y devuelve todos los desarrolladores que existen en la aplicacion.
     *
     * @return JSONArray {@link DesarrolladorDetailDTO} - Los desarrolladores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<DesarrolladorDetailDTO> getDesarrolladores() 
    {
        LOGGER.info("Desarrollador getDesarrollador: input: void");
        List<DesarrolladorDetailDTO> listaDesarrollador = listEntity2DetailDTO(dl.getDesarrolladores());
        LOGGER.log(Level.INFO, "DesarrolladorResource getDesarrolladores: output: {0}", listaDesarrollador);
        return listaDesarrollador;
    }
    
     /**
     * Busca el desarrollador con el id asociado recibido en la URL y lo devuelve.
     * @param desarrolladorId Identificador del desarrollador que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link DesarrolaldorDetailDTO} - el desarrollador
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el desarrollador.
     */
    @GET
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDetailDTO getDesarrollador(@PathParam("desarrolladorId") Long desarrolladorId) 
    {
        LOGGER.log(Level.INFO, "DesarrolladorResource getDesarrollador: input: {0}", desarrolladorId);
        DesarrolladorEntity desarrolladorEntity = dl.getDesarrollador(desarrolladorId);
        if (desarrolladorEntity == null) {
            throw new WebApplicationException(PATH1 + desarrolladorId + PATH2, 404);
        }
        DesarrolladorDetailDTO desarrolladorDetailDTO = new DesarrolladorDetailDTO(desarrolladorEntity);
        LOGGER.log(Level.INFO, "DesarrolladorResource getDesarrollador: output: {0}", desarrolladorDetailDTO);
        return desarrolladorDetailDTO;
    }
    
     /**
     * Actualiza  con la información que se recibe en el cuerpo de la petición,
     * el desarrollador con el id recibido en la URL.
     *
     * @param desarrolladorId Identificador del desarrollador que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param desarrollador {@link desarrolladorDTO} El desarrollador que se desea guardar.
     * @return JSON {@link desarrolladorDetailDTO} - el desarrollador guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el desarrollador a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar rl desarrollador.
     */
    @PUT
    @Path("{desarrolladorId: \\d+}")
    public DesarrolladorDetailDTO updateDesarrollador(@PathParam("desarrolladorId") Long desarrolladorId, DesarrolladorDetailDTO desarrollador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DesarrolladorResource updateDesarrollador: input: id: {0} , book: {1}", new Object[]{desarrolladorId, desarrollador});
        desarrollador.setId(desarrolladorId);
        if (dl.getDesarrollador(desarrolladorId) == null) {
            throw new WebApplicationException(PATH1 + desarrolladorId + PATH2, 404);
        }
        DesarrolladorDetailDTO detailDTO = new DesarrolladorDetailDTO(dl.updateDesarrollador( desarrollador.toEntity()));
        LOGGER.log(Level.INFO, "DesarrolladorResource updateDesarrollador: output: {0}", detailDTO);
        return detailDTO;
    }
    
     /**
     * Borra el desarrollador con el id asociado recibido en la URL.
     *
     * @param desarrolladorId Identificador del desarrollador que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el desarrollador.
     */
    @DELETE
    @Path("{desarrolladorId: \\d+}")
    public void deleteDesarrollador(@PathParam("desarrolladorId") Long desarrolladorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DesarrolladorResource deleteDesarrollador: input: {0}", desarrolladorId);
        DesarrolladorEntity entity =dl.getDesarrollador(desarrolladorId);
        if (entity == null) {
            throw new WebApplicationException(PATH1 + desarrolladorId + PATH2, 404);
        }
 
        dl.deleteRequisito(desarrolladorId);
        LOGGER.info("DesarrolladorResource deleteDesarrollador: output: void");
    }
    
      /**
     * Convierte una lista de entidades a DetailDTO.
     *
     * Este método convierte una lista de objetos DesarrolladorEntity a una lista de
     * objetos DesarrolladorDetailDTO (json)
     * @param entityList corresponde a la lista de desarrolladores de tipo Entity que
     * vamos a convertir a DetailDTO.
     * @return la lista de Desarrollador en forma DetailDTO (json)
     */
    private List<DesarrolladorDetailDTO> listEntity2DetailDTO(List<DesarrolladorEntity> entityList) 
    {
        LOGGER.info("ListDetail convirtiendo::");
        List<DesarrolladorDetailDTO> list = new ArrayList<>();
        for (DesarrolladorEntity entity : entityList) 
        {
            list.add(new DesarrolladorDetailDTO(entity));
        }
        LOGGER.info("ListDetail Saliendo::");
        return list;
    }
   
     /**
     * Conexión con el servicio casos de uso a un desarrollador
     * {@link PrizeAuthorResource}
     *
     * Este método conecta la ruta de /prizes con las rutas de /author que
     * dependen del premio, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga del autor del premio.
     *
     * @param desarrolladorId El ID de la editorial con respecto a la cual se accede al
     * servicio.
     * @return El servicio de autor para este premio en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que s genera cuando no se el premio.
     */
    @Path("{desarrolladorId: \\d+}/equipo")
    public Class<DesarrolladorEquipoDesarrolloResource> getDesarrolladorEquipoDesarrolloResource(@PathParam("desarrolladorId") Long desarrolladorId) {
    
        if (dl.getDesarrollador(desarrolladorId) == null) {
            throw new WebApplicationException(PATH1 + desarrolladorId + PATH2, 404);
        }
        return DesarrolladorEquipoDesarrolloResource.class;
    }
}


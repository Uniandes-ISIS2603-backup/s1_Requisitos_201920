/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.PersonaDTO;
import co.edu.uniandes.csw.requisitos.dtos.PersonaDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.PersonaLogic;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
     
     private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());
     /**
      * Crea una nueva persona con la información que se recibe en el cuerpo de la
      * petición y se regresa un objeto idéntico con un id auto-generado por la
      * base de datos.
      * 
      * @param persona (@link PersonaDTO) - La persona que se desea guardar.
      * @return JSON (@link PersonaDTO) - La iteracion guardada con el atributo id
      * autogenerado.
      * @throws BusinessLogicException  (@link BusinessLogicExceptionMapper) -
      * Error de la lógica que se genera cuando ya existe la iteración.
      */
      @POST
    public PersonaDTO createPersona(PersonaDTO persona) throws BusinessLogicException     
    {
        LOGGER.log(Level.INFO, "PersonaResource createPersona: input: {0}");
        PersonaDTO nuevaPersonaDTO = new PersonaDTO(rl.createPersona(persona.toEntity()));
        LOGGER.log(Level.INFO, "PersonaResource createPersona: output: {0}");
        return nuevaPersonaDTO;
    }
    
    /**
     * Busca y devuelve todas las Personas que existen en la aplicacion.
     * 
     * @return JSONArray (@link PersonaDetailDTO) -Las personas encontradas en la 
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PersonaDetailDTO> getPersonas() {
        LOGGER.info("PersonaResource getPersonas: input: void");
        List<PersonaDetailDTO> listaPersonas = listEntity2DetailDTO(rl.getPersonas());
        LOGGER.log(Level.INFO, "PersonaResource getPersonas: output: {0}", listaPersonas);
        return listaPersonas;
    }
    /**
     * Busca la persona con el id asociado recibido en la URL y lo devuelve.
     *
     * @param personaId Identificador de la persona que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link PersonaDetailDTO} - la persona
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la persona.
     */
    @GET
    @Path("{personaId: \\d+}")
    public PersonaDetailDTO getPersona(@PathParam("personaId") Long personaId) {
        LOGGER.log(Level.INFO, "PersonaResource getPersona: input: {0}", personaId);
        PersonaEntity personaEntity = rl.getPersona(personaId);
        if (personaEntity == null) {
            throw new WebApplicationException("El recurso /persona/" + personaId + " no existe.", 404);
        }
        PersonaDetailDTO personaDetailDTO = new PersonaDetailDTO(personaEntity);
        LOGGER.log(Level.INFO, "PersonaResource getPersona: output: {0}", personaDetailDTO);
        return personaDetailDTO;
    }

    /**
     * Actualiza la persona con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param personaId Identificador de la persona que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param persona {@link personaDTO} La persona que se desea guardar.
     * @return JSON {@link personaDetailDTO} - la persona guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la persona a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la persona.
     */
    @PUT
    @Path("{personaId: \\d+}")
    public PersonaDetailDTO updatePersona(@PathParam("personaId") Long personaId, PersonaDetailDTO persona) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PersonaResource updatePersona: input: id: {0} , book: {1}", new Object[]{personaId, persona});
        persona.setId(personaId);
        if (rl.getPersona(personaId) == null) {
            throw new WebApplicationException("El recurso /persona/" + personaId + " no existe.", 404);
        }
        PersonaDetailDTO detailDTO = new PersonaDetailDTO(rl.updatePersona( persona.toEntity()));
        LOGGER.log(Level.INFO, "PersonaResource updatePersona: output: {0}", detailDTO);
        return detailDTO;
    }

       /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PersonaEntity a una lista de
     * objetos PersonaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de iteraciones de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Personaes en forma DTO (json)
     */
    private List<PersonaDetailDTO> listEntity2DetailDTO(List<PersonaEntity> entityList) {
        List<PersonaDetailDTO> list = new ArrayList<>();
        for (PersonaEntity entity : entityList) {
            list.add(new PersonaDetailDTO(entity));
        }
        
        return list;
    }
    
     /**
     * Borra la persona con el id asociado recibido en la URL.
     * @param personaId
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     */
    @DELETE
    @Path("{personaId: \\d+}")
    public void deletePersona(@PathParam("personaId") Long personaId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "PersonaResource deleteReqPersona: input: {0}", personaId);
        PersonaEntity entity = rl.getPersona(personaId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /persona/" + personaId + " no existe.", 404);
        }
        rl.deletePersona(personaId);
        LOGGER.info("PersonaResource deleteReqPersona output: void");
    } 
}

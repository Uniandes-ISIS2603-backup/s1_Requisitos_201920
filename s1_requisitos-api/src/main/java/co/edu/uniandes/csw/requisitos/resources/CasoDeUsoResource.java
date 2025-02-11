/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.resources;

import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDTO;
import co.edu.uniandes.csw.requisitos.dtos.CasoDeUsoDetailDTO;
import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
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
 * @author Maria Alejandra Escalante
 */
//declaraciones necesarias para convertir a json y para pruebas en postman
@Path("casos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CasoDeUsoResource {

    private static final Logger LOGGER = Logger.getLogger(CasoDeUsoResource.class.getName());
    /**
     * Atributo logica
     */
    @Inject
    private CasoDeUsoLogic cl;

    /**
     * Crea una nuevo caso de uso con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param casoDeUso
     * @return JSON {@link CasoDeUsoDTO} -El requisito guardado con el atributo
     * id autogenerado.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @POST
    public CasoDeUsoDTO createCasoDeUso(CasoDeUsoDTO casoDeUso) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CasoDeUsoResource createCasoDeUso: input: {0}", casoDeUso);
        CasoDeUsoEntity casoDeUsoEntity = casoDeUso.toEntity();
        LOGGER.log(Level.INFO, "CasoDeUsoResource createCasoDeUso: input: {0}", casoDeUso);
        casoDeUsoEntity = cl.crearCasoDeUso(casoDeUsoEntity);
        return new CasoDeUsoDTO(casoDeUsoEntity);
    }

    /*
    * retorna una lista con todos los casos de uso como DetailDTO
     */
    @GET
    public List<CasoDeUsoDetailDTO> getCasosdeUso() {
        LOGGER.info("CasoDeUsoResource getcasosDeUso: input: void");
        List<CasoDeUsoDetailDTO> listaCasos = listEntity2DetailDTO(cl.getCasos());
        LOGGER.log(Level.INFO, "CasoDeUsoResource getCasosDeUso: output: {0}", listaCasos);
        return listaCasos;
    }

    /*
    retorna un solo caso de uso dado por id como detailDTO
     */
    @GET
    @Path("{casosId:\\d+}")
    public CasoDeUsoDetailDTO getCasoDeUso(@PathParam("casosId") Long casosId) {
        LOGGER.log(Level.INFO, "CasoDeUsoResource getCasoDeUso: input: {0}", casosId);
        CasoDeUsoEntity entidad = cl.getCaso(casosId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /casos/" + casosId + " no existe.", 404);
        }
        CasoDeUsoDetailDTO detail = new CasoDeUsoDetailDTO(entidad);
        LOGGER.log(Level.INFO, "CasoDeUsoResource getCasoDeUso: output:{0}", detail);
        return detail;
    }

    /*
    actualiza caso de uso y lo retorna como detailDTO
     */
    @PUT
    @Path("{casosId: \\d+}")
    public CasoDeUsoDetailDTO updateCasoDeUso(@PathParam("casosId") Long casosId, CasoDeUsoDTO caso) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CasodeUsoResource updateCasoDeUso: input: id: {0} , caso: {1}", new Object[]{casosId, caso});

        caso.setId(casosId);
        if (cl.getCaso(casosId) == null) {
            throw new WebApplicationException("El resource /casos/" + casosId + " no existe", 404);
        }
        CasoDeUsoDetailDTO nuevo = new CasoDeUsoDetailDTO(cl.updateCasoDeUso(caso.toEntity()));
        LOGGER.log(Level.INFO, "CasoDeUsoResource updateCasoDeUso: output:{0}", nuevo);
        return nuevo;
    }

    /*
    borra un caso de uso dado por id
     */
    @Path("{casosId: \\d+}")
    @DELETE
    public void deleteCaso(@PathParam("casosId") Long casosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CasoDeUsoResource deleteCaso: input: {0}", casosId);
        CasoDeUsoEntity nuevo = cl.getCaso(casosId);
        if (nuevo == null) {
            throw new WebApplicationException("Resource /casos/" + casosId + " no existe ", 404);
        }
        LOGGER.info("CasoDeUsoResource deleteCaso: output: void");
        cl.deleteCaso(casosId);
        LOGGER.info("CasoDeUsoResource deleteCasoDeUso: output: void");
    }
    
    /*
    metodo que convierte una lista de entidades a una lista de DetailDTO
     */
    private List<CasoDeUsoDetailDTO> listEntity2DetailDTO(List<CasoDeUsoEntity> entityList) {
        LOGGER.info("CasoDeUsoResource listEntity2DetailDTO: input");
        List<CasoDeUsoDetailDTO> list = new ArrayList<>();
        for (CasoDeUsoEntity entity : entityList) {
            list.add(new CasoDeUsoDetailDTO(entity));
        }
        LOGGER.info("CasoDeUsoResource listEntity2DetailDTO: output");
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
     * @param prizesId El ID de la editorial con respecto a la cual se accede al
     * servicio.
     * @return El servicio de autor para este premio en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que s genera cuando no se el premio.
     */
    @Path("{casosId: \\d+}/desarrolladorc")
    public Class<CasoDeUsoDesarrolladorResource> getCasoDeUsoDesarrolladorResource(@PathParam("casosId") Long casosId) {
    
        if (cl.getCaso(casosId) == null) {
            throw new WebApplicationException("El recurso /casos/" + casosId + " no existe.", 404);
        }
        return CasoDeUsoDesarrolladorResource.class;
    }
    
    
  

}
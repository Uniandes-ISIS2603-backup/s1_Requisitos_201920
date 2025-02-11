/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;


import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicole Bahamon Martínez
 */
@Stateless
public class ModificacionesDesarrolladorLogic {
 
    private static final Logger LOGGER = Logger.getLogger(ModificacionesDesarrolladorLogic.class.getName());

    @Inject
    private ModificacionesPersistence modificacionPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private DesarrolladorPersistence desarrolladorPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * Agregar un desarrollador a un requisito
     *
     * @param modificacionesId El id modificacion a guardar
     * @param desarrolladorId El id del desarrollador al cual se le va a guardar la modificacion.
     * @return La modificacion que fue agregado al desarrollador.
     */
    public DesarrolladorEntity addAuthor(Long modificacionesId, Long desarrolladorId) {
       
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(desarrolladorId);
        ModificacionesEntity modificacionEntity = modificacionPersistence.find(modificacionesId);
       modificacionEntity.setDesarrolladorModificaciones(desarrolladorEntity);
        List<ModificacionesEntity> listaFun=desarrolladorEntity.getModificaciones();
        listaFun.add(modificacionEntity);
        desarrolladorEntity.setModificaciones(listaFun);
        modificacionPersistence.update(modificacionEntity);
       desarrolladorPersistence.update(desarrolladorEntity);
       return desarrolladorPersistence.find(desarrolladorId);
    }
  
    /**
     *
     * Obtener una modificacion por medio de su id y el de su desarrollador.
     *
     * @param modificacionesId id de la modificacion a ser buscado.
     * @return el desarrollador solicitada por medio de su id.
     */
    public DesarrolladorEntity getDesarrollador(Long modificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el desarrollador del requisito con id = {0}", modificacionesId);
        DesarrolladorEntity authorEntity =modificacionPersistence.find(modificacionesId).getDesarrolladorModificaciones();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el desarrollador del requisito con id = {0}", modificacionesId);
       return authorEntity;
    }

    /**
     * Remplazar desarrollador de una modificacion
     *
     * @param modificacionesId el id de la modificacion que se quiere actualizar.
     * @param desarrolladorId El id del nuevo desarrollador asociado al premio.
     * @return el nuevo desarrollador asociado.
     */
    public DesarrolladorEntity replaceAuthor(Long modificacionesId, Long desarrolladorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el desarrollador de la modificacion con id = {0}", modificacionesId);
        DesarrolladorEntity autorEntity = desarrolladorPersistence.find(desarrolladorId);
        ModificacionesEntity modificacionEntity = modificacionPersistence.find(modificacionesId);
       modificacionEntity.setDesarrolladorModificaciones(autorEntity);
        modificacionPersistence.update(modificacionEntity);
       return desarrolladorPersistence.find(desarrolladorId);
    }

    /**
     * Borrar el desarrollador de una modificacion
     *
     * @param modificacionesId La modificacion que se desea borrar del desarrollador.
     * @throws BusinessLogicException si el requisito no tiene autor
     */
    public void removeDesarrollador(Long modificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el desarrollador de la modificacion con id = {0}", modificacionesId);
       ModificacionesEntity modificacionEntity = modificacionPersistence.find(modificacionesId);
        if (modificacionEntity.getDesarrolladorModificaciones() == null) {
            throw new BusinessLogicException("La modificacion no tiene desarrollador");
        }
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(modificacionEntity.getDesarrolladorModificaciones().getId());
        modificacionEntity.setDesarrolladorModificaciones(null);
        desarrolladorEntity.getModificaciones().remove(modificacionEntity);
       
    }
    
}
 


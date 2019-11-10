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
     * Agregar un desarrollador a una modificacion
     *
     * @param modificacionId El id modificacion a guardar
     * @param desarrolladorId El id del desarrollador al cual se le va a guardar la modificacion.
     * @return El requisito que fue agregado al desarrollador.
     */
    public DesarrolladorEntity addAuthor(Long modificacionId, Long desarrolladorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el desarrollador con id = {0} a la modificacion con id = " + modificacionId, desarrolladorId);
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(desarrolladorId);
        ModificacionesEntity modificacionEntity = modificacionPersistence.find(modificacionId);
        modificacionEntity.setDesarrolladorModificaciones(desarrolladorEntity);
        List<ModificacionesEntity> listaFun=desarrolladorEntity.getModificaciones();
        listaFun.add(modificacionEntity);
        desarrolladorEntity.setModificaciones(listaFun);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + modificacionId, desarrolladorId);
        modificacionPersistence.update(modificacionEntity);
        desarrolladorPersistence.update(desarrolladorEntity);
        return desarrolladorPersistence.find(desarrolladorId);

    }
 
    /**
     *
     * Obtener una modificacion por medio de su id y el de su desarrollador.
     *
     * @param modificacionId id de la modificacion a ser buscada.
     * @return el desarrollador solicitada por medio de su id.
     */
    public DesarrolladorEntity getDesarrollador(Long modificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el desarrollador del requisito con id = {0}", modificacionId);
        DesarrolladorEntity authorEntity = modificacionPersistence.find(modificacionId).getDesarrolladorModificaciones();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el desarrollador del requisito con id = {0}", modificacionId);
        return authorEntity;
    }

    /**
     * Remplazar desarrollador de una modificacion
     *
     * @param modificacionId el id de la modificacion que se quiere actualizar.
     * @param desarrolladorId El id del nuevo desarrollador asociado a la modificacion.
     * @return el nuevo desarrollador asociado.
     */
    public DesarrolladorEntity replaceAuthor(Long modificacionId, Long desarrolladorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el desarrollador de la modificacion con id = {0}", modificacionId);
        DesarrolladorEntity autorEntity = desarrolladorPersistence.find(desarrolladorId);
        ModificacionesEntity modificacionEntity = modificacionPersistence.find(modificacionId);
        modificacionEntity.setDesarrolladorModificaciones(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el desarrollador con id = {0} la modificaciòn con id = " + modificacionId, desarrolladorId);
        modificacionPersistence.update(modificacionEntity);
        return desarrolladorPersistence.find(desarrolladorId);
    }

    /**
     * Borrar el desarrollador de una modificacion
     *
     * @param modificacionId La modificacion que se desea borrar del desarrollador.
     * @throws BusinessLogicException si el requisito no tiene autor
     */
    public void removeDesarrollador(Long modificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el desarrollador de la modificacion con id = {0}", modificacionId);
        ModificacionesEntity modificacionEntity = modificacionPersistence.find(modificacionId);
        if (modificacionEntity.getDesarrolladorModificaciones() == null) {
            throw new BusinessLogicException("El requisito no tiene desarrollador");
        }
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(modificacionEntity.getDesarrolladorModificaciones().getId());
        modificacionEntity.setDesarrolladorModificaciones(null);
        desarrolladorEntity.getModificaciones().remove(modificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el desarrollador con id = {0} de la modificacion con id = " + modificacionId, desarrolladorEntity.getId());
    }
}
 


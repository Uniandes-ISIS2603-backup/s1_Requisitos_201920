/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicole Bahamon Martínez
 */
@Stateless
public class RequisitosDesarrolladorLogic {
 
    private static final Logger LOGGER = Logger.getLogger(RequisitosDesarrolladorLogic.class.getName());

    @Inject
    private RequisitoPersistence requisitoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private DesarrolladorPersistence desarrolladorPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un desarrollador a un requisito
     *
     * @param RequisitoId El id requisito a guardar
     * @param DesarrolladorId El id del desarrollador al cual se le va a guardar el premio.
     * @return El requisito que fue agregado al desarrollador.
     */
    public DesarrolladorEntity addAuthor(Long RequisitoId, Long DesarrolladorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el desarrollador con id = {0} al requisito con id = " + RequisitoId, DesarrolladorId);
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(DesarrolladorId);
        RequisitosEntity requisitosEntity = requisitoPersistence.find(RequisitoId);
        requisitosEntity.setAutor(desarrolladorEntity.getNombre());
        requisitosEntity.setDesarrollador(desarrolladorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + RequisitoId, DesarrolladorId);
        return desarrolladorPersistence.find(DesarrolladorId);
    }

    /**
     *
     * Obtener un requisito por medio de su id y el de su desarrollador.
     *
     * @param RequisitoId id del requisito a ser buscado.
     * @return el desarrollador solicitada por medio de su id.
     */
    public DesarrolladorEntity getDesarrollador(Long RequisitoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el desarrollador del requisito con id = {0}", RequisitoId);
        DesarrolladorEntity authorEntity = requisitoPersistence.find(RequisitoId).getDesarrollador();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el desarrollador del requisito con id = {0}", RequisitoId);
        return authorEntity;
    }

    /**
     * Remplazar desarrollador de un requisito
     *
     * @param RequisitoId el id del requisito que se quiere actualizar.
     * @param DesarrolladorId El id del nuevo desarrollador asociado al premio.
     * @return el nuevo desarrollador asociado.
     */
    public DesarrolladorEntity replaceAuthor(Long RequisitoId, Long DesarrolladorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor del premio premio con id = {0}", RequisitoId);
        DesarrolladorEntity autorEntity = desarrolladorPersistence.find(DesarrolladorId);
        RequisitosEntity requisitoEntity = requisitoPersistence.find(RequisitoId);
        requisitoEntity.setDesarrollador(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + RequisitoId, DesarrolladorId);
        return desarrolladorPersistence.find(DesarrolladorId);
    }

    /**
     * Borrar el autor de un requisito
     *
     * @param RequisitoId El requisito que se desea borrar del autor.
     * @throws BusinessLogicException si el requisito no tiene autor
     */
    public void removeDesarrollador(Long RequisitoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el desarrollador del requisito con id = {0}", RequisitoId);
        RequisitosEntity requisitoEntity = requisitoPersistence.find(RequisitoId);
        if (requisitoEntity.getDesarrollador() == null) {
            throw new BusinessLogicException("El requisito no tiene desarrollador");
        }
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(requisitoEntity.getDesarrollador().getId());
        requisitoEntity.setDesarrollador(null);
        desarrolladorEntity.getRequisitos().remove(requisitoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el desarrollador con id = {0} del requisito con id = " + RequisitoId, desarrolladorEntity.getId());
    }
}
 


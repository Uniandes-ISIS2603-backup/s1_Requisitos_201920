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
public class RequisitosDesarrolladorLogic {
 
    private static final Logger LOGGER = Logger.getLogger(RequisitosDesarrolladorLogic.class.getName());

    @Inject
    private RequisitoPersistence requisitoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private DesarrolladorPersistence desarrolladorPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * Agregar un desarrollador a un requisito
     *
     * @param requisitoId El id requisito a guardar
     * @param desarrolladorId El id del desarrollador al cual se le va a guardar el premio.
     * @return El requisito que fue agregado al desarrollador.
     */
    public DesarrolladorEntity addAuthor(Long requisitoId, Long desarrolladorId) {
       
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(desarrolladorId);
        RequisitosEntity requisitosEntity = requisitoPersistence.find(requisitoId);
        requisitosEntity.setAutor(desarrolladorEntity.getNombre());
        requisitosEntity.setDesarrollador(desarrolladorEntity);
        List<RequisitosEntity> listaFun=desarrolladorEntity.getRequisitos();
        listaFun.add(requisitosEntity);
        desarrolladorEntity.setRequisitos(listaFun);
        requisitoPersistence.update(requisitosEntity);
       desarrolladorPersistence.update(desarrolladorEntity);
       return desarrolladorPersistence.find(desarrolladorId);
    }
  
    /**
     *
     * Obtener un requisito por medio de su id y el de su desarrollador.
     *
     * @param requisitoId id del requisito a ser buscado.
     * @return el desarrollador solicitada por medio de su id.
     */
    public DesarrolladorEntity getDesarrollador(Long requisitoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el desarrollador del requisito con id = {0}", requisitoId);
        DesarrolladorEntity authorEntity = requisitoPersistence.find(requisitoId).getDesarrollador();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el desarrollador del requisito con id = {0}", requisitoId);
       return authorEntity;
    }

    /**
     * Remplazar desarrollador de un requisito
     *
     * @param requisitoId el id del requisito que se quiere actualizar.
     * @param desarrolladorId El id del nuevo desarrollador asociado al premio.
     * @return el nuevo desarrollador asociado.
     */
    public DesarrolladorEntity replaceAuthor(Long requisitoId, Long desarrolladorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor del premio premio con id = {0}", requisitoId);
        DesarrolladorEntity autorEntity = desarrolladorPersistence.find(desarrolladorId);
        RequisitosEntity requisitoEntity = requisitoPersistence.find(requisitoId);
        requisitoEntity.setDesarrollador(autorEntity);
        requisitoPersistence.update(requisitoEntity);
       return desarrolladorPersistence.find(desarrolladorId);
    }

    /**
     * Borrar el autor de un requisito
     *
     * @param requisitoId El requisito que se desea borrar del autor.
     * @throws BusinessLogicException si el requisito no tiene autor
     */
    public void removeDesarrollador(Long requisitoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el desarrollador del requisito con id = {0}", requisitoId);
        RequisitosEntity requisitoEntity = requisitoPersistence.find(requisitoId);
        if (requisitoEntity.getDesarrollador() == null) {
            throw new BusinessLogicException("El requisito no tiene desarrollador");
        }
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(requisitoEntity.getDesarrollador().getId());
        requisitoEntity.setDesarrollador(null);
        desarrolladorEntity.getRequisitos().remove(requisitoEntity);
       
    }
    
}
 


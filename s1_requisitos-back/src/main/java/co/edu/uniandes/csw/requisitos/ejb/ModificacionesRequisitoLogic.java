/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Martinez
 */
@Stateless
public class ModificacionesRequisitoLogic {
    private static final Logger LOGGER = Logger.getLogger(ModificacionesRequisitoLogic.class.getName());
     
    @Inject
    private RequisitoPersistence requisitoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ModificacionesPersistence modPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
   /**
     * Agregar un requisito a un caso de uso
     *
     * @param casoDeUsoId El id del caso de uso
     * @param requisitoId id del requisito
     * @return El requisito con el caso de uso adicionado
     */
    public RequisitosEntity addRequisito(Long modId, Long reqId) {
        LOGGER.log(Level.INFO, String.format("Inicia proceso de asociar el requisito con id = %d al casoDeUso con id = %d" , reqId, modId));
        //Encuentra las dos entidades
        ModificacionesEntity modEntity = modPersistence.find(modId);
        RequisitosEntity reqEntity = requisitoPersistence.find(reqId);
        //Añade al requisito el caso de uso
        modEntity.setModificacionesRequisito(reqEntity);
        //Añade al caso de uso el requisito
        List<ModificacionesEntity> lista = reqEntity.getModificaciones();
        lista.add(modEntity);
        reqEntity.setModificaciones(lista);
        //Actualiza las dos entidades en la base de datos
        requisitoPersistence.update(reqEntity);
        modPersistence.update(modEntity);
        LOGGER.log(Level.INFO,String.format("Termina proceso de asociar el requisito id = %d al casoDeUso con id = %d", reqId, modId));
        return requisitoPersistence.find(reqId);
    }
    
    /**
     * Obtener un requisito por medio del id de una de sus modificaciones
     * @param modId id de la modificacion
     * @return requisito al que corresponde la modificacion
     */
    public RequisitosEntity getRequisito(Long modId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el requisito de la modificacion con id = {0}" , modId);
        RequisitosEntity req = modPersistence.find(modId).getModificacionesRequisito();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el requisito de la modificacion con id = {0}", modId);
        return req;
    }
    
    /**
     * Borrar el caso de uso de un requisito
     *
     * @param requisitoId El requisito que se desea borrar del caso de uso.
     * @throws BusinessLogicException si el requisito no tiene caso de uso
     */
    public void removeRequisito(Long modId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el requisito de la modificacion con id = " +modId, modId);
        //Encuentra las dos entidades
        ModificacionesEntity modEntity = modPersistence.find(modId);
        if (modEntity.getModificacionesRequisito()==null) {
            throw new BusinessLogicException("La modificacion no tiene requisito");
        }
        RequisitosEntity reqEntity = requisitoPersistence.find(modEntity.getModificacionesRequisito().getId());
        if (reqEntity==null) {
            throw new BusinessLogicException("La modificacion no tiene requisito");
        }
        //Elimina del requisito el caso de uso y del caso de uso el requisito
        modEntity.setModificacionesRequisito(null);
        reqEntity.getModificaciones().remove(modEntity);
        //Actualiza dentro de la base de datos las entidades
        modPersistence.update(modEntity);
        requisitoPersistence.update(reqEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el caso de uso con id = {0} del requisito con id = " + modId, modEntity.getId());
    }
    
    /**
     * Reemplazar caso de uso de un requisito
     *
     * @param requisitoId el id del requisito que se quiere actualizar.
     * @param casoDeUsoId El id del nuevo caso de uso asociado al requisito.
     * @return el nuevo caso de uso asociado.
     */
    public RequisitosEntity replaceRequisito(Long modId, Long reqId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el requisito de la modificacion con id = " +modId, modId);
        //Encuentra las 3 entidades
        RequisitosEntity reqEntityNuevo = requisitoPersistence.find(reqId);
        ModificacionesEntity modEntity = modPersistence.find(modId);
        RequisitosEntity reqEntityViejo= modEntity.getModificacionesRequisito();
        //actualiza la entidad de requisito
        modEntity.setModificacionesRequisito(reqEntityNuevo);
        //actualiza la lista de la entidad casoEntityNuevo
        List<ModificacionesEntity> listaNuevo = reqEntityNuevo.getModificaciones();
        listaNuevo.add(modEntity);
        reqEntityNuevo.setModificaciones(listaNuevo);
        //actualiza la lista de la entidad casoEntityViejo
        List<ModificacionesEntity> listaViejo = reqEntityViejo.getModificaciones();
        listaViejo.remove(modEntity);
        reqEntityViejo.setModificaciones(listaViejo);
        //actualiza las tres entidades en la base de datos
        requisitoPersistence.update(reqEntityViejo);
        requisitoPersistence.update(reqEntityNuevo);
        modPersistence.update(modEntity);
       
        LOGGER.log(Level.INFO, "Termina proceso de asociar el caso de uso con id = {0} al requisito con id = " + modId, reqId);
        return requisitoPersistence.find(reqId);
    }
}

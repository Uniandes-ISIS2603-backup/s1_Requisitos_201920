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

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Martinez
 */
@Stateless
public class ModificacionesRequisitoLogic {
    
     
    @Inject
    private RequisitoPersistence requisitoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ModificacionesPersistence modPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
   /**
     * Agregar un requisito a un caso de uso
     *
     * @param modId El id del caso de uso
     * @param reqId id del requisito
     * @return El requisito con el caso de uso adicionado
     */
    public RequisitosEntity addRequisito(Long modId, Long reqId) {
       
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
        
        return requisitoPersistence.find(reqId);
    }
    
    /**
     * Obtener un requisito por medio del id de una de sus modificaciones
     * @param modId id de la modificacion
     * @return requisito al que corresponde la modificacion
     */
    public RequisitosEntity getRequisito(Long modId) 
    {
       
        RequisitosEntity req = modPersistence.find(modId).getModificacionesRequisito();
       
        return req;
    }
    
    /**
     * Borrar el caso de uso de un requisito
     *
     * @param modId El requisito que se desea borrar del caso de uso.
     * @throws BusinessLogicException si el requisito no tiene caso de uso
     */
    public void removeRequisito(Long modId) throws BusinessLogicException {
      
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
        
    }
    
    /**
     * Reemplazar caso de uso de un requisito
     *
     * @param reqId el id del requisito que se quiere actualizar.
     * @param modId El id del nuevo caso de uso asociado al requisito.
     * @return el nuevo caso de uso asociado.
     */
    public RequisitosEntity replaceRequisito(Long modId, Long reqId) {
      
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
       
        
        return requisitoPersistence.find(reqId);
    }
}

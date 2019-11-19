/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Nicolás Andrés Tobo Urrutia
 */
public class RequisitosCasoDeUsoLogic 
{
     private static final Logger LOGGER = Logger.getLogger(RequisitosCasoDeUsoLogic.class.getName());
     
    @Inject
    private RequisitoPersistence requisitoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private CasoDeUsoPersistence casoDeUsoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     * Agregar un requisito a un caso de uso
     *
     * @param casoDeUsoId El id del caso de uso
     * @param requisitoId id del requisito
     * @return El requisito con el caso de uso adicionado
     */
    public CasoDeUsoEntity addCasoDeUso(Long requisitoId, Long casoDeUsoId) {
        LOGGER.log(Level.INFO,"Inicia proceso de asociar el requisito con id ={0} al casoDeUso con id = {1} ",new Object[]{requisitoId,casoDeUsoId});
        //Encuentra las dos entidades
        RequisitosEntity requisitoEntity = requisitoPersistence.find(requisitoId);
        CasoDeUsoEntity casoEntity = casoDeUsoPersistence.find(casoDeUsoId);
        //Añade al requisito el caso de uso
        requisitoEntity.setRequisitosFuncionalesCaso(casoEntity);
        //Añade al caso de uso el requisito
        List<RequisitosEntity> listaFun=casoEntity.getFuncionales();
        listaFun.add(requisitoEntity);
        casoEntity.setFuncionales(listaFun);
        //Actualiza las dos entidades en la base de datos
        casoDeUsoPersistence.update(casoEntity);
        requisitoPersistence.update(requisitoEntity);
        LOGGER.log(Level.INFO,"Termina proceso de asociar el requisito id ={0} al casoDeUso con id = {1}",new Object[]{requisitoId,casoDeUsoId});
        return casoDeUsoPersistence.find(casoDeUsoId);
    }
    
    /**
     *
     * Obtener un requisito por medio de su id y el de su casoDeUso.
     *
     * @param requisitoId id del requisito a ser buscado.
     * @return el CasoDeUso solicitada por medio de su id.
     */
    public CasoDeUsoEntity getCasoDeUso(Long requisitoId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el casoDeUso del requisito con id = {0}", requisitoId);
        CasoDeUsoEntity casoEntity = requisitoPersistence.find(requisitoId).getRequisitosFuncionalesCaso();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el casoDeUso del requisito con id = {0}", requisitoId);
        return casoEntity;
    }
    
    /**
     * Borrar el caso de uso de un requisito
     *
     * @param requisitoId El requisito que se desea borrar del caso de uso.
     * @throws BusinessLogicException si el requisito no tiene caso de uso
     */
    public void removeCasoDeUso(Long requisitoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el caso de uso del requisito con id = {0}", requisitoId);
        //Encuentra las dos entidades
        RequisitosEntity requisitoEntity = requisitoPersistence.find(requisitoId);
        if (requisitoEntity.getRequisitosFuncionalesCaso() == null) {
            throw new BusinessLogicException("El requisito no tiene caso de uso");
        }
        CasoDeUsoEntity casoEntity = casoDeUsoPersistence.find(requisitoEntity.getRequisitosFuncionalesCaso().getId());
        //Elimina del requisito el caso de uso y del caso de uso el requisito
        requisitoEntity.setRequisitosFuncionalesCaso(null);
        casoEntity.getFuncionales().remove(requisitoEntity);
        //Actualiza dentro de la base de datos las entidades
        requisitoPersistence.update(requisitoEntity);
        casoDeUsoPersistence.update(casoEntity);
        LOGGER.log(Level.INFO,"Termina proceso de borrar el caso de uso con id = {0} del requisito con id = {1} ",new Object[]{casoEntity.getId(), requisitoEntity.getId()});
    }
    
    /**
     * Reemplazar caso de uso de un requisito
     *
     * @param requisitoId el id del requisito que se quiere actualizar.
     * @param casoDeUsoId El id del nuevo caso de uso asociado al requisito.
     * @return el nuevo caso de uso asociado.
     */
    public CasoDeUsoEntity replaceCasoDeUso(Long requisitoId, Long casoDeUsoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el caso de uso del requisito requisito con id = {0}", requisitoId);
        //Encuentra las 3 entidades
        CasoDeUsoEntity casoEntityNuevo = casoDeUsoPersistence.find(casoDeUsoId);
        RequisitosEntity requisitoEntity = requisitoPersistence.find(requisitoId);
        CasoDeUsoEntity casoEntityViejo= requisitoEntity.getRequisitosFuncionalesCaso();
        //actualiza la entidad de requisito
        requisitoEntity.setRequisitosFuncionalesCaso(casoEntityNuevo);
        //actualiza la lista de la entidad casoEntityNuevo
        List<RequisitosEntity>listaNuevo=casoEntityNuevo.getFuncionales();
        listaNuevo.add(requisitoEntity);
        casoEntityNuevo.setFuncionales(listaNuevo);
        //actualiza la lista de la entidad casoEntityViejo
        List<RequisitosEntity>listaViejo=casoEntityViejo.getFuncionales();
        listaViejo.remove(requisitoEntity);
        casoEntityViejo.setFuncionales(listaViejo);
        //actualiza las tres entidades en la base de datos
        casoDeUsoPersistence.update(casoEntityViejo);
        casoDeUsoPersistence.update(casoEntityNuevo);
        requisitoPersistence.update(requisitoEntity);
       
        LOGGER.log(Level.INFO,"Termina proceso de asociar el caso de uso con id = {0} al requisito con id ={1}",new Object[]{casoDeUsoId,requisitoId});
        return casoDeUsoPersistence.find(casoDeUsoId);
    }
    
    
}

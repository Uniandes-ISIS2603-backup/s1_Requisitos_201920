/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.IteracionPersistence;
import co.edu.uniandes.csw.requisitos.persistence.ProyectoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class ProyectoIteracionLogic {
    private static final Logger LOGGER = Logger.getLogger(ProyectoIteracionLogic.class.getName());
     
    @Inject
    private IteracionPersistence iteracionPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ProyectoPersistence proyectoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    /**
     * Agregar una iteracion a un proyecto
     *
     * @param proyectoId El id del proyecto al que se le desea ingresar la
     * @param iteracionId id del iteracion
     * @return El proyecto con la iteración añadida
     */
    public ProyectoEntity addProyecto(Long iteracionId, Long proyectoId) {
        LOGGER.log(Level.INFO,"Inicia proceso de asociar la iteracion con id ={0} al proyecto con id = {1} ",new Object[]{iteracionId,proyectoId});
        //Encuentra las dos entidades
        IteracionEntity iteracionEntity = iteracionPersistence.find(iteracionId);
        ProyectoEntity proyectoEntity = proyectoPersistence.find(proyectoId);
        //Añade la iteracion al proyecto
        iteracionEntity.setIteracionProyecto(proyectoEntity);
        //Añade al proyecto la iteracion
        List<IteracionEntity> listaFun= proyectoEntity.getIteraciones();
        listaFun.add(iteracionEntity);
        proyectoEntity.setIteraciones(listaFun);
        //Actualiza las dos entidades en la base de datos
        proyectoPersistence.update(proyectoEntity);
        iteracionPersistence.update(iteracionEntity);
        LOGGER.log(Level.INFO,"Termina proceso de asociar la iteracion id ={0} al proyecto con id = {1}",new Object[]{iteracionId,proyectoId});
        return proyectoPersistence.find(proyectoId);
    }
    
    /**
     *
     * Obtener el proyecto de una iteracion.
     *
     * @param iteracionId id de la iteracion a ser buscado.
     * @return el proyecto solicitada por medio de su id.
     */
    public ProyectoEntity getProyecto(Long iteracionId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el proyecto de la iteracion con id = {0}", iteracionId);
        ProyectoEntity proyectoEntity = iteracionPersistence.find(iteracionId).getIteracionProyecto();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el proyecto de la iteracion con id = {0}", iteracionId);
        return proyectoEntity;
    }
    
    /**
     * Borrar el caso de uso de un iteracion
     *
     * @param iteracionId la iteracion que se desea borrar del proyecto.
     * @throws BusinessLogicException la iteracion no tiene proyecto
     */
    public void removeProyecto(Long iteracionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el proyecto de la iteracion con id = {0}", iteracionId);
        //Encuentra las dos entidades
        IteracionEntity iteracionEntity = iteracionPersistence.find(iteracionId);
        if (iteracionEntity.getIteracionProyecto() == null) {
            throw new BusinessLogicException("La iteracion no tiene un proyecto asociado");
        }
        ProyectoEntity proyectoEntity = proyectoPersistence.find(iteracionEntity.getIteracionProyecto().getId());
        //Elimina del iteracion el caso de uso y del caso de uso el iteracion
        iteracionEntity.setIteracionProyecto(null);
        proyectoEntity.getIteraciones().remove(iteracionEntity);
        //Actualiza dentro de la base de datos las entidades
        iteracionPersistence.update(iteracionEntity);
        proyectoPersistence.update(proyectoEntity);
        LOGGER.log(Level.INFO,"Termina proceso de borrar el proyecto con id = {0} de la iteracion con id = {1} ",new Object[]{proyectoEntity.getId(), iteracionEntity.getId()});
    }
    
    /**
     * Reemplazar caso de uso de un iteracion
     * @pre Se asume que existen los dos Casos de uso y que se va a sustituir uno con otro
     * @param iteracionId el id del iteracion que se quiere actualizar.
     * @param proyectoId El id del nuevo caso de uso asociado al iteracion.
     * @return el nuevo caso de uso asociado.
     */
    public ProyectoEntity replaceProyecto(Long iteracionId, Long proyectoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el caso de uso del iteracion iteracion con id = {0}", iteracionId);
        //Encuentra las 3 entidades
        ProyectoEntity casoEntityNuevo = proyectoPersistence.find(proyectoId);
        IteracionEntity iteracionEntity = iteracionPersistence.find(iteracionId);
        ProyectoEntity casoEntityViejo = iteracionEntity.getIteracionProyecto();
        //actualiza la entidad de iteracion
        iteracionEntity.setIteracionProyecto(casoEntityNuevo);
        //actualiza la lista de la entidad casoEntityNuevo
        List<IteracionEntity>listaNuevo=casoEntityNuevo.getIteraciones();
        listaNuevo.add(iteracionEntity);
        casoEntityNuevo.setIteraciones(listaNuevo);
        //actualiza la lista de la entidad casoEntityViejo
        List<IteracionEntity>listaViejo=casoEntityViejo.getIteraciones();
        listaViejo.remove(iteracionEntity);
        casoEntityViejo.setIteraciones(listaViejo);
        //actualiza las tres entidades en la base de datos
        proyectoPersistence.update(casoEntityViejo);
        proyectoPersistence.update(casoEntityNuevo);
        iteracionPersistence.update(iteracionEntity);
       
        LOGGER.log(Level.INFO,"Termina proceso de asociar el caso de uso con id = {0} al iteracion con id ={1}",new Object[]{proyectoId,iteracionId});
        return proyectoPersistence.find(proyectoId);
    }
    
    
}

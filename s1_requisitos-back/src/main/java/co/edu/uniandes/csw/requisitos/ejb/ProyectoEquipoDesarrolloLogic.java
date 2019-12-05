/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.persistence.EquipoDesarrolloPersistence;
import co.edu.uniandes.csw.requisitos.persistence.ProyectoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class ProyectoEquipoDesarrolloLogic {
      private static final Logger LOGGER = Logger.getLogger(ProyectoEquipoDesarrolloLogic.class.getName());

    @Inject
    private ProyectoPersistence proyectoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private EquipoDesarrolloPersistence desarrolloPersistence;// Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     /**
     * Agregar un desarrollador a una modificacion
     *
     * @param proyectoId El id  a guardar
     * @param equipoId El id del proyecto al cual se le va a guardar el equipo de desarrollo.
     * @return El requisito que fue agregado al desarrollador.
     */
    public EquipoDesarrolloEntity addEquipoDesarollador(Long proyectoId, Long equipoId) {
        
        EquipoDesarrolloEntity desarrolladorEntity = desarrolloPersistence.find(equipoId);
        ProyectoEntity proyectoEntity = proyectoPersistence.find(proyectoId);
        proyectoEntity.setEquipo(desarrolladorEntity);
        proyectoPersistence.update(proyectoEntity);
        
        return desarrolladorEntity;
    }

    /**
     *
     * Obtener una modificacion por medio de su id y el de su desarrollador.
     *
     * @param proyectoId id del poryecto a ser buscada.
     * @return el equipo de desarrollo solicitado por medio de su id.
     */
    public EquipoDesarrolloEntity getEquipoDesarrollo(Long proyectoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el equipo de desarrollo del proyecto con id = {0}", proyectoId);
        EquipoDesarrolloEntity equipoEntity = proyectoPersistence.find(proyectoId).getEquipo();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el equipo de desarollo del proyecto con id = {0}", proyectoId);
        return equipoEntity;
    }

    /**
     * Remplazar 
     *
     * @param modificacionId el id de la modificacion que se quiere actualizar.
     * @param desarrolladorId El id del nuevo desarrollador asociado a la modificacion.
     * @return el nuevo desarrollador asociado.
     */
   
     public EquipoDesarrolloEntity cambiarDesarrollador(Long proyectoId, Long casoId) {
        ProyectoEntity proyectoEntity= proyectoPersistence.find(proyectoId);
        
        EquipoDesarrolloEntity equipoEntity= desarrolloPersistence.find(casoId);
        proyectoEntity.setEquipo(equipoEntity);
        proyectoPersistence.update(proyectoEntity);
        
        return equipoEntity;

    
    }

    
}

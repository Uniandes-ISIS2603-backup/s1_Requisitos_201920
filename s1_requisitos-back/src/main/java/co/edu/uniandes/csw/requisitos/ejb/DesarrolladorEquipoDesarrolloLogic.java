/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
import co.edu.uniandes.csw.requisitos.persistence.EquipoDesarrolloPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @equipoDesarrollo jf.rubio
 */
@Stateless
public class DesarrolladorEquipoDesarrolloLogic {
    
    @Inject
    private EquipoDesarrolloPersistence equipoDesarrolloPersistence;
   
    @Inject
    private DesarrolladorPersistence desarrolladorPersistence;
    
    /**
     * Agrega un equipo a un desarrollador
     * @param equipoId el id del equipo
     * @param desarrolladorId el id del desarrollador
     * @return la entidad del equipo de desarrollo
     */
    public EquipoDesarrolloEntity addEquipoDesarrollo(Long equipoId, Long desarrolladorId)
    {
        EquipoDesarrolloEntity equipoDesarrolloEntity = equipoDesarrolloPersistence.find(equipoId);
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(desarrolladorId);
        desarrolladorEntity.setEquipoDesarrollo(equipoDesarrolloEntity);
        desarrolladorPersistence.update(desarrolladorEntity);
        return equipoDesarrolloEntity; 
    }
    
     /**
     *
     * Obtener un premio por medio de su id y el de su autor.
     *
     * @param desarrolladorsId id del premio a ser buscado.
     * @return el autor solicitada por medio de su id.
     */
    public EquipoDesarrolloEntity getEquipoDesarrollo(Long desarrolladorsId) {
        EquipoDesarrolloEntity equipoDesarrolloEntity = desarrolladorPersistence.find(desarrolladorsId).getEquipoDesarrollo();
        return equipoDesarrolloEntity;
    }

    /**
     * Remplazar autor de un premio
     *
     * @param desarrolladorsId el id del premio que se quiere actualizar.
     * @param equipoDesarrollosId El id del nuebo autor asociado al premio.
     * @return el nuevo autor asociado.
     */
    public EquipoDesarrolloEntity replaceEquipoDesarrollo(Long desarrolladorsId, Long equipoDesarrollosId) {
        EquipoDesarrolloEntity autorEntity = equipoDesarrolloPersistence.find(equipoDesarrollosId);
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(desarrolladorsId);
        desarrolladorEntity.setEquipoDesarrollo(autorEntity);
        return equipoDesarrolloPersistence.find(equipoDesarrollosId);
    }

    /**
     * Borrar el autor de un premio
     *
     * @param desarrolladorsId El premio que se desea borrar del autor.
     * @throws BusinessLogicException si el premio no tiene autor
     */
    public void removeEquipoDesarrollo(Long desarrolladorsId) throws BusinessLogicException {
        DesarrolladorEntity desarrolladorEntity = desarrolladorPersistence.find(desarrolladorsId);
        if (desarrolladorEntity.getEquipoDesarrollo() == null) {
            throw new BusinessLogicException("El premio no tiene autor");
        }
        EquipoDesarrolloEntity equipoDesarrolloEntity = equipoDesarrolloPersistence.find(desarrolladorEntity.getEquipoDesarrollo().getId());
        desarrolladorEntity.setEquipoDesarrollo(null);
        equipoDesarrolloEntity.getIntegrantes().remove(desarrolladorEntity);
    }
    
}

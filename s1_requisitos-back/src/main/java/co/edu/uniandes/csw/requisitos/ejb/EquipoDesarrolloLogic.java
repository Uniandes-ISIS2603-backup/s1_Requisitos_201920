/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.EquipoDesarrolloPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Martinez
 */
@Stateless
public class EquipoDesarrolloLogic {
    @Inject
    private EquipoDesarrolloPersistence persistence;
    
    public EquipoDesarrolloEntity createEquipoDesarrollo(EquipoDesarrolloEntity equipo) throws BusinessLogicException{
        if(equipo.getId()==null){
            throw new BusinessLogicException("El equipo de desarrollo no existe");
        }
        equipo = persistence.create(equipo);
        return equipo;
    }
    
    /**
     * Obtiene todos los equipos de desarrollo en la base de datos
     * @return lista con los equipos de desarrollo existentes
     */
    public List<EquipoDesarrolloEntity> getEquipos(){
       List<EquipoDesarrolloEntity> equipo = persistence.findAll();
        return equipo; 
    }

    /**
     * Obtener un equipo con un id dado
     * @param id: id del equipo de desarrollo a buscar
     * @return equipo con el id dado
     */
    public EquipoDesarrolloEntity getEquipo(Long id) 
    { 
        EquipoDesarrolloEntity equipo = persistence.find(id);
        return equipo;
    }
    
    /**
     * Actualiza un equipo siguiendo las reglas de negocio
     * @param equipo de desarrollo a actualizar
     * @return equipo actualizado
     * @throws BusinessLogicException en caso de haber inconsistencias respecto a la actualizacion y reglas de negocio 
     */
    public EquipoDesarrolloEntity updateEquipo(EquipoDesarrolloEntity equipo)throws BusinessLogicException{
        if(equipo.getId()==null){
            throw new BusinessLogicException("El equipo de desarrollo no existe");
        }
        equipo = persistence.update(equipo);
        return equipo;
    }
    
    /**
     * Borra un equipo dado un id
     * @param id del equipo a borrar
     * @return equipo de desarrollo borrado
     */
    public EquipoDesarrolloEntity deleteEquipo(Long id){
        return persistence.delete(id);
    }
}

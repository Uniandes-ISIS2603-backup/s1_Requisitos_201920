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
 * corregido por: juan Rubio
 */
@Stateless
public class EquipoDesarrolloLogic {
    
    @Inject
    private EquipoDesarrolloPersistence persistence;
    
    public EquipoDesarrolloEntity createEquipoDesarrollo(EquipoDesarrolloEntity equipo) throws BusinessLogicException{

      
        if(persistence.findByEquipoDesarrollo(equipo.getEquipoDesarrollo())!=null)
        {
            throw new BusinessLogicException("Ya existe un equipo con ese nombre");
        }
       return persistence.create(equipo);
    }
    
    /**
     * Obtiene todos los equipos de desarrollo en la base de datos
     * @return lista con los equipos de desarrollo existentes
     */
    public List<EquipoDesarrolloEntity> getEquipos(){
        return persistence.findAll();
    }

    /**
     * Obtener un equipo con un id dado
     * @param id: id del equipo de desarrollo a buscar
     * @return equipo con el id dado
     */
    public EquipoDesarrolloEntity getEquipo(Long id) 
    { 
        return persistence.find(id);

    }
    
      /**
     * Obtener un equipo con un id dado
     * @param equipoDesarrollo: id del equipo de desarrollo a buscar
     * @return equipo con el id dado
     */
    public EquipoDesarrolloEntity getEquipoByEquipoDesarrollo(String equipoDesarrollo) 
    { 
      return persistence.findByEquipoDesarrollo(equipoDesarrollo);
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
        if(equipo.getEquipoDesarrollo()==null || equipo.getEquipoDesarrollo().equals(""))
        {
            throw new BusinessLogicException("El equipo de desarrollo debe tener nombre");

        }
        if(persistence.findByEquipoDesarrollo(equipo.getEquipoDesarrollo())!=null)
        {
            throw new BusinessLogicException("Ya existe un equipo con ese nombre");

        }
        return  persistence.update(equipo);
    }
    
    /**
     * Borra un equipo dado un id
     * @param id del equipo a borrar
     * @return equipo de desarrollo borrado
     */
    public void deleteEquipo(Long id){
         persistence.delete(id);
    }
}

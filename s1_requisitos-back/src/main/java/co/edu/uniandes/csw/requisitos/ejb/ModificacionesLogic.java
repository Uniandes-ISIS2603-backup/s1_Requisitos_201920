/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class ModificacionesLogic {
   
    @Inject
    private ModificacionesPersistence persistence;
    
    
    public ModificacionesEntity createModificaciones(ModificacionesEntity modi) throws BusinessLogicException{
        if (modi.getDescripcion()==null){
            throw new BusinessLogicException("descripcion vacia");
        }
        if (modi.getFechaModificacion()==null ){
            throw new BusinessLogicException ("la fecha no puede estar vacia");
        }
        
        modi=persistence.create(modi);
        return modi;
    }
    
    public List<ModificacionesEntity> getModificaciones(){
        List<ModificacionesEntity> lista = persistence.findAll();
        return lista;
    }
    
    public ModificacionesEntity getModificacion(Long id){
        ModificacionesEntity nueva=persistence.find(id);
        return nueva;
    }
    
    public ModificacionesEntity updateModificaciones(ModificacionesEntity modi) throws BusinessLogicException{
        if (modi.getDescripcion()==null){
            throw new BusinessLogicException("descripcion vacia");
        }
        if (modi.getFechaModificacion()==null ){
            throw new BusinessLogicException ("la fecha no puede estar vacia");
        }
        
        ModificacionesEntity nueva= persistence.update(modi);
        return nueva;
    }
    
    public void deleteModificaciones(Long id){
        persistence.delete(id);
    }
}

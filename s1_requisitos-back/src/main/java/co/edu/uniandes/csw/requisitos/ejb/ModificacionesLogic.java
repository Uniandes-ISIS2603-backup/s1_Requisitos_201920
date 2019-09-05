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
        modi=persistence.create(modi);
        return modi;
    }
}

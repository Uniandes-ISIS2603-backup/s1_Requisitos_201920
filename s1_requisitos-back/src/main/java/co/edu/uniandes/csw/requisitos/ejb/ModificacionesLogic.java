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
import co.edu.uniandes.csw.requisitos.podam.DateStrategy;
import java.time.Instant;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class ModificacionesLogic {
     @Temporal(TemporalType.DATE)
     @PodamStrategyValue(DateStrategy.class)
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
}

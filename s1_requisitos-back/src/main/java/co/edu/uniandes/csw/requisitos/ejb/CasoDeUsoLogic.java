/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class CasoDeUsoLogic {
    @Inject
    private CasoDeUsoPersistence persistence;
    
    public CasoDeUsoEntity crearCasoDeUso(CasoDeUsoEntity caso) throws BusinessLogicException{
        if (caso.getPruebas()==null){
            throw new BusinessLogicException("Las pruebas no deben ser nulas");
            
        }
        if (caso.getDocumentacion()==null){
            throw new BusinessLogicException("la Documentacion no debe ser nula");
            
        }
        if (caso.getServicios()==null){
            throw new BusinessLogicException("Los Servicios no deben ser nulos");
            
        }
        /*
        if (caso.getEntidades().size()<1){
            throw new BusinessLogicException("debe tener al menos una entidad asociada al caso");
        }
        if (caso.getFuncional().size()<1){
            throw new BusinessLogicException ("debe tener al menor un requisito funcional");
        }
        if (caso.getPosCondiciones().size()<1){
            throw new BusinessLogicException("debe especificar las pos condiciones del caso");
        }
        if (caso.getPreCondiciones().size()<1){
            throw new BusinessLogicException("debe especificar las pre condiciones del caso");
        }
*/
        
        
        caso=persistence.create(caso);
        return caso;
    }
    
    public List<CasoDeUsoEntity> getCasos() 
    {
        List<CasoDeUsoEntity>  casos = persistence.findAll();
        return casos;
    }
    
    public CasoDeUsoEntity updateCasoDeUso(CasoDeUsoEntity caso) throws BusinessLogicException{
         if (caso.getPruebas()==null){
            throw new BusinessLogicException("Las pruebas no deben ser nulas");
            
        }
        if (caso.getDocumentacion()==null){
            throw new BusinessLogicException("la Documentacion no debe ser nula");
            
        }
        if (caso.getServicios()==null){
            throw new BusinessLogicException("Los Servicios no deben ser nulos");
            
        }
        if (caso.getEntidades().size()<1){
            throw new BusinessLogicException("debe tener al menos una entidad asociada al caso");
        }
        if (caso.getFuncional().size()<1){
            throw new BusinessLogicException ("debe tener al menor un requisito funcional");
        }
        if (caso.getPosCondiciones().size()<1){
            throw new BusinessLogicException("debe especificar las pos condiciones del caso");
        }
        if (caso.getPreCondiciones().size()<1){
            throw new BusinessLogicException("debe especificar las pre condiciones del caso");
        }
        CasoDeUsoEntity nueva= persistence.update(caso);
        return nueva;
    }
    
    public void deleteRequisito(Long CasoDeUSoId) 
    {
         persistence.delete(CasoDeUSoId);
    }
}

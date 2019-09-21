/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class CasoDeUsoLogic {
    @Inject
    private CasoDeUsoPersistence persistance;
    
    public CasoDeUsoEntity crearCasoDeUso(CasoDeUsoEntity caso) throws BusinessLogicException{
        if (caso.getResponsable()==null){
            throw new BusinessLogicException("El responsable no debe ser nulo");
            
        }
        if (caso.getPruebas()==null){
            throw new BusinessLogicException("Las pruebas no deben ser nulas");
            
        }
        if (caso.getDocumentacion()==null){
            throw new BusinessLogicException("la Documentacion no debe ser nula");
            
        }
        if (caso.getServicios()==null){
            throw new BusinessLogicException("Los Servicios no deben ser nulos");
            
        }
        caso=persistance.create(caso);
        return caso;
    }
}

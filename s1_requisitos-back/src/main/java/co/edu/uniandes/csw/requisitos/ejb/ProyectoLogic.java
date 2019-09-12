/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ProyectoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Martinez
 */
@Stateless
public class ProyectoLogic {
    
    @Inject
    private ProyectoPersistence persistence;
    
    public ProyectoEntity createProyecto(ProyectoEntity proyecto)throws BusinessLogicException{
        if(proyecto.getFechaInicial().before(proyecto.getFechaFinal())){
            proyecto = persistence.create(proyecto);
            return proyecto;
        }else{
            throw new BusinessLogicException("La fecha iicial es posterior a la final");
        }
        
       
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class CasoDeUsoDesarrolladorLogic {
    @Inject
    private CasoDeUsoPersistence casoPersistence;
    
    private static final Logger LOGGER = Logger.getLogger(CasoDeUsoDesarrolladorLogic.class.getName());
    @Inject
    private DesarrolladorPersistence desPersistence;
    
    
    public DesarrolladorEntity addRepresentante(Long representanteId, Long CasoId)throws BusinessLogicException{
        
        DesarrolladorEntity desEntity= desPersistence.find(representanteId);
        System.out.println("aca2"+desEntity.getTipoString());
         System.out.println("aca2"+desEntity.getId());
        if (!desEntity.getTipoString().equals("RepresentanteDelCliente")){
            throw new BusinessLogicException("la persona debe ser de  tipo respresantante");
        }
        CasoDeUsoEntity casoEntity= casoPersistence.find(CasoId);
        casoEntity.setRepresentanteDelCliente(desEntity);
        casoPersistence.update(casoEntity);
     
        return desEntity;
    }
    
    
    public DesarrolladorEntity getRepresentante(Long casoId){
        DesarrolladorEntity desEntity= casoPersistence.find(casoId).getRepresentanteDelCliente();
        
        return desEntity;
    }
    
    
    public DesarrolladorEntity cambiarRepresentante(Long representanteId, Long CasoId)throws BusinessLogicException{
        DesarrolladorEntity desEntity= desPersistence.find(representanteId);
        
        if (!desEntity.getTipoString().equals("RepresentanteDelCliente")){
            throw new BusinessLogicException("la persona debe ser de tipo representante");
        }
        CasoDeUsoEntity casoEntity= casoPersistence.find(CasoId);
        casoEntity.setRepresentanteDelCliente(desEntity);
        casoPersistence.update(casoEntity);
        return desEntity;
        
    }
    
    
    
     public DesarrolladorEntity addResponsable(Long representanteId, Long CasoId)throws BusinessLogicException{
        DesarrolladorEntity desEntity= desPersistence.find(representanteId);
        System.out.println(desEntity.getTipoString());
        if (!desEntity.getTipoString().equals("Responsable")){
            throw new BusinessLogicException("la persona debe ser de tipo responsable");
        }
         LOGGER.log(Level.INFO, "fuck this shit");
        CasoDeUsoEntity casoEntity= casoPersistence.find(CasoId);
        casoEntity.setResponsable(desEntity);
        casoPersistence.update(casoEntity);
        return desEntity;
    }
    
    
    public DesarrolladorEntity getResponsable(Long casoId){
        DesarrolladorEntity desEntity= casoPersistence.find(casoId).getResponsable();
        
        return desEntity;
    }
    
    
    public DesarrolladorEntity cambiarResponsable(Long representanteId, Long CasoId) throws BusinessLogicException{
        DesarrolladorEntity desEntity= desPersistence.find(representanteId);
        if (!desEntity.getTipoString().equals("Responsable")){
            throw new BusinessLogicException("la persona debe ser de tipo responsable");
        }
        CasoDeUsoEntity casoEntity= casoPersistence.find(CasoId);
        casoEntity.setResponsable(desEntity);
        casoPersistence.update(casoEntity);
        
        return desEntity;
        
    }
    

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.EscalabilidadEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.EscalabilidadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Rubio
 */
@Stateless
public class EscalabilidadLogic {
        @Inject
    private EscalabilidadPersistence pEscalabilidad;
    
    public EscalabilidadEntity createEscalabilidad(EscalabilidadEntity escalabilidad) throws BusinessLogicException
    {
        if(escalabilidad.getTipo()== null)
            throw new BusinessLogicException("El nombre de estudiante está vacío");
        escalabilidad = pEscalabilidad.create(escalabilidad);
        return escalabilidad;
    }
     /**
     * Obtener un escalabilidad por medio de su id.
     * @param escalabilidadId: id del escalabilidad para ser buscada.
     * @return el escalabilidad solicitado por medio de su id.
     */
    public EscalabilidadEntity getEscalabilidad(Long escalabilidadId) 
    { 
        EscalabilidadEntity escalabilidadEntity = pEscalabilidad.find(escalabilidadId);
        return escalabilidadEntity;
    }
     /**
     * Obtener todas las escalabilidads existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<EscalabilidadEntity> getEscalabilidads() 
    {
        List<EscalabilidadEntity> escalabilidads = pEscalabilidad.findAll();
        return escalabilidads;
    }
    
       /**
     *
     * Actualiza un escalabilidad
     * @param escalabilidad
     * @return escalabilidad con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public EscalabilidadEntity updateEscalabilidad(EscalabilidadEntity escalabilidad) throws BusinessLogicException 
    {
        if(escalabilidad.getTipo()==null||escalabilidad.getTipo().equals(""))
        {
            throw new BusinessLogicException("Falta el nombre de la escalabilidad.");
        } 
       
      
        EscalabilidadEntity newEntity =pEscalabilidad.update(escalabilidad);
        return newEntity;
    }  
    /**
     * Borra una escalabilidad.
     * @param escalabilidadId: id de la escalabilidad a borrar
     */
    public void deleteEscalabilidad(Long escalabilidadId) 
    {
         pEscalabilidad.delete(escalabilidadId);
    } 
    
}

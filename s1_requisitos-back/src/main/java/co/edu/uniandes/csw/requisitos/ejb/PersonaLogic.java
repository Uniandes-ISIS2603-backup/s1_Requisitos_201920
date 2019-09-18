/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.PersonaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Rubio
 */
@Stateless
public class PersonaLogic {
    
    @Inject
    private PersonaPersistence pPersona;
    
    public PersonaEntity createPersona(PersonaEntity persona) throws BusinessLogicException
    {
        if(persona.getNombre()== null)
            throw new BusinessLogicException("El nombre de estudiante está vacío");
        if(persona.getCorreo() == null)
            throw new BusinessLogicException("El correo de estudiante está vacío");
        persona = pPersona.create(persona);
        return persona;
    }
     /**
     * Obtener un persona por medio de su id.
     * @param personaId: id del persona para ser buscada.
     * @return el persona solicitado por medio de su id.
     */
    public PersonaEntity getPersona(Long personaId) 
    { 
        PersonaEntity personaEntity = pPersona.find(personaId);
        return personaEntity;
    }
     /**
     * Obtener todas las personas existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<PersonaEntity> getPersonas() 
    {
        List<PersonaEntity> personas = pPersona.findAll();
        return personas;
    }
    
       /**
     *
     * Actualiza un persona
     * @param persona
     * @return persona con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public PersonaEntity updatePersona(PersonaEntity persona) throws BusinessLogicException 
    {
        if(persona.getNombre()==null||persona.getNombre().equals(""))
        {
            throw new BusinessLogicException("Falta el nombre de la persona.");
        } 
         if(persona.getCorreo()==null||persona.getCorreo().equals(""))
        {
            throw new BusinessLogicException("Falta el correo de la persona.");
        } 
      
        PersonaEntity newEntity =pPersona.update(persona);
        return newEntity;
    }  
    /**
     * Borra una persona.
     * @param personaId: id de la persona a borrar
     */
    public void deletePersona(Long personaId) 
    {
         pPersona.delete(personaId);
    } 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Rubio
 */
@Stateless
public class PersonaPersistence {

    /**
     * Administrador de entidades
     */
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    
    /**
     * Metodo encargado de crear una entidad de persona
     * @param persona la entidad de al persona
     * @return la perosna que se envio por parametro
     */
    public PersonaEntity create(PersonaEntity persona)
    {
      em.persist(persona);
      return persona;
    }
    
    /**
     * Encuentra la persona mediante un id único
     * @param pPersonaId id de la peronsa
     * @return  la entidad de la persona
     */
    public PersonaEntity find(Long pPersonaId ) {
        return em.find(PersonaEntity.class, pPersonaId);
    }
    
    /**
     * Encontrar por nombre
     * @param pNombre el nombre de la persona que se quiere encontrar
     * @return 
     */
    public PersonaEntity findByNombre(String pNombre)
    {
        PersonaEntity buscado = null;
        List<PersonaEntity> lista = findAll();
        for(PersonaEntity persona: lista)
        {
            if(persona.getNombre().equals(pNombre))
                buscado = persona;
        }
        return buscado;
    }
    
    /**
     * Encuentra a la persona por correo
     * @param pCorreo el correo de la persona
     * @return la perosna con el correo esperado
     */
       public PersonaEntity findByCorreo(String pCorreo)
    {
        PersonaEntity buscado = null;
        List<PersonaEntity> lista = findAll();
        for(PersonaEntity persona: lista)
        {
            if(persona.getCorreo().equals(pCorreo))
                buscado = persona;
        }
        return buscado;
    }
          public PersonaEntity findByCedula(int pCedula)
    {
        PersonaEntity buscado = null;
        List<PersonaEntity> lista = findAll();
        for(PersonaEntity persona: lista)
        {
            if(persona.getCedula()==pCedula)
                buscado = persona;
        }
        return buscado;
    }
          /**
           * Encontrar toas las personas
           * @return 
           */
    public List<PersonaEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM PersonaEntity u", PersonaEntity.class);
        return query.getResultList();
    }
    
    /**
     * modificar a una persona
     * @param pPersona la persona a modificar
     * @return  la persona modificada
     */
    public PersonaEntity update(PersonaEntity pPersona)
    {
        return em.merge(pPersona);
    }
    
    /**
     * borra una persona mediante su id
     * @param perId 
     */
    public void delete(Long perId)
    {
        PersonaEntity persona = em.find(PersonaEntity.class, perId);
        em.remove(persona);
    }
}

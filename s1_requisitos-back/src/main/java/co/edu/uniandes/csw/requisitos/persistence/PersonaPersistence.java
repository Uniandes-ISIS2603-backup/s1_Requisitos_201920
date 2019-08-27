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

    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public PersonaEntity create(PersonaEntity persona)
    {
      em.persist(persona);
      return persona;
    }
    
    public PersonaEntity find(Long pPersonaId ) {
        return em.find(PersonaEntity.class, pPersonaId);
    }
    
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
    public List<PersonaEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM PersonaEntity u", PersonaEntity.class);
        return query.getResultList();
    }
    public PersonaEntity update(PersonaEntity pPersona)
    {
        return em.merge(pPersona);
    }
    
    public void delete(Long perId)
    {
        PersonaEntity persona = em.find(PersonaEntity.class, perId);
        em.remove(persona);
    }
}

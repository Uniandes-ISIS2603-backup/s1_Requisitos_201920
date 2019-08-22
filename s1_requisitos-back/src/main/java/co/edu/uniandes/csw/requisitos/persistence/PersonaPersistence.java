/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Estudiante
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.AtributoCalidadEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nicole Bahamon
 */
@Stateless
public class AtributoCalidadPersistence {
        @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public AtributoCalidadEntity create(AtributoCalidadEntity requisito)
    {
      em.persist(requisito);
      return requisito;
    }
    
}


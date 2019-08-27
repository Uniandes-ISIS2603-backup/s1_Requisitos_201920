/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class IteracionPersistence {

    @PersistenceContext(unitName ="requisitosPU")
    protected EntityManager em;
    public IteracionEntity create(IteracionEntity iteracion) {
        em.persist(iteracion);
        return iteracion;
    }

}

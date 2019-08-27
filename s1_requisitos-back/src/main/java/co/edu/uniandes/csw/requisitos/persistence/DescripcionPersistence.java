/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Rubio
 */
@Stateless
public class DescripcionPersistence {
    
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public DescripcionEntity create(DescripcionEntity descripcion)
    {
        em.persist(descripcion);
        return descripcion;
    }
    
}

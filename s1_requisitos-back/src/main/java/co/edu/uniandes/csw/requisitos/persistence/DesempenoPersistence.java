/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.DesempenoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicole Bahamon
 */
@Stateless
public class DesempenoPersistence {
    
@PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public DesempenoEntity create(DesempenoEntity desempeno)
    {
        em.persist(desempeno);
        return desempeno;
    }
     public DesempenoEntity find(Long pDesempenoId ) {
        return em.find(DesempenoEntity.class, pDesempenoId);
    }
    
    public DesempenoEntity findByTipo(String pDesempeno)
    {
        DesempenoEntity buscado = null;
        List<DesempenoEntity> lista = findAll();
        for(DesempenoEntity desempeno: lista)
        {
            if(desempeno.getDesempeno().equals(pDesempeno))
                buscado = desempeno;
        }
        return buscado;
    }
    public List<DesempenoEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM DesempenoEntity u", DesempenoEntity.class);
        return query.getResultList();
    }
    public DesempenoEntity update(DesempenoEntity pDesempeno)
    {
        return em.merge(pDesempeno);
    }
    
    public void delete(Long escId)
    {
        DesempenoEntity desempeno = em.find(DesempenoEntity.class, escId);
        em.remove(desempeno);
    }
    
}


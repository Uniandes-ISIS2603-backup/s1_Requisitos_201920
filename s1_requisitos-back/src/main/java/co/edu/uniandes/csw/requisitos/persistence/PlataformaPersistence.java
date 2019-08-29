/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.PlataformaEntity;
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
public class PlataformaPersistence {
@PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public PlataformaEntity create(PlataformaEntity plataforma)
    {
        em.persist(plataforma);
        return plataforma;
    }
     public PlataformaEntity find(Long pPlataformaId ) {
        return em.find(PlataformaEntity.class, pPlataformaId);
    }
    
    public PlataformaEntity findByTipo(String pPlataforma)
    {
        PlataformaEntity buscado = null;
        List<PlataformaEntity> lista = findAll();
        for(PlataformaEntity plataforma: lista)
        {
            if(plataforma.getPlataforma().equals(pPlataforma))
                buscado = plataforma;
        }
        return buscado;
    }
    public List<PlataformaEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM PlataformaEntity u", PlataformaEntity.class);
        return query.getResultList();
    }
    public PlataformaEntity update(PlataformaEntity pPlataforma)
    {
        return em.merge(pPlataforma);
    }
    
    public void delete(Long escId)
    {
        PlataformaEntity plataforma = em.find(PlataformaEntity.class, escId);
        em.remove(plataforma);
    }
    
}


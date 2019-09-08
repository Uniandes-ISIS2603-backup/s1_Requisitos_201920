/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
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
public class DescripcionPersistence {
    
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public DescripcionEntity create(DescripcionEntity descripcion)
    {
        em.persist(descripcion);
        return descripcion;
    }
     public DescripcionEntity find(Long pDescripcionId ) {
        return em.find(DescripcionEntity.class, pDescripcionId);
    }
    
    public DescripcionEntity findByTipo(String pDescripcion)
    {
        DescripcionEntity buscado = null;
        List<DescripcionEntity> lista = findAll();
        for(DescripcionEntity descripcion: lista)
        {
            if(descripcion.getDescripcion().equals(pDescripcion))
                buscado = descripcion;
        }
        return buscado;
    }
    public List<DescripcionEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM DescripcionEntity u", DescripcionEntity.class);
        return query.getResultList();
    }
    public DescripcionEntity update(DescripcionEntity pDescripcion)
    {
        return em.merge(pDescripcion);
    }
    
    public void delete(Long escId)
    {
        DescripcionEntity descripcion = em.find(DescripcionEntity.class, escId);
        em.remove(descripcion);
    }
    
}

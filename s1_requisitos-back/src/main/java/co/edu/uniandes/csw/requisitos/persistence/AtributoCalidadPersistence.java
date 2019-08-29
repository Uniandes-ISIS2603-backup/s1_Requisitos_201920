/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.AtributoCalidadEntity;
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
public class AtributoCalidadPersistence {
 @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public AtributoCalidadEntity create(AtributoCalidadEntity atributoCalidad)
    {
        em.persist(atributoCalidad);
        return atributoCalidad;
    }
     public AtributoCalidadEntity find(Long pAtributoCalidadId ) {
        return em.find(AtributoCalidadEntity.class, pAtributoCalidadId);
    }
    
    public AtributoCalidadEntity findByTipo(String pAtributoCalidad)
    {
        AtributoCalidadEntity buscado = null;
        List<AtributoCalidadEntity> lista = findAll();
        for(AtributoCalidadEntity atributoCalidad: lista)
        {
            if(atributoCalidad.getTipo().equals(pAtributoCalidad))
                buscado = atributoCalidad;
        }
        return buscado;
    }
    public List<AtributoCalidadEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM AtributoCalidadEntity u", AtributoCalidadEntity.class);
        return query.getResultList();
    }
    public AtributoCalidadEntity update(AtributoCalidadEntity pAtributoCalidad)
    {
        return em.merge(pAtributoCalidad);
    }
    
    public void delete(Long escId)
    {
        AtributoCalidadEntity atributoCalidad = em.find(AtributoCalidadEntity.class, escId);
        em.remove(atributoCalidad);
    }
    
}



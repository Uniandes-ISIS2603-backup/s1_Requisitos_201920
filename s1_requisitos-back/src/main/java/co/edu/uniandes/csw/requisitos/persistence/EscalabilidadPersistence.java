/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.EscalabilidadEntity;
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
public class EscalabilidadPersistence {
    
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public EscalabilidadEntity create(EscalabilidadEntity escalabilidad)
    {
        em.persist(escalabilidad);
        return escalabilidad;
    }
     public EscalabilidadEntity find(Long pEscalabilidadId ) {
        return em.find(EscalabilidadEntity.class, pEscalabilidadId);
    }
    
    public EscalabilidadEntity findByTipo(String pTipo)
    {
        EscalabilidadEntity buscado = null;
        List<EscalabilidadEntity> lista = findAll();
        for(EscalabilidadEntity escalabilidad: lista)
        {
            if(escalabilidad.getTipo().equals(pTipo))
                buscado = escalabilidad;
        }
        return buscado;
    }
    public List<EscalabilidadEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM EscalabilidadEntity u", EscalabilidadEntity.class);
        return query.getResultList();
    }
    public EscalabilidadEntity update(EscalabilidadEntity pEscalabilidad)
    {
        return em.merge(pEscalabilidad);
    }
    
    public void delete(Long escId)
    {
        EscalabilidadEntity escalabilidad = em.find(EscalabilidadEntity.class, escId);
        em.remove(escalabilidad);
    }
    
}

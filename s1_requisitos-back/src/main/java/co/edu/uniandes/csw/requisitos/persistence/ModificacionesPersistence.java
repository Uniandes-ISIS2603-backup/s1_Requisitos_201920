/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;


import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class ModificacionesPersistence {
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em; 
    
    public ModificacionesEntity create (ModificacionesEntity modificacion){
        em.persist(modificacion);
        return modificacion;
    }
    
     public ModificacionesEntity find(Long requisitoId) {
         System.out.println("me suicido");
         System.out.println ("me quito un ovario"+em.find(ModificacionesEntity.class, requisitoId).getDescripcion());
        return em.find(ModificacionesEntity.class, requisitoId);
    }
  
    /**
     * Retorna una lista con todos los elementos
     * @return 
     */
    public List<ModificacionesEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from ModificacionesEntity u",ModificacionesEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza la informacion de un requisito 
     * @param requisito
     * @return 
     */
    public ModificacionesEntity update(ModificacionesEntity requisito)
    {
       return em.merge(requisito);
    }
   
    /**
     * Elimina un requisito
     * @param reqId 
     */
     
    public void delete(Long reqId)
    {
       ModificacionesEntity requisito=em.find(ModificacionesEntity.class,reqId);
       em.remove(requisito);
    }
   
}

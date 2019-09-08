/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Martinez
 */
@Stateless
public class ProyectoPersistence {

    @PersistenceContext(unitName = "requisitosPU")
    protected EntityManager em;
    
    /**
     * Metodo de persistencia de un proyecto
     * @param proyecto que se va a persistir
     * @return 
     */
    public ProyectoEntity create(ProyectoEntity proyecto) {
        em.persist(proyecto);
        
        return proyecto;
    }

    /**
     * Metodo que busca un Proyecto con un id dado
     * @param id del proyecto que se desea
     * @return 
     */
    public ProyectoEntity find(Long id){
        return em.find(ProyectoEntity.class, id);
    }
    
    /**
     * Metodo que actualiza la informacion de un proyecto
     * @param proyecto
     * @return 
     */
    public ProyectoEntity update(ProyectoEntity proyecto){
        return em.merge(proyecto);
    }
    
    /**
     * Metodo que elimina un proyecto dado su id
     * @param id del proyecto a eliminar
     * @return 
     */
    public ProyectoEntity delete(Long id){
        ProyectoEntity proy = em.find(ProyectoEntity.class, id);
        em.remove(proy);
        return proy;
    }
    
}

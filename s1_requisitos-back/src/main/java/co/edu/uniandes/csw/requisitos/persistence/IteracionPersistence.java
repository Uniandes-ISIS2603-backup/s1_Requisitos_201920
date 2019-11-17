/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class IteracionPersistence {
    

    @PersistenceContext(unitName ="requisitosPU")
    protected EntityManager em;
     /**
     * Método que persiste una entidad de tipo representante del cliente
     * @param iteracion representante del cliente el cual se desea presistir en la base de datos
     * @return 
     */
    public IteracionEntity create(IteracionEntity iteracion) {
        em.persist(iteracion);
        return iteracion;
    }
     /**
     * Encuentra en la base de datos el elemento con el id
     * @param IteracionId
     * @return  null si no lo encuentra, de lo contrario el elemento
     */
    public IteracionEntity find(Long iteracionId) {
        return em.find(IteracionEntity.class, iteracionId);
    }
    /**
     * Retorna una lista con todos los elementos
     * @return 
     */
    public List<IteracionEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from IteracionEntity u",IteracionEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza la informacion de una iteracion
     * @param iteracion entidad del tipo iteracion con la info que se desea actualizar
     * @return 
     */
    public IteracionEntity update(IteracionEntity iteracion)
    {
       return em.merge(iteracion);
    }
    /**
     * Elimina una iteracion
     * @param reqId 
     */
    public void delete(Long reqId)
    {
       IteracionEntity iteracion=em.find(IteracionEntity.class,reqId);
       em.remove(iteracion);
    }
    

}

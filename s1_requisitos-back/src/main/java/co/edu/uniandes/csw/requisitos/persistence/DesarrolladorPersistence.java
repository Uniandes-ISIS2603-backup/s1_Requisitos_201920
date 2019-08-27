/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas Tobo
 */
@Stateless
public class DesarrolladorPersistence 
{
    @PersistenceContext(unitName = "requisitosPU")
    protected EntityManager em;
    
     /**
     * Crea la persistencia del Requisito
     * @param desarrollador
     * @return desarrollador
     */
    public DesarrolladorEntity create(DesarrolladorEntity desarrollador) 
    {
        em.persist(desarrollador);
        return desarrollador;
    }
    /**
     * Encuentra en la base de datos el elemento con el id
     * @param desarrolladorId
     * @return  null si no lo encuentra, de lo contrario el elemento
     */
    public DesarrolladorEntity find(Long desarrolladorId) {
        return em.find(DesarrolladorEntity.class, desarrolladorId);
    }
    /**
     * Retorna una lista con todos los elementos
     * @return 
     */
    public List<DesarrolladorEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from DesarrolladorEntity u",DesarrolladorEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza la informacion de un desarrollador 
     * @param desarrollador
     * @return 
     */
    public DesarrolladorEntity update(DesarrolladorEntity  desarrollador)
    {
       return em.merge(desarrollador);
    }
    /**
     * Elimina un desarrollador
     * @param reqId 
     */
    public void delete(Long reqId)
    {
       DesarrolladorEntity desarrollador=em.find(DesarrolladorEntity.class,reqId);
       em.remove(desarrollador);
    }
    
}

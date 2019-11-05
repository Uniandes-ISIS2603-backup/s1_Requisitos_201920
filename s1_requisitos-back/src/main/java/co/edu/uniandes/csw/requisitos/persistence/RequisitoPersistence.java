/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que representa la persistencia de los requisitos funcionales
 *
 * @author Nicolás Tobo
 */
@Stateless
public class RequisitoPersistence {
    
    /**
     * Entity manager que maneja la aplicacion
     */
    @PersistenceContext(unitName = "requisitosPU")
    protected EntityManager em;

    /**
     * Crea la persistencia del Requisito
     * @param requisito
     * @return requisito
     */
    public RequisitosEntity create(RequisitosEntity requisito) 
    {
        em.persist(requisito);
        return requisito;
    }
    /**
     * Encuentra en la base de datos el elemento con el id
     * @param requisitoId
     * @return  null si no lo encuentra, de lo contrario el elemento
     */
    public RequisitosEntity find(Long requisitoId) 
    {
        return em.find(RequisitosEntity.class, requisitoId);
    }
    /**
     * Encuentra en la base de datos el elemento con el autor dado por parametro
     * @param Author 
     * @return null si no lo encuentra, de lo contrario el elemento
     */
    public RequisitosEntity findByAuthor(String Author)
    {
        RequisitosEntity buscado=null;
        List<RequisitosEntity> lista=findAll();
        for (RequisitosEntity requisito : lista) 
        {
            if(requisito.getAutor().equals(Author))
                buscado=requisito;
        }
        return buscado;
    }
    /**
     * Encuentra en la base de datos el elemento con el nombre dado por parametro
     * @param Name
     * @return null si no lo encuentra, de lo contrario el elemento
     */
    public RequisitosEntity findByName(String Name)
    {
        RequisitosEntity buscado=null;
        List<RequisitosEntity> lista=findAll();
        for (RequisitosEntity requisito : lista) 
        {
            if(requisito.getNombre().equals(Name))
                buscado=requisito;
        }
        return buscado;
    }
    /**
     * Retorna una lista con todos los elementos
     * @return 
     */
    public List<RequisitosEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from RequisitosEntity u",RequisitosEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza la informacion de un requisito 
     * @param requisito
     * @return 
     */
    public RequisitosEntity update(RequisitosEntity requisito)
    {
       return em.merge(requisito);
    }
    /**
     * Elimina un requisito
     * @param reqId 
     */
    public void delete(Long reqId)
    {
       RequisitosEntity requisito=em.find(RequisitosEntity.class,reqId);
       em.remove(requisito);
    }
}

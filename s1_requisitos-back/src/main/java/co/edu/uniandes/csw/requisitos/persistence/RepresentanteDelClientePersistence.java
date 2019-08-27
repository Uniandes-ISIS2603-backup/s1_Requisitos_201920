/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
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
public class RepresentanteDelClientePersistence {

    @PersistenceContext(unitName ="requisitosPU")
    protected EntityManager em;
    /**
     * Método que persiste una entidad de tipo representante del cliente
     * @param representante representante del cliente el cual se desea presistir en la base de datos
     * @return 
     */
    public RepresentanteDelClienteEntity create(RepresentanteDelClienteEntity representante) {
        em.persist(representante);
        return representante;
    }
     /**
     * Encuentra en la base de datos el elemento con el id
     * @param representanteId
     * @return  null si no lo encuentra, de lo contrario el elemento
     */
    public RepresentanteDelClienteEntity find(Long representanteId) {
        return em.find(RepresentanteDelClienteEntity.class, representanteId);
    }
    /**
     * Retorna una lista con todos los elementos
     * @return 
     */
    public List<RepresentanteDelClienteEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from RepresentanteDelClienteEntity u",RepresentanteDelClienteEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza la informacion de un representante del cliente
     * @param representante entidad del tipo representante con la info que se desea actualizar
     * @return 
     */
    public RepresentanteDelClienteEntity update(RepresentanteDelClienteEntity representante)
    {
       return em.merge(representante);
    }
    /**
     * Elimina un representante del cliente
     * @param reqId 
     */
    public void delete(Long reqId)
    {
       RepresentanteDelClienteEntity representante=em.find(RepresentanteDelClienteEntity.class,reqId);
       em.remove(representante);
    }
    

}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
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
public class CasoDeUsoPersistence {

    @PersistenceContext(unitName = "requisitosPU")
    protected EntityManager em;

    public CasoDeUsoEntity create(CasoDeUsoEntity casoDeUso) {
        em.persist(casoDeUso);
        return casoDeUso;
    }

    public CasoDeUsoEntity find(Long requisitoId) {
        return em.find(CasoDeUsoEntity.class, requisitoId);
    }

    /**
     * Retorna una lista con todos los elementos
     *
     * @return
     */
    public List<CasoDeUsoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CasoDeUsoEntity u", CasoDeUsoEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza la informacion de un requisito
     *
     * @param requisito
     * @return
     */
    public CasoDeUsoEntity update(CasoDeUsoEntity requisito) {
        return em.merge(requisito);
    }

    /**
     * Elimina un requisito
     *
     * @param reqId
     */
    public void delete(Long reqId) {
        CasoDeUsoEntity requisito = em.find(CasoDeUsoEntity.class, reqId);
        em.remove(requisito);
    }
}

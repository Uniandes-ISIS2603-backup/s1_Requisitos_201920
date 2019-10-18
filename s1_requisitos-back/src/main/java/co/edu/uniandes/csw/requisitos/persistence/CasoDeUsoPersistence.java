/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;

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
//conexion a la base de datos

    @PersistenceContext(unitName = "requisitosPU")
    protected EntityManager em;

    /*
    *Crea un caso de uso en la base de datos
    *@param CasoDeUso entity a ser persistido
    *@return casoDeUsoEntity que fue persistido
     */
    public CasoDeUsoEntity create(CasoDeUsoEntity casoDeUso) {
        em.persist(casoDeUso);
        return casoDeUso;
    }

    /*
    * busca un caso de uso con un id
    *@param id a ser buscado
    *@return CasoDeUsoEntity encontrado
    */
    public CasoDeUsoEntity find(Long requisitoId) {
        return em.find(CasoDeUsoEntity.class, requisitoId);
    }

    /**
     * Retorna una lista con todos los elementos
     *
     * @return Lista con todos los casos de uso
     */
    public List<CasoDeUsoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CasoDeUsoEntity u", CasoDeUsoEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza la informacion de un casoDeUso
     *
     * @param CasoDeUsoEntity a ser modificado
     * @return CasoDeUsoEntity modificado
     */
    public CasoDeUsoEntity update(CasoDeUsoEntity requisito) {
        return em.merge(requisito);
    }

    /**
     * Elimina un CasoDeUso
     *
     * @param id del caso de uso a ser eliminado
     */
    public void delete(Long reqId) {
        CasoDeUsoEntity requisito = em.find(CasoDeUsoEntity.class, reqId);
        em.remove(requisito);
    }
}

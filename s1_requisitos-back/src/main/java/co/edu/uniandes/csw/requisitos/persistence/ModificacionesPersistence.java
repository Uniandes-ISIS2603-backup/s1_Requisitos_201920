/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class ModificacionesPersistence {

    //conexion con la base de datos
    @PersistenceContext(unitName = "requisitosPU")
    //entity manager
    protected EntityManager em;

    /*
    *metodo que crea en la base de datos un modificacion
    *@param ModificacionEntity a ser creada en la base
    *@return retorna una variable de tipo ModificacionesEntity creada  
     */
    public ModificacionesEntity create(ModificacionesEntity modificacion) {
        em.persist(modificacion);
        return modificacion;
    }

    /*
    *metodo que busca en la base de datos por id
    *@param id buscado
    *@return retorna una variable de tipo ModificacionesEntity encontrada
     */
    public ModificacionesEntity find(Long requisitoId) {
        return em.find(ModificacionesEntity.class, requisitoId);
    }

    /**
     * Retorna una lista con todos los elementos
     *
     * @return lista con todas las modificaciones
     */
    public List<ModificacionesEntity> findAll() {
        TypedQuery query = em.createQuery("select u from ModificacionesEntity u", ModificacionesEntity.class);
        return query.getResultList();
    }

    /*
    * Busca una Modificacion por fecha y retorna una lista con las encontrada
    *@param fecha de la busqueda
    * @return una lista de modificaciones que fueron creadas en la fecha dada por parametro
     */
    public List<ModificacionesEntity> findByDate(Date fecha) {
        List<ModificacionesEntity> buscado = new ArrayList<>();
        List<ModificacionesEntity> lista = findAll();
        for (ModificacionesEntity mod : lista) {
            if (mod.getFechaModificacion().equals(fecha)) {
                buscado.add(mod);
            }
        }

        return buscado;
    }

    /**
     * Actualiza la informacion de una Modificacion
     *
     * @param ModificacionEntity
     * @return Modificacion entity actualizada
     */
    public ModificacionesEntity update(ModificacionesEntity requisito) {
        return em.merge(requisito);
    }

    /**
     * Elimina una Modificacion
     *
     * @param id de la modificacion a ser borrada
     */
    public void delete(Long reqId) {
        ModificacionesEntity requisito = em.find(ModificacionesEntity.class, reqId);
        em.remove(requisito);
    }

}

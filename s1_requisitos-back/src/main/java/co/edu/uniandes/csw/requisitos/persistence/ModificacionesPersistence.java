/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class RepresentanteDelClientePersistence {

    @PersistenceContext(unitName ="requisitosPU")
    protected EntityManager em;
    public RepresentanteDelClienteEntity create(RepresentanteDelClienteEntity representante) {
        em.persist(representante);
        return representante;
    }

}



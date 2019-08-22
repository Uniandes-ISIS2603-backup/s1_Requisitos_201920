/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase que representa la persistencia de los requisitos funcionales
 * @author Nicolás Tobo 
 */
@Stateless
public class RequisitoPersistence 
{
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;
    public RequisitosEntity create(RequisitosEntity requisito)
    {
      em.persist(requisito);
      return requisito;
    }
    
}

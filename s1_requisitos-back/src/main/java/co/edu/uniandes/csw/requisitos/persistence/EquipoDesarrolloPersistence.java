/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Martinez
 */
@Stateless
public class EquipoDesarrolloPersistence {
    
     @PersistenceContext(unitName ="requisitosPU")
     protected EntityManager em;
     
     /**
      * Metodo de persistencia de un equipo de desarrollo
      * @param equipo a persistir
      * @return 
      */
     public EquipoDesarrolloEntity create(EquipoDesarrolloEntity equipo){
         em.persist(equipo);
         
         return equipo;
     }
    
     /**
      * Metodo que busca un equipo con un id dado
      * @param id del equipo
      * @return 
      */
     public EquipoDesarrolloEntity find(Long id){
         return em.find(EquipoDesarrolloEntity.class, id);
     }
     
     /**
      * Metodo que busca todos los equipos de desarrollo
      * @return 
      */
     public List<EquipoDesarrolloEntity> findAll(){
         TypedQuery query = em.createQuery("select u from EquipoDesarrolloEntity u", EquipoDesarrolloEntity.class);
         return query.getResultList();
     }
     
     /**
      * Metodo que actualiza la informacion de un equipo
      * @param equipo
      * @return 
      */
     public EquipoDesarrolloEntity update(EquipoDesarrolloEntity equipo){
         return em.merge(equipo);
     }
     
     /**
      * Metodo que elimina un equipo basado en su id
      * @param id del equipo a eliminar
      * @return 
      */
     public void delete(Long id){
         EquipoDesarrolloEntity eq = em.find(EquipoDesarrolloEntity.class, id);
         em.remove(eq);
     }
     
       
    /**
     * Encuentra en la base de datos el elemento con el nombre dado por parametro
     * @param Name
     * @return null si no lo encuentra, de lo contrario el elemento
     */
    public EquipoDesarrolloEntity findByEquipoDesarrollo(String name)
    {
        EquipoDesarrolloEntity buscado=null;
        List<EquipoDesarrolloEntity> lista=findAll();
        for (EquipoDesarrolloEntity requisito : lista) 
        {
            if(requisito!=null && requisito.getEquipoDesarrollo()!=null &&requisito.getEquipoDesarrollo().equals(name))
                buscado=requisito;
        }
        return buscado;
    }
    
}

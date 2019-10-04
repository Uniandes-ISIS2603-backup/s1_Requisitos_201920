/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.persistence;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 *
 * @author Nicole Bahamon
 */
@Stateless
public class FuncionalPersistence 
{
    @PersistenceContext(unitName="requisitosPU")
    protected EntityManager em;

    public FuncionalEntity create(FuncionalEntity funcional)
    {
        em.persist(funcional);
        return funcional;
    }
     public FuncionalEntity find(Long pFuncionalId ) 
     {
        return em.find(FuncionalEntity.class, pFuncionalId);
    }
    
    public FuncionalEntity findByTipo(String pFuncional)
    {
        FuncionalEntity buscado = null;
        List<FuncionalEntity> lista = findAll();
        for(FuncionalEntity funcional: lista)
        {
            if(funcional.getNombre().equals(pFuncional))
                buscado = funcional;
        }
        return buscado;
    }
    
    /**
     * Encuentra en la base de datos el elemento con el nombre dado por parametro
     * @param Name
     * @return null si no lo encuentra, de lo contrario el elemento
     */
    public FuncionalEntity findByName(String Name)
    {
        FuncionalEntity buscado=null;
        List<FuncionalEntity> lista=findAll();
        for (FuncionalEntity funcional : lista) 
        {
            if(funcional.getNombre().equals(Name))
                buscado=funcional;
        }
        return buscado;
    }
    
    
    
    public List<FuncionalEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM FuncionalEntity u", FuncionalEntity.class);
        return query.getResultList();
    }
    public FuncionalEntity update(FuncionalEntity pFuncional)
    {
        return em.merge(pFuncional);
    }
    
    public void delete(Long escId)
    {
        FuncionalEntity funcional = em.find(FuncionalEntity.class, escId);
        em.remove(funcional);
    }
    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.IteracionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Rodrigo José González
 */
@Stateless
public class IteracionLogic {
     /**
    * Clase persistence de iteracion.
    */  
    @Inject
    private IteracionPersistence ip;
    
    /**
   * Crea una iteracion asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param iteracion. Requisito entity a ser verificado.
   * @return
   * @throws BusinessLogicException 
   */
  public IteracionEntity createIteracion(IteracionEntity iteracion) throws BusinessLogicException
  {
       if(iteracion.getFechaFin()==null||iteracion.getFechaInicio() == null)
      {
         throw new BusinessLogicException("No hay fechas definidas para el proyecto");      
      }
      iteracion=ip.create(iteracion);
      return iteracion;
  }
  
   /**
     * Obtener todos los interaciones existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<IteracionEntity> getIteraciones() 
    {
        List<IteracionEntity> iteraciones = ip.findAll();
        return iteraciones;
    }
    /**
     * Obtener una iteracion por medio de su id.
     * @param iteracionId: id del desarrollador para ser buscada.
     * @return la iteracion solicitada por medio de su id.
     */
    
    public IteracionEntity getIteracion(Long iteracionId) 
    { 
        IteracionEntity iteracionEntity = ip.find(iteracionId);
        return iteracionEntity;
    }
       /**
     *
     * Actualiza una iteracion
     * @param iteracion
     * @return iteracion con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public IteracionEntity  updateIteracion(IteracionEntity  iteracion) throws BusinessLogicException 
    {
       if(iteracion.getFechaFin()==null||iteracion.getFechaInicio()==null)
      {
         throw new BusinessLogicException("Las fechas no estan bien definidas");      
      }
        IteracionEntity newEntity =ip.update(iteracion);
        return newEntity;
    }  
     /**
     * Borra una iteracion.
     * @param iteracionId: id de la iteracion a borrar
     */
    public void deleteRequisito(Long iteracionId) 
    {
         ip.delete(iteracionId);
    }
    
}

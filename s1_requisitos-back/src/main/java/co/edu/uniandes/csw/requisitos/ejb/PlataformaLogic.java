/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.PlataformaEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.PlataformaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicole Bahamon Martinez
 */
@Stateless
public class PlataformaLogic {
    
   
@Inject
    private PlataformaPersistence persistence;
          /**
   * Crea una plataforma asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param plataforma. Plataforma entity a ser verificado.
   * @return plataforma 
   * @throws BusinessLogicException 
   */
    
    public PlataformaEntity createPlataforma(PlataformaEntity plataforma) throws BusinessLogicException
       {
           if (plataforma.getPlataforma()==null)
              {
                  throw new BusinessLogicException("La plataforma no esta definida aun");
              }
           plataforma=persistence.create(plataforma);
           return plataforma;
       }

     
  
 
    /**
   * Obtener todas las plataformas existentes en la base de datos.
     * @return una lista de las plataformas
     */
    public List<PlataformaEntity> getPlataformas() 
    {
        List<PlataformaEntity> plataforma = persistence.findAll();
        return plataforma;
    }
    /**
     * Obtener una plataforma por medio de su id.
     * @param plataformaID: id de la plataforma para ser buscada.
     * @return plataforma solicitada por medio de su id.
     */
    public PlataformaEntity getPlataforma(Long plataformaID) 
    { 
        PlataformaEntity plataformaEntity = persistence.find(plataformaID);
        return plataformaEntity;
    }
    
       /**
     *
     * Actualiza una plataforma
     * @param plataforma
     * @return plataforma con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public PlataformaEntity updatePlataforma(PlataformaEntity plataforma) throws BusinessLogicException 
    {
        if(plataforma.getPlataforma()==null)
      {
         throw new BusinessLogicException("Falta por definir la plataforma");      
      }
      
        PlataformaEntity newEntity =persistence.update(plataforma);
        return newEntity;
    }  
    /**
     * Borra una plataforma
     * @param plataformaID: id de la plataforma a borrar
     */
    public void deletePlataforma(Long plataformaID) 
    {
         persistence.delete(plataformaID);
    }   
}

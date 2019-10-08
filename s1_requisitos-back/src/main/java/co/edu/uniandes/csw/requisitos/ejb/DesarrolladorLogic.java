/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 * Parte logica de entidad desarrollador
 * 
 * @author Nicolás Tobo 
 * Adaptado de :https://github.com/Uniandes-isis2603/backstepbystep/blob/master/backstepbystep-back/src/main/java/co/edu/uniandes/csw/bookstore/ejb/BookLogic.java
 */
public class DesarrolladorLogic 
{
  /**
    * Clase persistence de desarrollador.
    */  
  @Inject
  private DesarrolladorPersistence dp;  

 /**
   * Crea un desarrollador asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param desarrollador. Requisito entity a ser verificado.
   * @return
   * @throws BusinessLogicException 
   */
  public DesarrolladorEntity createDesarrollador(DesarrolladorEntity desarrollador) throws BusinessLogicException
  {
       if(desarrollador.getTipo()==null||desarrollador.getTipo().equals(""))
      {
         throw new BusinessLogicException("Falta tipo del desarrollador.");      
      }
      else if(dp.find(desarrollador.getId())!=null)
      {
         throw new BusinessLogicException("Ya existe un desarrollador con ese id.");      
      }
      else if(dp.findByName(desarrollador.getNombre())!=null)
      {
         throw new BusinessLogicException("Ya existe un desarrollador con ese nombre.");      
      }
      else if(dp.findByCedula(desarrollador.getCedula())!=null)
      {
         throw new BusinessLogicException("Ya existe un desarrollador con esa cedula.");      
      }
      desarrollador=dp.create(desarrollador);
      return desarrollador;
  }
  
   /**
     * Obtener todos los desarrolladores existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<DesarrolladorEntity> getDesarrolladores() 
    {
        List<DesarrolladorEntity> desarrolladores = dp.findAll();
        return desarrolladores;
    }
   /**
     * Obtener un desarrollador por medio de su id.
     * @param desarrolladorId: id del desarrollador para ser buscada.
     * @return el desarrollador solicitado por medio de su id.
     */
    public DesarrolladorEntity getDesarrollador(Long desarrolladorId) 
    { 
        DesarrolladorEntity requisitoEntity = dp.find(desarrolladorId);
        return requisitoEntity;
    }
       /**
     *
     * Actualiza un desarrollador
     * @param desarrollador
     * @return desarrollador con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public DesarrolladorEntity  updateDesarrollador(DesarrolladorEntity  desarrollador) throws BusinessLogicException 
    {
       if(desarrollador.getTipo()==null||desarrollador.getTipo().equals(""))
      {
         throw new BusinessLogicException("Falta tipo del desarrollador.");      
      }
        DesarrolladorEntity newEntity =dp.update(desarrollador);
        return newEntity;
    }  
     /**
     * Borra un desarrollador.
     * @param desarrolladorId: id del requisito a borrar
     */
    public void deleteRequisito(Long desarrolladorId) 
    {
         dp.delete(desarrolladorId);
    }
    
}

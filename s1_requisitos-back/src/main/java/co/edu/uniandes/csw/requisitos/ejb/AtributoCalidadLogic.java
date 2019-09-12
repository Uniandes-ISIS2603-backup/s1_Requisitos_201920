/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.AtributoCalidadEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.AtributoCalidadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicole Bahamon Martinez
 */
@Stateless
public class AtributoCalidadLogic {
    
    @Inject
    private AtributoCalidadPersistence persistence;
     /**
   * Crea un AtributoCalidad asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param atributoCalidad. AtributoCalidad entity a ser verificado.
   * @return atributoCalidad 
   * @throws BusinessLogicException 
   */
    public AtributoCalidadEntity createAtributoCalidad(AtributoCalidadEntity atributoCalidad) throws BusinessLogicException
       {
           if (atributoCalidad.getTipo()==null)
              {
                  throw new BusinessLogicException("El atributo de calidad aun no tiene definido un tipo");
              }
           atributoCalidad=persistence.create(atributoCalidad);
           return atributoCalidad;
       }
   

   /**
     * Obtener todas los atributos de calidad existentes en la base de datos.
     * @return una lista de atributos de calidad.
     */
    public List<AtributoCalidadEntity> getAtributosCalidad() 
    {
        List<AtributoCalidadEntity> atributoscalidad = persistence.findAll();
        return atributoscalidad;
    }
    /**
     * Obtener un atributo calidad por medio de su id.
     * @param atributoCalidadID: id del atributo calidad para ser buscada.
     * @return el requisito solicitado por medio de su id.
     */
    public AtributoCalidadEntity getAtributoCalidad(Long atributoCalidadID) 
    { 
        AtributoCalidadEntity atributoCalidadEntity = persistence.find(atributoCalidadID);
        return atributoCalidadEntity;
    }
    
       /**
     *
     * Actualiza un atributo de calidad
     * @param atributo de calidad
     * @return atributo de calidad con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public AtributoCalidadEntity updateAtributoCalidad(AtributoCalidadEntity atributoCalidad) throws BusinessLogicException 
    {
        if(atributoCalidad.getTipo()==null)
      {
         throw new BusinessLogicException("Falta por definir el tipo del atributo de calidad");      
      }
      
        AtributoCalidadEntity newEntity =persistence.update(atributoCalidad);
        return newEntity;
    }  
    /**
     * Borra un atributo de calidad.
     * @param atributoCalidadID: id del atributo de calidad a borrar
     */
    public void deleteAtributoCalidad(Long atributoCalidadID) 
    {
         persistence.delete(atributoCalidadID);
    } 
}


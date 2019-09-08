/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Parte logica de entidad requisito
 * @author Nicolás Tobo
 */
@Stateless
public class RequisitoLogic 
{
   /**
    * Clase persistence de requisito.
    */  
  @Inject
  private RequisitoPersistence rp;
  /**
   * Crea un requisito asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param requisito. Requisito entity a ser verificado.
   * @return
   * @throws BusinessLogicException 
   */
  public RequisitosEntity createRequisito(RequisitosEntity requisito) throws BusinessLogicException
  {
      if(requisito.getAutor()==null||requisito.getAutor().equals(""))
      {
         throw new BusinessLogicException("Falta autor del requisito.");      
      }
      else if(requisito.getDescripcion()==null||requisito.getDescripcion().equals(""))
      {
         throw new BusinessLogicException("Falta la descripción del requisito.");      
      }
       else if(requisito.getImportancia()==null)
      {
         throw new BusinessLogicException("Falta la importancia del requisito.");      
      }
      //  else if(requisito.getImportancia()<0)
      //{
        // throw new BusinessLogicException("La importancia del requisito no puede ser negativa.");      
      //}
       else if(requisito.getEstabilidad()==null)
      {
         throw new BusinessLogicException("Falta definir la estabilidad del requisito.");      
      }
       else if(requisito.getFuente()==null||requisito.getFuente().equals(""))
      {
         throw new BusinessLogicException("Falta la fuente del requisito.");      
      }
      requisito=rp.create(requisito);
      return requisito;
  }
   /**
     * Obtener todas los requisitos existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<RequisitosEntity> getRequisitos() 
    {
        List<RequisitosEntity> editorials = rp.findAll();
        return editorials;
    }
    /**
     * Obtener un requisito por medio de su id.
     * @param requisitoId: id del requisito para ser buscada.
     * @return el requisito solicitado por medio de su id.
     */
    public RequisitosEntity getRequisito(Long requisitoId) 
    { 
        RequisitosEntity requisitoEntity = rp.find(requisitoId);
        return requisitoEntity;
    }
    
       /**
     *
     * Actualiza un requisito
     * @param requisito
     * @return requisito con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public RequisitosEntity updateRequisito(RequisitosEntity requisito) throws BusinessLogicException 
    {
        if(requisito.getAutor()==null||requisito.getAutor().equals(""))
      {
         throw new BusinessLogicException("Falta autor del requisito.");      
      }
      else if(requisito.getDescripcion()==null||requisito.getDescripcion().equals(""))
      {
         throw new BusinessLogicException("Falta la descripción del requisito.");      
      }
       else if(requisito.getImportancia()==null)
      {
         throw new BusinessLogicException("Falta la importancia del requisito.");      
      }
      //  else if(requisito.getImportancia()<0)
      //{
        // throw new BusinessLogicException("La importancia del requisito no puede ser negativa.");      
      //}
       else if(requisito.getEstabilidad()==null)
      {
         throw new BusinessLogicException("Falta definir la estabilidad del requisito.");      
      }
       else if(requisito.getFuente()==null||requisito.getFuente().equals(""))
      {
         throw new BusinessLogicException("Falta la fuente del requisito.");      
      }
        RequisitosEntity newEntity =rp.update(requisito);
        return newEntity;
    }  
    /**
     * Borra un requisito.
     * @param requisitoId: id del requisito a borrar
     */
    public void deleteRequisito(Long requisitoId) 
    {
         rp.delete(requisitoId);
    } 
}

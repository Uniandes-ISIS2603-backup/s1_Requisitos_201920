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
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 * Parte logica de entidad requisito
 * @author Nicolás Tobo
 * Adaptado de: https://github.com/Uniandes-isis2603/backstepbystep/blob/master/backstepbystep-back/src/main/java/co/edu/uniandes/csw/bookstore/ejb/BookLogic.java
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
      verificacionBasica(requisito);
      verificacionTipoRequisito(requisito);
      return rp.create(requisito);
  }
   /**
     * Obtener todas los requisitos existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<RequisitosEntity> getRequisitos() 
    {
        return rp.findAll();
    }
    /**
     * Obtener un requisito por medio de su id.
     * @param requisitoId: id del requisito para ser buscada.
     * @return el requisito solicitado por medio de su id.
     */
    public RequisitosEntity getRequisito(Long requisitoId) 
    { 
        return rp.find(requisitoId);
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
        verificacionBasica(requisito);
        verificacionTipoRequisito(requisito);
        return rp.update(requisito);
    }  
    /**
     * Borra un requisito.
     * @param requisitoId: id del requisito a borrar
     */
    public void deleteRequisito(Long requisitoId) 
    {
         rp.delete(requisitoId);
    } 
    
    /**
     * Verifica varias condiciones basicas de un requisito
     */
    private void verificacionBasica(RequisitosEntity requisito) throws BusinessLogicException
    {
        if(requisito.getId()==null)
      {
         throw new BusinessLogicException("Falta el id del requisito.");      
      }
      else if(rp.findByName(requisito.getNombre())!=null)
      {
         throw new BusinessLogicException("Ya existe un requisito con ese nombre.");      
      }
      else if(requisito.getNombre()==null||requisito.getNombre().equals(""))
      {
         throw new BusinessLogicException("Falta nombre del requisito.");      
      }
      else if(requisito.getAutor()==null||requisito.getAutor().equals(""))
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
        else if(requisito.getImportancia()<0)
      {
         throw new BusinessLogicException("La importancia del requisito no puede ser negativa.");      
      }
       else if(requisito.getEstabilidad()==null)
      {
         throw new BusinessLogicException("Falta definir la estabilidad del requisito.");      
      }
       else if(requisito.getFuente()==null||requisito.getFuente().equals(""))
      {
         throw new BusinessLogicException("Falta la fuente del requisito.");      
      }
    }
    
    /**
     * verifica el tipo del requisito
     */
    private  void verificacionTipoRequisito(RequisitosEntity requisito) throws BusinessLogicException
    {
       if(requisito.getTipo()==null)
      {
         throw new BusinessLogicException("El tipo de un requisito no puede ser null.");      
      }
       else if(!(requisito.getTipo().equals(RequisitosEntity.TipoRequisito.DESEMPEÑO)||requisito.getTipo().equals(RequisitosEntity.TipoRequisito.ESCALABILIDAD)||requisito.getTipo().equals(RequisitosEntity.TipoRequisito.FUNCIONAL)||requisito.getTipo().equals(RequisitosEntity.TipoRequisito.PLATAFORMA)||requisito.getTipo().equals(RequisitosEntity.TipoRequisito.SEGURIDAD)))
       {
        throw new BusinessLogicException("El tipo deberia estar dentro de los tipos posibles."); 
       }
    }
}
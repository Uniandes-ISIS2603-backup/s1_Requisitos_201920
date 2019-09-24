/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.RepresentanteDelClientePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Rodrigo José González Oviedo
 */
public class RepresentanteDelClienteLogic {
     /**
    * Clase persistence del cliente.
    */  
    @Inject
    private RepresentanteDelClientePersistence rp;
    /**
   * Crea un representante del ciente asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param representante. Requisito entity a ser verificado.
   * @return
   * @throws BusinessLogicException 
   */
  public RepresentanteDelClienteEntity createRepresentanteDelCliente(RepresentanteDelClienteEntity representante) throws BusinessLogicException
  {
       if(representante.getNombre() == null)
      {
         throw new BusinessLogicException("El representante no tiene nombre");      
      }
      return rp.create(representante);
      
  }
   /**
     * Obtener todos los representantes del cliente existentes en la base de datos.
     * @return una lista de representantes del cliente.
     */
    public List<RepresentanteDelClienteEntity> getRepresentantes() 
    {
        List<RepresentanteDelClienteEntity> representantes = rp.findAll();
        return representantes;
    }
   /**
     * Obtener un representante por medio de su id.
     * @param representanteId: id del representante para ser buscada.
     * @return el representante solicitado por medio de su id.
     */
    public RepresentanteDelClienteEntity getRepresentante(Long representanteId) 
    { 
        RepresentanteDelClienteEntity representanteEntity = rp.find(representanteId);
        return representanteEntity;
    }
         /**
     *
     * Actualiza un representante
     * @param representante
     * @return representante con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public RepresentanteDelClienteEntity  updateRepresentante(RepresentanteDelClienteEntity  representante) throws BusinessLogicException 
    {
       if(representante.getNombre()==null)
      {
         throw new BusinessLogicException("El nombre del representante no está definido");      
      }
        RepresentanteDelClienteEntity newEntity =rp.update(representante);
        return newEntity;
    }  
     /**
     * Borra un representante.
     * @param representanteId: id del requisito a borrar
     */
    public void deleteRequisito(Long desarrolladorId) 
    {
         rp.delete(desarrolladorId);
    }
    
    
}

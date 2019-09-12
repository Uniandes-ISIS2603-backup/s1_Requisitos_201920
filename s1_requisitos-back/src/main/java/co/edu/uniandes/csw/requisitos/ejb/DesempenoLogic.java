/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.DesempenoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DesempenoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicole Bahamon Martinez
 */
@Stateless
public class DesempenoLogic {
    
    
@Inject
    private DesempenoPersistence persistence;
      /**
   * Crea un Desempeño asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param desempeño. Desempeño entity a ser verificado.
   * @return desempeño 
   * @throws BusinessLogicException 
   */
    public DesempenoEntity createDesempeno(DesempenoEntity desempeno) throws BusinessLogicException
       {
           if (desempeno.getDesempeno()==null)
              {
                  throw new BusinessLogicException("El desempeño no esta definido aun");
              }
           desempeno=persistence.create(desempeno);
           return desempeno;
       }
 
    /**
   * Obtener todas los desempenos existentes en la base de datos.
     * @return una lista de desempenos.
     */
    public List<DesempenoEntity> getDesempenos() 
    {
        List<DesempenoEntity> desempeno = persistence.findAll();
        return desempeno;
    }
    /**
     * Obtener un desempeno por medio de su id.
     * @param desempenoID: id del desempeno para ser buscada.
     * @return el desempeno solicitado por medio de su id.
     */
    public DesempenoEntity getDesempeno(Long desempenoID) 
    { 
        DesempenoEntity desempenoEntity = persistence.find(desempenoID);
        return desempenoEntity;
    }
    
       /**
     *
     * Actualiza un desempeno
     * @param desempeno
     * @return desempeno con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public DesempenoEntity updateDesempeno(DesempenoEntity desempeno) throws BusinessLogicException 
    {
        if(desempeno.getDesempeno()==null)
      {
         throw new BusinessLogicException("Falta por definir el desempeño");      
      }
      
        DesempenoEntity newEntity =persistence.update(desempeno);
        return newEntity;
    }  
    /**
     * Borra un desempeno
     * @param desempenoID: id del desempeno a borrar
     */
    public void deleteDesempeno(Long desempenoID) 
    {
         persistence.delete(desempenoID);
    }   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.FuncionalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicole Bahamon Martinez
 */
@Stateless
public class FuncionalLogic {
    @Inject
    private FuncionalPersistence persistence;
       /**
   * Crea un requisito funcional asegurandose de cumplir todas las reglas logicas de la apliacion.
   * @param funcional. Funcional entity a ser verificado.
   * @return funcional 
   * @throws BusinessLogicException 
   */
    public FuncionalEntity createFuncional(FuncionalEntity funcional) throws BusinessLogicException
       {
           if (funcional.getNombre()==null)
              {
                  throw new BusinessLogicException("El requisito funcional no tiene un nombre definido");
              }
           funcional=persistence.create(funcional);
           return funcional;
       }
     
  
 
    /**
   * Obtener todas los requisitos funcionales existentes en la base de datos.
     * @return una lista de requisitos funcionales.
     */
    public List<FuncionalEntity> getFuncionales() 
    {
        List<FuncionalEntity> funcional = persistence.findAll();
        return funcional;
    }
    /**
     * Obtener un requisito funcional por medio de su id.
     * @param funcionalID: id del requisito funcional para ser buscado.
     * @return el requisito funcional solicitado por medio de su id.
     */
    public FuncionalEntity getFuncional(Long funcionalID) 
    { 
        FuncionalEntity funcinalEntity = persistence.find(funcionalID);
        return funcinalEntity;
    }
    
       /**
     *
     * Actualiza un requisito funcional
     * @param funcional
     * @return funcional con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public FuncionalEntity updateFuncional(FuncionalEntity funcional) throws BusinessLogicException 
    {
        if(funcional.getNombre()==null)
      {
         throw new BusinessLogicException("Falta por definir el nombre del requisito funcional");      
      }
      
        FuncionalEntity newEntity =persistence.update(funcional);
        return newEntity;
    }  
    /**
     * Borra un requisito funcional
     * @param funcionalID: id del requisito funcional a borrar
     */
    public void deleteFuncional(Long funcionalID) 
    {
         persistence.delete(funcionalID);
    }   
}  

    


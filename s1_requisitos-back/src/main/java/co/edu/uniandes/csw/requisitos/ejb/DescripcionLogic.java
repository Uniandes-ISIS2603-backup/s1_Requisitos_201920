/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DescripcionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Rubio
 */
@Stateless
public class DescripcionLogic {
    @Inject
    private DescripcionPersistence pDescripcion;
    
    /**
     * Método encargado de la creación de una descripción
     * @param descripcion la entidad de la descripcion
     * @return la entidad de la descripcion con sus respectivos elmeentos
     * @throws BusinessLogicException 
     */
    public DescripcionEntity createDescripcion(DescripcionEntity descripcion) throws BusinessLogicException
    {
        if(descripcion.getDescripcion()== null)
            throw new BusinessLogicException("El nombre de estudiante está vacío");
        descripcion = pDescripcion.create(descripcion);
        return descripcion;
    }
     /**
     * Obtener un descripciona por medio de su id.
     * @param descripcionId: id del descripciona para ser buscada.
     * @return el descripciona solicitado por medio de su id.
     */
    public DescripcionEntity getDescripcion(Long descripcionId) 
    { 
        DescripcionEntity descripcionEntity = pDescripcion.find(descripcionId);
        return descripcionEntity;
    }
     /**
     * Obtener todas las descripcionas existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<DescripcionEntity> getDescripcions() 
    {
        List<DescripcionEntity> descripciones = pDescripcion.findAll();
        return descripciones;
    }
    
       /**
     *
     * Actualiza un descripciona
     * @param descripcion
     * @return descripciona con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public DescripcionEntity updateDescripcion(DescripcionEntity descripcion) throws BusinessLogicException 
    {
        if(descripcion.getDescripcion()==null||descripcion.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("Falta el nombre de la descripciona.");
        } 

      
        DescripcionEntity newEntity =pDescripcion.update(descripcion);
        return newEntity;
    }  
    /**
     * Borra una descripciona.
     * @param descripcionId: id de la descripciona a borrar
     */
    public void deleteDescripcion(Long descripcionId) 
    {
         pDescripcion.delete(descripcionId);
    } 
    
}

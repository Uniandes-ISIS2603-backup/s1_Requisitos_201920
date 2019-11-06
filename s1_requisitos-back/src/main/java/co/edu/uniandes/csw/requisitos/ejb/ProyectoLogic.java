/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ProyectoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Juan Martinez
 */
@Stateless
public class ProyectoLogic {
    
    @Inject
    private ProyectoPersistence persistence;
    
    public ProyectoEntity createProyecto(ProyectoEntity proyecto)throws BusinessLogicException{
        if(proyecto.getFechaInicial().before(proyecto.getFechaFinal())){
            proyecto = persistence.create(proyecto);
            return proyecto;
        }else{
            throw new BusinessLogicException("La fecha iicial es posterior a la final");
        }
    }
    
    /**
     * Obtener todos los proyectos existentes en la base de datos.
     * @return una lista de requisitos.
     */
    public List<ProyectoEntity> getProyectos() 
    {
        return persistence.findAll();

        
    }
    /**
     * Obtener una iteracion por medio de su id.
     * @param iteracionId: id del desarrollador para ser buscada.
     * @return la iteracion solicitada por medio de su id.
     */
    
    public ProyectoEntity getProyecto(Long iteracionId) 
    { 
        return persistence.find(iteracionId);

    }
       /**
     *
     * Actualiza una iteracion
     * @param iteracion
     * @return iteracion con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    public ProyectoEntity  updateProyecto(ProyectoEntity  iteracion) throws BusinessLogicException 
    {
       if(iteracion.getFechaFinal()==null||iteracion.getFechaInicial()==null)
      {
         throw new BusinessLogicException("Las fechas no estan bien definidas");      
      }
        return  persistence.update(iteracion);
        
    }  
     /**
     * Borra una iteracion.
     * @param iteracionId: id de la iteracion a borrar
     */
    public void deleteProyecto(Long iteracionId) 
    {
         persistence.delete(iteracionId);
    }
    
    
    
    
}

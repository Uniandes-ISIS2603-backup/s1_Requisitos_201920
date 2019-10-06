/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class ModificacionesLogic {

    //persistencia de Modificaciones
    @Inject
    private ModificacionesPersistence persistence;

    /*
    *crea las modificaciones teniendo en cuenta las reglas de negocio
    * @param Modificaciones entity a ser creada
    * @return modificacionesEntity creada
     */
    public ModificacionesEntity createModificaciones(ModificacionesEntity modi) throws BusinessLogicException {
        if (modi.getDescripcion() == null) {
            throw new BusinessLogicException("descripcion vacia");
        }
        if (modi.getFechaModificacion() == null) {
            throw new BusinessLogicException("la fecha no puede estar vacia");
        }

        modi = persistence.create(modi);
        return modi;
    }

    /*
    * retorna una lista con todas las modificaciones
     */
    public List<ModificacionesEntity> getModificaciones() {
        List<ModificacionesEntity> lista = persistence.findAll();
        return lista;
    }

    /*
   * retorna una modificacion dado su id
    *@param id de modificacion a ser encontrada
    * modificacion encontrada
     */
    public ModificacionesEntity getModificacion(Long id) {
        ModificacionesEntity nueva = persistence.find(id);
        return nueva;
    }

    /*
    * actualiza un modificacion
    *@return modificaicon actualizada cumpliendo las reglas de negocio
     */
    public ModificacionesEntity updateModificaciones(ModificacionesEntity modi) throws BusinessLogicException {
        if (modi.getDescripcion() == null) {
            throw new BusinessLogicException("descripcion vacia");
        }
        if (modi.getFechaModificacion() == null) {
            throw new BusinessLogicException("la fecha no puede estar vacia");
        }

        ModificacionesEntity nueva = persistence.update(modi);
        return nueva;
    }

    /*
    * borra una modificacion
    */
    public void deleteModificaciones(Long id) {
        persistence.delete(id);
    }
}

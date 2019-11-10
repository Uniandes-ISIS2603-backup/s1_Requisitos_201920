/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @casoDeUso jf.rubio
 */
@Stateless
public class ModificacionesCasoDeUsoLogic {
    
    @Inject
    private ModificacionesPersistence modificacionesPersistence;
    
    @Inject
    private CasoDeUsoPersistence casoDeUsoPersistence;
    
     /**
     * Agregar un casoDeUso a una modificacion
     *
     * @param modificacionessId El id premio a guardar
     * @param casoDeUsosId El id del casoDeUso al cual se le va a guardar el premio.
     * @return El premio que fue agregado al casoDeUso.
     */
    public CasoDeUsoEntity addCasoDeUso(Long casoDeUsosId, Long modificacionessId) {
        CasoDeUsoEntity casoDeUsoEntity = casoDeUsoPersistence.find(casoDeUsosId);
        ModificacionesEntity modificacionesEntity = modificacionesPersistence.find(modificacionessId);
        modificacionesEntity.setCasoModificaciones(casoDeUsoEntity);
        return casoDeUsoPersistence.find(casoDeUsosId);
    }

    /**
     *
     * Obtener una modificacion por medio de su id y el de su casoDeUso.
     *
     * @param modificacionessId id del premio a ser buscado.
     * @return el casoDeUso solicitada por medio de su id.
     */
    public CasoDeUsoEntity getCasoDeUso(Long modificacionessId) {
        CasoDeUsoEntity casoDeUsoEntity = modificacionesPersistence.find(modificacionessId).getCasoModificaciones();
        return casoDeUsoEntity;
    }

    /**
     * Remplazar casoDeUso de una modificacion
     *
     * @param modificacionessId el id de la modificacion que se quiere actualizar.
     * @param casoDeUsosId El id del nuebo casoDeUso asociado al premio.
     * @return el nuevo casoDeUso asociado.
     */
    public CasoDeUsoEntity replaceCasoDeUso(Long modificacionessId, Long casoDeUsosId) {
        CasoDeUsoEntity casoDeUsoEntity = casoDeUsoPersistence.find(casoDeUsosId);
        ModificacionesEntity modificacionesEntity = modificacionesPersistence.find(modificacionessId);
        modificacionesEntity.setCasoModificaciones(casoDeUsoEntity);
        return casoDeUsoPersistence.find(casoDeUsosId);
    }

    /**
     * Borrar el casoDeUso de una modificacion
     *
     * @param modificacionessId El premio que se desea borrar del casoDeUso.
     * @throws BusinessLogicException si el premio no tiene casoDeUso
     */
    public void removeCasoDeUso(Long modificacionessId) throws BusinessLogicException {
        ModificacionesEntity modificacionesEntity = modificacionesPersistence.find(modificacionessId);
        if (modificacionesEntity.getCasoModificaciones() == null) {
            throw new BusinessLogicException("El premio no tiene casoDeUso");
        }
        CasoDeUsoEntity casoDeUsoEntity = casoDeUsoPersistence.find(modificacionesEntity.getCasoModificaciones().getId());
        modificacionesEntity.setCasoModificaciones(null);
        casoDeUsoEntity.getModificaciones().remove(modificacionesEntity);
    }
}

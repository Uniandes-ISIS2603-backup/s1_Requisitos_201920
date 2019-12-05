/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class CasoDeUsoLogic {

    //declaracion de la persistencia
    @Inject
    private CasoDeUsoPersistence persistence;

    /*
    * crea un casoDeUsoEntity teniendo en cuenta las reglas del negocio
    *@param CasoDeUsoEntity a ser creado
    *@return CasoDeUsoEntity creado
     */
    public void validar(CasoDeUsoEntity caso)  throws BusinessLogicException
    {
          if (caso.getNombre()==null){
            throw new BusinessLogicException("el  nombre no debe ser nulo");
        }
        if (caso.getPruebas() == null) {
            throw new BusinessLogicException("Las pruebas no deben ser nulas");

        }
        if (caso.getDocumentacion() == null) {
            throw new BusinessLogicException("la Documentacion no debe ser nula");
        } 
        if (caso.getServicios() == null) {
            throw new BusinessLogicException("los servicios no deben ser nulos");
        }
        if (caso.getServicios().isEmpty()){
            throw new BusinessLogicException("debe haber por lo menos un servicio");
            
        }
         if (caso.getEntidades().isEmpty()){
            throw new BusinessLogicException("debe haber por lo menos una entidad");
            
        }
         if (caso.getPreCondiciones().isEmpty()){
            throw new BusinessLogicException("debe haber por lo menos una precondicion");
            
        }
         if (caso.getPosCondiciones().isEmpty()){
            throw new BusinessLogicException("debe haber por lo menos una posCondicion");
            
        }
        
    }
    public CasoDeUsoEntity crearCasoDeUso(CasoDeUsoEntity caso) throws BusinessLogicException {
        validar(caso);
        caso = persistence.create(caso);
        return caso;
    }

    /*
    * retorna una lista de todos los casos
    *@return lista con todos los casos
     */
    public List<CasoDeUsoEntity> getCasos() {
        return persistence.findAll();
        
    }

    /*
    * retorna un caso de uso buscado por id
    *@param id del caso de uso a ser buscado
    *@return caso de uso encontrado
     */
    public CasoDeUsoEntity getCaso(Long id) {
        return persistence.find(id);
        
    }

    /*
    *actualiza un caso de uso existente
    *@param caso de uso a ser modificado
    *@return caso de uso modificado cumpliendo con las reglas de negocio
     */
    public CasoDeUsoEntity updateCasoDeUso(CasoDeUsoEntity caso) throws BusinessLogicException {
       validar(caso);
        return persistence.update(caso);
    }

    /*
    *Elimina un caso de uso
     */
    public void deleteCaso(Long casoDeUSoId) {
        persistence.delete(casoDeUSoId);
    }
}

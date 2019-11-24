/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.ejb;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Stateless
public class CasoDeUsoDesarrolladorLogic {
    @Inject
    private CasoDeUsoPersistence casoPersistence;
    
    private static final Logger LOGGER = Logger.getLogger(CasoDeUsoDesarrolladorLogic.class.getName());
    @Inject
    private DesarrolladorPersistence desPersistence;
    
    /**
     * Añade un representante de cliente al caso de uso 
     * @param representanteId
     * @param CasoId
     * @return Caso de uso
     * @throws BusinessLogicException 
     */
    public DesarrolladorEntity addRepresentante(Long representanteId, Long CasoId)throws BusinessLogicException
    {
        //Encuentra al desarrollador
        DesarrolladorEntity desEntity= desPersistence.find(representanteId);
        //Verifica el tipo de persona
        if (!desEntity.getTipoString().equals("RepresentanteDelCliente")){
            throw new BusinessLogicException("la persona debe ser de  tipo respresantante");
        }
        //Encuentra el caso de uso 
        CasoDeUsoEntity casoEntity= casoPersistence.find(CasoId);
        //Añade al caso de uso el representante.
        casoEntity.setRepresentanteDelCliente(desEntity);
        //Añade al representante el caso de uso
        List<CasoDeUsoEntity> listCasosDeUso=desEntity.getCasosDeUsoRepresentante();
        listCasosDeUso.add(casoEntity);
        desEntity.setCasosDeUsoRepresentante(listCasosDeUso);
        //Actualiza las dos entidades en la base de dato
        casoPersistence.update(casoEntity);
        desPersistence.update(desEntity);
     
        return desEntity;
    }
    
    /**
     * Obtiene el representante del cliente de un caso de uso
     * @param casoId
     * @return 
     */
    public DesarrolladorEntity getRepresentante(Long casoId)
    {
        DesarrolladorEntity desEntity= casoPersistence.find(casoId).getRepresentanteDelCliente();     
        return desEntity;
    }
    
    /**
     * Cambia el representante de un caso de uso
     * @pre Se asume que existen los dos representantes y que se va a sustituir uno con otro
     * @param representanteId
     * @param CasoId
     * @return
     * @throws BusinessLogicException 
     */
    public DesarrolladorEntity cambiarRepresentante(Long representanteId, Long CasoId)throws BusinessLogicException
    {
        //Encuentra las 3 entidades
        DesarrolladorEntity desEntityNuevo= desPersistence.find(representanteId);
        CasoDeUsoEntity casoEntity=casoPersistence.find(CasoId);
        DesarrolladorEntity desEntityViejo=casoEntity.getRepresentanteDelCliente();
        //Verifica la logica de la relacion 
        if (!desEntityNuevo.getTipoString().equals("RepresentanteDelCliente")){
            throw new BusinessLogicException("la persona debe ser de tipo representante");
        }
        //actualiza la entidad de casoDeUso
        casoEntity.setRepresentanteDelCliente(desEntityNuevo);
        //actualiza la lista de la entidad desEntityNuevo
        List<CasoDeUsoEntity>listaNuevo=desEntityNuevo.getCasosDeUsoRepresentante();
        listaNuevo.add(casoEntity);
        desEntityNuevo.setCasosDeUsoRepresentante(listaNuevo);
        //actualiza la lista de la entidad desEntityViejo
        List<CasoDeUsoEntity>listaViejo=desEntityNuevo.getCasosDeUsoRepresentante();
        listaViejo.remove(casoEntity);
        desEntityViejo.setCasosDeUsoRepresentante(listaViejo);
         //actualiza las tres entidades en la base de datos
        casoPersistence.update(casoEntity);
        desPersistence.update(desEntityViejo);
        desPersistence.update(desEntityNuevo);
        
        return desEntityNuevo;
    }
    
    
    /**
     * Añade un responsable a el caso de uso
     * @param responsableId
     * @param CasoId
     * @return
     * @throws BusinessLogicException 
     */
     public DesarrolladorEntity addResponsable(Long responsableId, Long CasoId)throws BusinessLogicException
     {
        //Encuentra al desarrollador
        DesarrolladorEntity desEntity= desPersistence.find(responsableId);
        //Verifica el tipo de persona
        if (!desEntity.getTipoString().equals("Responsable")){
            throw new BusinessLogicException("la persona debe ser de  tipo responsable");
        }
        //Encuentra el caso de uso 
        CasoDeUsoEntity casoEntity= casoPersistence.find(CasoId);
        //Añade al caso de uso el responsable.
        casoEntity.setResponsable(desEntity);
        //Añade al responsable el caso de uso
        List<CasoDeUsoEntity> listCasosDeUso=desEntity.getCasosDeUsoResponsable();
        listCasosDeUso.add(casoEntity);
        desEntity.setCasosDeUsoResponsable(listCasosDeUso);
        //Actualiza las dos entidades en la base de dato
        casoPersistence.update(casoEntity);
        desPersistence.update(desEntity);
        return desEntity;
    }
    
    /**
     * Retorna el respondable del caso de uso 
     * @param casoId
     * @return 
     */
    public DesarrolladorEntity getResponsable(Long casoId){
        
        DesarrolladorEntity desEntity= casoPersistence.find(casoId).getResponsable();
        return desEntity;
    }
    
    /**
     * Cambia el responsable de un caso de uso 
     * @pre Se asume que existen los dos responsables y que se va a sustituir uno con otro
     * @param responsableId
     * @param CasoId
     * @return
     * @throws BusinessLogicException 
     */
    public DesarrolladorEntity cambiarResponsable(Long responsableId, Long CasoId) throws BusinessLogicException
    {
        //Encuentra las 3 entidades
        DesarrolladorEntity desEntityNuevo= desPersistence.find(responsableId);
        CasoDeUsoEntity casoEntity=casoPersistence.find(CasoId);
        DesarrolladorEntity desEntityViejo=casoEntity.getResponsable();
        //Verifica la logica de la relacion 
        if (!desEntityNuevo.getTipoString().equals("Responsable")){
            throw new BusinessLogicException("la persona debe ser de tipo responsable");
        }
        //actualiza la entidad de casoDeUso
        casoEntity.setResponsable(desEntityNuevo);
        //actualiza la lista de la entidad desEntityNuevo
        List<CasoDeUsoEntity>listaNuevo=desEntityNuevo.getCasosDeUsoResponsable();
        listaNuevo.add(casoEntity);
        desEntityNuevo.setCasosDeUsoResponsable(listaNuevo);
        //actualiza la lista de la entidad desEntityViejo
        List<CasoDeUsoEntity>listaViejo=desEntityNuevo.getCasosDeUsoResponsable();
        listaViejo.remove(casoEntity);
        desEntityViejo.setCasosDeUsoResponsable(listaViejo);
         //actualiza las tres entidades en la base de datos
        casoPersistence.update(casoEntity);
        desPersistence.update(desEntityViejo);
        desPersistence.update(desEntityNuevo);
        
        return desEntityNuevo;    
    }
    

    
    
}

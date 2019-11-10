/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link RequisitoDTO} para manejar las relaciones entre los
 * RequisitoDTO y  modificacionesDTO.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *       "id": number,
 *       "fuente":String,
 *        "autor":String,
 *        "descripcion":String,
 *       "importancia":number,
 *       "estabilidad":boolean,
 *       "nombre":String,
 *       "comentariosAdicionales":String,
 *        "tipo":String,
 *        "casoDeUso":{@link CasoDeUsoDTO},
 *        "modificaciones":[{@link  ModificacionesDTO}]
 *  }
 * </pre> Por ejemplo un requisito se representa asi:<br>
 *
 *  *<pre>
 *
 *   {
 *          "id": 13,
 *           "fuente":"Empresa X",
 *           "autor":"Carlos Barragan",
 *          "descripcion":"Intento1",
 *           "importancia":10,
 *           "estabilidad":true,
 *           "nombre":"ImplementarIntento1",
 *          "comentariosAdicionales":"Hola",
 *          "tipo":"FUNCIONAL",
 *          "casoDeUso":
 *          {
 *              "documentacion": "doc",
 *                "id": "1010",
 *               "pruebas": false,
 *              "servicios": "FuncionalPrueba"
 *           },
 *           "modificaciones":[
 *              {
 *                "id": 2,
 *                "descripcion": "se cambio el nombre del usuario",
 *                "fechaModificacion ": "2014-01-01T23:28:56.782Z"
 *              },
 *              {
 *                "id": 3,
 *                "descripcion": "se cambio el nombre del usuario",
 *                "fechaModificacion ": "2014-01-01T23:28:56.782Z"
 *             }
 *            ]
 *      }
 *
 * </pre>
 *
 * @author Nicolás Tobo
 */
public class RequisitosDetailDTO extends RequisitosDTO 
{
    /**
     * Lista de ModificacionesDTO que puede tener el requisito
     */
    private List<ModificacionesDTO> modificaciones;
    
    /**
     * Constructor por defecto del detailDto
     */
    public RequisitosDetailDTO()
    {
        super();
    }
    /**
     * Crea un objeto RequisitosDetailDTO a partir de un objeto RequisitosEntity
     * incluyendo los atributos de RequisitoDTO.
     *
     * @param requisitoEntity Entidad RequisitosEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public RequisitosDetailDTO(RequisitosEntity requisitoEntity) 
    {
         super(requisitoEntity);
         if (requisitoEntity != null&&requisitoEntity.getModificaciones()!=null) 
         {
            modificaciones = new ArrayList<>();
            for (ModificacionesEntity entityMod : requisitoEntity.getModificaciones()) 
            {
                modificaciones.add(new ModificacionesDTO(entityMod));
            }
         }
    }
    
    /**
     * Convierte un objeto RequisitosDetailDTO a RequisitosEntity incluyendo los
     * atributos de RequisitosDTO.
     *
     * @return Nueva objeto RequisitosEntity.
     *
     */
    @Override
    public RequisitosEntity toEntity() {
        RequisitosEntity desarrolladorEntity = super.toEntity();
        if (modificaciones != null) {
            List<ModificacionesEntity> modsEntity = new ArrayList<>();
            for (ModificacionesDTO dtoReq : modificaciones) {
                modsEntity.add(dtoReq.toEntity());
            }
            desarrolladorEntity.setModificaciones(modsEntity);
        }
        return desarrolladorEntity;
    }

 
    /**
     * Retorna el las modificacionesDto de un requisitoDetailDto
     * @return 
     */
    public List<ModificacionesDTO> getModificaciones() {
        return modificaciones;
    }
    /**
     * Define las modificacionesDto de un requisitoDetailDto
     * @param modificaciones 
     */
    public void setModificaciones(List<ModificacionesDTO> modificaciones) {
        this.modificaciones = modificaciones;
    }
    
   
}
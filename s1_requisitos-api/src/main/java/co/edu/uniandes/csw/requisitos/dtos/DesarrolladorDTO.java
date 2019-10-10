/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import java.util.List;

/**
 * DesarrolladorDTO Objeto de transferencia de datos de Desarrollador. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id":number,
 *      "nombre":string,
 *      "correo":string,
 *      "cedula":number,
 *      "tipo":string,
 *      "requisitos":[],
 *      "modificaciones":[]   
 *  }
 * </pre> Por ejemplo un desarrollador se representa asi:<br>
 *
 * <pre>
 *
 *  {
 * "id":20,
 * "nombre":"F Bogoya",
 * "correo":"cb@compañiax.com",
 * "cedula":666,
 * "tipo":"Lider",
 * "requisitos":[{
 * "id": 13,
 * "fuente":"Ove",
 * "autor":"L",
 * "descripcion":"BLA",
 * "importancia":5,
 * "estabilidad":true,
 * "nombre":"Prueba herencia",
 * "comentariosAdicionales":"",
 * "casoDeUso":
 * {
 * "servicios":"hola",
 * "id": 12,
 * "documentacion":"como",
 * "pruebas":true
 * },
 * "modificaciones":[]
 * }
 * ]
 * }
 *
 * </pre>
 * @author Nicolas Tobo
 */
public class DesarrolladorDTO extends PersonaDTO
{
     /**
     * String que representa el tipo del desarrollador
     */
    private String tipo;
    /**
     * Constructor del detailDto
     */
    public DesarrolladorDTO()
    {
    }
     /**
     * Constructor del DTO a partir de una entidad
     * @param casoDeUso
     * @param entidad
     */
    public DesarrolladorDTO (DesarrolladorEntity entidad) 
    {
         super.setNombre(entidad.getNombre());
         super.setId(entidad.getId());
         super.setCorreo(entidad.getCorreo());
         super.setCedula(entidad.getCedula());  
         this.setTipo(entidad.getTipo());
    }
    
    /**
     * Metodo que devuelve un entity a partir de un DTO
     */
    public DesarrolladorEntity toEntity()
    {
         DesarrolladorEntity entidad=new DesarrolladorEntity();
         entidad.setNombre(this.getNombre());
         entidad.setId(this.getId());
         entidad.setCorreo(this.getCorreo());
         entidad.setCedula(this.getCedula());
         entidad.setTipo(this.tipo);
       
         return entidad;
   }
    /**
     * Retorna el tipo del desarrollador
     * @return tipo del desarrollador
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Define el tipo del desarrollador
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
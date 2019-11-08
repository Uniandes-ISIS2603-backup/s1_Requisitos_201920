/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity.TipoDesarrollador;
import java.io.Serializable;


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
public class DesarrolladorDTO implements Serializable
{
    
   
     /**
     * String que representa el tipo del desarrollador
     */
   private Long id;
    private TipoDesarrollador tipo;
    private String nombre;
    private String correo;
    private Integer edad;
    private Integer cedula;
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
        if (entidad!=null){
         this.setNombre(entidad.getNombre());
         this.setId(entidad.getId());
         this.setCorreo(entidad.getCorreo());
         this.setCedula(entidad.getCedula());  
         this.setTipo(entidad.getTipo());
        }
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
         entidad.setTipo(this.getTipo());
       
         return entidad;
   }

    /**
     * @return the Id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public TipoDesarrollador getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoDesarrollador tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the cedula
     */
    public Integer getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

   
}
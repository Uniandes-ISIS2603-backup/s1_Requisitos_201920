/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import java.util.List;

/**
 *
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
       
         return entidad;
   }
    /**
     * BLOQUE GETTER AND SETTER
     */
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}

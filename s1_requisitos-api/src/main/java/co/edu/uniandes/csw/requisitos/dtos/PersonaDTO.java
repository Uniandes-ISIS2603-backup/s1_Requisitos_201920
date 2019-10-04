/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;

/**
 *
 * @author Estudiante
 */
public class PersonaDTO {

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the cedula
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
    private String nombre;
    private String correo;
    private Integer cedula;
    private Long id;
    
    public PersonaEntity toEntity(){
    
         PersonaEntity persona=new PersonaEntity();
         persona.setId(this.getId());
         persona.setNombre(this.getNombre());
         persona.setCorreo(this.getCorreo());
         persona.setCedula(this.getCedula());
         return persona;
    }
      /**
     * Constructor vacio
     */
    public PersonaDTO(){
        
    }
    
    /**
     * Constructor del DTO a partir de una entidad
     * @param persona
     */
    public PersonaDTO(PersonaEntity persona)
    {
         setId(persona.getId());
         setNombre(persona.getNombre());
         setCorreo(persona.getCorreo());
         setCedula(persona.getCedula());
         
   
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.AtributoCalidadEntity;
import java.io.Serializable;

/**
 *
 * @author n.bahamon
 */
public class AtributoCalidadDTO extends RequisitosDTO implements Serializable{
  
    /**
     * Constructor vacio
    */
    private String tipo;
    /**
     * Constructor vacio
    */
    public AtributoCalidadDTO() 
    {
    }
     /**
     * Constructor del DTO a partir de una entidad
     * @param entidad
     */
    public AtributoCalidadDTO(AtributoCalidadEntity entidad) 
    {
         super.setNombre(entidad.getNombre());
         super.setId(entidad.getId());
         super.setAutor(entidad.getAutor());
         super.setFuente(entidad.getFuente());
         super.setDescripcion(entidad.getDescripcion());
         super.setEstabilidad(entidad.getEstabilidad());
         super.setComentariosAdicionales(entidad.getComentariosAdicionales());
         super.setImportancia(entidad.getImportancia());
         this.setTipo(entidad.getTipo());
    
    }
    /**
     * Metodo que devuelve un entity a partir de un DTO
     */
    public AtributoCalidadEntity toEntity()
    {
         AtributoCalidadEntity entidad=new AtributoCalidadEntity();
         entidad.setNombre(this.getNombre());
         entidad.setId(this.getId());
         entidad.setAutor(this.getAutor());
         entidad.setFuente(this.getFuente());
         entidad.setDescripcion(this.getDescripcion());
         entidad.setEstabilidad(this.getEstabilidad());
         entidad.setComentariosAdicionales(this.getComentariosAdicionales());
         entidad.setImportancia(this.getImportancia());
         entidad.setTipo(this.tipo);
         return entidad;
   }

  

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    public Boolean getEstabilidad() {
        return estabilidad;
    }

    public void setEstabilidad(Boolean estabilidad) {
        this.estabilidad = estabilidad;
    }

    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
       public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}


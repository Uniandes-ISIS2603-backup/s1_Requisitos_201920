/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.io.Serializable;

/**
 *
 * @author Nicolás Tobo
 */
public class FuncionalDTO extends RequisitosDTO implements Serializable 
{
    /**
     * Caso de uso del reqFuncional
     */
    private CasoDeUsoDTO casoDeUso;
    /**
     * Constructor vacio
    */
    public FuncionalDTO() 
    {
    }
     /**
     * Constructor del DTO a partir de una entidad
     * @param casoDeUso
     * @param entidad
     */
    public FuncionalDTO(FuncionalEntity entidad) 
    {
         super.setNombre(entidad.getNombre());
         super.setId(entidad.getId());
         super.setAutor(entidad.getAutor());
         super.setFuente(entidad.getFuente());
         super.setDescripcion(entidad.getDescripcion());
         super.setEstabilidad(entidad.getEstabilidad());
         super.setComentariosAdicionales(entidad.getComentariosAdicionales());
         super.setImportancia(entidad.getImportancia());
         CasoDeUsoDTO x=new CasoDeUsoDTO(entidad.getCasoFuncional());
         this.casoDeUso = x;
    }
    /**
     * Metodo que devuelve un entity a partir de un DTO
     */
    public FuncionalEntity toEntity()
    {
         FuncionalEntity entidad=new FuncionalEntity();
         entidad.setNombre(this.getNombre());
         entidad.setId(this.getId());
         entidad.setAutor(this.getAutor());
         entidad.setFuente(this.getFuente());
         entidad.setDescripcion(this.getDescripcion());
         entidad.setEstabilidad(this.getEstabilidad());
         entidad.setComentariosAdicionales(this.getComentariosAdicionales());
         entidad.setImportancia(this.getImportancia());
         entidad.setCasoFuncional(casoDeUso.toEntity());
         return entidad;
   }

    /**
     * Bloque getters and setters 
     * 
     */
    public CasoDeUsoDTO getCasoDeUso() 
    {
        return casoDeUso;
    }

    public void setCasoDeUso(CasoDeUsoDTO casoDeUso) 
    {
        this.casoDeUso = casoDeUso;
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
}

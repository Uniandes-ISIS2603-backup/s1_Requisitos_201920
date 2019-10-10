/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
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
     * Retorna el caso de uso del requisito funcional
     * @return casoDeUso del requisito funcional
     */
    public CasoDeUsoDTO getCasoDeUso() 
    {
        return casoDeUso;
    }
     /**
     * Retorna la fuente del requisito
     * @return fuente del requisito
     */
     public String getFuente() {
        return fuente;
    }
    /**
     * Define la fuente del requisito
     * @param fuente 
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    /**
     * Retorna el autor del requisito
     * @return 
     */
    public String getAutor() {
        return autor;
    }
    /**
     * Define el autor del requisito
     * @param autor 
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * Retorna la descripcion del requisito
     * @return descripcion del requisito
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * 
     * Define la descripcion del requisito
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Retorna la importancia del requisito
     * @return 
     */
    public Integer getImportancia() {
        return importancia;
    }
    /**
     * Define la importancia del requisito
     * @param importancia 
     */
    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }
    /**
     * Retorna un indicador si el requisito es estable
     * @return 
     */
    public Boolean getEstabilidad() {
        return estabilidad;
    }
    /**
     * Define la estabilidad del requisito
     * @param estabilidad 
     */
    public void setEstabilidad(Boolean estabilidad) {
        this.estabilidad = estabilidad;
    }
    /**
     * Retorna los comentarios adicionales que tenga un requisito
     * @return comentarios adicionales
     */
    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }
    /**
     * Define los comentarios adicionales de un requisito
     * @param comentariosAdicionales 
     */
    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }
    /**
     * Retorna el id de un requisito
     * @return id
     */
    public Long getId() {
        return id;
    }
    /**
     * Define el id de un requisito
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Retorna el nombre del requisito
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Define el nombre del requisito
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
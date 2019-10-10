/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import java.io.Serializable;

/**
 *
 * @author Nicolás Tobo 
 */
public class RequisitosDTO implements Serializable 
{
   /**
     * Fuente de donde se escribe el requisito
     */
    protected String fuente;
    /**
     * Autor del requisito
     */
    protected String autor;
    /**
     * Informacion que tiene el requisito
     */
    protected String descripcion;
    /**
     * Numero que representa la importancia del requisito
     */
    protected Integer importancia;
    /**
     * Boolena que representa si el requisito es estable(fijo)
     */
    protected Boolean estabilidad;
    /**
     * Comentarios adicionales sobre el requisito
     */
    protected String comentariosAdicionales;
    /**
     * Id del DTO
     */
    protected Long id;
     /**
      * Nombre del requisito
      */
     protected String nombre;
    /**
     * Constructor vacio
     */
    public RequisitosDTO()
    {
        
    }
    
    /**
     * Constructor del DTO a partir de una entidad
     * @param entidad
     */
    public RequisitosDTO(RequisitosEntity entidad)
    {
         setNombre(entidad.getNombre());
         setId(entidad.getId());
         setAutor(entidad.getAutor());
         setFuente(entidad.getFuente());
         setDescripcion(entidad.getDescripcion());
         setEstabilidad(entidad.getEstabilidad());
         setComentariosAdicionales(entidad.getComentariosAdicionales());
         setImportancia(entidad.getImportancia()); 
    }
    
    /**
     * Metodo que devuelve un entity a partir de un DTO
     */
    public RequisitosEntity toEntity(){
    
         RequisitosEntity entidad=new RequisitosEntity();
         entidad.setNombre(this.getNombre());
         entidad.setId(this.getId());
         entidad.setAutor(this.getAutor());
         entidad.setFuente(this.getFuente());
         entidad.setDescripcion(this.getDescripcion());
         entidad.setEstabilidad(this.getEstabilidad());
         entidad.setComentariosAdicionales(this.getComentariosAdicionales());
         entidad.setImportancia(this.getImportancia());
         return entidad;
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
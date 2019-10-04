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
     * BLOQUE GETTERS AND SETTERS
     */
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

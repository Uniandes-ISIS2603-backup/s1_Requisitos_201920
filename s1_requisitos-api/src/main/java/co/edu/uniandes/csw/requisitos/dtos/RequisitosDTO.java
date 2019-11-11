/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity.TipoRequisito;
import java.io.Serializable;

/** 
 * RequisitoDTO Objeto de transferencia de datos de Requisito. Los DTO contienen 
 * las representaciones de los JSON que se transfieren entre el cliente y el 
 * servidor. 
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
 *        "casoDeUso":{@link CasoDeUsoDTO} 
 *  } 
 * </pre> Por ejemplo un requisito se representa asi:<br> 
 * <pre> 
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
 *           } 
 *      } 
 * 
 * </pre> 
 * 
 * @author Nicolás Tobo 
 */ 
public class RequisitosDTO implements Serializable 
{
   /**
     * Fuente de donde se escribe el requisito
     */
    private String fuente;
    /**
     * Autor del requisito
     */
    private String autor;
    /**
     * Informacion que tiene el requisito
     */
    private String descripcion;
    /**
     * Numero que representa la importancia del requisito
     */
    private Integer importancia;
    /**
     * Boolena que representa si el requisito es estable(fijo)
     */
    private Boolean estabilidad;
    /**
     * Comentarios adicionales sobre el requisito
     */
    private String comentariosAdicionales;
    /**
     * Id del DTO
     */
    private Long id;
     /**
      * Nombre del requisito
      */
     private String nombre;
     /**
      * tipo Del requisito
      */
     private TipoRequisito tipo;
     /**
      * Caso de uso del requisito
      */
     private CasoDeUsoEntity requisitosFuncionalesCaso;
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
         setTipo(entidad.getTipo());
         setRequisitosFuncionalesCaso(entidad.getRequisitosFuncionalesCaso());
         
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
         entidad.setTipo(this.getTipo());
         entidad.setRequisitosFuncionalesCaso(this.getRequisitosFuncionalesCaso());
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
    /**
     * Retorna el tipo del requisito
     * @return 
     */
    public TipoRequisito getTipo() {
        return tipo;
    }
    /**
    *Define el tipo del requisito
    */
    public void setTipo(TipoRequisito tipo) {
        this.tipo = tipo;
    }
    /**
     * Retorna el caso de uso del requisito
     * @return 
     */
    public CasoDeUsoEntity getRequisitosFuncionalesCaso() {
        return requisitosFuncionalesCaso;
    }
    /**
     * Define la base de datos 
     * @param requisitosFuncionalesCaso 
     */
    public void setRequisitosFuncionalesCaso(CasoDeUsoEntity requisitosFuncionalesCaso) {
        this.requisitosFuncionalesCaso = requisitosFuncionalesCaso;
    }
    
}
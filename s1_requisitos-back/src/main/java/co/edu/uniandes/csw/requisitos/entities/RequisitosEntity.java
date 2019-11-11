/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 * Entidad que representa un requisito funcional
 *
 * @author Nicolas Tobo
 */
@Entity
public class RequisitosEntity extends BaseEntity implements Serializable {

    /**
     * Enumeracion que determina el tipo de requsito
     * @author Nicolas Tobo
     */
    public enum TipoRequisito 
    {
        FUNCIONAL,SEGURIDAD,PLATAFORMA,ESCALABILIDAD,DESEMPENO
    }

    /**
     * Desarrollador del requisito
     */
    @PodamExclude
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private DesarrolladorEntity desarrollador;

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
    @PodamIntValue(minValue = 0, maxValue = Integer.MAX_VALUE)
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
     * nombre del requisito
     */
    private String nombre;
    /**
     * Tipo del requisito
     */
    private TipoRequisito tipo;
  
    /**
     * Lista de modificaciones del requisito
     */
    @PodamExclude
    @OneToMany(
            mappedBy = "modificacionesRequisito",
            cascade = CascadeType.ALL
    )
    private List<ModificacionesEntity> modificaciones = new ArrayList<>();
    /**
     * Caso de uso del requisito
     */
    @PodamExclude
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private CasoDeUsoEntity requisitosFuncionalesCaso;

    /**
     * @return the desarrollador
     */
    public DesarrolladorEntity getDesarrollador() {
        return desarrollador;
    }

    /**
     * @param desarrollador the desarrollador to set
     */
    public void setDesarrollador(DesarrolladorEntity desarrollador) {
        this.desarrollador = desarrollador;
    }

    /**
     * @return the modificaciones
     */
    public List<ModificacionesEntity> getModificaciones() {
        return modificaciones;
    }

    /**
     * @param modificaciones the modificaciones to set
     */
    public void setModificaciones(List<ModificacionesEntity> modificaciones) {
        this.modificaciones = modificaciones;
    }

    /**
     * @return the requisitosFuncionalesCaso
     */
    public CasoDeUsoEntity getRequisitosFuncionalesCaso() {
        return requisitosFuncionalesCaso;
    }

    /**
     * @param requisitosFuncionalesCaso the requisitosFuncionalesCaso to set
     */
    public void setRequisitosFuncionalesCaso(CasoDeUsoEntity requisitosFuncionalesCaso) {
        this.requisitosFuncionalesCaso = requisitosFuncionalesCaso;
    }


    /**
     * Retorna si el requisito es estable o no
     *
     * @return
     */
    public Boolean getEstabilidad() {
        return estabilidad;
    }

    /**
     * Define la estabilidad del requisito
     *
     * @param estabilidad
     */
    public void setEstabilidad(Boolean estabilidad) {
        this.estabilidad = estabilidad;
    }

    /**
     * Retorna los comentarios adicionales del requisito
     *
     * @return
     */
    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    /**
     * Define los comentarios adicionales del requisito
     *
     * @param comentariosAdicionales
     */
    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }

    /**
     * Retorna el nombre del requisito
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el tipo del requisito
     * @param tipo 
     */
    public void setTipo(TipoRequisito tipo) {
        this.tipo = tipo;
    }
    /**
     * Define el tipo del requisito por medio de un string
     * @param tipo
     */
    public void setTipoString(String tipo) 
    {
       if(tipo.equalsIgnoreCase("FUNCIONAL"))
          this.tipo = TipoRequisito.FUNCIONAL; 
       else if(tipo.equalsIgnoreCase("SEGURIDAD"))
          this.tipo=TipoRequisito.SEGURIDAD;
       else if(tipo.equalsIgnoreCase("PLATAFORMA"))
          this.tipo=TipoRequisito.PLATAFORMA;
       else if(tipo.equalsIgnoreCase("ESCALABILIDAD"))
          this.tipo=TipoRequisito.ESCALABILIDAD;
       else if(tipo.equalsIgnoreCase("DESEMPENO"))
          this.tipo=TipoRequisito.DESEMPENO;   
    }
    /**
     * Retorna la fuente
     *
     * @return
     */
    public String getFuente() {
        return fuente;
    }

    /**
     * Define la fuente de
     *
     * @param fuente
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    /**
     * Retorna el autor del requisito
     *
     * @return
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define el autor del requisito
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * Define el nombre del requisito
     *
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
     * Retorna la descripcion del requisito
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Definerecibe la descripcion del requisito
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la importancia del requisito
     *
     * @return
     */
    public Integer getImportancia() {
        return importancia;
    }

    /**
     * Define la importancia del requisito
     *
     * @param importancia
     */
    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }
   
}

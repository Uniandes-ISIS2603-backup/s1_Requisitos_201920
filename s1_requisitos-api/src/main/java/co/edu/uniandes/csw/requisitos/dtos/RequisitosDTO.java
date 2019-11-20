/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity.TipoRequisito;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
 *           "autor": "Carlos Barragan",
 *            "comentariosAdicionales": "Hola",
 *            "descripcion": "Intento1",
 *            "estabilidad": true,
 *            "fuente": "Empresa X",
 *            "id": 1,
 *            "importancia": 10,
 *            "nombre": "ImplementarIntento1",
 *              "requisitosFuncionalesCaso": {
 *              "id": 1,
 *               "caminosAlternos": [],
 *              "caminosExcepcion": [],
 *              "entidades": [],
 *              "funcionales": [],
 *              "modificaciones": [],
 *              "posCondiciones": [],
 *              "preCondiciones": [],
 *              "servicios": []
 *              },
 *               "tipo": "SEGURIDAD"
 *      }
 *
 * </pre>
 *
 * @author Nicolás Tobo
 */
public class RequisitosDTO implements Serializable {

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
    @Enumerated(EnumType.ORDINAL)
    private TipoRequisito tipo;
    /**
     * Caso de uso del requisito
     */
    private CasoDeUsoDTO requisitosFuncionalesCaso;
    /**
     * Desarrollador del requisito
     */
    private DesarrolladorDTO desarrollador;

    /**
     * Constructor vacio
     */
    public RequisitosDTO() {
        
    }

    /**
     * Constructor del DTO a partir de una entidad
     *
     * @param entidad
     */
    public RequisitosDTO(RequisitosEntity entidad) {
        if (entidad != null) {
            this.nombre = entidad.getNombre();
            this.id = entidad.getId();
            this.autor = entidad.getAutor();
            this.fuente = entidad.getFuente();
            this.descripcion = entidad.getDescripcion();
            this.estabilidad = entidad.getEstabilidad();
            this.comentariosAdicionales = entidad.getComentariosAdicionales();
            this.importancia = entidad.getImportancia();
            this.tipo = entidad.getTipo();
            if (entidad.getRequisitosFuncionalesCaso() != null) {
                this.requisitosFuncionalesCaso = new CasoDeUsoDTO(entidad.getRequisitosFuncionalesCaso());
            } else {
                this.requisitosFuncionalesCaso = null;
            }
            if (entidad.getDesarrollador() != null) {
                this.desarrollador = new DesarrolladorDTO(entidad.getDesarrollador());
            } else {
                this.desarrollador = null;
            }
        }
        
    }

    /**
     * Metodo que devuelve un entity a partir de un DTO
     */
    public RequisitosEntity toEntity() {
        RequisitosEntity entidad = new RequisitosEntity();
        entidad.setNombre(this.nombre);
        entidad.setId(this.id);
        entidad.setAutor(this.autor);
        entidad.setFuente(this.fuente);
        entidad.setDescripcion(this.descripcion);
        entidad.setEstabilidad(this.estabilidad);
        entidad.setComentariosAdicionales(this.comentariosAdicionales);
        entidad.setImportancia(this.importancia);
        entidad.setTipo(this.tipo);
        if (this.requisitosFuncionalesCaso != null) {
            entidad.setRequisitosFuncionalesCaso(this.requisitosFuncionalesCaso.toEntity());
        }
        if (this.desarrollador != null) {
            entidad.setDesarrollador(this.desarrollador.toEntity());
        }
        return entidad;
    }

    /**
     * Define la fuente del requisito
     *
     * @param fuente
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
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
     *
     * Define la descripcion del requisito
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 
    
    /**
     * Define la importancia del requisito
     *
     * @param importancia
     */
    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
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
     * Define los comentarios adicionales de un requisito
     *
     * @param comentariosAdicionales
     */
    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
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
     * Define el tipo del requisito
     */
    public void setTipo(TipoRequisito tipo) {
        this.tipo = tipo;
    }

    /**
     * Define el desarrollador
     * @param desarrollador 
     */
    public void setDesarrollador(DesarrolladorDTO desarrollador) {
        this.desarrollador = desarrollador;
    }    
    
    /**
     * Define el id de un requisito
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }    
    
    /**
     * Retorna la fuente del requisito
     *
     * @return fuente del requisito
     */
    public String getFuente() {
        return fuente;
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
     * Retorna la descripcion del requisito
     *
     * @return descripcion del requisito
     */
    public String getDescripcion() {
        return descripcion;
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
     * Retorna un indicador si el requisito es estable
     *
     * @return
     */
    public Boolean getEstabilidad() {
        return estabilidad;
    }

    /**
     * Retorna los comentarios adicionales que tenga un requisito
     *
     * @return comentarios adicionales
     */
    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    /**
     * Retorna el id de un requisito
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Retorna el nombre del requisito
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * Retorna el tipo del requisito
     *
     * @return
     */
    public TipoRequisito getTipo() {
        return tipo;
    }

    /**
     * Retorna el caso de uso del requisito
     *
     * @return
     */
    public CasoDeUsoDTO getRequisitosFuncionalesCaso() {
        return requisitosFuncionalesCaso;
    }

    /**
     * Define la base de datos
     *
     * @param requisitosFuncionalesCaso
     */
    public void setRequisitosFuncionalesCaso(CasoDeUsoDTO requisitosFuncionalesCaso) {
        this.requisitosFuncionalesCaso = requisitosFuncionalesCaso;
    }
    /**
     * Retorna el desarrollador
     * @return 
     */
    public DesarrolladorDTO getDesarrollador() {
        return desarrollador;
    }
}

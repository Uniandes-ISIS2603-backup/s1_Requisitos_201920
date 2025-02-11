/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * CasoDeUsoDTO Objeto de transferencia de datos de CasoDeUso. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "servicios": string,
 *      "documentacion": string,
 *      "pruebas": boolean
 *   }
 * </pre> Por ejemplo un casoDeUso se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "servicios": "Login",
 *      "documentacion": "realiza el logn de usuario",
 *      "prubas": "false"
 *   }
 *
 * </pre>
 *
 * @author Maria Alejandra Escalante
 */
public class CasoDeUsoDTO implements Serializable {

    /*
    se crean las variables del tipo caso de uso
     */
    private Long id;
    private String nombre;
    private List<String> servicios = new ArrayList<>();
    //variable encargada de almacenar la documentacion del caso de uso
    private String documentacion;
    //variable encargada de almacenar los resultados de las pruebas del caso de uso
    private Boolean pruebas;
    //variable encargada de almacenar las entidades involucradas en el caso

    private List<String> entidades = new ArrayList<>();

    //variable encargada de almacenar los caminos de excepcion involucradas en el caso
    private List<String> caminosExcepcion = new ArrayList<>();

    //variable encargada de almacenar las postcondiciones involucradas en el caso
    private List<String> posCondiciones = new ArrayList<>();

    //variable encargada de almacenar las pre condicionesinvolucradas en el caso
    private List<String> preCondiciones = new ArrayList<>();

    //variable encargada de almacenar los caminos alternos involucradas en el caso
    private List<String> caminosAlternos = new ArrayList<>();

    private DesarrolladorDTO representanteDelCliente;
    private DesarrolladorDTO responsable;

    public CasoDeUsoDTO() {

    }

    public CasoDeUsoDTO(CasoDeUsoEntity caso) {
        if (caso != null) {
            this.id = caso.getId();
            this.nombre = caso.getNombre();
            this.pruebas = caso.getPruebas();
            this.documentacion = caso.getDocumentacion();
            this.servicios = caso.getServicios();
            this.caminosAlternos = caso.getCaminosAlternos();
            this.caminosExcepcion = caso.getCaminosExcepcion();
            this.entidades = caso.getEntidades();
            this.posCondiciones = caso.getPosCondiciones();
            this.preCondiciones = caso.getPreCondiciones();
            if (caso.getRepresentanteDelCliente() != null) {
                this.representanteDelCliente = new DesarrolladorDTO(caso.getRepresentanteDelCliente());
            } else {
                this.representanteDelCliente = null;
            }
            if (caso.getResponsable() != null) {
                this.responsable = new DesarrolladorDTO(caso.getResponsable());
            } else {
                this.responsable = null;
            }
        }
    }

    public CasoDeUsoEntity toEntity() {
        CasoDeUsoEntity entidad = new CasoDeUsoEntity();
        entidad.setId(this.id);
        entidad.setPruebas(this.pruebas);
        entidad.setDocumentacion(this.documentacion);
        entidad.setServicios(this.servicios);
        entidad.setCaminosAlternos(this.caminosAlternos);
        entidad.setCaminosExcepcion(this.caminosExcepcion);
        entidad.setEntidades(this.entidades);
        entidad.setPosCondiciones(this.posCondiciones);
        entidad.setPreCondiciones(this.preCondiciones);
        if (this.representanteDelCliente != null) {
            entidad.setRepresentanteDelCliente(this.representanteDelCliente.toEntity());
        }
        if (this.responsable != null) {
            entidad.setResponsable(this.responsable.toEntity());
        }
        entidad.setNombre(this.nombre);
        return entidad;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the servicios
     */
    public List<String> getServicios() {
        return servicios;
    }

    /**
     * @return the documentacion
     */
    public String getDocumentacion() {
        return documentacion;
    }

    /**
     * @return the entidades
     */
    public List<String> getEntidades() {
        return entidades;
    }

    /**
     * @return the posCondiciones
     */
    public List<String> getPosCondiciones() {
        return posCondiciones;
    }

    /**
     * @return the representanteDelCliente
     */
    public DesarrolladorDTO getRepresentanteDelCliente() {
        return representanteDelCliente;
    }

    /**
     * @return the pruebas
     */
    public Boolean getPruebas() {
        return pruebas;
    }

    /**
     * @return the caminosAlternos
     */
    public List<String> getCaminosAlternos() {
        return caminosAlternos;
    }

    /**
     * @return the caminosExcepcion
     */
    public List<String> getCaminosExcepcion() {
        return caminosExcepcion;
    }

    /**
     * @return the responsable
     */
    public DesarrolladorDTO getResponsable() {
        return responsable;
    }

    /**
     * @return the preCondiciones
     */
    public List<String> getPreCondiciones() {
        return preCondiciones;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    /**
     * @param documentacion the documentacion to set
     */
    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    /**
     * @param pruebas the pruebas to set
     */
    public void setPruebas(Boolean pruebas) {
        this.pruebas = pruebas;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(List<String> entidades) {
        this.entidades = entidades;
    }

    /**
     * @param caminosExcepcion the caminosExcepcion to set
     */
    public void setCaminosExcepcion(List<String> caminosExcepcion) {
        this.caminosExcepcion = caminosExcepcion;
    }

    /**
     * @param posCondiciones the posCondiciones to set
     */
    public void setPosCondiciones(List<String> posCondiciones) {
        this.posCondiciones = posCondiciones;
    }

    /**
     * @param preCondiciones the preCondiciones to set
     */
    public void setPreCondiciones(List<String> preCondiciones) {
        this.preCondiciones = preCondiciones;
    }

    /**
     * @param caminosAlternos the caminosAlternos to set
     */
    public void setCaminosAlternos(List<String> caminosAlternos) {
        this.caminosAlternos = caminosAlternos;
    }

    /**
     * @param representanteDelCliente the representanteDelCliente to set
     */
    public void setRepresentanteDelCliente(DesarrolladorDTO representanteDelCliente) {
        this.representanteDelCliente = representanteDelCliente;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(DesarrolladorDTO responsable) {
        this.responsable = responsable;
    }

}

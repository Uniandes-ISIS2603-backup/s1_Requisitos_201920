/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * CasoDeUsoDTO Objeto de transferencia de datos de CasoDeUso. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
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

    
    private DesarrolladorEntity representanteDelCliente;
    private DesarrolladorEntity responsable;

    
    public CasoDeUsoDTO (){
        
    }
    
    public CasoDeUsoDTO(CasoDeUsoEntity caso){
        if (caso!=null){
        this.id=caso.getId();
        this.nombre=caso.getNombre();
        this.pruebas=caso.getPruebas();
        this.documentacion=caso.getDocumentacion();
        this.servicios=caso.getServicios();
        this.caminosAlternos=caso.getCaminosAlternos();
        this.caminosExcepcion=caso.getCaminosExcepcion();
        this.entidades=caso.getEntidades();
        this.posCondiciones=caso.getPosCondiciones();
        this.preCondiciones=caso.getPreCondiciones();
        this.representanteDelCliente=caso.getRepresentanteDelCliente();
        this.responsable=caso.getResponsable();
        
    }
    }
    
    
    public CasoDeUsoEntity toEntity(){
        CasoDeUsoEntity entidad= new CasoDeUsoEntity();
        entidad.setId(this.getId());
        entidad.setPruebas(this.getPruebas());
        entidad.setDocumentacion(this.getDocumentacion());
        entidad.setServicios(this.getServicios());
        entidad.setCaminosAlternos(this.getCaminosAlternos());
        entidad.setCaminosExcepcion(this.getCaminosExcepcion());
        entidad.setEntidades(this.getEntidades());
        entidad.setPosCondiciones(this.getPosCondiciones());
        entidad.setPreCondiciones(this.getPreCondiciones());
        entidad.setRepresentanteDelCliente(this.getRepresentanteDelCliente());
        entidad.setResponsable(this.getResponsable());
        entidad.setNombre(this.getNombre());
        return entidad;
    }

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
     * @return the servicios
     */
    public List<String> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the documentacion
     */
    public String getDocumentacion() {
        return documentacion;
    }

    /**
     * @param documentacion the documentacion to set
     */
    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    /**
     * @return the pruebas
     */
    public Boolean getPruebas() {
        return pruebas;
    }

    /**
     * @param pruebas the pruebas to set
     */
    public void setPruebas(Boolean pruebas) {
        this.pruebas = pruebas;
    }

    /**
     * @return the entidades
     */
    public List<String> getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(List<String> entidades) {
        this.entidades = entidades;
    }

    /**
     * @return the caminosExcepcion
     */
    public List<String> getCaminosExcepcion() {
        return caminosExcepcion;
    }

    /**
     * @param caminosExcepcion the caminosExcepcion to set
     */
    public void setCaminosExcepcion(List<String> caminosExcepcion) {
        this.caminosExcepcion = caminosExcepcion;
    }

    /**
     * @return the posCondiciones
     */
    public List<String> getPosCondiciones() {
        return posCondiciones;
    }

    /**
     * @param posCondiciones the posCondiciones to set
     */
    public void setPosCondiciones(List<String> posCondiciones) {
        this.posCondiciones = posCondiciones;
    }

    /**
     * @return the preCondiciones
     */
    public List<String> getPreCondiciones() {
        return preCondiciones;
    }

    /**
     * @param preCondiciones the preCondiciones to set
     */
    public void setPreCondiciones(List<String> preCondiciones) {
        this.preCondiciones = preCondiciones;
    }

    /**
     * @return the caminosAlternos
     */
    public List<String> getCaminosAlternos() {
        return caminosAlternos;
    }

    /**
     * @param caminosAlternos the caminosAlternos to set
     */
    public void setCaminosAlternos(List<String> caminosAlternos) {
        this.caminosAlternos = caminosAlternos;
    }

    /**
     * @return the representanteDelCliente
     */
    public DesarrolladorEntity getRepresentanteDelCliente() {
        return representanteDelCliente;
    }

    /**
     * @param representanteDelCliente the representanteDelCliente to set
     */
    public void setRepresentanteDelCliente(DesarrolladorEntity representanteDelCliente) {
        this.representanteDelCliente = representanteDelCliente;
    }

    /**
     * @return the responsable
     */
    public DesarrolladorEntity getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(DesarrolladorEntity responsable) {
        this.responsable = responsable;
    }
    
    
    
}

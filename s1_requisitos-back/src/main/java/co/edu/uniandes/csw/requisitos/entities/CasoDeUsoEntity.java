/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Maria Alejandra Escalante
 */
@Entity
public class CasoDeUsoEntity extends BaseEntity implements Serializable {

    //variable encargada de almacenar los servicios del caso de uso
    @ElementCollection
    @CollectionTable(name = "servicios")
    private List<String> servicios = new ArrayList<>();
    //variable encargada de almacenar la documentacion del caso de uso
    private String documentacion;
    //variable encargada de almacenar los resultados de las pruebas del caso de uso
    private Boolean pruebas;
    //variable que indica el nombre del caso
    private String nombre;
    //variable encargada de almacenar las entidades involucradas en el caso
    @ElementCollection
    @CollectionTable(name = "entidades")
    private List<String> entidades = new ArrayList<>();

    //variable encargada de almacenar los caminos de excepcion involucradas en el caso
    @ElementCollection
    @CollectionTable(name = "caminosExcepcion")
    private List<String> caminosExcepcion = new ArrayList<>();

    //variable encargada de almacenar las postcondiciones involucradas en el caso
    @ElementCollection
    @CollectionTable(name = "posCondiciones")
    private List<String> posCondiciones = new ArrayList<>();

    //variable encargada de almacenar las pre condicionesinvolucradas en el caso
    @ElementCollection
    @CollectionTable(name = "preCondiciones")
    private List<String> preCondiciones = new ArrayList<>();

    //variable encargada de almacenar los caminos alternos involucradas en el caso
    @ElementCollection
    @CollectionTable(name = "caminosAlternos")
    private List<String> caminosAlternos = new ArrayList<>();

    //Relacion con Modificacion
    @PodamExclude
    @OneToMany(
            mappedBy = "casoModificaciones",
            cascade = CascadeType.ALL
            
    )
    private List<ModificacionesEntity> modificaciones = new ArrayList<>();

    //Relacion con representante del cliente
    @PodamExclude
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private DesarrolladorEntity representanteDelCliente;

    //relacion con requisito funcional
    @PodamExclude
    @OneToMany(
            mappedBy = "requisitosFuncionalesCaso",
            cascade = CascadeType.ALL
    )
    private List<RequisitosEntity> funcionales = new ArrayList<>();

    @PodamExclude
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private DesarrolladorEntity responsable;

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
     * @return the funcionales
     */
    public List<RequisitosEntity> getFuncionales() {
        return funcionales;
    }

    /**
     * @param funcionales the funcionales to set
     */
    public void setFuncionales(List<RequisitosEntity> funcionales) {
        this.funcionales = funcionales;
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

    /**
     * @return Una cadena de caracteres con la información de la urgencia
     */
    @Override
    public String toString() {
        return "CasoDeUso [nombre=" +nombre + ", pruebas= " + pruebas + "]";
    }
    

}

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
  
    private String servicios;

    private String documentacion;
    private Boolean pruebas;
    
    /*
    //Relacion con Modificacion
    @PodamExclude
    @OneToMany(
        mappedBy = "casoModificaciones", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<ModificacionesEntity> modificaciones=new ArrayList<>();
     */
     
    //Relaciones con Descripcion
      @PodamExclude
   @OneToMany(
        mappedBy = "casoEntidades", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<DescripcionEntity> entidades= new ArrayList<>() ;
      
      
    @PodamExclude
   @OneToMany(
        mappedBy = "casoCaminos", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<DescripcionEntity> caminosExcepcion= new ArrayList<>();
    
    
    @PodamExclude
   @OneToMany(
        mappedBy = "casoPosCondiciones", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<DescripcionEntity> posCondiciones= new ArrayList<>();
   
    @PodamExclude
   @OneToMany(
        mappedBy = "casoPreCondiciones", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<DescripcionEntity> preCondiciones= new ArrayList<>();
    
    
    @PodamExclude
   @OneToMany(
        mappedBy = "casoAlterno", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<DescripcionEntity> preCaminoAlterno= new ArrayList<>();

   
   
   //Relacion con representante del cliente
     @PodamExclude
   @ManyToOne
   private RepresentanteDelClienteEntity representante;
   
   //relacion con requisito funcional
      @PodamExclude
   @OneToMany(
        mappedBy = "casoFuncional", 
        //cascade =CascadeType.ALL ,
        fetch = javax.persistence.FetchType.LAZY
        
    )
    private List<FuncionalEntity> funcional= new ArrayList<>();

    /**
     * @return the servicios
     */
    public String getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(String servicios) {
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
     * @return the modificaciones
     */
   /* public List<ModificacionesEntity> getModificaciones() {
        return modificaciones;
    }
*/
    /**
     * @param modificaciones the modificaciones to set
     *//*
    public void setModificaciones(List<ModificacionesEntity> modificaciones) {
        this.modificaciones = modificaciones;
    }
*/
    /**
     * @return the entidades
     */
    public List<DescripcionEntity> getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(List<DescripcionEntity> entidades) {
        this.entidades = entidades;
    }

    /**
     * @return the caminosExcepcion
     */
    public List<DescripcionEntity> getCaminosExcepcion() {
        return caminosExcepcion;
    }

    /**
     * @param caminosExcepcion the caminosExcepcion to set
     */
    public void setCaminosExcepcion(List<DescripcionEntity> caminosExcepcion) {
        this.caminosExcepcion = caminosExcepcion;
    }

    /**
     * @return the posCondiciones
     */
    public List<DescripcionEntity> getPosCondiciones() {
        return posCondiciones;
    }

    /**
     * @param posCondiciones the posCondiciones to set
     */
    public void setPosCondiciones(List<DescripcionEntity> posCondiciones) {
        this.posCondiciones = posCondiciones;
    }

    /**
     * @return the preCondiciones
     */
    public List<DescripcionEntity> getPreCondiciones() {
        return preCondiciones;
    }

    /**
     * @param preCondiciones the preCondiciones to set
     */
    public void setPreCondiciones(List<DescripcionEntity> preCondiciones) {
        this.preCondiciones = preCondiciones;
    }

    /**
     * @return the preCaminoAlterno
     */
    public List<DescripcionEntity> getPreCaminoAlterno() {
        return preCaminoAlterno;
    }

    /**
     * @param preCaminoAlterno the preCaminoAlterno to set
     */
    public void setPreCaminoAlterno(List<DescripcionEntity> preCaminoAlterno) {
        this.preCaminoAlterno = preCaminoAlterno;
    }

    /**
     * @return the representante
     */
    public RepresentanteDelClienteEntity getRepresentante() {
        return representante;
    }

    /**
     * @param representante the representante to set
     */
    public void setRepresentante(RepresentanteDelClienteEntity representante) {
        this.representante = representante;
    }

    /**
     * @return the funcional
     */
    public List<FuncionalEntity> getFuncional() {
        return funcional;
    }

    /**
     * @param funcional the funcional to set
     */
    public void setFuncional(List<FuncionalEntity> funcional) {
        this.funcional = funcional;
    }
   
  

   

}

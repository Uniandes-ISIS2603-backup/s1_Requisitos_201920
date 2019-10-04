/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Estudiante
 */
@Entity
public class RepresentanteDelClienteEntity extends BaseEntity implements Serializable {
    @PodamExclude
   @OneToMany(
        mappedBy = "representante", 
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<CasoDeUsoEntity> casosDeUso= new ArrayList<>();
     
     
    private String nombre;

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
     * @return the casosDeUso
     */
    public List<CasoDeUsoEntity> getCasosDeUso() {
        return casosDeUso;
    }

    /**
     * @param casosDeUso the casosDeUso to set
     */
    public void setCasosDeUso(List<CasoDeUsoEntity> casosDeUso) {
        this.casosDeUso = casosDeUso;
    }
    
}

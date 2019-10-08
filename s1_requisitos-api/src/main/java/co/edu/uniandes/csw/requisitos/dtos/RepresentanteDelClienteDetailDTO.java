/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rj.gonzalez10
 */
public class RepresentanteDelClienteDetailDTO extends RepresentanteDelClienteDTO {
    private List<CasoDeUsoDTO> casosDeUso;
    
    public RepresentanteDelClienteDetailDTO(RepresentanteDelClienteEntity entidad)
    {
        super(entidad);
         if (entidad != null) {
            if (entidad.getCasosDeUso() != null) {
                casosDeUso = new ArrayList<>();
                for (CasoDeUsoEntity entityBook : entidad.getCasosDeUso()) {
                    casosDeUso.add(new CasoDeUsoDTO(entityBook));
                }
            }
        }
    }
     public RepresentanteDelClienteDetailDTO()
    {
        
    }
      @Override
    public RepresentanteDelClienteEntity toEntity() {
        RepresentanteDelClienteEntity representante = super.toEntity();
        if (casosDeUso != null) {
            List<CasoDeUsoEntity> casosEntity = new ArrayList<>();
            for (CasoDeUsoDTO dtoCaso : casosDeUso) {
                casosEntity.add(dtoCaso.toEntity());
            }
            representante.setCasosDeUso(casosEntity);
        }
        return representante;
    }

    /**
     * @return the casosDeUso
     */
    public List<CasoDeUsoDTO> getCasosDeUso() {
        return casosDeUso;
    }

    /**
     * @param casosDeUso the casosDeUso to set
     */
    public void setCasosDeUso(List<CasoDeUsoDTO> casosDeUso) {
        this.casosDeUso = casosDeUso;
    }
    
}

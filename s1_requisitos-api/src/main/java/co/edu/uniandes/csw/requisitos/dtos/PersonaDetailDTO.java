/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jf.rubio
 */
public class PersonaDetailDTO extends PersonaDTO implements Serializable{

    /**
     * @return the modificaciones
     */
    public List<ModificacionesDTO> getModificaciones() {
        return modificaciones;
    }

    /**
     * @param modificaciones the modificaciones to set
     */
    public void setModificaciones(List<ModificacionesDTO> modificaciones) {
        this.modificaciones = modificaciones;
    }
 
    /**
     * Lista de ModificacionesDTO que puede tener la persona
     * */
    private List<ModificacionesDTO> modificaciones;
    
    /**
     * constructor del detail
     */
    public PersonaDetailDTO()
    {
        
    }
    
    public PersonaDetailDTO(PersonaEntity persona)
    {
        super(persona);
        if(persona!=null)
        {
            modificaciones = new ArrayList<>();
            for(ModificacionesEntity modificacionEntity: persona.getModificaciones())
            {
                modificaciones.add(new ModificacionesDTO(modificacionEntity));
            }
        }
    }
    
     /**
     * Convierte un objeto AuthorDetailDTO a AuthorEntity incluyendo los
     * atributos de AuthorDTO.
     *
     * @return Nueva objeto AuthorEntity.
     *
     */
    @Override
    public PersonaEntity toEntity() {
        PersonaEntity persona = super.toEntity();
        if (getModificaciones() != null) {
            List<ModificacionesEntity> booksEntity = new ArrayList<>();
            for (ModificacionesDTO dtoModificaciones : getModificaciones()) {
                booksEntity.add(dtoModificaciones.toEntity());
            }
            persona.setModificaciones(booksEntity);
        }
        return persona;
    }
}

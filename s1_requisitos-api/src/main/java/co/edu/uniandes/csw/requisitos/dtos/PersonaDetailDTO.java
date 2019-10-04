/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import java.util.List;

/**
 *
 * @author jf.rubio
 */
public class PersonaDetailDTO extends PersonaDTO{
 
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
}

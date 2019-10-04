/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.dtos;

import java.util.List;

/**
 *
 * @author Nicolás Tobo
 */
public class RequisitosDetailDTO extends RequisitosDTO 
{
    /**
     * Lista de ModificacionesDTO que puede tener el requisito
     */
    private List<ModificacionesDTO> modificaciones;
    
    /**
     * Constructor del detailDto
     */
    public RequisitosDetailDTO(){  
    }
}


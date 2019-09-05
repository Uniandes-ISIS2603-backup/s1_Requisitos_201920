/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ModificacionesLogic;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



/**
 *
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class ModificacionesLogicTest {
    private PodamFactory factory= new PodamFactoryImpl();
    @Inject
    private ModificacionesLogic modificacionesLogic;
    
    
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ModificacionesEntity.class)
                .addClass(ModificacionesLogic.class)
                .addClass(ModificacionesPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Test
    public void createModificaciones()throws BusinessLogicException{
        ModificacionesEntity mod=factory.manufacturePojo(ModificacionesEntity.class);
        ModificacionesEntity result= modificacionesLogic.createModificaciones(mod);
        Assert.assertNotNull(result);
    }
    
}

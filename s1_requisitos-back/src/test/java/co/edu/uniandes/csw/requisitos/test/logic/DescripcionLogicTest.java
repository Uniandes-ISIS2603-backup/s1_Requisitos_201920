/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.DescripcionLogic;
import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DescripcionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author Juan Felipe Rubio
 */
@RunWith(Arquillian.class)
public class DescripcionLogicTest {
       
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DescripcionLogic descripcionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createDescripcion() throws BusinessLogicException{

	DescripcionEntity newDescripcion = factory.manufacturePojo(DescripcionEntity.class);
	DescripcionEntity result = descripcionLogic.createDescripcion(newDescripcion);
	Assert.assertNotNull(result);
        
        DescripcionEntity entidad = em.find(DescripcionEntity.class, result.getId());
        Assert.assertEquals(entidad.getDescripcion(), result.getDescripcion());
    }
        @Test (expected = BusinessLogicException.class)
    public void createDescripcionDescripcionNull() throws BusinessLogicException{

            DescripcionEntity newDescripcion = factory.manufacturePojo(DescripcionEntity.class);
            newDescripcion.setDescripcion(null);
            DescripcionEntity result = descripcionLogic.createDescripcion(newDescripcion);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DescripcionEntity.class.getPackage())
                .addPackage(DescripcionLogic.class.getPackage())
                .addPackage(DescripcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
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
 * @author EJuan Rubio
 */
@RunWith(Arquillian.class)
public class DescripcionPersistenceTest {
    
    @Inject
    private DescripcionPersistence dp;
    
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addClass(DescripcionEntity.class)
                .addClass(DescripcionPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     @PersistenceContext
    private EntityManager em;
     
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        DescripcionEntity descripcion = factory.manufacturePojo(DescripcionEntity.class);
        DescripcionEntity result = dp.create(descripcion);
        Assert.assertNotNull(result);
        
     DescripcionEntity entity=em.find(DescripcionEntity.class, result.getId());
      Assert.assertEquals(descripcion.getDescripcion(), entity.getDescripcion());
    }
}

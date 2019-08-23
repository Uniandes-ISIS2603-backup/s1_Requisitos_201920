/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;


import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;

import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
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
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class ModificacionesPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ModificacionesEntity.class)
                .addClass(ModificacionesPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    @Inject 
    ModificacionesPersistence a;
   
    @PersistenceContext
    private EntityManager em;
    @Test
    public void testCreate()
    {
        PodamFactory factory=new PodamFactoryImpl();
        ModificacionesEntity modificacion= factory.manufacturePojo(ModificacionesEntity.class);
        ModificacionesEntity result= a.create(modificacion);
        Assert.assertNotNull(result);
        
         ModificacionesEntity entity=em.find(ModificacionesEntity.class, modificacion.getId());
      
      Assert.assertEquals(modificacion.getDescripcion(),entity.getDescripcion());
      //Assert.assertEquals(modificacion.getFechaModificacion(),entity.getFechaModificacion());
     
    }
}

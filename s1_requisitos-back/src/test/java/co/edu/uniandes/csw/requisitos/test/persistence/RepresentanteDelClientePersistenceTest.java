/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import co.edu.uniandes.csw.requisitos.persistence.RepresentanteDelClientePersistence;
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
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class RepresentanteDelClientePersistenceTest {
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RepresentanteDelClienteEntity.class)
                .addClass(RepresentanteDelClientePersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    RepresentanteDelClientePersistence rcp;
    /*
     Manejador de entidades.
     */
    @PersistenceContext
    private EntityManager em;
    
      @Test
    public void createTest() {
          PodamFactory factory = new PodamFactoryImpl();
        RepresentanteDelClienteEntity representante = factory.manufacturePojo(RepresentanteDelClienteEntity.class);
        
        RepresentanteDelClienteEntity result = rcp.create(representante);
          Assert.assertNotNull(result);
        
        RepresentanteDelClienteEntity esperado = em.find(RepresentanteDelClienteEntity.class, result.getId());
        Assert.assertEquals(esperado.getNombre(), representante.getNombre());
        
    }

    
}

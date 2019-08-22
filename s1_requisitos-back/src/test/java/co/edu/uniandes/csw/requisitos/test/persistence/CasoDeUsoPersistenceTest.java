/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;


import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
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
public class CasoDeUsoPersistenceTest {
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CasoDeUsoEntity.class)
                .addClass(CasoDeUsoPersistence.class)
                .addAsManifestResource("METAINF/persistence.xml","persistence.xml")
                .addAsManifestResource("METAINF/beans.xml","beans.xml");
    }
    @Inject 
    CasoDeUsoPersistence a;
    @PersistenceContext
    private EntityManager em;
    @Test
    public void testCreate()
    {
        PodamFactory factory=new PodamFactoryImpl();
        CasoDeUsoEntity casoDeUso= factory.manufacturePojo(CasoDeUsoEntity.class);
        CasoDeUsoEntity result= a.create(casoDeUso);
        Assert.assertNotNull(result);
        
        CasoDeUsoEntity entity=em.find(CasoDeUsoEntity.class, casoDeUso.getId());
      
      Assert.assertEquals(casoDeUso.getDocumentacion(),entity.getDocumentacion());
    }
}

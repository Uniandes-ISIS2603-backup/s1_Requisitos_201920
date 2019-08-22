/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import co.edu.uniandes.csw.requisitos.persistence.FuncionalPersistence;
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
 * @author Nicole Bahamon
 */
@RunWith(Arquillian.class)
public class FuncionalPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(FuncionalEntity.class)
                .addClass(FuncionalPersistence.class)
                .addAsManifestResource("METAINF/persistence.xml","persistence.xml")
                .addAsManifestResource("METAINF/beans.xml","beans.xml");
    }
    @Inject 
    FuncionalPersistence a;
    @Test
    public void testCreate()
    {
        PodamFactory factory=new PodamFactoryImpl();
        FuncionalEntity funcional= factory.manufacturePojo(FuncionalEntity.class);
        FuncionalEntity result= a.create(funcional);
        Assert.assertNotNull(result);
    }
    
}

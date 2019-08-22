/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.persistence.PersonaPersistence;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class PersonaPersistenceTest {
    @Inject
    PersonaPersistence pp;
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(PersonaEntity.class)
                .addClass(PersonaPersistence.class)
                .addAsManifestResource("METAINF/persistence.xml","persistence.xml")
                .addAsManifestResource("METAINF/beans.xml","beans.xml");
    }
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PersonaEntity persona = factory.manufacturePojo(PersonaEntity.class);
        PersonaEntity result = pp.create(persona);
        Assert.assertNotNull(result);
    }
    
}

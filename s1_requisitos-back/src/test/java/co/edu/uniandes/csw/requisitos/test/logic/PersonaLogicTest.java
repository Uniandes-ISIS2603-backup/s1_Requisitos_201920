/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.PersonaLogic;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.PersonaPersistence;
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
 * @author Juan Rubio
 */
@RunWith(Arquillian.class)
public class PersonaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PersonaLogic personaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createPersona() throws BusinessLogicException{

	PersonaEntity newPersona = factory.manufacturePojo(PersonaEntity.class);
	PersonaEntity result = personaLogic.createPersona(newPersona);
	Assert.assertNotNull(result);
        
        PersonaEntity entidad = em.find(PersonaEntity.class, result.getId());
        Assert.assertEquals(entidad.getNombre(), result.getNombre());
    }
    
    @Test (expected = BusinessLogicException.class)
public void createPersonaNombreNull() throws BusinessLogicException{

	PersonaEntity newPersona = factory.manufacturePojo(PersonaEntity.class);
	newPersona.setNombre(null);
	PersonaEntity result = personaLogic.createPersona(newPersona);
}

    @Deployment
    public static JavaArchive createDeployment() {
        
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PersonaEntity.class.getPackage())
                .addPackage(PersonaLogic.class.getPackage())
                .addPackage(PersonaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
}

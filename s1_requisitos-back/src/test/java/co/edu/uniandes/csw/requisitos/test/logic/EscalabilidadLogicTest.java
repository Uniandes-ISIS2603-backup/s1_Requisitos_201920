/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.EscalabilidadLogic;
import co.edu.uniandes.csw.requisitos.entities.EscalabilidadEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.EscalabilidadPersistence;
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
public class EscalabilidadLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EscalabilidadLogic escalabilidadLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createEscalabilidad() throws BusinessLogicException{

	EscalabilidadEntity newEscalabilidad = factory.manufacturePojo(EscalabilidadEntity.class);
	EscalabilidadEntity result = escalabilidadLogic.createEscalabilidad(newEscalabilidad);
	Assert.assertNotNull(result);
        
        EscalabilidadEntity entidad = em.find(EscalabilidadEntity.class, result.getId());
        Assert.assertEquals(entidad.getTipo(), result.getTipo());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createEscalabilidadTipoNull() throws BusinessLogicException{

            EscalabilidadEntity newEscalabilidad = factory.manufacturePojo(EscalabilidadEntity.class);
            newEscalabilidad.setTipo(null);
            EscalabilidadEntity result = escalabilidadLogic.createEscalabilidad(newEscalabilidad);
    }


    @Deployment
    public static JavaArchive createDeployment() {
        
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EscalabilidadEntity.class.getPackage())
                .addPackage(EscalabilidadLogic.class.getPackage())
                .addPackage(EscalabilidadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
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
 *Clase que prueba la persistencia de los requisitos funcionales
 * @author Nicolas Tobo
 */
@RunWith(Arquillian.class)
public class RequisitoPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
     return ShrinkWrap.create(JavaArchive.class)  
             .addClass(RequisitosEntity.class)
             .addClass(RequisitoPersistence.class)
             .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
             .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    @Inject
    private RequisitoPersistence rp;
    /**
     * Manejador de entidades usado para buscar el resultado creado por el Podam
     */
    @PersistenceContext
    private EntityManager em;
    
    @Test 
    public void createTest()
    {
      PodamFactory factory=new PodamFactoryImpl();
      
      RequisitosEntity newEntity= factory.manufacturePojo(RequisitosEntity.class);
      
      RequisitosEntity req=rp.create(newEntity);
      Assert.assertNotNull(req);
      
      RequisitosEntity entity=em.find(RequisitosEntity.class, req.getId());
      
      Assert.assertEquals(newEntity.getAutor(),entity.getAutor());
      Assert.assertEquals(newEntity.getFuente(),entity.getFuente());
      Assert.assertEquals(newEntity.getEstabilidad(),entity.getEstabilidad());
      Assert.assertEquals(newEntity.getComentariosAdicionales(),entity.getComentariosAdicionales());
      Assert.assertEquals(newEntity.getDescripcion(),entity.getDescripcion());
      Assert.assertEquals(newEntity.getImportancia(),entity.getImportancia());
    }  
}   

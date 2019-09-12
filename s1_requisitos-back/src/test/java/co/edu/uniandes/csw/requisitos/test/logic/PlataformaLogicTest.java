/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.PlataformaLogic;
import co.edu.uniandes.csw.requisitos.entities.PlataformaEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.PlataformaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Nicole Bahamon Martinez
 */
@RunWith(Arquillian.class)
public class PlataformaLogicTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlataformaEntity.class.getPackage())
                .addPackage(PlataformaLogic.class.getPackage())
                .addPackage(PlataformaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
                
    }
   private PodamFactory factory=new PodamFactoryImpl();
    @Inject
    private PlataformaLogic plataformaLogic;
    @Inject
    UserTransaction utx;
  @PersistenceContext
    private EntityManager em;
   private List<PlataformaEntity> data = new ArrayList<>();

   
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

 
    private void clearData() {
        em.createQuery("delete from PlataformaEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PlataformaEntity entidad = factory.manufacturePojo(PlataformaEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    @Test
    public void createPlataformaTest() throws BusinessLogicException{
        PlataformaEntity newEntity = factory.manufacturePojo(PlataformaEntity.class);
        PlataformaEntity result= plataformaLogic.createPlataforma(newEntity);
        Assert.assertNotNull(result);
        
        PlataformaEntity entity=em.find(PlataformaEntity.class,result.getId());
        Assert.assertEquals(entity.getPlataforma(),result.getPlataforma());
    }
    @Test(expected =BusinessLogicException.class)
    public void createPlataformaPlataformaNull() throws BusinessLogicException{
        PlataformaEntity newEntity = factory.manufacturePojo(PlataformaEntity.class);
      newEntity.setPlataforma(null);
        PlataformaEntity result= plataformaLogic.createPlataforma(newEntity);
    }

    @Test
    public void getPlataformasTest() {
        List<PlataformaEntity> lista = plataformaLogic.getPlataformas();
        Assert.assertEquals(data.size(), lista.size());
        for (PlataformaEntity entidad : lista) {
            boolean encontrado = false;
            for (PlataformaEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
   
    @Test
    public void getPlataformaTest() {
        PlataformaEntity nuevaEnt = data.get(0);
        PlataformaEntity entidad = plataformaLogic.getPlataforma(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
            Assert.assertEquals(nuevaEnt.getPlataforma(), entidad.getPlataforma());
        
    }
  
    @Test
    public void updatePlataformaTest() throws BusinessLogicException 
    {
        PlataformaEntity entity = data.get(0);
        PlataformaEntity pojoEntity = factory.manufacturePojo(PlataformaEntity.class);
        pojoEntity.setId(entity.getId());
        plataformaLogic.updatePlataforma(pojoEntity);
        PlataformaEntity resp = em.find(PlataformaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getPlataforma(), resp.getPlataforma());
 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updatePlataformaConPlataformaNullTest() throws BusinessLogicException 
    {
        PlataformaEntity entidad = data.get(0);
        PlataformaEntity  pojoEntity = factory.manufacturePojo(PlataformaEntity.class);
        pojoEntity.setPlataforma(null);
        pojoEntity.setId(entidad.getId());
        plataformaLogic.updatePlataforma(pojoEntity);
    }
     
    @Test
    public void deletePlataformaTest() throws BusinessLogicException 
    {
        PlataformaEntity entity = data.get(0);
        plataformaLogic.deletePlataforma(entity.getId());
        PlataformaEntity deleted = em.find(PlataformaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

} 


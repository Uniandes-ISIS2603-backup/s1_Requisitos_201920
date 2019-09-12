/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.DesempenoLogic;
import co.edu.uniandes.csw.requisitos.entities.DesempenoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.DesempenoPersistence;
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
public class DesempenoLogicTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DesempenoEntity.class.getPackage())
                .addPackage(DesempenoLogic.class.getPackage())
                .addPackage(DesempenoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");

    
}
       private PodamFactory factory=new PodamFactoryImpl();
    @Inject
    private DesempenoLogic desempenoLogic;
    @Inject
    UserTransaction utx;
  @PersistenceContext
    private EntityManager em;
   private List<DesempenoEntity> data = new ArrayList<>();

   
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
        em.createQuery("delete from DesempenoEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DesempenoEntity entidad = factory.manufacturePojo(DesempenoEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    @Test
    public void createDesempenoTest() throws BusinessLogicException{
        DesempenoEntity newEntity = factory.manufacturePojo(DesempenoEntity.class);
        DesempenoEntity result= desempenoLogic.createDesempeno(newEntity);
        Assert.assertNotNull(result);
        
        DesempenoEntity entity=em.find(DesempenoEntity.class,result.getId());
        Assert.assertEquals(entity.getDesempeno(),result.getDesempeno());
    }
    @Test(expected =BusinessLogicException.class)
    public void createDesempenoConDesempenoNull() throws BusinessLogicException{
        DesempenoEntity newEntity = factory.manufacturePojo(DesempenoEntity.class);
      newEntity.setDesempeno(null);
        DesempenoEntity result= desempenoLogic.createDesempeno(newEntity);
    }

    @Test
    public void getDesempenosTest() {
        List<DesempenoEntity> lista = desempenoLogic.getDesempenos();
        Assert.assertEquals(data.size(), lista.size());
        for (DesempenoEntity entidad : lista) {
            boolean encontrado = false;
            for (DesempenoEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
   
    @Test
    public void getDesempenoTest() {
        DesempenoEntity nuevaEnt = data.get(0);
        DesempenoEntity entidad = desempenoLogic.getDesempeno(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
            Assert.assertEquals(nuevaEnt.getDesempeno(), entidad.getDesempeno());
        
    }
  
    @Test
    public void updateDesempenoTest() throws BusinessLogicException 
    {
        DesempenoEntity entity = data.get(0);
        DesempenoEntity pojoEntity = factory.manufacturePojo(DesempenoEntity.class);
        pojoEntity.setId(entity.getId());
        desempenoLogic.updateDesempeno(pojoEntity);
        DesempenoEntity resp = em.find(DesempenoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDesempeno(), resp.getDesempeno());
 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateDesempenoConDesempenoNullTest() throws BusinessLogicException 
    {
        DesempenoEntity entidad = data.get(0);
        DesempenoEntity  pojoEntity = factory.manufacturePojo(DesempenoEntity.class);
        pojoEntity.setDesempeno(null);
        pojoEntity.setId(entidad.getId());
        desempenoLogic.updateDesempeno(pojoEntity);
    }
     
    @Test
    public void deletePlataformaTest() throws BusinessLogicException 
    {
        DesempenoEntity entity = data.get(0);
        desempenoLogic.deleteDesempeno(entity.getId());
        DesempenoEntity deleted = em.find(DesempenoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    }
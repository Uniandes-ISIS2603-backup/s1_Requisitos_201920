/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.AtributoCalidadLogic;
import co.edu.uniandes.csw.requisitos.entities.AtributoCalidadEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.AtributoCalidadPersistence;
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
public class AtributoCalidadLogicTest {
    private PodamFactory factory=new PodamFactoryImpl();
    @Inject
    private AtributoCalidadLogic atributoCalidadLogic;
    @Inject
    UserTransaction utx;
  @PersistenceContext
    private EntityManager em;
    private List<AtributoCalidadEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AtributoCalidadEntity.class.getPackage())
                .addPackage(AtributoCalidadLogic.class.getPackage())
                .addPackage(AtributoCalidadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");

    
}
 
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
        em.createQuery("delete from AtributoCalidadEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AtributoCalidadEntity entidad = factory.manufacturePojo(AtributoCalidadEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    @Test
    public void createAtributoCalidad() throws BusinessLogicException{
        AtributoCalidadEntity newEntity = factory.manufacturePojo(AtributoCalidadEntity.class);
        AtributoCalidadEntity result= atributoCalidadLogic.createAtributoCalidad(newEntity);
        Assert.assertNotNull(result);
        
        AtributoCalidadEntity entity=em.find(AtributoCalidadEntity.class,result.getId());
        Assert.assertEquals(entity.getTipo(),result.getTipo());
    }
    @Test(expected =BusinessLogicException.class)
    public void createAtributoCalidadTipoNull() throws BusinessLogicException{
        AtributoCalidadEntity newEntity = factory.manufacturePojo(AtributoCalidadEntity.class);
      newEntity.setTipo(null);
        AtributoCalidadEntity result= atributoCalidadLogic.createAtributoCalidad(newEntity);
    }

   
   
    /**
     * Prueba para consultar la lista de requisitos.
     */
    @Test
    public void getAtributosCalidadTest() {
        List<AtributoCalidadEntity> lista = atributoCalidadLogic.getAtributosCalidad();
        Assert.assertEquals(data.size(), lista.size());
        for (AtributoCalidadEntity entidad : lista) {
            boolean encontrado = false;
            for (AtributoCalidadEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
   
    @Test
    public void getAtributoCalidadTest() {
        AtributoCalidadEntity nuevaEnt = data.get(0);
        AtributoCalidadEntity entidad = atributoCalidadLogic.getAtributoCalidad(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
            Assert.assertEquals(nuevaEnt.getTipo(), entidad.getTipo());
        
    }
  
    @Test
    public void updateAtributoCalidadTest() throws BusinessLogicException 
    {
        AtributoCalidadEntity entity = data.get(0);
        AtributoCalidadEntity pojoEntity = factory.manufacturePojo(AtributoCalidadEntity.class);
        pojoEntity.setId(entity.getId());
        atributoCalidadLogic.updateAtributoCalidad(pojoEntity);
        AtributoCalidadEntity resp = em.find(AtributoCalidadEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateAtributoCalidadConTipoNullTest() throws BusinessLogicException 
    {
        AtributoCalidadEntity entidad = data.get(0);
        AtributoCalidadEntity  pojoEntity = factory.manufacturePojo(AtributoCalidadEntity.class);
        pojoEntity.setTipo(null);
        pojoEntity.setId(entidad.getId());
        atributoCalidadLogic.updateAtributoCalidad(pojoEntity);
    }
     
    @Test
    public void deleteAtributoCalidadTest() throws BusinessLogicException 
    {
        AtributoCalidadEntity entity = data.get(0);
        atributoCalidadLogic.deleteAtributoCalidad(entity.getId());
        AtributoCalidadEntity deleted = em.find(AtributoCalidadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
    
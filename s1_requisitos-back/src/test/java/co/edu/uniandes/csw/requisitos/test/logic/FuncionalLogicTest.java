/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.FuncionalLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.FuncionalPersistence;
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
public class FuncionalLogicTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FuncionalEntity.class.getPackage())
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(FuncionalLogic.class.getPackage())
                .addPackage(FuncionalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");  
    }
private PodamFactory factory=new PodamFactoryImpl();
    @Inject
    private FuncionalLogic funcionalLogic;
    @Inject
    UserTransaction utx;
  @PersistenceContext
    private EntityManager em;
    private List<FuncionalEntity> data = new ArrayList<>();

   
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
        em.createQuery("delete from FuncionalEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FuncionalEntity entidad = factory.manufacturePojo(FuncionalEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    @Test
    public void createFuncionalTest() throws BusinessLogicException
    {
        FuncionalEntity newEntity = factory.manufacturePojo(FuncionalEntity.class);
        FuncionalEntity result= funcionalLogic.createFuncional(newEntity);
        Assert.assertNotNull(result);
        
        FuncionalEntity entity=em.find(FuncionalEntity.class,result.getId());
        Assert.assertEquals(entity.getNombre(),result.getNombre());
    }
    @Test(expected =BusinessLogicException.class)
    public void createFuncionalNombreNull() throws BusinessLogicException{
        FuncionalEntity newEntity = factory.manufacturePojo(FuncionalEntity.class);
      newEntity.setNombre(null);
        FuncionalEntity result= funcionalLogic.createFuncional(newEntity);
    }
    /**
     * Prueba para asegurarse que no cree un requisito con un nombre repetido.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createFuncionalNombreRepetido()throws BusinessLogicException
    {
        FuncionalEntity nuevaEnt=factory.manufacturePojo(FuncionalEntity.class);
        nuevaEnt.setNombre(data.get(0).getNombre());
        FuncionalEntity resultado=funcionalLogic.createFuncional(nuevaEnt);
    }
    

    @Test
    public void getFuncionalesTest() {
        List<FuncionalEntity> lista = funcionalLogic.getFuncionales();
        Assert.assertEquals(data.size(), lista.size());
        for (FuncionalEntity entidad : lista) {
            boolean encontrado = false;
            for (FuncionalEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
   
    @Test
    public void getFuncionalTest() {
        FuncionalEntity nuevaEnt = data.get(0);
        FuncionalEntity entidad = funcionalLogic.getFuncional(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
        Assert.assertEquals(nuevaEnt.getNombre(), entidad.getNombre());
        
    }
  
    @Test
    public void updateAtributoCalidadTest() throws BusinessLogicException 
    {
        FuncionalEntity entity = data.get(0);
        FuncionalEntity pojoEntity = factory.manufacturePojo(FuncionalEntity.class);
        pojoEntity.setId(entity.getId());
        funcionalLogic.updateFuncional(pojoEntity);
        FuncionalEntity resp = em.find(FuncionalEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateFuncionalConNombreNullTest() throws BusinessLogicException 
    {
        FuncionalEntity entidad = data.get(0);
        FuncionalEntity  pojoEntity = factory.manufacturePojo(FuncionalEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entidad.getId());
        funcionalLogic.updateFuncional(pojoEntity);
    }
     
    @Test
    public void deleteFuncionalTest() throws BusinessLogicException 
    {
        FuncionalEntity entity = data.get(0);
        funcionalLogic.deleteFuncional(entity.getId());
        FuncionalEntity deleted = em.find(FuncionalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
    
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.FuncionalEntity;
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
 * @author Nicole Bahamon
 */
@RunWith(Arquillian.class)
public class FuncionalPersistenceTest {
    
  @Inject
    private FuncionalPersistence fp;
    
      /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<FuncionalEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FuncionalEntity.class.getPackage())
                .addPackage(FuncionalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     @PersistenceContext
    private EntityManager em;
     
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        FuncionalEntity funcional = factory.manufacturePojo(FuncionalEntity.class);
        FuncionalEntity result = fp.create(funcional);
        Assert.assertNotNull(result);
        
     FuncionalEntity entity=em.find(FuncionalEntity.class, result.getId());
      Assert.assertEquals(funcional.getNombre(), entity.getNombre());
    }
       /**
     * Configuración inicial de todas las pruebas.
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from FuncionalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            FuncionalEntity entidad = factory.manufacturePojo(FuncionalEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        FuncionalEntity Entity1 = data.get(0);
        FuncionalEntity encontrado = fp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getNombre(), encontrado.getNombre());
       
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<FuncionalEntity> lista = fp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (FuncionalEntity ent1 : lista) {
            boolean encontrado = false;
            for (FuncionalEntity ent2 : data) {
                if (ent1.getId().equals(ent2.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    /**
     * Prueba el metodo update.
     */
    @Test
    public void updateTest() {
        FuncionalEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FuncionalEntity nuevaEnt = factory.manufacturePojo(FuncionalEntity.class);

        nuevaEnt.setId(entidad.getId());

        fp.update(nuevaEnt);

        FuncionalEntity resp = em.find(FuncionalEntity.class, entidad.getId());
        Assert.assertEquals(resp.getNombre(), nuevaEnt.getNombre());
       
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        FuncionalEntity entidad = data.get(0);
        fp.delete(entidad.getId());
        FuncionalEntity eliminada = em.find(FuncionalEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByTipoTest() 
    {
        FuncionalEntity entidad = data.get(0);
        FuncionalEntity nuevaEnt= fp.findByTipo(entidad.getNombre());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getNombre(), entidad.getNombre());
      
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;
import co.edu.uniandes.csw.requisitos.persistence.DescripcionPersistence;
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
 * @author EJuan Rubio
 */
@RunWith(Arquillian.class)
public class DescripcionPersistenceTest {
    
    @Inject
    private DescripcionPersistence dp;
    
      /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<DescripcionEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DescripcionEntity.class.getPackage())
                .addPackage(DescripcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     @PersistenceContext
    private EntityManager em;
     
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        DescripcionEntity descripcion = factory.manufacturePojo(DescripcionEntity.class);
        DescripcionEntity result = dp.create(descripcion);
        Assert.assertNotNull(result);
        
     DescripcionEntity entity=em.find(DescripcionEntity.class, result.getId());
      Assert.assertEquals(descripcion.getDescripcion(), entity.getDescripcion());
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
        em.createQuery("delete from DescripcionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            DescripcionEntity entidad = factory.manufacturePojo(DescripcionEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        DescripcionEntity Entity1 = data.get(0);
        DescripcionEntity encontrado = dp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getDescripcion(), encontrado.getDescripcion());
       
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<DescripcionEntity> lista = dp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (DescripcionEntity ent1 : lista) {
            boolean encontrado = false;
            for (DescripcionEntity ent2 : data) {
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
        DescripcionEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DescripcionEntity nuevaEnt = factory.manufacturePojo(DescripcionEntity.class);

        nuevaEnt.setId(entidad.getId());

        dp.update(nuevaEnt);

        DescripcionEntity resp = em.find(DescripcionEntity.class, entidad.getId());
        Assert.assertEquals(resp.getDescripcion(), nuevaEnt.getDescripcion());
       
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        DescripcionEntity entidad = data.get(0);
        dp.delete(entidad.getId());
        DescripcionEntity eliminada = em.find(DescripcionEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByTipoTest() 
    {
        DescripcionEntity entidad = data.get(0);
        DescripcionEntity nuevaEnt= dp.findByTipo(entidad.getDescripcion());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getDescripcion(), entidad.getDescripcion());
      
    }
}

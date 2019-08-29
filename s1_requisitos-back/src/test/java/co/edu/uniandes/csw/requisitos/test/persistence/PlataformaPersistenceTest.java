/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.PlataformaEntity;
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
 * @author Nicole Bahamon
 */
@RunWith(Arquillian.class)
public class PlataformaPersistenceTest {
@Inject
    private PlataformaPersistence pp;
    
      /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<PlataformaEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addClass(PlataformaEntity.class)
                .addClass(PlataformaPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     @PersistenceContext
    private EntityManager em;
     
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PlataformaEntity plataforma = factory.manufacturePojo(PlataformaEntity.class);
        PlataformaEntity result = pp.create(plataforma);
        Assert.assertNotNull(result);
        
     PlataformaEntity entity=em.find(PlataformaEntity.class, result.getId());
      Assert.assertEquals(plataforma.getPlataforma(), entity.getPlataforma());
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
        em.createQuery("delete from PlataformaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            PlataformaEntity entidad = factory.manufacturePojo(PlataformaEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        PlataformaEntity Entity1 = data.get(0);
        PlataformaEntity encontrado = pp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getPlataforma(), encontrado.getPlataforma());
       
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<PlataformaEntity> lista = pp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (PlataformaEntity ent1 : lista) {
            boolean encontrado = false;
            for (PlataformaEntity ent2 : data) {
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
        PlataformaEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PlataformaEntity nuevaEnt = factory.manufacturePojo(PlataformaEntity.class);

        nuevaEnt.setId(entidad.getId());

        pp.update(nuevaEnt);

        PlataformaEntity resp = em.find(PlataformaEntity.class, entidad.getId());
        Assert.assertEquals(resp.getPlataforma(), nuevaEnt.getPlataforma());
       
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        PlataformaEntity entidad = data.get(0);
        pp.delete(entidad.getId());
        PlataformaEntity eliminada = em.find(PlataformaEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByTipoTest() 
    {
        PlataformaEntity entidad = data.get(0);
        PlataformaEntity nuevaEnt= pp.findByTipo(entidad.getPlataforma());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getPlataforma(), entidad.getPlataforma());
      
    }
}


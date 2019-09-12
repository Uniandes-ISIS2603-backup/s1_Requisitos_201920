/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;
import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.persistence.IteracionPersistence;
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
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class IteracionPersistenceTest {
 
    @Inject
    IteracionPersistence ip;
    /*
     Manejador de entidades.
     */
    @PersistenceContext
    private EntityManager em;
     /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<IteracionEntity> data = new ArrayList<>();
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(IteracionEntity.class)
                .addClass(IteracionPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from IteracionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            IteracionEntity entidad = factory.manufacturePojo(IteracionEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    
      @Test
    public void createTest() {
          PodamFactory factory = new PodamFactoryImpl();
        IteracionEntity representante = factory.manufacturePojo(IteracionEntity.class);
        
        IteracionEntity result = ip.create(representante);
          Assert.assertNotNull(result);
        
        IteracionEntity esperado = em.find(IteracionEntity.class, result.getId());
        Assert.assertEquals(esperado.getDescripcion(), representante.getDescripcion());
        
    }
     /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        IteracionEntity Entity1 = data.get(0);
        IteracionEntity encontrado = ip.find(Entity1.getId());

        Assert.assertEquals(Entity1.getNombre(), encontrado.getNombre());
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<IteracionEntity> lista = ip.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (IteracionEntity ent1 : lista) {
            boolean encontrado = false;
            for (IteracionEntity ent2 : data) {
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
        IteracionEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        IteracionEntity nuevaEnt = factory.manufacturePojo(IteracionEntity.class);

        nuevaEnt.setId(entidad.getId());

        ip.update(nuevaEnt);

        IteracionEntity resp = em.find(IteracionEntity.class, entidad.getId());
        Assert.assertEquals(resp.getNombre(), nuevaEnt.getNombre());
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        IteracionEntity entidad = data.get(0);
        ip.delete(entidad.getId());
        IteracionEntity eliminada = em.find(IteracionEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
}

    


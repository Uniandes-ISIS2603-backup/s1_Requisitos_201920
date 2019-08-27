/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import co.edu.uniandes.csw.requisitos.persistence.RepresentanteDelClientePersistence;
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
public class RepresentanteDelClientePersistenceTest {

    @Inject
    RepresentanteDelClientePersistence rcp;
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
    private List<RepresentanteDelClienteEntity> data = new ArrayList<>();
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RepresentanteDelClienteEntity.class)
                .addClass(RepresentanteDelClientePersistence.class)
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
        em.createQuery("delete from RepresentanteDelClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RepresentanteDelClienteEntity entidad = factory.manufacturePojo(RepresentanteDelClienteEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    
      @Test
    public void createTest() {
          PodamFactory factory = new PodamFactoryImpl();
        RepresentanteDelClienteEntity representante = factory.manufacturePojo(RepresentanteDelClienteEntity.class);
        
        RepresentanteDelClienteEntity result = rcp.create(representante);
          Assert.assertNotNull(result);
        
        RepresentanteDelClienteEntity esperado = em.find(RepresentanteDelClienteEntity.class, result.getId());
        Assert.assertEquals(esperado.getNombre(), representante.getNombre());
        
    }
     /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        RepresentanteDelClienteEntity Entity1 = data.get(0);
        RepresentanteDelClienteEntity encontrado = rcp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getNombre(), encontrado.getNombre());
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<RepresentanteDelClienteEntity> lista = rcp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (RepresentanteDelClienteEntity ent1 : lista) {
            boolean encontrado = false;
            for (RepresentanteDelClienteEntity ent2 : data) {
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
        RepresentanteDelClienteEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RepresentanteDelClienteEntity nuevaEnt = factory.manufacturePojo(RepresentanteDelClienteEntity.class);

        nuevaEnt.setId(entidad.getId());

        rcp.update(nuevaEnt);

        RepresentanteDelClienteEntity resp = em.find(RepresentanteDelClienteEntity.class, entidad.getId());
        Assert.assertEquals(resp.getNombre(), nuevaEnt.getNombre());
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        RepresentanteDelClienteEntity entidad = data.get(0);
        rcp.delete(entidad.getId());
        RepresentanteDelClienteEntity eliminada = em.find(RepresentanteDelClienteEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
}

    


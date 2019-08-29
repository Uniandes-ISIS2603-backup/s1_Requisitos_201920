/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.DesempenoEntity;
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
 * @author Nicole Bahamon
 */
@RunWith(Arquillian.class)
public class DesempenoPersistenceTest {
 
    @Inject
    private DesempenoPersistence dp;
    
      /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<DesempenoEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addClass(DesempenoEntity.class)
                .addClass(DesempenoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
     @PersistenceContext
    private EntityManager em;
     
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        DesempenoEntity desempeno = factory.manufacturePojo(DesempenoEntity.class);
        DesempenoEntity result = dp.create(desempeno);
        Assert.assertNotNull(result);
        
     DesempenoEntity entity=em.find(DesempenoEntity.class, result.getId());
      Assert.assertEquals(desempeno.getDesempeno(), entity.getDesempeno());
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
        em.createQuery("delete from DesempenoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            DesempenoEntity entidad = factory.manufacturePojo(DesempenoEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        DesempenoEntity Entity1 = data.get(0);
        DesempenoEntity encontrado = dp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getDesempeno(), encontrado.getDesempeno());
       
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<DesempenoEntity> lista = dp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (DesempenoEntity ent1 : lista) {
            boolean encontrado = false;
            for (DesempenoEntity ent2 : data) {
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
        DesempenoEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DesempenoEntity nuevaEnt = factory.manufacturePojo(DesempenoEntity.class);

        nuevaEnt.setId(entidad.getId());

        dp.update(nuevaEnt);

        DesempenoEntity resp = em.find(DesempenoEntity.class, entidad.getId());
        Assert.assertEquals(resp.getDesempeno(), nuevaEnt.getDesempeno());
       
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        DesempenoEntity entidad = data.get(0);
        dp.delete(entidad.getId());
        DesempenoEntity eliminada = em.find(DesempenoEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByTipoTest() 
    {
        DesempenoEntity entidad = data.get(0);
        DesempenoEntity nuevaEnt= dp.findByTipo(entidad.getDesempeno());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getDesempeno(), entidad.getDesempeno());
      
    }
}




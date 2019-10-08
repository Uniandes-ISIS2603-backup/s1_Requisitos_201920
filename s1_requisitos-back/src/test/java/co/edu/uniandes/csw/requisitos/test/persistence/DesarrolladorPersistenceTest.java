/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
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
 * @author Nicolas Tobo Referencias/modificado
 * de:https://github.com/Uniandes-isis2603/backstepbystep/blob/master/backstepbystep-back/src/test/java/co/edu/uniandes/csw/bookstore/test/persistence/RequisitosPersistenceTest.java
 */
@RunWith(Arquillian.class)
public class DesarrolladorPersistenceTest {

    /**
     * Objeto DesarrolladorPersistence a probar
     */
    @Inject
    private DesarrolladorPersistence dp;
    /**
     * Manejador de entidades usado para buscar el resultado creado por el Podam
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
    private List<DesarrolladorEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                
                .addPackage(DesarrolladorEntity.class.getPackage())
                .addPackage(DesarrolladorPersistence.class.getPackage())
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
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DesarrolladorEntity entidad = factory.manufacturePojo(DesarrolladorEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo create
     */
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();

        DesarrolladorEntity nuevaEnt = factory.manufacturePojo(DesarrolladorEntity.class);
        Assert.assertNotNull(nuevaEnt);

        DesarrolladorEntity desarrollador = dp.create(nuevaEnt);
        Assert.assertNotNull(desarrollador);

        DesarrolladorEntity entidad = em.find(DesarrolladorEntity.class, desarrollador.getId());
        Assert.assertEquals(entidad.getTipo(), nuevaEnt.getTipo());
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        DesarrolladorEntity Entity1 = data.get(0);
        DesarrolladorEntity encontrado = dp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getTipo(), encontrado.getTipo());
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<DesarrolladorEntity> lista = dp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (DesarrolladorEntity ent1 : lista) {
            boolean encontrado = false;
            for (DesarrolladorEntity ent2 : data) {
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
        DesarrolladorEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DesarrolladorEntity nuevaEnt = factory.manufacturePojo(DesarrolladorEntity.class);

        nuevaEnt.setId(entidad.getId());

        dp.update(nuevaEnt);

        DesarrolladorEntity resp = em.find(DesarrolladorEntity.class, entidad.getId());
        Assert.assertEquals(resp.getTipo(), nuevaEnt.getTipo());
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        DesarrolladorEntity entidad = data.get(0);
        dp.delete(entidad.getId());
        DesarrolladorEntity eliminada = em.find(DesarrolladorEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
      @Test
    public void findByNameTest() 
    {
        DesarrolladorEntity entidad = data.get(0);
        DesarrolladorEntity nuevaEnt= dp.findByName(entidad.getNombre());
        Assert.assertNotNull(nuevaEnt);
        Assert.assertEquals(entidad.getNombre(),nuevaEnt.getNombre());
        Assert.assertEquals(entidad.getTipo(),nuevaEnt.getTipo());
    }
    
     @Test
    public void findByCedulaTest() 
    {
        DesarrolladorEntity entidad = data.get(0);
        DesarrolladorEntity nuevaEnt= dp.findByCedula(entidad.getCedula());
        Assert.assertNotNull(nuevaEnt);
        Assert.assertEquals(entidad.getNombre(),nuevaEnt.getNombre());
        Assert.assertEquals(entidad.getTipo(),nuevaEnt.getTipo());
    }
}
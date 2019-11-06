/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
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
 * Clase que prueba la persistencia de los requisitos funcionales
 *
 * @author Nicolas Tobo Referencias/modificado
 * de:https://github.com/Uniandes-isis2603/backstepbystep/blob/master/backstepbystep-back/src/test/java/co/edu/uniandes/csw/bookstore/test/persistence/RequisitosPersistenceTest.java
 */
@RunWith(Arquillian.class)
public class RequisitoPersistenceTest {

    /**
     * Objeto requisitoPersistence a probar
     */
    @Inject
    private RequisitoPersistence rp;
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
    private List<RequisitosEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RequisitosEntity.class.getPackage())
                .addPackage(RequisitoPersistence.class.getPackage())
                .addPackage(ModificacionesEntity.class.getPackage())
                .addPackage(DesarrolladorEntity.class.getPackage())
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
    private void clearData() 
    {
        em.createQuery("delete from RequisitosEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RequisitosEntity entidad = factory.manufacturePojo(RequisitosEntity.class);
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

        RequisitosEntity nuevaEnt = factory.manufacturePojo(RequisitosEntity.class);
        Assert.assertNotNull(nuevaEnt);
        RequisitosEntity req = rp.create(nuevaEnt);
        Assert.assertNotNull(req);

        RequisitosEntity entidad = em.find(RequisitosEntity.class, req.getId());

        Assert.assertEquals(nuevaEnt.getAutor(), entidad.getAutor());
        Assert.assertEquals(nuevaEnt.getFuente(), entidad.getFuente());
        Assert.assertEquals(nuevaEnt.getEstabilidad(), entidad.getEstabilidad());
        Assert.assertEquals(nuevaEnt.getComentariosAdicionales(), entidad.getComentariosAdicionales());
        Assert.assertEquals(nuevaEnt.getDescripcion(), entidad.getDescripcion());
        Assert.assertEquals(nuevaEnt.getImportancia(), entidad.getImportancia());
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        RequisitosEntity Entity1 = data.get(0);
        RequisitosEntity encontrado = rp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getAutor(), encontrado.getAutor());
        Assert.assertEquals(Entity1.getFuente(), encontrado.getFuente());
        Assert.assertEquals(Entity1.getEstabilidad(), encontrado.getEstabilidad());
        Assert.assertEquals(Entity1.getComentariosAdicionales(), encontrado.getComentariosAdicionales());
        Assert.assertEquals(Entity1.getDescripcion(), encontrado.getDescripcion());
        Assert.assertEquals(Entity1.getImportancia(), encontrado.getImportancia());
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<RequisitosEntity> lista = rp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (RequisitosEntity ent1 : lista) {
            boolean encontrado = false;
            for (RequisitosEntity ent2 : data) {
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
    public void updateTest() 
    {
        RequisitosEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RequisitosEntity nuevaEnt = factory.manufacturePojo(RequisitosEntity.class);

        nuevaEnt.setId(entidad.getId());

        rp.update(nuevaEnt);

        RequisitosEntity resp = em.find(RequisitosEntity.class, entidad.getId());
        Assert.assertEquals(resp.getAutor(), nuevaEnt.getAutor());
        Assert.assertEquals(resp.getFuente(), nuevaEnt.getFuente());
        Assert.assertEquals(resp.getEstabilidad(), nuevaEnt.getEstabilidad());
        Assert.assertEquals(resp.getComentariosAdicionales(), nuevaEnt.getComentariosAdicionales());
        Assert.assertEquals(resp.getDescripcion(), nuevaEnt.getDescripcion());
        Assert.assertEquals(resp.getImportancia(), nuevaEnt.getImportancia());
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        RequisitosEntity entidad = data.get(0);
        rp.delete(entidad.getId());
        RequisitosEntity eliminada = em.find(RequisitosEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByAuthorTest() {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity nuevaEnt = rp.findByAuthor(entidad.getAutor());
        Assert.assertNotNull(nuevaEnt);

        Assert.assertEquals(nuevaEnt.getAutor(), entidad.getAutor());
        Assert.assertEquals(nuevaEnt.getFuente(), entidad.getFuente());
        Assert.assertEquals(nuevaEnt.getEstabilidad(), entidad.getEstabilidad());
        Assert.assertEquals(nuevaEnt.getComentariosAdicionales(), entidad.getComentariosAdicionales());
        Assert.assertEquals(nuevaEnt.getDescripcion(), entidad.getDescripcion());
        Assert.assertEquals(nuevaEnt.getImportancia(), entidad.getImportancia());
    }

    @Test
    public void findByNameTest() {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity nuevaEnt = rp.findByName(entidad.getNombre());
        Assert.assertNotNull(nuevaEnt);

        Assert.assertEquals(nuevaEnt.getAutor(), entidad.getAutor());
        Assert.assertEquals(nuevaEnt.getFuente(), entidad.getFuente());
        Assert.assertEquals(nuevaEnt.getEstabilidad(), entidad.getEstabilidad());
        Assert.assertEquals(nuevaEnt.getComentariosAdicionales(), entidad.getComentariosAdicionales());
        Assert.assertEquals(nuevaEnt.getDescripcion(), entidad.getDescripcion());
        Assert.assertEquals(nuevaEnt.getImportancia(), entidad.getImportancia());
    }
}

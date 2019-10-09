/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.DescripcionEntity;

import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class CasoDeUsoLogicTest {

    //podamn factory para generar valores random
    private PodamFactory factory = new PodamFactoryImpl();
    //entity manager
    @PersistenceContext
    private EntityManager em;
    //logica
    @Inject
    private CasoDeUsoLogic casoLogic;
    //User transaction
    @Inject
    UserTransaction utx;

    //lista de datos que seran usados para las pruebas
    private List<CasoDeUsoEntity> data = new ArrayList<>();

    /*

    *inicio de las pruebas (conexion a la persistencia ya las calses de logica y persistencia
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(CasoDeUsoLogic.class.getPackage())
                .addPackage(CasoDeUsoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /*
    *borra la lista y la llena de nuevo antes de cada prueba
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

    /*
    * borra los datos de la lista 
     */
    private void clearData() {
        em.createQuery("delete from CasoDeUsoEntity").executeUpdate();
    }

    /*
    ¨*inserta datos generados por el podam fatcory a la lista
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CasoDeUsoEntity entidad = factory.manufacturePojo(CasoDeUsoEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /*
    *test de crear caso de uso 
     */
    @Test
    public void createCasoDeUso() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        /*
        DesarrolladorEntity resp= factory.manufacturePojo(DesarrolladorEntity.class);
        resp.setTipo("Desarrollador");
        Assert.assertNotNull(resp);
        caso.setResponsable(resp);
    */
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
        Assert.assertNotNull(result);
        
        CasoDeUsoEntity entity = em.find(CasoDeUsoEntity.class, result.getId());
        Assert.assertEquals(entity.getDocumentacion(), result.getDocumentacion());
        Assert.assertEquals(entity.getPruebas(), result.getPruebas());
        Assert.assertEquals(entity.getServicios(), result.getServicios());

    }

    /*
    teste de crear un caso con las pruebas en null
     */
    @Test(expected = BusinessLogicException.class)
    public void crearCasoPruebasNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setPruebas(null);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
    }

    /*
    test de crear un caso con los servicios en null
     */
    @Test(expected = BusinessLogicException.class)
    public void crearCasoServiciosNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setServicios(null);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
    }

    /*
    test de crear un caso con la documentacion en null
     */
    @Test(expected = BusinessLogicException.class)
    public void crearCasoDocumentacionNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setDocumentacion(null);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
    }

    /*
    test de buscar con caso dado un id
     */
    @Test
    public void getCasoDeUsoTest() {
        CasoDeUsoEntity caso = data.get(0);
        CasoDeUsoEntity encontrado = casoLogic.getCaso(caso.getId());
        Assert.assertNotNull(encontrado);
        Assert.assertEquals(encontrado.getDocumentacion(), caso.getDocumentacion());
        Assert.assertEquals(encontrado.getPruebas(), caso.getPruebas());
        Assert.assertEquals(encontrado.getServicios(), caso.getServicios());
    }

    /*
    test de dar todos los casos de uso
     */
    @Test
    public void getCasosDeUsoTest() {
        List<CasoDeUsoEntity> lista = casoLogic.getCasos();
        Assert.assertEquals(data.size(), lista.size());

        for (CasoDeUsoEntity ent1 : lista) {
            boolean encontrado = false;
            for (CasoDeUsoEntity ent2 : data) {
                if (ent1.getId().equals(ent2.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    /*
    test de actualizar un caso de uso
     */
    public void updateCasoDeUsoTest() throws BusinessLogicException {
        CasoDeUsoEntity caso = data.get(0);
        CasoDeUsoEntity nueva = factory.manufacturePojo(CasoDeUsoEntity.class);

        nueva.setId(caso.getId());
        casoLogic.updateCasoDeUso(nueva);

        CasoDeUsoEntity encontrado = em.find(CasoDeUsoEntity.class, caso.getId());
        Assert.assertEquals(encontrado.getDocumentacion(), nueva.getDocumentacion());
        Assert.assertEquals(encontrado.getPruebas(), nueva.getPruebas());
        Assert.assertEquals(encontrado.getServicios(), nueva.getServicios());
    }

    /*
    test de actualizar un caso de uso pero con los servicios en null
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCasoDeUsoConServiciosNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = data.get(0);
        caso.setServicios(null);
        casoLogic.updateCasoDeUso(caso);
    }

    /*
    test de actualizar un caso de uso con documentacion en null
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCasoDeUsoDocumentacionNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = data.get(0);
        caso.setDocumentacion(null);
        casoLogic.updateCasoDeUso(caso);
    }

    /*
    test de actualizar caso de uso con pruebas en null
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCasoDeUsoPruebasNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = data.get(0);
        caso.setPruebas(null);
        casoLogic.updateCasoDeUso(caso);
    }

    /*
    test de borrar caso de uso
     */
    @Test
    public void deleteCasoDeUso() {
        CasoDeUsoEntity caso = data.get(0);
        casoLogic.deleteCaso(caso.getId());

        CasoDeUsoEntity buscado = em.find(CasoDeUsoEntity.class, caso.getId());
        Assert.assertNull(buscado);
    }

}

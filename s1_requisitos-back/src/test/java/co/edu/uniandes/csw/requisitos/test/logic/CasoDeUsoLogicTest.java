/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.ejb.ModificacionesLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import com.sun.jdo.api.persistence.enhancer.util.Assertion;
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

    private PodamFactory factory = new PodamFactoryImpl();
    @PersistenceContext
    private EntityManager em;
    @Inject
    private CasoDeUsoLogic casoLogic;
    @Inject
    UserTransaction utx;
    private List<CasoDeUsoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(CasoDeUsoLogic.class.getPackage())
                .addPackage(CasoDeUsoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

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
        em.createQuery("delete from CasoDeUsoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CasoDeUsoEntity entidad = factory.manufacturePojo(CasoDeUsoEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    @Test
    public void createCasoDeUso() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
        Assert.assertNotNull(result);

        CasoDeUsoEntity entity = em.find(CasoDeUsoEntity.class, result.getId());
        Assert.assertEquals(entity.getDocumentacion(), result.getDocumentacion());
        Assert.assertEquals(entity.getPruebas(), result.getPruebas());
        Assert.assertEquals(entity.getServicios(), result.getServicios());

    }

    @Test(expected = BusinessLogicException.class)
    public void crearCasoPruebasNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setPruebas(null);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearCasoServiciosNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setServicios(null);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearCasoDocumentacionNull() throws BusinessLogicException {
        CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setDocumentacion(null);
        CasoDeUsoEntity result = casoLogic.crearCasoDeUso(caso);
    }

    @Test
    public void getCasoDeUsoTest() {
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

    @Test(expected = BusinessLogicException.class)
    public void updateCasoDeUsoConServiciosNull() throws BusinessLogicException {
        CasoDeUsoEntity caso= data.get(0);
        caso.setServicios(null);
        casoLogic.updateCasoDeUso(caso);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateCasoDeUsoDocumentacionNull() throws BusinessLogicException {
        CasoDeUsoEntity caso= data.get(0);
        caso.setDocumentacion(null);
        casoLogic.updateCasoDeUso(caso);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateCasoDeUsoPruebasNull() throws BusinessLogicException {
        CasoDeUsoEntity caso= data.get(0);
        caso.setPruebas(null);
        casoLogic.updateCasoDeUso(caso);
    }
    
    @Test
    public void deleteCasoDeUso()  {
        CasoDeUsoEntity caso= data.get(0);
        casoLogic.deleteRequisito(caso.getId());
        
        CasoDeUsoEntity buscado= em.find(CasoDeUsoEntity.class, caso.getId());
        Assert.assertNull(buscado);
    }

}

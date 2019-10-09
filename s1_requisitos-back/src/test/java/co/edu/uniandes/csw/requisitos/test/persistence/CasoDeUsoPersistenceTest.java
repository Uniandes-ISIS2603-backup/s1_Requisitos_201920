/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
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
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class CasoDeUsoPersistenceTest {

    /**
     * Objeto requisitoPersistence a probar
     */
    @Inject
    private CasoDeUsoPersistence a;
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
    private List<CasoDeUsoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RequisitosEntity.class.getPackage())
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(CasoDeUsoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /*
    antes de cada prueba reinicia la lista de los casos de prueba
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
    borra los datos de la lista
     */
    private void clearData() {
        em.createQuery("delete from CasoDeUsoEntity").executeUpdate();
    }

    /*
    inserta datos en la lista dados por el podam
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
    test de crear casos de uso
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        CasoDeUsoEntity modificacion = factory.manufacturePojo(CasoDeUsoEntity.class);
        CasoDeUsoEntity result = a.create(modificacion);
        Assert.assertNotNull(result);

        CasoDeUsoEntity entity = em.find(CasoDeUsoEntity.class, modificacion.getId());

        Assert.assertEquals(modificacion.getDocumentacion(), entity.getDocumentacion());
        Assert.assertEquals(modificacion.getPruebas(), entity.getPruebas());

        Assert.assertEquals(modificacion.getServicios(), entity.getServicios());
        Assert.assertEquals(modificacion.getEntidades(), entity.getEntidades());
        Assert.assertEquals(modificacion.getCaminosExcepcion(), entity.getCaminosExcepcion());
        Assert.assertEquals(modificacion.getFuncional(), entity.getFuncional());
        Assert.assertEquals(modificacion.getPosCondiciones(), entity.getPosCondiciones());
        Assert.assertEquals(modificacion.getPreCondiciones(), entity.getPreCondiciones());
        Assert.assertEquals(modificacion.getPreCaminoAlterno(), entity.getPreCaminoAlterno());
        Assert.assertEquals(modificacion.getResponsable(), entity.getResponsable());
        Assert.assertEquals(modificacion.getRepresentante(), entity.getRepresentante());

    }

    /*
    test de retornar un caso de uso dado un id
     */
    @Test
    public void findTest() {
        CasoDeUsoEntity entity = data.get(0);
        CasoDeUsoEntity encontrado = a.find(entity.getId());

        Assert.assertEquals(encontrado.getDocumentacion(), entity.getDocumentacion());
        Assert.assertEquals(encontrado.getPruebas(), entity.getPruebas());

        Assert.assertEquals(encontrado.getServicios(), entity.getServicios());
        
        Assert.assertEquals(encontrado.getEntidades(), entity.getEntidades());
        Assert.assertEquals(encontrado.getCaminosExcepcion(), entity.getCaminosExcepcion());
        Assert.assertEquals(encontrado.getFuncional(), entity.getFuncional());
        Assert.assertEquals(encontrado.getPosCondiciones(), entity.getPosCondiciones());
        Assert.assertEquals(encontrado.getPreCondiciones(), entity.getPreCondiciones());
        Assert.assertEquals(encontrado.getPreCaminoAlterno(), entity.getPreCaminoAlterno());
        Assert.assertEquals(encontrado.getResponsable(), entity.getResponsable());
        Assert.assertEquals(encontrado.getRepresentante(), entity.getRepresentante());

    }

    /*
    test de retornar todos los casos de uso
     */
    @Test
    public void findAllTest() {
        List<CasoDeUsoEntity> lista = a.findAll();
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
    test de actualizar el caso de uso
     */
    @Test
    public void updateTest() {
        CasoDeUsoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CasoDeUsoEntity encontrado = factory.manufacturePojo(CasoDeUsoEntity.class);

        encontrado.setId(entity.getId());

        a.update(encontrado);

        CasoDeUsoEntity resp = em.find(CasoDeUsoEntity.class, entity.getId());
        Assert.assertEquals(resp.getDocumentacion(), encontrado.getDocumentacion());
        Assert.assertEquals(resp.getPruebas(), encontrado.getPruebas());

        Assert.assertEquals(resp.getServicios(), encontrado.getServicios());

    }

    /*
    test de borrar el caso de uso
     */
    @Test
    public void deleteTest() {
        CasoDeUsoEntity entidad = data.get(0);
        a.delete(entidad.getId());
        CasoDeUsoEntity eliminada = em.find(CasoDeUsoEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

}

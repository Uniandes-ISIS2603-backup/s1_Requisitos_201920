/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ModificacionesCasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;


/**
 *
 * @author jf.rubio
 */
@RunWith(Arquillian.class)
public class ModificacionesCasoDeUsoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ModificacionesCasoDeUsoLogic modificacionCasoDeUsoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CasoDeUsoEntity> data = new ArrayList<CasoDeUsoEntity>();
    
    private List<ModificacionesEntity> modificacionesData = new ArrayList();
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(ModificacionesCasoDeUsoLogic.class.getPackage())
                .addPackage(CasoDeUsoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from ModificacionesEntity").executeUpdate();
        em.createQuery("delete from CasoDeUsoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ModificacionesEntity modificaciones = factory.manufacturePojo(ModificacionesEntity.class);

            em.persist(modificaciones);
            modificacionesData.add(modificaciones);
        }
        for (int i = 0; i < 3; i++) {
            CasoDeUsoEntity entity = factory.manufacturePojo(CasoDeUsoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                modificacionesData.get(i).setCasoModificaciones(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Modificacioness existente a un CasoDeUso.
     */
    @Test
    public void addCasoDeUsoTest() {
        CasoDeUsoEntity entity = data.get(0);
        ModificacionesEntity modificacionEntity = modificacionesData.get(1);
        CasoDeUsoEntity response = modificacionCasoDeUsoLogic.addCasoDeUso(entity.getId(), modificacionEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un CasoDeUso.
     */
    @Test
    public void getCasoDeUsoTest() {
        ModificacionesEntity entity = modificacionesData.get(0);
        CasoDeUsoEntity resultEntity = modificacionCasoDeUsoLogic.getCasoDeUso(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getCasoModificaciones().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Modificacioness asociadas a una instancia
     * de CasoDeUso.
     */
    @Test
    public void replaceCasoDeUsoTest() {
        CasoDeUsoEntity entity = data.get(0);
        modificacionCasoDeUsoLogic.replaceCasoDeUso(modificacionesData.get(1).getId(), entity.getId());
        entity = modificacionCasoDeUsoLogic.getCasoDeUso(modificacionesData.get(1).getId());
        Assert.assertTrue(entity.getModificaciones().contains(modificacionesData.get(1)));
    }

    /**
     * Prueba para desasociar un Modificaciones existente de un CasoDeUso existente.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void removeModificacionesTest() throws BusinessLogicException {
        modificacionCasoDeUsoLogic.removeCasoDeUso(modificacionesData.get(0).getId());
        Assert.assertNull(modificacionCasoDeUsoLogic.getCasoDeUso(modificacionesData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un Modificaciones existente de un CasoDeUso existente.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeModificacionesSinCasoDeUsoTest() throws BusinessLogicException {
        modificacionCasoDeUsoLogic.removeCasoDeUso(modificacionesData.get(1).getId());
    }
}

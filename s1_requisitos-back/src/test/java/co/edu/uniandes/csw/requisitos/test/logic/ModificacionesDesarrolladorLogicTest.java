/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ModificacionesDesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
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
 * @author Nicole Bahamon Martínez
 */
@RunWith(Arquillian.class)
public class ModificacionesDesarrolladorLogicTest {
    
     private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ModificacionesDesarrolladorLogic modificacionesDesarrolladorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<DesarrolladorEntity> data = new ArrayList<>();

    private final List<ModificacionesEntity> modificacionesData = new ArrayList();
    
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DesarrolladorEntity.class.getPackage())
                .addPackage(ModificacionesDesarrolladorLogic.class.getPackage())
                .addPackage(DesarrolladorPersistence.class.getPackage())
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
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
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
            DesarrolladorEntity entity = factory.manufacturePojo(DesarrolladorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                modificacionesData.get(i).setDesarrolladorModificaciones(entity);
            }
        }
    }
     /**
     * Prueba para asociar una modificacion existente a un desarrollador.
     */
    @Test
    public void addDesarrolladorTest() {
        DesarrolladorEntity entity = data.get(0);
        ModificacionesEntity modEntity = modificacionesData.get(1);
        DesarrolladorEntity response = modificacionesDesarrolladorLogic.addAuthor(entity.getId(), modEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un desarrollador.
     */
    @Test
    public void getDesarrolladorTest() {
        ModificacionesEntity entity = modificacionesData.get(0);
        DesarrolladorEntity resultEntity = modificacionesDesarrolladorLogic.getDesarrollador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getDesarrolladorModificaciones().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de modificaciones asociadas a una instancia
     * de desarrollador.
     */
    @Test
    public void replaceDesarrolladorTest() {
        DesarrolladorEntity entity = data.get(0);
        modificacionesDesarrolladorLogic.replaceAuthor(modificacionesData.get(1).getId(), entity.getId());
        entity = modificacionesDesarrolladorLogic.getDesarrollador(modificacionesData.get(1).getId());
        Assert.assertTrue(entity.getModificaciones().contains(modificacionesData.get(1)));
    }
        

    /**
     * Prueba para desasociar una modificaion existente de un desarrollador existente.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void removeModificacionTest() throws BusinessLogicException {
        modificacionesDesarrolladorLogic.removeDesarrollador(modificacionesData.get(0).getId());
        Assert.assertNull(modificacionesDesarrolladorLogic.getDesarrollador(modificacionesData.get(0).getId()));
    }

    /**
     * Prueba para desasociar una modificacion existente de un desarrollador existente.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeModificacionSinDesarrolladorTest() throws BusinessLogicException {
        modificacionesDesarrolladorLogic.removeDesarrollador(modificacionesData.get(1).getId());
    }
}


    


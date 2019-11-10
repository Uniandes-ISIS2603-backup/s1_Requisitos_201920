/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.RequisitosDesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
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
public class RequisitosDesarrolladorLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RequisitosDesarrolladorLogic requisitosDesarrolladorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DesarrolladorEntity> data = new ArrayList<DesarrolladorEntity>();

    private List<RequisitosEntity> requisitosData = new ArrayList();
    
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DesarrolladorEntity.class.getPackage())
                .addPackage(RequisitosDesarrolladorLogic.class.getPackage())
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
    private void clearData() 
    {
        em.createQuery("delete from RequisitosEntity").executeUpdate();
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            RequisitosEntity requisitos = factory.manufacturePojo(RequisitosEntity.class);
         
            em.persist(requisitos);
            requisitosData.add(requisitos);
        }
        for (int i = 0; i < 3; i++) {
            DesarrolladorEntity entity = factory.manufacturePojo(DesarrolladorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                requisitosData.get(i).setDesarrollador(entity);
            }
        }
    }
     /**
     * Prueba para asociar un requisito existente a un desarrollador.
      */
     @Test
     public void addDesarrolladorTest() {
         DesarrolladorEntity entity = data.get(0);
         RequisitosEntity reqEntity = requisitosData.get(1);
         DesarrolladorEntity response = requisitosDesarrolladorLogic.addAuthor( reqEntity.getId(),entity.getId());

         Assert.assertNotNull(response);
         Assert.assertEquals(entity.getId(), response.getId());
     }
  
    /**
     * Prueba para consultar un desarrollador.
     */
    @Test
    public void getDesarrolladorTest() {
        RequisitosEntity entity = requisitosData.get(0);
        DesarrolladorEntity resultEntity = requisitosDesarrolladorLogic.getDesarrollador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getDesarrollador().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de requisitos asociadas a una instancia
     * de desarrollador.
     */
    @Test
    public void replaceDesarrolladorTest() {
        DesarrolladorEntity entity = data.get(0);
        requisitosDesarrolladorLogic.replaceAuthor(requisitosData.get(1).getId(), entity.getId());
        entity = requisitosDesarrolladorLogic.getDesarrollador(requisitosData.get(1).getId());
        Assert.assertTrue(entity.getRequisitos().contains(requisitosData.get(1)));
    }

    /**
     * Prueba para desasociar un requisito existente de un desarrollador existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeRequisitoTest() throws BusinessLogicException {
        requisitosDesarrolladorLogic.removeDesarrollador(requisitosData.get(0).getId());
        Assert.assertNull(requisitosDesarrolladorLogic.getDesarrollador(requisitosData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un requisito existente de un desarrollador existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeRequisitoSinDesarrolladorTest() throws BusinessLogicException {
        requisitosDesarrolladorLogic.removeDesarrollador(requisitosData.get(1).getId());
    }
}


    


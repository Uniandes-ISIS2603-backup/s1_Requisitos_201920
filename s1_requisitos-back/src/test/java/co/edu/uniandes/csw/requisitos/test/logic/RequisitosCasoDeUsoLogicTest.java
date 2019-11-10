/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.RequisitosCasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.RequisitoPersistence;
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
 * @author Nicolas Andres Tobo Urrutia
 */
@RunWith(Arquillian.class)
public class RequisitosCasoDeUsoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RequisitosCasoDeUsoLogic requisitoCasoDeUsoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CasoDeUsoEntity> data = new ArrayList<CasoDeUsoEntity>();

    private List<RequisitosEntity> requisitosData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(RequisitosCasoDeUsoLogic.class.getPackage())
                .addPackage(CasoDeUsoPersistence.class.getPackage())
                .addPackage(RequisitoPersistence.class.getPackage())
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
        } 
        catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } 
            catch (Exception e1) {
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
        em.createQuery("delete from CasoDeUsoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) 
        {
            RequisitosEntity requisito = factory.manufacturePojo(RequisitosEntity.class);
            
            em.persist(requisito);
            requisitosData.add(requisito);
        }
        for (int i = 0; i < 3; i++) 
        {
            CasoDeUsoEntity entity = factory.manufacturePojo(CasoDeUsoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) 
            {
                requisitosData.get(i).setRequisitosFuncionalesCaso(entity);
            }
        }
    }

    /**
     * Prueba para asociar un requisito existente a un Caso de uso.
     */
    @Test
    public void addAuthorTest() 
    {
        CasoDeUsoEntity entity = data.get(0);
        RequisitosEntity requisitoEntity = requisitosData.get(1);
        CasoDeUsoEntity response = requisitoCasoDeUsoLogic.addCasoDeUso(requisitoEntity.getId(),entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Caso de uso.
     */
    @Test
    public void getCasoDeUsoTest() 
    {
        RequisitosEntity entity = requisitosData.get(0);
        CasoDeUsoEntity resultEntity = requisitoCasoDeUsoLogic.getCasoDeUso(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getRequisitosFuncionalesCaso().getId(), resultEntity.getId());
    }

    /**
     * Prueba para reemplazar las instancias de requisitos asociadas a una instancia
     * de CasoDeUso.
     */
    @Test
    public void replaceCasoDeUsotest() 
    { 
        CasoDeUsoEntity entity = data.get(1);
        requisitoCasoDeUsoLogic.replaceCasoDeUso(requisitosData.get(0).getId(), entity.getId());
        entity = requisitoCasoDeUsoLogic.getCasoDeUso(requisitosData.get(0).getId());
        Assert.assertTrue(entity.getFuncionales().contains(requisitosData.get(0)));
    }

    /**
     * Prueba para desasociar un requisito existente de un caso de uso existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeRequisitoTest() throws BusinessLogicException 
    {
        requisitoCasoDeUsoLogic.removeCasoDeUso(requisitosData.get(0).getId());
        Assert.assertNull(requisitoCasoDeUsoLogic.getCasoDeUso(requisitosData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un requisito existente de un casoDeUso inexistente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeRequisitoSinCasoDeUsoTest() throws BusinessLogicException 
    {
        requisitoCasoDeUsoLogic.removeCasoDeUso(requisitosData.get(1).getId());
    }
    
}

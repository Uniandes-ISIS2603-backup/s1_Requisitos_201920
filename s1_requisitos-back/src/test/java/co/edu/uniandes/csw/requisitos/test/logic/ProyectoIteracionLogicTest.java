/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;
import co.edu.uniandes.csw.requisitos.ejb.ProyectoIteracionLogic;
import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.IteracionPersistence;
import co.edu.uniandes.csw.requisitos.persistence.ProyectoPersistence;
import java.util.ArrayList;
import java.util.List;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;

import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class ProyectoIteracionLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProyectoIteracionLogic requisitoProyectoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ProyectoEntity> data = new ArrayList<ProyectoEntity>();

    private List<IteracionEntity> requisitosData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProyectoEntity.class.getPackage())
                .addPackage(ProyectoIteracionLogic.class.getPackage())
                .addPackage(ProyectoPersistence.class.getPackage())
                .addPackage(IteracionPersistence.class.getPackage())
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
        em.createQuery("delete from IteracionEntity").executeUpdate();
        em.createQuery("delete from ProyectoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) 
        {
            IteracionEntity requisito = factory.manufacturePojo(IteracionEntity.class);
            
            em.persist(requisito);
            requisitosData.add(requisito);
        }
        for (int i = 0; i < 3; i++) 
        {
            ProyectoEntity entity = factory.manufacturePojo(ProyectoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) 
            {
                requisitosData.get(i).setIteracionProyecto(entity);
            }
        }
    }

    /**
     * Prueba para asociar una iteracion a un proyecto.
     */
    @Test
    public void addAuthorTest() 
    {
        ProyectoEntity entity = data.get(0);
        IteracionEntity requisitoEntity = requisitosData.get(1);
        ProyectoEntity response = requisitoProyectoLogic.addProyecto(requisitoEntity.getId(),entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un proyecto.
     */
    @Test
    public void getProyectoTest() 
    {
        IteracionEntity entity = requisitosData.get(0);
        ProyectoEntity resultEntity = requisitoProyectoLogic.getProyecto(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getIteracionProyecto().getId(), resultEntity.getId());
    }

    /**
     * Prueba para reemplazar las instancias de iteraciones asociadas a una instancia
     * de proyecto.
     */
    @Test
    public void replaceProyectotest() 
    { 
        ProyectoEntity entity = data.get(1);
        requisitoProyectoLogic.replaceProyecto(requisitosData.get(0).getId(), entity.getId());
        entity = requisitoProyectoLogic.getProyecto(requisitosData.get(0).getId());
        Assert.assertTrue(entity.getIteraciones().contains(requisitosData.get(0)));
    }

    /**
     * Prueba para desasociar una iteracion existente de un proyecto existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeIteracionTest() throws BusinessLogicException 
    {
        requisitoProyectoLogic.removeProyecto(requisitosData.get(0).getId());
        Assert.assertNull(requisitoProyectoLogic.getProyecto(requisitosData.get(0).getId()));
    }

    /**
     * Prueba para desasociar una iteracion existente de un proyecto inexistente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeIteracionSinProyectoTest() throws BusinessLogicException 
    {
        requisitoProyectoLogic.removeProyecto(requisitosData.get(1).getId());
    }
    
}

    

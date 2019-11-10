/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ModificacionesRequisitoLogic;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
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
 *
 * @author Juan Martinez
 */
@RunWith(Arquillian.class)
public class ModificacionesRequisitoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ModificacionesRequisitoLogic modificacionesRequisitoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RequisitosEntity> data = new ArrayList<RequisitosEntity>();

    private List<ModificacionesEntity> modificacionesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RequisitosEntity.class.getPackage())
                .addPackage(ModificacionesRequisitoLogic.class.getPackage())
                .addPackage(RequisitoPersistence.class.getPackage())
                .addPackage(ModificacionesPersistence.class.getPackage())
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
        em.createQuery("delete from ModificacionesEntity").executeUpdate();
        em.createQuery("delete from RequisitosEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) 
        {
            ModificacionesEntity modificacion = factory.manufacturePojo(ModificacionesEntity.class);
            
            em.persist(modificacion);
            modificacionesData.add(modificacion);
        }
        for (int i = 0; i < 3; i++) 
        {
            RequisitosEntity entity = factory.manufacturePojo(RequisitosEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) 
            {
                modificacionesData.get(i).setModificacionesRequisito(entity);
            }
        }
    }

    /**
     * Prueba para asociar una modificacion existente a un requisito
     */
    @Test
    public void addAuthorTest() 
    {
        RequisitosEntity entity = data.get(0);
        ModificacionesEntity modEntity = modificacionesData.get(1);
        RequisitosEntity response = modificacionesRequisitoLogic.addRequisito(modEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un requisito
     */
    @Test
    public void getRequisitoTest() 
    {
        ModificacionesEntity entity = modificacionesData.get(0);
        RequisitosEntity resultEntity = modificacionesRequisitoLogic.getRequisito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getModificacionesRequisito().getId(), resultEntity.getId());
    }

    /**
     * Prueba para reemplazar las instancias de modificaciones asociadas a una instancia
     * de CasoDeUso.
     */
    @Test
    public void replaceRequisitotest() 
    { 
        RequisitosEntity entity = data.get(1);
        modificacionesRequisitoLogic.replaceRequisito(modificacionesData.get(0).getId(), entity.getId()); 
        entity = modificacionesRequisitoLogic.getRequisito(modificacionesData.get(0).getId()); //(requisitosData.get(0).getId());
        Assert.assertTrue(entity.getModificaciones().contains(modificacionesData.get(0)));
    }

    /**
     * Prueba para desasociar un requisito existente de un caso de uso existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeModificacionTest() throws BusinessLogicException 
    {
        modificacionesRequisitoLogic.removeRequisito(modificacionesData.get(0).getId());
        Assert.assertNull(modificacionesRequisitoLogic.getRequisito(modificacionesData.get(0).getId()));
    }

    /**
     * Prueba para desasociar una modificacion existente de un requisito inexistente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeModificacionRequisitoTest() throws BusinessLogicException 
    {
        try{
          modificacionesRequisitoLogic.removeRequisito(modificacionesData.get(1).getId());  
        }catch(Exception e){
            throw new BusinessLogicException("No se puede desasociar una modificacion existente a un requisito inexistente");
        }
        
    }
}

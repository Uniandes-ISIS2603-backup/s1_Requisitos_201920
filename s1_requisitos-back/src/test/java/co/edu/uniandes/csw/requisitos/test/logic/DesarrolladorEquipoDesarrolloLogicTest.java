/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorEquipoDesarrolloLogic;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.EquipoDesarrolloPersistence;
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
 * @author jf.rubio
 */
@RunWith(Arquillian.class)
public class DesarrolladorEquipoDesarrolloLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DesarrolladorEquipoDesarrolloLogic desarrolladorEquipoDesarrolloLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<EquipoDesarrolloEntity> data = new ArrayList<EquipoDesarrolloEntity>();
    
    private List<DesarrolladorEntity> desarrolladoresData = new ArrayList();
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoDesarrolloEntity.class.getPackage())
                .addPackage(DesarrolladorEquipoDesarrolloLogic.class.getPackage())
                .addPackage(EquipoDesarrolloPersistence.class.getPackage())
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
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
        em.createQuery("delete from EquipoDesarrolloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            DesarrolladorEntity desarrolladores = factory.manufacturePojo(DesarrolladorEntity.class);

            em.persist(desarrolladores);
            desarrolladoresData.add(desarrolladores);
        }
        for (int i = 0; i < 3; i++) {
            EquipoDesarrolloEntity entity = factory.manufacturePojo(EquipoDesarrolloEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                desarrolladoresData.get(i).setEquipoDesarrollo(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Desarrolladors existente a un EquipoDesarrollo.
     */
    @Test
    public void addEquipoDesarrolloTest() {
        EquipoDesarrolloEntity entity = data.get(0);
        DesarrolladorEntity desarrolladorEntity = desarrolladoresData.get(1);
        EquipoDesarrolloEntity response = desarrolladorEquipoDesarrolloLogic.addEquipoDesarrollo(entity.getId(), desarrolladorEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un EquipoDesarrollo.
     */
    @Test
    public void getEquipoDesarrolloTest() {
        DesarrolladorEntity entity = desarrolladoresData.get(0);
        EquipoDesarrolloEntity resultEntity = desarrolladorEquipoDesarrolloLogic.getEquipoDesarrollo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getEquipoDesarrollo().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Desarrolladors asociadas a una instancia
     * de EquipoDesarrollo.
     */
    @Test
    public void replaceEquipoDesarrolloTest() {
        EquipoDesarrolloEntity entity = data.get(0);
        desarrolladorEquipoDesarrolloLogic.replaceEquipoDesarrollo(desarrolladoresData.get(1).getId(), entity.getId());
        entity = desarrolladorEquipoDesarrolloLogic.getEquipoDesarrollo(desarrolladoresData.get(1).getId());
        Assert.assertTrue(entity.getIntegrantes().contains(desarrolladoresData.get(1)));
    }

    /**
     * Prueba para desasociar un Desarrollador existente de un EquipoDesarrollo existente.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void removeDesarrolladorTest() throws BusinessLogicException {
        desarrolladorEquipoDesarrolloLogic.removeEquipoDesarrollo(desarrolladoresData.get(0).getId());
        Assert.assertNull(desarrolladorEquipoDesarrolloLogic.getEquipoDesarrollo(desarrolladoresData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un Desarrollador existente de un EquipoDesarrollo existente.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeDesarrolladorSinEquipoDesarrolloTest() throws BusinessLogicException {
        desarrolladorEquipoDesarrolloLogic.removeEquipoDesarrollo(desarrolladoresData.get(1).getId());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.DesarrolladorLogic;
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
 * Test de la clase RequisitoLogic
 * @author Nicolás Tobo
 */
@RunWith(Arquillian.class)
public class DesarrolladorLogicTest 
{
    /**
     * Manejador de entidades usado para buscar el resultado creado por el Podam
     */
    @PersistenceContext
    private EntityManager em; 
    /**
     * Objeto PodamFactory que genera datos aleatorios para las pruebas
     */
   private PodamFactory factory = new PodamFactoryImpl();
    /**
    * Objeto RequisitoLogic a probar
    */
   @Inject
   private DesarrolladorLogic dl;
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<DesarrolladorEntity> data = new ArrayList<>();
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DesarrolladorEntity.class.getPackage())
                .addPackage(DesarrolladorPersistence.class.getPackage())
                .addPackage(DesarrolladorLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
       /**
     * Configuración inicial de todas las pruebas.
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

   /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DesarrolladorEntity entidad = factory.manufacturePojo(DesarrolladorEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    
    /**
     * Prueba el metodo create de desarrollador logic
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void createTest() throws BusinessLogicException 
    {
        DesarrolladorEntity nuevoDes = factory.manufacturePojo(DesarrolladorEntity.class);
        DesarrolladorEntity desarrollador = dl.createDesarrollador(nuevoDes);
        Assert.assertNotNull(desarrollador);

        DesarrolladorEntity entidad = em.find(DesarrolladorEntity.class, desarrollador.getId());
        Assert.assertEquals(entidad.getTipo(), nuevoDes.getTipo());
    }
     /**
     * Prueba para asegurarse que el tipo de un desarrollador no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestTipoNull()throws BusinessLogicException
    {
        DesarrolladorEntity nuevaEnt=factory.manufacturePojo(DesarrolladorEntity.class);
        nuevaEnt.setTipo(null);
        DesarrolladorEntity resultado=dl.createDesarrollador(nuevaEnt);
    }

    /**
     * Prueba para consultar un desarrollador.
     */
    @Test
    public void getDesarrolladorTest() {
        DesarrolladorEntity nuevaEnt = data.get(0);
        DesarrolladorEntity entidad = dl.getDesarrollador(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
        Assert.assertEquals(nuevaEnt.getTipo(),entidad.getTipo());
    }
     /**
     * Prueba para consultar la lista de desarrolladores.
     */
    @Test
    public void getDesarrolladoresTest() {
        List<DesarrolladorEntity> lista = dl.getDesarrolladores();
        Assert.assertEquals(data.size(), lista.size());
        for (DesarrolladorEntity entidad : lista) {
            boolean encontrado = false;
            for (DesarrolladorEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
     /**
     * Prueba para actualizar un desarrollador con un tipo nulo.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDesarrolladorConTipoNullTest() throws BusinessLogicException 
    {
        DesarrolladorEntity entidad = data.get(0);
        DesarrolladorEntity  pojoEntity = factory.manufacturePojo(DesarrolladorEntity.class);
        pojoEntity.setTipo(null);
        pojoEntity.setId(entidad.getId());
        dl.updateDesarrollador(pojoEntity);
    }
     /**
     * Prueba para eliminar un desarrollador.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteRequisitoTest() throws BusinessLogicException 
    {
        DesarrolladorEntity entity = data.get(0);
        dl.deleteRequisito(entity.getId());
        DesarrolladorEntity deleted = em.find(DesarrolladorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

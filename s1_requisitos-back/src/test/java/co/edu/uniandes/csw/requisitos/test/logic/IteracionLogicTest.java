/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.IteracionLogic;
import co.edu.uniandes.csw.requisitos.entities.IteracionEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.IteracionPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class IteracionLogicTest {
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
   private IteracionLogic il;
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<IteracionEntity> data = new ArrayList<>();
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IteracionEntity.class.getPackage())
                .addPackage(IteracionPersistence.class.getPackage())
                .addPackage(IteracionLogic.class.getPackage())
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
        em.createQuery("delete from IteracionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            IteracionEntity entidad = factory.manufacturePojo(IteracionEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    
    /**
     * Prueba el metodo create de iteracion logic
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void createTest() throws BusinessLogicException 
    {
        IteracionEntity nuevoDes = factory.manufacturePojo(IteracionEntity.class);
        IteracionEntity desarrollador = il.createIteracion(nuevoDes);
        Assert.assertNotNull(desarrollador);

        IteracionEntity entidad = em.find(IteracionEntity.class, desarrollador.getId());
        Assert.assertEquals(entidad.getDescripcion(), nuevoDes.getDescripcion());
    }
     /**
     * Prueba para asegurarse la fecha de una iteracion de una iteracion no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createIteracionTestFechaFinNull()throws BusinessLogicException
    {
        IteracionEntity nuevaEnt=factory.manufacturePojo(IteracionEntity.class);
        nuevaEnt.setFechaFin(null);
        IteracionEntity resultado=il.createIteracion(nuevaEnt);
    }
      /**
     * Prueba para asegurarse que la fecha de una iteracion no puede ser un string vacio.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createIteracionTestFechaVacia()throws BusinessLogicException
    {
        IteracionEntity nuevaEnt=factory.manufacturePojo(IteracionEntity.class);
        nuevaEnt.setFechaFin(null);
        IteracionEntity resultado=il.createIteracion(nuevaEnt);
    }
    /**
     * Prueba para consultar una iteracion.
     */
    @Test
    public void getIteracionTest() {
        IteracionEntity nuevaEnt = data.get(0);
        IteracionEntity entidad = il.getIteracion(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
        Assert.assertEquals(nuevaEnt.getFechaFin(),entidad.getFechaFin());
    }
     /**
     * Prueba para consultar la lista de Iteraciones.
     */
    @Test
    public void getDesarrolladoresTest() {
        List<IteracionEntity> lista = il.getIteraciones();
        Assert.assertEquals(data.size(), lista.size());
        for (IteracionEntity entidad : lista) {
            boolean encontrado = false;
            for (IteracionEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
     /**
     * Prueba para actualizar una iteracion con una fecha vacio.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateIteracionConFechaVacioTest() throws BusinessLogicException 
    {
        IteracionEntity entidad = data.get(0);
        IteracionEntity  pojoEntity = factory.manufacturePojo(IteracionEntity.class);
        pojoEntity.setFechaFin(null);
        pojoEntity.setId(entidad.getId());
        il.updateIteracion(pojoEntity);
    }
    
     /**
     * Prueba para eliminar una iteracion.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteRequisitoTest() throws BusinessLogicException 
    {
        IteracionEntity entity = data.get(0);
        il.deleteRequisito(entity.getId());
        IteracionEntity deleted = em.find(IteracionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

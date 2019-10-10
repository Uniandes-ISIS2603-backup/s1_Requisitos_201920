/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.RequisitoLogic;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
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
 *Test de la clase RequisitoLogic
 * @author Nicolás Tobo
 * Adaptado de: https://github.com/Uniandes-isis2603/backstepbystep/blob/master/backstepbystep-back/src/test/java/co/edu/uniandes/csw/bookstore/test/logic/BookLogicTest.java
 */
@RunWith(Arquillian.class)
public class RequisitoLogicTest 
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
   private RequisitoLogic rl;
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<RequisitosEntity> data = new ArrayList<>();
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RequisitosEntity.class.getPackage())
                .addPackage(RequisitoPersistence.class.getPackage())
                .addPackage(RequisitoLogic.class.getPackage())
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
        em.createQuery("delete from RequisitosEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RequisitosEntity entidad = factory.manufacturePojo(RequisitosEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
   /**
    * Prueba para crear un requisito
    * @throws BusinessLogicException 
    */
    @Test
    public void createRequisitoTest() throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
        Assert.assertNotNull(resultado); 
             
        RequisitosEntity entidad = em.find(RequisitosEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEnt.getAutor(), entidad.getAutor());
        Assert.assertEquals(nuevaEnt.getFuente(), entidad.getFuente());
        Assert.assertEquals(nuevaEnt.getEstabilidad(), entidad.getEstabilidad());
        Assert.assertEquals(nuevaEnt.getComentariosAdicionales(), entidad.getComentariosAdicionales());
        Assert.assertEquals(nuevaEnt.getDescripcion(), entidad.getDescripcion());
        Assert.assertEquals(nuevaEnt.getImportancia(), entidad.getImportancia());
    }
    /**
     * Prueba para asegurarse que no cree un requisito con un nombre repetido.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoNombreRepetido()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setNombre(data.get(0).getNombre());
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
    /**
     * Prueba para asegurarse que un autor de un requisito no puede ser un string vacio.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestAutorVacio()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setAutor("");
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
    /**
     * Prueba para asegurarse que un nombre de un requisito no puede ser un string vacio.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestNombreVacio()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setNombre("");
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
     /**
     * Prueba para asegurarse que una descripcion de un requisito no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestDescripcionNull()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setDescripcion(null);
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
    /**
     * Prueba para asegurarse que una descripcion de un requisito no puede ser vacia.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestDescripcionVacia()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setDescripcion("");
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
     /**
     * Prueba para asegurarse que la importancia de un requisito no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestImportanciaNull()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setImportancia(null);
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
     /**
     * Prueba para asegurarse que la estabilidad de un requisito no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestEstabilidadNull()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setEstabilidad(null);
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
    /**
     * Prueba para asegurarse que la fuente de un requisito no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestFuenteNull()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setFuente(null);
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
     /**
     * Prueba para asegurarse que la fuente de un requisito no puede ser vacia.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestFuenteVacia()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setFuente("");
        RequisitosEntity resultado=rl.createRequisito(nuevaEnt);
    }
    /**
     * Prueba para consultar la lista de requisitos.
     */
    @Test
    public void getRequisitosTest() {
        List<RequisitosEntity> lista = rl.getRequisitos();
        Assert.assertEquals(data.size(), lista.size());
        for (RequisitosEntity entidad : lista) {
            boolean encontrado = false;
            for (RequisitosEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    /**
     * Prueba para consultar un requisito.
     */
    @Test
    public void getRequisitoTest() {
        RequisitosEntity nuevaEnt = data.get(0);
        RequisitosEntity entidad = rl.getRequisito(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
        Assert.assertEquals(nuevaEnt.getAutor(), entidad.getAutor());
        Assert.assertEquals(nuevaEnt.getFuente(), entidad.getFuente());
        Assert.assertEquals(nuevaEnt.getEstabilidad(), entidad.getEstabilidad());
        Assert.assertEquals(nuevaEnt.getComentariosAdicionales(), entidad.getComentariosAdicionales());
        Assert.assertEquals(nuevaEnt.getDescripcion(), entidad.getDescripcion());
        Assert.assertEquals(nuevaEnt.getImportancia(), entidad.getImportancia());   
    }
    /**
     * Prueba para actualizar un requisito.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void updateRequisitoTest() throws BusinessLogicException 
    {
        RequisitosEntity entity = data.get(0);
        RequisitosEntity pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setId(entity.getId());
        rl.updateRequisito(pojoEntity);
        RequisitosEntity resp = em.find(RequisitosEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getAutor(), resp.getAutor());
        Assert.assertEquals(pojoEntity.getFuente(), resp.getFuente());
        Assert.assertEquals(pojoEntity.getEstabilidad(), resp.getEstabilidad());
        Assert.assertEquals(pojoEntity.getComentariosAdicionales(), resp.getComentariosAdicionales());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getImportancia(), resp.getImportancia());  
    }
     /**
     * Prueba para actualizar un Requisito con un autor null.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConAutorNullTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setAutor(null);
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
     /**
     * Prueba para actualizar un Requisito con un autor vacio.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConAutorVacioTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setAutor("");
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
     /**
     * Prueba para actualizar un Requisito con una descripcion nula.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConDescripcionNullTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setDescripcion(null);
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
     /**
     * Prueba para actualizar un Requisito con una descripcion vacia.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConDescripcionVaciaTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setDescripcion("");
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
    /**
     * Prueba para actualizar un Requisito con una importancia nula.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConImportanciaNullTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setImportancia(null);
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
    /**
     * Prueba para actualizar un Requisito con una estabilidad nula.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConEstabilidadNullTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setEstabilidad(null);
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
    /**
     * Prueba para actualizar un Requisito con una fuente nula.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConFuenteNullTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setFuente(null);
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
     /**
     * Prueba para actualizar un Requisito con una fuente vacia.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConFuenteVaciaTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setFuente("");
        pojoEntity.setId(entidad.getId());
        rl.updateRequisito(pojoEntity);
    }
     /**
     * Prueba para eliminar un requisito.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteRequisitoTest() throws BusinessLogicException 
    {
        RequisitosEntity entity = data.get(0);
        rl.deleteRequisito(entity.getId());
        RequisitosEntity deleted = em.find(RequisitosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}

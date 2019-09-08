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
 *Test de Requsito Logic
 * @author Nicolás Tobo
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
     * Prueba para asegurarse que un autor no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestAutorNull()throws BusinessLogicException
    {
        RequisitosEntity nuevaEnt=factory.manufacturePojo(RequisitosEntity.class);
        nuevaEnt.setAutor(null);
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
     * Prueba para actualizar un Requisito con un autor inválido.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRequisitoConAutorInvalidoTest() throws BusinessLogicException 
    {
        RequisitosEntity entidad = data.get(0);
        RequisitosEntity  pojoEntity = factory.manufacturePojo(RequisitosEntity.class);
        pojoEntity.setAutor("");
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

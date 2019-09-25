/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.RepresentanteDelClienteLogic;
import co.edu.uniandes.csw.requisitos.entities.RepresentanteDelClienteEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.RepresentanteDelClientePersistence;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class RepresentanteDelClienteLogicTest {
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
    * Objeto RepresentanteDelClienteLogic a probar
    */
   @Inject
   private RepresentanteDelClienteLogic rl;
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<RepresentanteDelClienteEntity> data = new ArrayList<>();
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RepresentanteDelClienteEntity.class.getPackage())
                .addPackage(RepresentanteDelClientePersistence.class.getPackage())
                .addPackage(RepresentanteDelClienteLogic.class.getPackage())
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
        em.createQuery("delete from RepresentanteDelClienteEntity").executeUpdate();
    }
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RepresentanteDelClienteEntity entidad = factory.manufacturePojo(RepresentanteDelClienteEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
    /**
     * Prueba el metodo create de RepresentanteDelcliente logic
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void createTest() throws BusinessLogicException 
    {
        RepresentanteDelClienteEntity nuevoRep = factory.manufacturePojo(RepresentanteDelClienteEntity.class);
        RepresentanteDelClienteEntity representante = rl.createRepresentanteDelCliente(nuevoRep);
        Assert.assertNotNull(representante);

        RepresentanteDelClienteEntity entidad = em.find(RepresentanteDelClienteEntity.class, representante.getId());
        Assert.assertEquals(entidad.getNombre(), nuevoRep.getNombre());
    }
     /**
     * Prueba para asegurarse que el nombre del representante no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected=BusinessLogicException.class)
    public void createRequisitoTestTipoNull()throws BusinessLogicException
    {
        RepresentanteDelClienteEntity nuevaEnt=factory.manufacturePojo(RepresentanteDelClienteEntity.class);
        nuevaEnt.setNombre(null);
        RepresentanteDelClienteEntity resultado = rl.createRepresentanteDelCliente(nuevaEnt);
    }

    /**
     * Prueba para consultar un representante.
     */
    @Test
    public void getDesarrolladorTest() {
        RepresentanteDelClienteEntity nuevaEnt = data.get(0);
        RepresentanteDelClienteEntity entidad = rl.getRepresentante(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId());
        Assert.assertEquals(nuevaEnt.getNombre(),entidad.getNombre());
    }
     /**
     * Prueba para consultar la lista de representantes.
     */
    @Test
    public void getDesarrolladoresTest() {
        List<RepresentanteDelClienteEntity> lista = rl.getRepresentantes();
        Assert.assertEquals(data.size(), lista.size());
        for (RepresentanteDelClienteEntity entidad : lista) {
            boolean encontrado = false;
            for (RepresentanteDelClienteEntity entGuardada : data) 
            {
                if (entidad.getId().equals(entGuardada.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
     /**
     * Prueba para actualizar un representante con un tipo vacio.
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateRepresentanteConTipoNullTest() throws BusinessLogicException 
    {
        RepresentanteDelClienteEntity entidad = data.get(0);
        RepresentanteDelClienteEntity  pojoEntity = factory.manufacturePojo(RepresentanteDelClienteEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entidad.getId());
        rl.updateRepresentante(pojoEntity);
    }
    
     /**
     * Prueba para eliminar un representante.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteRequisitoTest() throws BusinessLogicException 
    {
        RepresentanteDelClienteEntity entity = data.get(0);
        rl.deleteRequisito(entity.getId());
        RepresentanteDelClienteEntity deleted = em.find(RepresentanteDelClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}


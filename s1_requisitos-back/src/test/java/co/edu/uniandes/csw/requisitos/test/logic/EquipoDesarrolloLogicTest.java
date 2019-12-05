/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.EquipoDesarrolloLogic;
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
 * @author Juan Martinez
 */
@RunWith(Arquillian.class)
public class EquipoDesarrolloLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EquipoDesarrolloLogic equipoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    UserTransaction utx;
    
    /**
     * Lista de ProyectoEntitys creados por Podam
     */
    private final List<EquipoDesarrolloEntity> list = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoDesarrolloEntity.class.getPackage())
                .addPackage(EquipoDesarrolloLogic.class.getPackage())
                .addPackage(EquipoDesarrolloPersistence.class.getPackage())
                .addAsManifestResource("Meta-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("Meta-INF/beans.xml","beans.xml");
                
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
        em.createQuery("delete from EquipoDesarrolloEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EquipoDesarrolloEntity entidad = factory.manufacturePojo(EquipoDesarrolloEntity.class);
            em.persist(entidad);
            list.add(entidad);
        }
    }
    
    /**
     * Prueba la creacion de un EquipoDesarrollo
     * @throws BusinessLogicException 
     */
    @Test
    public void createEquipoDesarrollo() throws BusinessLogicException{
        EquipoDesarrolloEntity entidadNueva = factory.manufacturePojo(EquipoDesarrolloEntity.class);
        EquipoDesarrolloEntity result = equipoLogic.createEquipoDesarrollo(entidadNueva);
        Assert.assertNotNull(result);
        
        EquipoDesarrolloEntity entidad = em.find(EquipoDesarrolloEntity.class, result.getId());
        Assert.assertEquals(entidad.getId(), result.getId());
    }
    
    
    /**
     * Prueba para get un EquipoDesarrollo
     */
    @Test
    public void getEquipoTest() {
        EquipoDesarrolloEntity nuevaEnt = list.get(0);
        EquipoDesarrolloEntity entidad = equipoLogic.getEquipo(nuevaEnt.getId());
        Assert.assertNotNull(entidad);
        
        Assert.assertEquals(nuevaEnt.getId(),entidad.getId()); 
    }
    
      /**
     * Prueba para consultar la lista de desarrolladores.
     */
    @Test
    public void getEquiposDesarrolloTest() {
        List<EquipoDesarrolloEntity> lista = equipoLogic.getEquipos();
        Assert.assertEquals(list.size(), lista.size());
        for (EquipoDesarrolloEntity entidad : lista) {
            boolean encontrado = false;
            for (EquipoDesarrolloEntity entGuardada : list) 
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
    public void updateEquipoDesarrolloConEquipoDesarrolloNuloNullTest() throws BusinessLogicException 
    {
        EquipoDesarrolloEntity entidad = list.get(0);
        EquipoDesarrolloEntity  pojoEntity = factory.manufacturePojo(EquipoDesarrolloEntity.class);
        pojoEntity.setEquipoDesarrollo(null);
        pojoEntity.setId(entidad.getId());
        equipoLogic.updateEquipo(pojoEntity);
    }
     /**
     * Prueba para eliminar un desarrollador.
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteRequisitoTest() throws BusinessLogicException 
    {
        EquipoDesarrolloEntity entity = list.get(0);
        equipoLogic.deleteEquipo(entity.getId());
        EquipoDesarrolloEntity deleted = em.find(EquipoDesarrolloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updateEquipoIdNull() throws BusinessLogicException 
    { EquipoDesarrolloEntity entity = new EquipoDesarrolloEntity();
    entity.setEquipoDesarrollo("jojojo");
        equipoLogic.updateEquipo(entity);
    }
     @Test
     public void getEquipoByEquipoDesarrollo() throws BusinessLogicException 
     {
                 EquipoDesarrolloEntity nuevaEnt = list.get(0);
        EquipoDesarrolloEntity entidad = equipoLogic.getEquipo(nuevaEnt.getId());
       EquipoDesarrolloEntity equ = equipoLogic.getEquipoByEquipoDesarrollo(entidad.getEquipoDesarrollo());
         Assert.assertEquals(entidad.getEquipoDesarrollo(), equ.getEquipoDesarrollo());
     }
    
    
}

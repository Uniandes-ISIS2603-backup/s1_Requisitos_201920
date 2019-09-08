/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
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
public class EquipoDesarrolloPersistenceTest {
    
    @Inject
    EquipoDesarrolloPersistence edp;
    
    /**
     * Manejador de entidades
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Lista de obj de prueba
     */
    private final List<EquipoDesarrolloEntity> list = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(EquipoDesarrolloEntity.class)
                .addClass(EquipoDesarrolloPersistence.class)
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
     * Prueba el metodo create
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        EquipoDesarrolloEntity proyecto = factory.manufacturePojo(EquipoDesarrolloEntity.class);
        EquipoDesarrolloEntity result = edp.create(proyecto);
        Assert.assertNotNull(result);
        
        EquipoDesarrolloEntity entity = em.find(EquipoDesarrolloEntity.class, proyecto.getId());
        Assert.assertEquals(proyecto.getEquipoDesarrollo(), entity.getEquipoDesarrollo());
    }
     /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        EquipoDesarrolloEntity Entity1 = list.get(0);
        EquipoDesarrolloEntity encontrado = edp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getEquipoDesarrollo(), encontrado.getEquipoDesarrollo());
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<EquipoDesarrolloEntity> lista = edp.findAll();
        Assert.assertEquals(list.size(), lista.size());

        for (EquipoDesarrolloEntity ent1 : lista) {
            boolean encontrado = false;
            for (EquipoDesarrolloEntity ent2 : list) {
                if (ent1.getId().equals(ent2.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    /**
     * Prueba el metodo update.
     */
    @Test
    public void updateTest() {
        EquipoDesarrolloEntity entidad = list.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EquipoDesarrolloEntity nuevaEnt = factory.manufacturePojo(EquipoDesarrolloEntity.class);

        nuevaEnt.setId(entidad.getId());

        edp.update(nuevaEnt);

        EquipoDesarrolloEntity resp = em.find(EquipoDesarrolloEntity.class, entidad.getId());
        Assert.assertEquals(resp.getEquipoDesarrollo(), nuevaEnt.getEquipoDesarrollo());
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        EquipoDesarrolloEntity entidad = list.get(0);
        edp.delete(entidad.getId());
        EquipoDesarrolloEntity eliminada = em.find(EquipoDesarrolloEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
    
    
}

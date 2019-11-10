/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.persistence.ProyectoPersistence;
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
public class ProyectoPersistenceTest {
    
    /**
     * Unidad de Persistencia
     */
    @Inject
    ProyectoPersistence pp;
    
    /**
     * Manejador entidades Podam
     */
    @PersistenceContext
    private EntityManager em;
    
     /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Lista de ProyectoEntitys creados por Podam
     */
    private final List<ProyectoEntity> list = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProyectoEntity.class.getPackage())
                .addPackage(ProyectoPersistence.class.getPackage())
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
        em.createQuery("delete from ProyectoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ProyectoEntity entidad = factory.manufacturePojo(ProyectoEntity.class);
            em.persist(entidad);
            list.add(entidad);
        }
    }
    
    /**
     * Prueba del metodo create
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ProyectoEntity proyecto = factory.manufacturePojo(ProyectoEntity.class);
        ProyectoEntity result = pp.create(proyecto);
        Assert.assertNotNull(result);
        
        ProyectoEntity entity = em.find(ProyectoEntity.class, proyecto.getId());
        Assert.assertEquals(proyecto.getNombre(), entity.getNombre());
        Assert.assertEquals(proyecto.getFechaInicial(), entity.getFechaInicial());
        Assert.assertEquals(proyecto.getEquipo(), entity.getEquipo());
        
    }
    
    /**
     * Prueba del metodo find
     */
    @Test
    public void findTest(){
        ProyectoEntity proy = list.get(0);
        ProyectoEntity encontrado = pp.find(proy.getId());
        
        Assert.assertEquals(proy.getNombre(),encontrado.getNombre());
    }
    
    /**
     * Prueba el metodo update.
     */
    @Test
    public void updateTest() {
        ProyectoEntity entidad = list.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProyectoEntity nuevaEnt = factory.manufacturePojo(ProyectoEntity.class);

        nuevaEnt.setId(entidad.getId());

        pp.update(nuevaEnt);

        ProyectoEntity resp = em.find(ProyectoEntity.class, entidad.getId());
        Assert.assertEquals(resp.getNombre(), nuevaEnt.getNombre());
    }
    
    /**
     * Prueba del metodo delete
     */
    @Test
    public void deleteTest(){
        ProyectoEntity entidad = list.get(0);
        pp.delete(entidad.getId());
        ProyectoEntity eliminada = em.find(ProyectoEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
}

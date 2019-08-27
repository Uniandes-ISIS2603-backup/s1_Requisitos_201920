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
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
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
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ProyectoEntity.class)
                .addClass(ProyectoPersistence.class)
                .addAsManifestResource("Meta-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("Meta-INF/beans.xml","beans.xml");
    }
    
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
     * Lista de ProyectoEntitys creados por Podam
     */
    private List<ProyectoEntity> list = new ArrayList<>();
    
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
        Assert.assertEquals(proyecto.getFechaFinal(), entity.getFechaFinal());
        
    }
    
    /**
     * Prueba del metodo find
     */
    @Test
    public void findTest(){
        
    }
    
    /**
     * Prueba del metodo delete
     */
    @Test
    public void deleteTest(){
        
    }
}

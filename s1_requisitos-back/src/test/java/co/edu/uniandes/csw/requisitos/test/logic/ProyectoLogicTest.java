/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ProyectoLogic;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
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
 * @author Juan Martrinez
 */
@RunWith(Arquillian.class)
public class ProyectoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ProyectoLogic proyectoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
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
                .addPackage(ProyectoLogic.class.getPackage())
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
    
    @Test
    public void createProyecto() throws BusinessLogicException{
        ProyectoEntity entidadNueva = factory.manufacturePojo(ProyectoEntity.class);
        ProyectoEntity result = proyectoLogic.createProyecto(entidadNueva);
        Assert.assertNotNull(result);
        
        ProyectoEntity entidad = em.find(ProyectoEntity.class, result.getId());
        Assert.assertEquals(entidad.getNombre(), result.getNombre());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createProyectoFechas() throws BusinessLogicException{
        
        ProyectoEntity nuevaEntidad = factory.manufacturePojo(ProyectoEntity.class);
        nuevaEntidad.setFechaInicial(nuevaEntidad.getFechaInicial());
        ProyectoEntity result = proyectoLogic.createProyecto(nuevaEntidad);
    }
    
}

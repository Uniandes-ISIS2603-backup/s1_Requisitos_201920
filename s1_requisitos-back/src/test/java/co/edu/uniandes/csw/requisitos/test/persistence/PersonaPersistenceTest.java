/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.persistence.PersonaPersistence;
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
 * @author Juan Rubio
 */
@RunWith(Arquillian.class)
public class PersonaPersistenceTest {
    
    @Inject
    private PersonaPersistence pp;
    
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<PersonaEntity> data = new ArrayList<>();
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(PersonaEntity.class)
                .addClass(PersonaPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
     @PersistenceContext
    private EntityManager em;
     
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PersonaEntity persona = factory.manufacturePojo(PersonaEntity.class);
        PersonaEntity result = pp.create(persona);
        Assert.assertNotNull(result);
        
      PersonaEntity entity=em.find(PersonaEntity.class, result.getId());
      Assert.assertEquals(persona.getNombre(), entity.getNombre());
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
        em.createQuery("delete from PersonaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            PersonaEntity entidad = factory.manufacturePojo(PersonaEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        PersonaEntity Entity1 = data.get(0);
        PersonaEntity encontrado = pp.find(Entity1.getId());

        Assert.assertEquals(Entity1.getNombre(), encontrado.getNombre());
       
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<PersonaEntity> lista = pp.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (PersonaEntity ent1 : lista) {
            boolean encontrado = false;
            for (PersonaEntity ent2 : data) {
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
        PersonaEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PersonaEntity nuevaEnt = factory.manufacturePojo(PersonaEntity.class);

        nuevaEnt.setId(entidad.getId());

        pp.update(nuevaEnt);

        PersonaEntity resp = em.find(PersonaEntity.class, entidad.getId());
        Assert.assertEquals(resp.getNombre(), nuevaEnt.getNombre());
       
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        PersonaEntity entidad = data.get(0);
        pp.delete(entidad.getId());
        PersonaEntity eliminada = em.find(PersonaEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByNombreTest() 
    {
        PersonaEntity entidad = data.get(0);
        PersonaEntity nuevaEnt= pp.findByNombre(entidad.getNombre());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getNombre(), entidad.getNombre());
      
    }
    
       @Test
    public void findByCorreoTest() 
    {
        PersonaEntity entidad = data.get(0);
        PersonaEntity nuevaEnt= pp.findByCorreo(entidad.getCorreo());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getCorreo(), entidad.getCorreo());
      
    }
       @Test
    public void findByCedulaTest() 
    {
        PersonaEntity entidad = data.get(0);
        PersonaEntity nuevaEnt= pp.findByCedula(entidad.getCedula());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getCedula(), entidad.getCedula());
      
    }
    
}

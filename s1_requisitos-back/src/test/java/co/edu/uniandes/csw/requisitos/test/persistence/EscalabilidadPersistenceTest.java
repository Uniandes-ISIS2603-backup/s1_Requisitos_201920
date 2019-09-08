/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;

import co.edu.uniandes.csw.requisitos.entities.EscalabilidadEntity;
import co.edu.uniandes.csw.requisitos.persistence.EscalabilidadPersistence;
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
 * @author Juan RUbio
 */
@RunWith(Arquillian.class)
public class EscalabilidadPersistenceTest {
    
    @Inject
    private EscalabilidadPersistence ep;
      /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<EscalabilidadEntity> data = new ArrayList<>();
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(EscalabilidadEntity.class)
                .addClass(EscalabilidadPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
     @PersistenceContext
    private EntityManager em;
     
     @Test
     public void createTest()
     {
         PodamFactory factory = new PodamFactoryImpl();
         EscalabilidadEntity escalabilidad = factory.manufacturePojo(EscalabilidadEntity.class);
         EscalabilidadEntity result = ep.create(escalabilidad);
         Assert.assertNotNull(result);
         
         EscalabilidadEntity entity = em.find(EscalabilidadEntity.class, result.getId());
         Assert.assertEquals(escalabilidad.getTipo(), entity.getTipo());
       
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
        em.createQuery("delete from EscalabilidadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            EscalabilidadEntity entidad = factory.manufacturePojo(EscalabilidadEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba el metodo find
     */
    @Test
    public void findTest() {
        EscalabilidadEntity Entity1 = data.get(0);
        EscalabilidadEntity encontrado = ep.find(Entity1.getId());

        Assert.assertEquals(Entity1.getTipo(), encontrado.getTipo());
       
    }

    /**
     * Prueba el metodo findAll
     */
    @Test
    public void findAllTest() {
        List<EscalabilidadEntity> lista = ep.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (EscalabilidadEntity ent1 : lista) {
            boolean encontrado = false;
            for (EscalabilidadEntity ent2 : data) {
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
        EscalabilidadEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EscalabilidadEntity nuevaEnt = factory.manufacturePojo(EscalabilidadEntity.class);

        nuevaEnt.setId(entidad.getId());

        ep.update(nuevaEnt);

        EscalabilidadEntity resp = em.find(EscalabilidadEntity.class, entidad.getId());
        Assert.assertEquals(resp.getTipo(), nuevaEnt.getTipo());
       
    }

    /**
     * Prueba el método delete.
     */
    @Test
    public void deleteTest() {
        EscalabilidadEntity entidad = data.get(0);
        ep.delete(entidad.getId());
        EscalabilidadEntity eliminada = em.find(EscalabilidadEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }

    @Test
    public void findByTipoTest() 
    {
        EscalabilidadEntity entidad = data.get(0);
        EscalabilidadEntity nuevaEnt= ep.findByTipo(entidad.getTipo());
        Assert.assertNotNull(nuevaEnt);
        
        Assert.assertEquals(nuevaEnt.getTipo(), entidad.getTipo());
      
    }
}

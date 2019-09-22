/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.persistence;


import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.entities.PersonaEntity;
import co.edu.uniandes.csw.requisitos.entities.RequisitosEntity;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;

import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import co.edu.uniandes.csw.requisitos.persistence.PersonaPersistence;
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
 *
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class ModificacionesPersistenceTest {
    
    
     /**
     * Objeto requisitoPersistence a probar
     */
    @Inject
    private ModificacionesPersistence a;
    /**
     * Manejador de entidades usado para buscar el resultado creado por el Podam
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Manejador de transacciones
     */
    @Inject
    UserTransaction utx;
    
    
      /**
     * Lista de objetos de prueba creados por el Podam
     */
    private List<ModificacionesEntity> data = new ArrayList<>();
        
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                
                .addPackage(ModificacionesEntity.class.getPackage())
                .addPackage(ModificacionesPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
 
            
  
    
    
    
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
    
    
         
    private void clearData() {
        em.createQuery("delete from ModificacionesEntity").executeUpdate();
    }
       private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ModificacionesEntity entidad = factory.manufacturePojo(ModificacionesEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }
       
       
    @Test
    public void testCreate()
    {
        PodamFactory factory=new PodamFactoryImpl();
        ModificacionesEntity modificacion= factory.manufacturePojo(ModificacionesEntity.class);
        ModificacionesEntity result= a.create(modificacion);
        Assert.assertNotNull(result);
        
         ModificacionesEntity entity=em.find(ModificacionesEntity.class, modificacion.getId());
      
      Assert.assertEquals(modificacion.getDescripcion(),entity.getDescripcion());
      Assert.assertEquals(modificacion.getFechaModificacion(),entity.getFechaModificacion());
     
    }
    
     @Test
    public void findTest() {
       ModificacionesEntity Entity1 = data.get(0);
        ModificacionesEntity encontrado = a.find(Entity1.getId());

        Assert.assertEquals(Entity1.getDescripcion(), encontrado.getDescripcion());
        Assert.assertEquals(Entity1.getFechaModificacion(), encontrado.getFechaModificacion());
        
    }
    
    
     @Test
    public void findAllTest() {
        List<ModificacionesEntity> lista = a.findAll();
        Assert.assertEquals(data.size(), lista.size());

        for (ModificacionesEntity ent1 : lista) {
            boolean encontrado = false;
            for (ModificacionesEntity ent2 : data) {
                if (ent1.getId().equals(ent2.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
     @Test
    public void updateTest() {
        ModificacionesEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ModificacionesEntity nuevaEnt = factory.manufacturePojo(ModificacionesEntity.class);

        nuevaEnt.setId(entidad.getId());

        a.update(nuevaEnt);

        ModificacionesEntity resp = em.find(ModificacionesEntity.class, entidad.getId());
        Assert.assertEquals(resp.getDescripcion(), nuevaEnt.getDescripcion());
        Assert.assertEquals(resp.getFechaModificacion(), nuevaEnt.getFechaModificacion());
        
    }
    /*
    @Test
    public void deleteTest() {
        ModificacionesEntity entidad = data.get(0);
        a.delete(entidad.getId());
        ModificacionesEntity eliminada = em.find(ModificacionesEntity.class, entidad.getId());
        Assert.assertNull(eliminada);
    }
*/
}

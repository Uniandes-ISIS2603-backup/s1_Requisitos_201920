/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ModificacionesLogic;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.ModificacionesPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Before;

/**
 *
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class ModificacionesLogicTest {
    /*
    se incia podam factory para poder generar valores aleatorios para las pruebas
    */
    private PodamFactory factory= new PodamFactoryImpl();
    /*
    entity manager
    */
    @PersistenceContext
    private EntityManager em;
    @Inject
    private ModificacionesLogic modificacionesLogic;
    
    @Inject
    UserTransaction utx;
    /*
    lista para guardar los datos de tipo moficacion para las pruebas
    */
    private List<ModificacionesEntity> data = new ArrayList<>();
    
    /*
    inicializacion del entorno de pruebas
    conexion a la base de datos y a las clases de persistencia y logica
    */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ModificacionesEntity.class.getPackage())
                .addPackage(ModificacionesLogic.class.getPackage())
                .addPackage(ModificacionesPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /*
    operaciones que se realizan antes de cada prueba
    se borran los datps de la lista y se vuelven a inicializar
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

    /*
    borra los datos de la lista
    */
    private void clearData() {
        em.createQuery("delete from ModificacionesEntity").executeUpdate();
    }
/*
    pobla la lista de datps generador por el podam factory
    */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ModificacionesEntity entidad = factory.manufacturePojo(ModificacionesEntity.class);
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /*
    test de  crera modificaciones
    */
    @Test
    public void createModificaciones()throws BusinessLogicException{
        ModificacionesEntity mod=factory.manufacturePojo(ModificacionesEntity.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format(new Date());
        try { 
            Date date= format.parse("2009-12-31");
            mod.setFechaModificacion(date);
        } catch (ParseException ex) {
            Logger.getLogger(ModificacionesLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ModificacionesEntity result= modificacionesLogic.createModificaciones(mod);
        Assert.assertNotNull(result);
        ModificacionesEntity entity= em.find(ModificacionesEntity.class, result.getId());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
        Assert.assertEquals(entity.getFechaModificacion(), result.getFechaModificacion());
        
    }
    /*
    test de crear modificacion con la descripcion nula
    */
    @Test(expected=BusinessLogicException.class)
    public void crearModificacionesDescripcionNull()throws BusinessLogicException{
        ModificacionesEntity mod= factory.manufacturePojo(ModificacionesEntity.class);
        mod.setDescripcion(null);
        ModificacionesEntity result= modificacionesLogic.createModificaciones(mod);
    }
    /*
    test de crear Modificaciones con Fechas en null
    */
    @Test(expected=BusinessLogicException.class)
    public void crearModificacionesFechaNull()throws BusinessLogicException{
        ModificacionesEntity mod= factory.manufacturePojo(ModificacionesEntity.class);
        mod.setFechaModificacion(null);
        ModificacionesEntity result= modificacionesLogic.createModificaciones(mod);
    }
    
    /*
    test de retornar todas las modificaciones
    */
    @Test
    public void findAllTest() {
        List<ModificacionesEntity> lista = modificacionesLogic.getModificaciones();
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
    /*
    test de encontrar una modificacion dado un id
    */
    @Test
    public void findCaso(){
        ModificacionesEntity entidad= data.get(0);
        ModificacionesEntity encontrado= modificacionesLogic.getModificacion(entidad.getId());
        Assert.assertNotNull(encontrado);
        Assert.assertEquals(encontrado.getDescripcion(), entidad.getDescripcion());
        Assert.assertEquals(encontrado.getFechaModificacion(), entidad.getFechaModificacion());
        
    }
    /*
    test de actualizar una modificacion
    */
    @Test
    public void updateTest() throws BusinessLogicException{
        ModificacionesEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ModificacionesEntity nuevaEnt = factory.manufacturePojo(ModificacionesEntity.class);

        nuevaEnt.setId(entidad.getId());

        modificacionesLogic.updateModificaciones(nuevaEnt);

        ModificacionesEntity resp = em.find(ModificacionesEntity.class, entidad.getId());
        Assert.assertEquals(resp.getDescripcion(), nuevaEnt.getDescripcion());
        Assert.assertEquals(resp.getFechaModificacion(), nuevaEnt.getFechaModificacion());

    }
    /*
    test de actualizar una modificacion con descripcion nula
    */
    @Test (expected=BusinessLogicException.class)
    public void updateModificacionesConDescripcionNull() throws BusinessLogicException{
        ModificacionesEntity mod=data.get(0);
        mod.setDescripcion(null);
        modificacionesLogic.updateModificaciones(mod);
    }
    
    /*
    test de actualizar modificacion con fechas nulas
    */
    @Test (expected=BusinessLogicException.class)
    public void updateModificacionesConFechaNull() throws BusinessLogicException{
        ModificacionesEntity mod=data.get(0);
        mod.setFechaModificacion(null);
        modificacionesLogic.updateModificaciones(mod);
    }
    /*
    test de borrar modificacion
    */
    @Test
    public void deleteCasoDeUsoTest(){
        ModificacionesEntity mod= data.get(0);
        modificacionesLogic.deleteModificaciones(mod.getId());
        
        ModificacionesEntity buscada= em.find(ModificacionesEntity.class, mod.getId());
        Assert.assertNull(buscada);
    }
    
}

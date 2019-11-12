/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.ProyectoEquipoDesarrolloLogic;
import co.edu.uniandes.csw.requisitos.entities.EquipoDesarrolloEntity;
import co.edu.uniandes.csw.requisitos.entities.ProyectoEntity;
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
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class ProyectoEquipoDesarrolloLogicTest {
   
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProyectoEquipoDesarrolloLogic logica;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<EquipoDesarrolloEntity> data = new ArrayList();

    private List<ProyectoEntity> casoData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoDesarrolloEntity.class.getPackage())

                .addPackage(ProyectoEquipoDesarrolloLogic.class.getPackage())
                .addPackage(EquipoDesarrolloPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /*
    *borra la lista y la llena de nuevo antes de cada prueba
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        
        em.createQuery("delete from ProyectoEntity ").executeUpdate();
        em.createQuery("delete from EquipoDesarrolloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {

            ProyectoEntity caso = factory.manufacturePojo(ProyectoEntity.class);
           
            em.persist(caso);
            casoData.add(caso);
            System.out.println(casoData.size());
        }
    
        for (int i = 0; i < 4; i++) {
             
            EquipoDesarrolloEntity entidad = factory.manufacturePojo(EquipoDesarrolloEntity.class);
    
            em.persist(entidad);
            data.add(entidad);
            System.out.println(data.size());
        }
    }

    /**
     * Prueba para asociar un Caso de uso existente a un Representante
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void addEquipoDesarollador() throws BusinessLogicException, Exception {
        System.out.println(data.size());
        EquipoDesarrolloEntity entity = data.get(0);

        System.out.println("aca"+entity.getId());
        ProyectoEntity casoEntity = casoData.get(0);
        EquipoDesarrolloEntity response = logica.addEquipoDesarollador(casoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

   
   

    /**
     * Prueba para consultar un Desarrollador representante
     */
    @Test
    public void getEquipoDesarrollo() throws BusinessLogicException {
        ProyectoEntity entity = casoData.get(0);
        EquipoDesarrolloEntity des=data.get(0);
        logica.addEquipoDesarollador(entity.getId(), des.getId());
        EquipoDesarrolloEntity resultEntity = logica.getEquipoDesarrollo(entity.getId());
        Assert.assertNotNull(resultEntity);
        //Assert.assertEquals(entity.getRepresentanteDelCliente().getId(), resultEntity.getId());
    }


    /**
     * Prueba para remplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void cambiarDesarrolladorTest() throws BusinessLogicException, Exception {
        EquipoDesarrolloEntity entity = data.get(3);
        
        logica.cambiarDesarrollador(casoData.get(1).getId(), entity.getId());
        entity = logica.getEquipoDesarrollo(casoData.get(1).getId());
        Assert.assertTrue(entity.getProyectos().contains(casoData.get(1)));
    }

    

    
    


}

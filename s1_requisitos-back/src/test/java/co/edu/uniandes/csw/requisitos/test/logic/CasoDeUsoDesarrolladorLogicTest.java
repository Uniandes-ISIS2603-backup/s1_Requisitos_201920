/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoDesarrolladorLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.DesarrolladorEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import co.edu.uniandes.csw.requisitos.persistence.DesarrolladorPersistence;
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
public class CasoDeUsoDesarrolladorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CasoDeUsoDesarrolladorLogic logica;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DesarrolladorEntity> data = new ArrayList();

    private List<CasoDeUsoEntity> casoData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DesarrolladorEntity.class.getPackage())
                .addPackage(CasoDeUsoEntity.class.getPackage())
                .addPackage(CasoDeUsoDesarrolladorLogic.class.getPackage())
                .addPackage(CasoDeUsoPersistence.class.getPackage())
                .addPackage(DesarrolladorPersistence.class.getPackage())
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
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
        em.createQuery("delete from CasoDeUsoEntity ").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);
           
            em.persist(caso);
            casoData.add(caso);
   
        }
    
        for (int i = 0; i < 3; i++) {
             
            DesarrolladorEntity entidad = factory.manufacturePojo(DesarrolladorEntity.class);
            if (i==0){
              entidad.setTipoString("RepresentanteDelCliente");
            }
            if (i==1){
                entidad.setTipoString("Responsable");
            }
            em.persist(entidad);
            data.add(entidad);
        }
    }

    /**
     * Prueba para asociar un Caso de uso existente a un Representante
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void addRepresentanteTest() throws BusinessLogicException, Exception {
        
        DesarrolladorEntity entity = data.get(0);

        System.out.println("aca"+entity.getId());
        System.out.println("aca"+entity.getTipoString());
        CasoDeUsoEntity casoEntity = casoData.get(0);
        DesarrolladorEntity response = logica.addRepresentante(entity.getId(), casoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para asociar un Caso de uso existente a un Desarrollador
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void addResponsableTest() throws BusinessLogicException, Exception {
        DesarrolladorEntity entity = data.get(1);
        entity.setTipoString("Responsable");
        CasoDeUsoEntity casoEntity = casoData.get(0);
        DesarrolladorEntity response = logica.addResponsable(entity.getId(), casoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Desarrollador representante
     */
    @Test
    public void getRepresentanteTest() {
        CasoDeUsoEntity entity = casoData.get(0);
        DesarrolladorEntity resultEntity = logica.getRepresentante(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getRepresentanteDelCliente().getId(), resultEntity.getId());
    }

    /**
     * Prueba para consultar un Desarrollador responsable
     */
    @Test
    public void getResponsableTest() {
        CasoDeUsoEntity entity = casoData.get(0);
        DesarrolladorEntity resultEntity = logica.getResponsable(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getResponsable().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void replaceresponsableTest() throws BusinessLogicException, Exception {
        DesarrolladorEntity entity = data.get(0);
        entity.setTipoString("Responsable");
        logica.cambiarResponsable(casoData.get(1).getId(), entity.getId());
        entity = logica.getResponsable(casoData.get(1).getId());
        Assert.assertTrue(entity.getCasosDeUsoResponsable().contains(casoData.get(1)));
    }

    /**
     * Prueba para remplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void replaceRepresentanteTest() throws BusinessLogicException, Exception {
        DesarrolladorEntity entity = data.get(0);
        entity.setTipoString("RepresentanteDelCliente");
        logica.cambiarRepresentante(casoData.get(1).getId(), entity.getId());
        entity = logica.getRepresentante(casoData.get(1).getId());
        Assert.assertTrue(entity.getCasosDeUsoRepresentante().contains(casoData.get(1)));
    }

    /**
     * Prueba para remplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable con tipo equivocado
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void replaceresponsableTestConTipoEquivocado() throws BusinessLogicException, Exception {
        DesarrolladorEntity entity = data.get(0);
        entity.setTipoString("RepresentanteDelCliente");
        logica.cambiarResponsable(casoData.get(1).getId(), entity.getId());
        entity = logica.getResponsable(casoData.get(1).getId());
        Assert.assertTrue(entity.getCasosDeUsoResponsable().contains(casoData.get(1)));
    }

    /**
     * Prueba para remplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable con tipo equivocado
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void replaceRepresentanteTestConTipoEquivocado() throws BusinessLogicException, Exception {
        DesarrolladorEntity entity = data.get(0);
        entity.setTipoString("Responsable");
        logica.cambiarRepresentante(casoData.get(1).getId(), entity.getId());
        entity = logica.getRepresentante(casoData.get(1).getId());
        Assert.assertTrue(entity.getCasosDeUsoRepresentante().contains(casoData.get(1)));
    }

}

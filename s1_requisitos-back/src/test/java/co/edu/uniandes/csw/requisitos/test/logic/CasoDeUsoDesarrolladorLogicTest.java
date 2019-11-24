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

                .addPackage(CasoDeUsoDesarrolladorLogic.class.getPackage())
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
        
        em.createQuery("delete from CasoDeUsoEntity ").executeUpdate();
        em.createQuery("delete from DesarrolladorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 4; i++) {
             
            DesarrolladorEntity entidad = factory.manufacturePojo(DesarrolladorEntity.class);
            if (i==0 || i==2){
              entidad.setTipo(DesarrolladorEntity.TipoDesarrollador.REPRESENTANTEDELCLIENTE);
            }
            if (i==1 || i==3){
                entidad.setTipo(DesarrolladorEntity.TipoDesarrollador.RESPONSABLE);
            }
            em.persist(entidad);
            data.add(entidad);
        }
        for (int i = 0; i < 3; i++) 
        {
            CasoDeUsoEntity caso = factory.manufacturePojo(CasoDeUsoEntity.class);  
            if(i==0)
                caso.setRepresentanteDelCliente(data.get(0));
            else if(i==1)
                caso.setResponsable(data.get(1));
            em.persist(caso);
            casoData.add(caso);
        }
    }

    /**
     * Prueba para asociar un Caso de uso existente a un Representante
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void addRepresentanteTest() throws BusinessLogicException, Exception {
        System.out.println(data.size());
        DesarrolladorEntity entity = data.get(0);

        System.out.println("aca"+entity.getId());
        System.out.println("aca"+entity.getTipo());
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
        CasoDeUsoEntity casoEntity = casoData.get(0);
        DesarrolladorEntity response = logica.addResponsable(entity.getId(), casoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Desarrollador representante
     */
    @Test
    public void getRepresentanteTest() throws BusinessLogicException {
        CasoDeUsoEntity entity = casoData.get(0);
        DesarrolladorEntity des=data.get(0);
        logica.addRepresentante(des.getId(), entity.getId());
        DesarrolladorEntity resultEntity = logica.getRepresentante(entity.getId());
        Assert.assertNotNull(resultEntity);
        //Assert.assertEquals(entity.getRepresentanteDelCliente().getId(), resultEntity.getId());
    }

    /**
     * Prueba para consultar un Desarrollador responsable
     */
    @Test
    public void getResponsableTest() throws BusinessLogicException {
        CasoDeUsoEntity entity = casoData.get(1);
        DesarrolladorEntity des=data.get(1);
        logica.addResponsable(des.getId(), entity.getId());
        
        DesarrolladorEntity resultEntity = logica.getResponsable(entity.getId());
        Assert.assertNotNull(resultEntity);
       // Assert.assertEquals(entity.getResponsable().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void replaceresponsableTest() throws BusinessLogicException, Exception 
    {
        DesarrolladorEntity entity = data.get(3);
        logica.cambiarResponsable(entity.getId(), casoData.get(1).getId());
        entity = logica.getResponsable(casoData.get(1).getId());
        Assert.assertTrue(entity.getCasosDeUsoResponsable().contains(casoData.get(1)));
    }

    /**
     * Prueba para reemplazar las instancias de Casos de uso asociadas a una
     * instancia de Desarrollador responsable
     *
     * @throws co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException
     */
    @Test
    public void replaceRepresentanteTest() throws BusinessLogicException, Exception 
    {
        DesarrolladorEntity entity = data.get(2);
        logica.cambiarRepresentante( entity.getId(), casoData.get(0).getId());
        entity = logica.getRepresentante(casoData.get(0).getId());
        Assert.assertTrue(entity.getCasosDeUsoRepresentante().contains(casoData.get(0)));
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
        logica.cambiarResponsable( entity.getId(), casoData.get(1).getId());
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
        DesarrolladorEntity entity = data.get(1);
        logica.cambiarRepresentante( entity.getId(), casoData.get(1).getId());
        entity = logica.getRepresentante(casoData.get(1).getId());
        Assert.assertTrue(entity.getCasosDeUsoRepresentante().contains(casoData.get(1)));
    }

}

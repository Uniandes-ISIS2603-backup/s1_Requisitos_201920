/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.requisitos.test.logic;

import co.edu.uniandes.csw.requisitos.ejb.CasoDeUsoLogic;
import co.edu.uniandes.csw.requisitos.ejb.ModificacionesLogic;
import co.edu.uniandes.csw.requisitos.entities.CasoDeUsoEntity;
import co.edu.uniandes.csw.requisitos.entities.ModificacionesEntity;
import co.edu.uniandes.csw.requisitos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.requisitos.persistence.CasoDeUsoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Maria Alejandra Escalante
 */
@RunWith(Arquillian.class)
public class CasoDeUsoLogicTest {
    private PodamFactory factory= new PodamFactoryImpl();
    @PersistenceContext
    private EntityManager em;
    @Inject
    private CasoDeUsoLogic casoLogic;
    @Inject
    UserTransaction utx;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CasoDeUsoEntity.class)
                .addClass(CasoDeUsoLogic.class)
                .addClass(CasoDeUsoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    
    @Test
    public void createCasoDeUso()throws BusinessLogicException{
        CasoDeUsoEntity caso= factory.manufacturePojo(CasoDeUsoEntity.class);
        CasoDeUsoEntity result= casoLogic.crearCasoDeUso(caso);
        Assert.assertNotNull(result);
        
        CasoDeUsoEntity entity=em.find(CasoDeUsoEntity.class, result.getId());
        Assert.assertEquals(entity.getDocumentacion(), result.getDocumentacion());
        Assert.assertEquals(entity.getPruebas(), result.getPruebas());
        Assert.assertEquals(entity.getServicios(), result.getServicios());
        Assert.assertEquals(entity.getResponsable(), result.getResponsable());
        
        
    }
    
    
    @Test (expected=BusinessLogicException.class)
    public void crearCasoResponsableNull()throws BusinessLogicException{
        CasoDeUsoEntity caso= factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setResponsable(null);
        CasoDeUsoEntity result= casoLogic.crearCasoDeUso(caso);
                }
    @Test (expected=BusinessLogicException.class)
    public void crearCasoPruebasNull()throws BusinessLogicException{
        CasoDeUsoEntity caso= factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setPruebas(null);
        CasoDeUsoEntity result= casoLogic.crearCasoDeUso(caso);
                }
    
    @Test (expected=BusinessLogicException.class)
    public void crearCasoServiciosNull()throws BusinessLogicException{
        CasoDeUsoEntity caso= factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setServicios(null);
        CasoDeUsoEntity result= casoLogic.crearCasoDeUso(caso);
                }
    @Test (expected=BusinessLogicException.class)
    public void crearCasoDocumentacionNull()throws BusinessLogicException{
        CasoDeUsoEntity caso= factory.manufacturePojo(CasoDeUsoEntity.class);
        caso.setDocumentacion(null);
        CasoDeUsoEntity result= casoLogic.crearCasoDeUso(caso);
                }
}

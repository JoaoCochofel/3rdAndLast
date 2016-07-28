/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import Entities.Inquerito;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author João
 */
@Stateless
public class InqueritoFacade extends AbstractFacade<Inquerito> {

    @PersistenceContext(unitName = "ProjecFinalJavaEEAltranPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InqueritoFacade() {
        super(Inquerito.class);
    }
    
    public void guardaInq(Inquerito inq){
        em.persist(inq);
    }
    
    public long getLast(){
        return (((Long)em.createQuery("select max(i.idInquerito) from Inquerito i").getSingleResult())+1);
    }
    
//    public Inquerito getInqueritoById(Long id){
//        return (Inquerito)(em.createQuery("select i from Inquerito i where i = "+id).getSingleResult());
//    }
}

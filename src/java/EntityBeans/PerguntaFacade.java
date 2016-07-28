/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import Entities.Pergunta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author João
 */
@Stateless
public class PerguntaFacade extends AbstractFacade<Pergunta> {

    @PersistenceContext(unitName = "ProjecFinalJavaEEAltranPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerguntaFacade() {
        super(Pergunta.class);
    }
    
}

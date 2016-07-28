/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Inquerito;
import Entities.Pergunta;
import Entities.Resposta;
import EntityBeans.InqueritoFacade;
import EntityBeans.PerguntaFacade;
import EntityBeans.RespostaFacade;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author João
 */
@ManagedBean
@SessionScoped
public class InqueritosLister implements Serializable{
    private List<Inquerito> inqueritos;
    private Map<Pergunta, List<Resposta>> perguntasRespostas;
    private List<Resposta> respostasDadas;
    long idInq;
    
    private List <Pergunta> lp;
    private List<List> ll;
    
    
    
    
    private Inquerito inquerito;
    
    @EJB
    InqueritoFacade incFac;
    @EJB
    PerguntaFacade perFac;
    @EJB
    RespostaFacade resFac;
    
    public List<Resposta> getRespostasDadas() {
        return respostasDadas;
    }

    public void setRespostasDadas(List<Resposta> respostasDadas) {
        this.respostasDadas = respostasDadas;
    }
    
    public void setInquerito(Inquerito inquerito){
        this.inquerito = inquerito;
    }

    public Map<Pergunta, List<Resposta>> getPerguntasRespostas() {
        return perguntasRespostas;
    }

    
    
    public void setPerguntasRespostas(Map<Pergunta, List<Resposta>> perguntasRespostas) {
        this.perguntasRespostas = perguntasRespostas;
    }

    public List<Pergunta> getLp() {
        return lp;
    }

    public void setLp(List<Pergunta> lp) {
        this.lp = lp;
    }

    public List<List> getLl() {
        return ll;
    }

    public void setLl(List<List> ll) {
        this.ll = ll;
    }
    
    public Inquerito getInquerito(){
        List<Pergunta> tmpPergunta = new ArrayList();
        List<Resposta> tmpResposta = new ArrayList();
        tmpPergunta = perFac.findAll();
        tmpResposta = resFac.findAll();
        List<Resposta> resTmp2;
        perguntasRespostas = new HashMap();
        for (Inquerito inq : inqueritos) {
            if(inq.getIdInquerito()==idInq){
                inquerito = inq;
            }
        }
        //inquerito =  incFac.objectByTextMatch("IDINQUERITO", Long.toString(idInq)).get(0);
        for (Pergunta per : tmpPergunta) {
            resTmp2 = new ArrayList();
            if(per.getInquerito().getIdInquerito() == idInq){
                for (Resposta resposta : tmpResposta) {
                    if(resposta.getPergunta().getIdPergunta().equals(per.getIdPergunta())){
                        resTmp2.add(resposta);
                    }
                }
            }
            perguntasRespostas.put(per, resTmp2);
        }
        map2List();
        return inquerito;
    }
    
    public void map2List(){
        lp = new ArrayList<>(perguntasRespostas.keySet());
        ll = new ArrayList();
        for (Pergunta p : lp) {
            ll.add(perguntasRespostas.get(p));
        }
    }
    
    public String selectInq(long idInq){
        inquerito = getInquerito();
        this.idInq = idInq;
        this.inquerito= getInquerito();
        return("inquerito/View");
    }
    
    public List<Inquerito> getInqueritos() {
        fetchInqueritos();
        return inqueritos;
    }

    public void setInqueritos(List<Inquerito> inqueritos) {
        this.inqueritos = inqueritos;
    }
    
    private void fetchInqueritos(){
        inqueritos = incFac.findAll();
    }
    
    public List<Resposta> getRespostasPergunta(Pergunta p){
        return perguntasRespostas.get(p);
    }
    
    public String deploy(){
        return "yes!";
    }

    
}

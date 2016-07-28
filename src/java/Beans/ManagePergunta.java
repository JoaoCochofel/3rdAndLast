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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author João
 */
@ManagedBean
@SessionScoped
@Named(value = "managePergunta")
public class ManagePergunta implements Serializable {

    private String inquerito;
    private Map<String, List<String>> perguntasRespostas;
    private List<String> respostas;
    private String pergunta;
    private String resposta;

    private Date dataInit;
    private Date dataFim;

    @EJB
    InqueritoFacade incFac;
    @EJB
    PerguntaFacade perFac;
    @EJB
    RespostaFacade resFac;

    /**
     * Creates a new instance of ManagePergunta
     */
    public ManagePergunta() {
        respostas = new ArrayList<>();
        perguntasRespostas = new HashMap<>();
    }

    public Date getDataInit() {
        return dataInit;
    }

    public void setDataInit(Date dataInit) {
        this.dataInit = dataInit;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    

    public String getInquerito() {
        return inquerito;
    }

    public void setInquerito(String inquerito) {
        this.inquerito = inquerito;
    }
    

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Map<String, List<String>> getPerguntasRespostas() {
        return perguntasRespostas;
    }

    public void setPerguntasRespostas(Map<String, List<String>> perguntasRespostas) {
        this.perguntasRespostas = perguntasRespostas;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public void remove(String str) {
        respostas.remove(str);
    }

    public void add() {
        respostas.add("");
    }

    public String addMap() {
        for (String resposta1 : respostas) {
            if (resposta1.isEmpty()) {
                respostas.remove(resposta1);
            }
        }
        perguntasRespostas.put(pergunta, respostas);
        respostas = new ArrayList();
        resposta = "";
        pergunta = "";
        return "inquerito";
    }

    //public void deploy() {
    public String deploy() {
        Inquerito inq = new Inquerito();
        Pergunta per ;
        Resposta res ;

        System.out.println("mapa perg"+perguntasRespostas.keySet());
        System.out.println("mapa perg: "+perguntasRespostas.get(perguntasRespostas.keySet().iterator().next()));
        
        System.out.println("titulo inquerito: "+inquerito);
        inq.setTituloInquerito(inquerito);
        inq.setDataInicio(dataInit.toString());
        inq.setDataFim(dataFim.toString());
        inq.setIdInquerito(incFac.getLast());
        //inq.setIdInquerito(4l);
        System.out.println("inquerito criado");
        
        System.out.println("inqurito:"+inq);
        
        incFac.guardaInq(inq);
        
        //incFac.create(inq);
        
        System.out.println("inquerito guardado na base de dados");
        for (String perguntaq : perguntasRespostas.keySet()) {
            per = new Pergunta();
            per.setInquerito(inq);
            per.setTextoPergunta(perguntaq);
            System.out.println("pergunta "+perguntaq+" criada");
            perFac.create(per);
            System.out.println("pergunta guardada na bd");
            for (String respostaq : perguntasRespostas.get(per.getTextoPergunta())) {
                res=new Resposta();
                res.setTexto(respostaq);
                res.setPergunta(per);
                res.setRespCerta(null); //TODO - implementar possibilidade de ser resposta certa ou nao
                resFac.create(res);
            }
        }

        return "manage";
        
    }
    
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.TipoUtilizador;
import Entities.Utilizador;
import EntityBeans.InqueritoFacade;
import EntityBeans.TipoUtilizadorFacade;
import EntityBeans.UtilizadorFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Altran
 */
@ManagedBean
//@RequestScoped
@ViewScoped

@Named(value = "controlUser")
public class ControlUser implements Serializable {

    @EJB
    private TipoUtilizadorFacade tipoUtilizadorFacade;

    private Utilizador user;
    private long idTipo;

    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    UtilizadorFacade userFac;

    public List<TipoUtilizador> getTipoUtilizador() {
        return tipoUtilizadorFacade.findAll();
    }

    public ControlUser() {
        user = new Utilizador();
    }

    public void setNome(String nome) {
        user.setNome(nome);
    }

    public String getNome() {
        return user.getNome();
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }

    public void setTelefone(long telef) {
        user.setTelefone(telef);
    }

    public String insertUser() {
        user.setTipoUtilizador(tipoUtilizadorFacade.find(this.idTipo));
        userFac.create(user);

        return "fixe";
    }

    public String printUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("mySession").toString();
    }

}

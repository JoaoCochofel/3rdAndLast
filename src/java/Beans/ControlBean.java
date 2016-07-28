/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Algoritmos.SessionUtils;
import Entities.Utilizador;
import EntityBeans.UtilizadorFacade;
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Altran
 */
@ManagedBean
//@RequestScoped
@ViewScoped

@Named(value = "ControlBean")
public class ControlBean implements Serializable {

    @EJB
    UtilizadorFacade userFac;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Size(min = 1, max = 10)
    @NotEmpty
    private String username;

    @Size(min = 1, max = 10)
    @NotEmpty
    private String password;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String checkUser() {
        List<Utilizador> userList;
        userList = userFac.findAll();

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("mySession", this.getUsername());

        for (Utilizador utilizador : userList) {
            if (utilizador.getNome().equals(username) && utilizador.getPassWord().equals(password)) {
                switch (utilizador.getTipoUtilizador().getDesignacao()) {
                    case "Admin":
                        return "adminPage";

                    case "Manager":
                        return "managerPage";

                    case "User":
                        return "userPage";
                }
            }
        }
        return "error";
    }

    public String createUser() {
        return "newUser";
    }

    public String viewUser() {
        return "viewUser";
    }

    public String editUser() {
        return "editUser";
    }

    public String viewUserType() {
        return "viewUserType";
    }

    public String createUserType() {
        return "createUserType";
    }

    public String editUserType() {
        return "editUserType";
    }

    public String printUser() {
        System.out.println(this.getUsername());
        return this.getUsername();

    }

}

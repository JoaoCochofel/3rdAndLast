/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Inquerito;
import EntityBeans.InqueritoFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Altran
 */
@ManagedBean
@ViewScoped
@Named(value = "controlManager")
public class ControlManager implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    static Connection con = null;
    static Statement stmt = null;
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/GesInquerito";
    static final String USER = "app";
    static final String PASS = "app";

    private List<Inquerito> inqueritos;

    @EJB
    InqueritoFacade incFac;

    public String countInq() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {

        con = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "select * from inquerito";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;
        while (rs.next()) {
            i++;
        }

        stmt.close();
        con.close();

        return Integer.toString(i);

    }

    public String getCurrInq() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        Date date = new Date();
        String data = dateFormat.format(date);
        con = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "select * from inquerito where datafim > '" + data + "'";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;
        while (rs.next()) {
            i++;
        }

        stmt.close();
        con.close();

        return Integer.toString(i);
    }

    public List<Inquerito> getInqueritos() {
        fetchInqueritos();
        return inqueritos;
    }

    private void fetchInqueritos() {
        inqueritos = incFac.findAll();
    }

}

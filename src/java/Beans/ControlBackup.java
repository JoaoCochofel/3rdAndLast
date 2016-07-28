/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Algoritmos.NewClass;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Altran
 */
@ManagedBean
@ViewScoped
@Named(value = "controlBackup")

public class ControlBackup implements Serializable{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @NotEmpty
    private String tipoTempo;

    @NotEmpty
    private String tempo;

    /**
     * @return the tipoTempo
     */
    public String getTipoTempo() {
        return tipoTempo;
    }

    /**
     * @param tipoTempo the tipoTempo to set
     */
    public void setTipoTempo(String tipoTempo) {
        this.tipoTempo = tipoTempo;
    }

    /**
     * @return the tempo
     */
    public String getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    //public long myLong = Integer.parseInt(this.getTempo()) * 60 * 1000;
    public String teste;

    public String makeBackup() {

        long myLong = Integer.parseInt(this.getTempo()) * 60 * 1000;
        // String teste;

        final ControlBackup test = new ControlBackup();

        NewClass nc = new NewClass();
        Timer timer = new Timer();

        timer.schedule(
                new TimerTask() {

            @Override
            public void run() {
                try {
                    nc.guardar();
                } catch (IOException ex) {
                    Logger.getLogger(ControlBackup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ControlBackup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControlBackup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, myLong);

        return "sucesso";
    }

}

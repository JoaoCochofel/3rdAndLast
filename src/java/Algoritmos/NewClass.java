package Algoritmos;

import Entities.Inquerito;
import Entities.Pergunta;
import Entities.RespInquerito;
import Entities.Resposta;
import Entities.TipoUtilizador;
import Entities.Utilizador;
import Entities.UtilizadorInquerito;
import EntityBeans.InqueritoFacade;
import EntityBeans.PerguntaFacade;
import EntityBeans.RespInqueritoFacade;
import EntityBeans.RespostaFacade;
import EntityBeans.TipoUtilizadorFacade;
import EntityBeans.UtilizadorFacade;
import EntityBeans.UtilizadorInqueritoFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Altran
 */
public class NewClass {

    @EJB
    InqueritoFacade inqFac;
    @EJB
    PerguntaFacade perguntaFac;
    @EJB
    RespInqueritoFacade respInqFac;
    @EJB
    RespostaFacade respFac;
    @EJB
    TipoUtilizadorFacade tipoUserFac;
    @EJB
    UtilizadorFacade userFac;
    @EJB
    UtilizadorInqueritoFacade userInqFac;

    public void guardar() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {

        //String sucess = "";
        FileOutputStream fout = new FileOutputStream("clientesBin.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        List<Inquerito> inqList = null;
        List<Pergunta> pergList = null;
        List<RespInquerito> respInqList = null;
        List<Resposta> respList = null;
        List<TipoUtilizador> tipoUserList = null;
        List<Utilizador> userList = null;
        List<UtilizadorInquerito> userInqList = null;

        try {
            inqList = inqFac.findAll();
            pergList = perguntaFac.findAll();
            respInqList = respInqFac.findAll();
            respList = respFac.findAll();
            tipoUserList = tipoUserFac.findAll();
            userList = userFac.findAll();
            userInqList = userInqFac.findAll();

            oos.writeObject(inqList);
            oos.writeObject(pergList);
            oos.writeObject(respInqList);
            oos.writeObject(respList);
            oos.writeObject(tipoUserList);
            oos.writeObject(userList);
            oos.writeObject(userInqList);

        } catch (NullPointerException ne) {
            oos.writeObject("");
        } finally {
            //sucess = "sucesso";
        }

       // return sucess;
    }

}

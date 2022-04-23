/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Richieste;

import ha.admin.ConnessioneBD;
import ha.admin.SERVER;
import Utenti.Utenti;
import Utenti.Utenti.Utente;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author MAALFING
 */
public class Richieste {

    private String String(int aInt) {
        return Integer.toString(aInt);
    }

    public class Richiesta {

        public String Mittente, testo, destinatario;
        public boolean attiva;
        public int iD;

        public Richiesta(String Mittente, String testo, String destinatario, int iD) {
            this.Mittente = Mittente;
            this.testo = testo;
            this.destinatario = destinatario;
            this.iD = iD;
        }

    }

    Connection con = null;
    public List<Richiesta> vett;

    public Richieste() {
        this.vett = new ArrayList();

    }

    public void addRic(Richiesta R) {
        vett.add(R);
    }

    public void ElRic(int iD) {
        vett.remove(iD);
    }

    public Richiesta getByiD(int iD) {
        return vett.get(iD);
    }

    public List<Richiesta> getList() {
        return vett;
    }

    public boolean Riempi() {
        vett = new ArrayList<Richiesta>();
        boolean exists = false;
//        Statement stmt = null;
//        Connection con = null;
        JSONArray jsarray = null;

        try {
            jsarray = SERVER.readJsonaFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/request.php?tipo=R");
            
            if (jsarray.getJSONObject(0).get("Esito").equals("V")) {               
               exists=true;
                for (int i = 1; i < jsarray.length(); i++) {
                    vett.add(new Richiesta(jsarray.getJSONObject(i).getString("Mittente"), jsarray.getJSONObject(i).getString("Testo"), jsarray.getJSONObject(i).getString("Destinatario"), jsarray.getJSONObject(i).getInt("iD")));
                    
                }
            } else if (jsarray.getJSONObject(0).get("Esito").equals("N")) {
                System.out.println("Non c’è niente in rieschieste");
            }

        } catch (IOException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            con = ConnessioneBD.con();
//            stmt = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String sql = "SELECT * FROM `Richieste`;";
//        try {
//            ResultSet rs = stmt.executeQuery(sql);
//            if (!rs.next()) {
//                exists = false;
//                System.out.println("Non c'è niente in richieste");
//            } else {
//                exists = true;
//                vett.add(new Richiesta(Integer.toString(rs.getInt("Mittente")), rs.getString("Testo"), Integer.toString(rs.getInt("Destinatario")), rs.getInt("iD")));
//                System.out.println("riesco a riempire le richieste");
//            }
//            while (rs.next()) {
//                System.out.println("riesco a riempire le richieste");
//                vett.add(new Richiesta(Integer.toString(rs.getInt("Mittente")), rs.getString("Testo"), Integer.toString(rs.getInt("Destinatario")), rs.getInt("iD")));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return exists;
    }

    public Utente RichUtente(int iD) {
//        Statement stmt = null;
//        con=ConnessioneBD.con();
        Utenti Us = new Utenti();
        Utente U = Us.new Utente(-1, "", "", "", "", "", "");
        JSONObject json = null;

        try {
            json = SERVER.readJsonFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/richUtente.php?iD=" + iD);

            return Us.new Utente(json.getInt("iD"), json.getString("Nome"), json.getString("Cognome"), json.getString("nome_utente"), json.getString("e-mail"), json.getString("Tipo"), json.getString("Telefono"));
        } catch (IOException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            stmt = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//                    String sql = "SELECT * FROM `utenti` WHERE `iD`="+ Integer.toString(iD)+";";
//        try {
//            ResultSet rs = stmt.executeQuery(sql);
//            if (rs.next()) {
//                return new Utenti(rs.getInt("iD"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("nome_utente"), rs.getString("e-mail"), rs.getString("Tipo"), rs.getString("Telefono"));
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return U;
    }

//    public List<Utenti> ListAdmin() {
//        Statement stmt = null;
//        con = ConnessioneBD.con();
//        Utenti Us = new Utenti();
//        Utente U = Us.new Utente(-1, "", "", "", "", "", "");
//        List<Utenti> vett = null;
//        try {
//            stmt = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
////        String sql = "SELECT * FROM `utenti`;";
////        try {
////            ResultSet rs = stmt.executeQuery(sql);
////            if (rs.next()) {
////                if (rs.getString("Tipo").equals("admin")) {
////                    vett.add(Us.new Utente(rs.getInt("iD"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("nome_utente"), rs.getString("e-mail"), rs.getString("Tipo"), rs.getString("Telefono")));
////                }
////            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return vett;
//    }

}

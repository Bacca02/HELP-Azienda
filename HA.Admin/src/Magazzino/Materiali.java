/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazzino;

import ha.admin.SERVER;
import Richieste.Richieste;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import javax.imageio.*;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author MAALFING
 */
public class Materiali {

    public class Materiale {

        public String Materiale, Marca, Posizione;
        public int iD, Quantita;
        public String IPath;
        public Image I;

        public Materiale(String Materiale, String Marca, String Posizione, int iD, String IPath, int Quantita) {
            this.Materiale = Materiale;
            this.Marca = Marca;
            this.Posizione = Posizione;
            this.iD = iD;
            this.IPath = IPath;
            this.Quantita = Quantita;
        }
    }
    Connection con = null;
    public List<Materiale> vett;

    public Materiali() {
        this.vett = new ArrayList();

    }

    public void addMat(Materiale M) {
        vett.add(M);
    }

    public void ElMat(int iD) {
        vett.remove(iD);
    }

    public Materiale getByiD(int iD) {
        return vett.get(iD);
    }

    public List<Materiale> getList() {
        return vett;
    }
    
    public String [] getNomi(){
        String[] vettN = new String[vett.size()];
        System.out.println(vett.size());
        for (int i = 0; i < vett.size(); i++) {
            vettN[i]=vett.get(i).Materiale;
        }
        
        return vettN;
    }

    public boolean Riempi() {
        boolean exists = false;
//        Statement stmt = null;
//        Connection con = null;
        JSONArray jsarray = null;

        try {
            jsarray = SERVER.readJsonaFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/request.php?tipo=M");
            
            if (jsarray.getJSONObject(0).get("Esito").equals("V")) {               
               exists=true;
                for (int i = 1; i < jsarray.length(); i++) {
                    vett.add(new Materiale(jsarray.getJSONObject(i).getString("Materiale"), jsarray.getJSONObject(i).getString("Marca"), jsarray.getJSONObject(i).getString("Posizione"), jsarray.getJSONObject(i).getInt("iD"),jsarray.getJSONObject(i).getString("Path"),jsarray.getJSONObject(i).getInt("quantita")));
                    
                }
            } else if (jsarray.getJSONObject(0).get("Esito").equals("F")) {
                System.out.println("Non c’è niente in rieschieste");
            }

        } catch (IOException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Statement stmt = null;
//        Connection con = null;
//        try {
//            con = ConnessioneBD.con();
//            stmt = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(Materiali.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String sql = "SELECT * FROM `materiale`;";
//        try {
//            ResultSet rs = stmt.executeQuery(sql);
//            if (!rs.next()) {
//                exists = false;
//                System.out.println("Non c'è niente in magazzino");
//            } else {
//                exists = true;
//                vett.add(new Materiale(rs.getString("Materiale"), rs.getString("Marca"), rs.getString("Posizione"), rs.getInt("iD"), rs.getString("Path"), rs.getInt("quantita")));
//                System.out.println("riesco a riempire il magazzino");
//            }
//            while (rs.next()) {
//                System.out.println("riesco a riempire il magazzino");
//                vett.add(new Materiale(rs.getString("Materiale"), rs.getString("Marca"), rs.getString("Posizione"), rs.getInt("iD"), rs.getString("Path"), rs.getInt("quantita")));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Materiali.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return exists;
    }

}

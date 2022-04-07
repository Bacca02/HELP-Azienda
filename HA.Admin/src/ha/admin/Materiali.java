/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import javax.imageio.*;

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

    public boolean Riempi() {
        boolean exists = false;
        Statement stmt = null;
        Connection con = null;
        try {
            con = ConnessioneBD.con();
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Materiali.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * FROM `materiale`;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                exists = false;
                System.out.println("Non c'è niente in magazzino");
            } else {
                exists = true;
                vett.add(new Materiale(rs.getString("Materiale"), rs.getString("Marca"), rs.getString("Posizione"), rs.getInt("iD"), rs.getString("Path"), rs.getInt("quantita")));
                System.out.println("riesco a riempire il magazzino");
            }
            while (rs.next()) {
                System.out.println("riesco a riempire il magazzino");
                vett.add(new Materiale(rs.getString("Materiale"), rs.getString("Marca"), rs.getString("Posizione"), rs.getInt("iD"), rs.getString("Path"), rs.getInt("quantita")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Materiali.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ha.admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        boolean exists=false;
        Statement stmt = null;
        Connection con = null;
        try {
            con=ConnessioneBD.con();
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * FROM `Richieste`;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                exists=false;
                System.out.println("Non c'è niente");
            }
            else{
                exists=true;
                vett.add(new Richiesta(Integer.toString(rs.getInt("Mittente")),rs.getString("Testo"),Integer.toString(rs.getInt("Destinatario")),rs.getInt("iD")));
                System.out.println("riesco a riempire");
            }
            while(rs.next()){
                System.out.println("riesco a riempire");
                vett.add(new Richiesta(Integer.toString(rs.getInt("Mittente")),rs.getString("Testo"),Integer.toString(rs.getInt("Destinatario")),rs.getInt("iD")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
    
    
    public Utente RichUtente(int iD){
        Statement stmt = null;
        con=ConnessioneBD.con();
        Utente U = new Utente(-1, "", "", "", "", "", "");
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }

                    String sql = "SELECT * FROM `utenti` WHERE `iD`="+ Integer.toString(iD)+";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Utente(rs.getInt("iD"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("nome_utente"), rs.getString("e-mail"), rs.getString("Tipo"), rs.getString("Telefono"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U;
    }
    
    public List<Utente> ListAdmin(){
        Statement stmt = null;
        con=ConnessioneBD.con();
        Utente U = new Utente(-1, "", "", "", "", "", "");
        List<Utente> vett= null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }

                    String sql = "SELECT * FROM `utenti`;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("Tipo").equals("admin")) {
                    vett.add(new Utente(rs.getInt("iD"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("nome_utente"), rs.getString("e-mail"), rs.getString("Tipo"), rs.getString("Telefono")));
                }                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vett;
    }
    

}

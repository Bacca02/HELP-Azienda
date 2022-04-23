/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utenti;

import ha.admin.SERVER;
import java.io.IOException;
import java.lang.System.*;
import java.lang.System.Logger.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author MAALFING
 */
public class Utenti {

    public List<Utente> vett;
    
    public Utenti(){
        vett= new ArrayList();
        
    }
    public class Utente {

        public int iD;
        public String nome, cognome, username,Password, email, tipo, telefono;

        public Utente(int iD, String nome, String cognome, String email, String username, String Password, String tipo, String telefono) {
            this.iD = iD;
            this.nome = nome;
            this.cognome = cognome;
            this.username = username;
            this.email = email;
            this.Password=Password;
            this.tipo = tipo;
            this.telefono = telefono;
        }
        
        public Utente(int iD, String nome, String cognome, String email, String username, String tipo, String telefono) {
            this.iD = iD;
            this.nome = nome;
            this.cognome = cognome;
            this.username = username;
            this.email = email;
            
            this.tipo = tipo;
            this.telefono = telefono;
        }

    }
    
    public void addUt(Utente U) {
        vett.add(U);
    }

    public void ElUt(int iD) {
        vett.remove(iD);
    }

    public Utente getByiD(int iD) {
        return vett.get(iD);
    }

    public List<Utente> getList() {
        return vett;
    }
    
    public boolean Riempi() {
        vett=new ArrayList<Utente>();
        boolean exists = false;
//        Statement stmt = null;
//        Connection con = null;
        JSONArray jsarray = null;

        try {
            jsarray = SERVER.readJsonaFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/request.php?tipo=U");
            
            if (jsarray.getJSONObject(0).get("Esito").equals("V")) {               
               exists=true;
                for (int i = 1; i < jsarray.length(); i++) {
                    vett.add(new Utente(jsarray.getJSONObject(i).getInt("iD"), jsarray.getJSONObject(i).getString("Nome"), jsarray.getJSONObject(i).getString("Cognome"), jsarray.getJSONObject(i).getString("e-mail"),jsarray.getJSONObject(i).getString("nome_utente"),jsarray.getJSONObject(i).getString("Tipo"),jsarray.getJSONObject(i).getString("Telefono")));
                    
                }
            } else if (jsarray.getJSONObject(0).get("Esito").equals("F")) {
                System.out.println("Non c’è niente in utenti");
            }

        } catch (IOException ex) {
            //Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
           // Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return exists;
    }

}

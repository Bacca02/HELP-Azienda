/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fornitori;

import Magazzino.Materiali;
import Richieste.Richieste;
import ha.admin.SERVER;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author MAALFING
 */
public class Fornitori {

    public List<Fornitore> vett;

    public class Fornitore {

        public int iD;
        public String nome, telefono, indirizzo;

        public Fornitore(int iD, String nome, String telefono, String indirizzo) {
            this.iD = iD;
            this.nome = nome;
            this.telefono = telefono;
            this.indirizzo = indirizzo;
        }

    }

    public Fornitori() {
        vett = new ArrayList();

    }

    public void AF(Fornitore F) {
        vett.add(F);

    }

    public List<Fornitore> getVett() {
        return vett;
    }
    
    
    public String getNomebyiD(int iD){
        for (int i = 0; i < vett.size(); i++) {
            if (vett.get(i).iD==iD) {
                return vett.get(i).nome;
            }
        }
        return "";
    }
    
    public String [] getNomi(){
        String[] vettN = new String[vett.size()];
        
        for (int i = 0; i < vett.size(); i++) {
            vettN[i]=vett.get(i).nome;
        }
        
        return vettN;
    }
    
    
    String getNF(int iD) {
        for (int i = 0; i < vett.size(); i++) {
            if (vett.get(i).iD==iD) {
                return vett.get(i).nome;
            }
        }
        return "";
    }
    
    public void ElFor(int iD) {
        vett.remove(iD);
    }
    public boolean Riempi() {
        vett = new ArrayList<Fornitore>();
        boolean exists = false;
//        Statement stmt = null;
//        Connection con = null;
        JSONArray jsarray = null;

        try {
            jsarray = SERVER.readJsonaFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/request.php?tipo=F");
            
            if (jsarray.getJSONObject(0).get("Esito").equals("V")) {               
               exists=true;
                for (int i = 1; i < jsarray.length(); i++) {
                    vett.add(new Fornitore(jsarray.getJSONObject(i).getInt("iD"), jsarray.getJSONObject(i).getString("Nome"), jsarray.getJSONObject(i).getString("Telefono"), jsarray.getJSONObject(i).getString("Indirizzo")));
                    
                }
            } else if (jsarray.getJSONObject(0).get("Esito").equals("F")) {
                System.out.println("Non c’è niente in fornitori");
            }

        } catch (IOException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Richieste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

}

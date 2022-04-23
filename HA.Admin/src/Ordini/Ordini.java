/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordini;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import ha.admin.SERVER;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.json.*;

/**
 *
 * @author MAALFING
 */
public class Ordini {
    public class Ordine {
        public int iDOrdine, iDFornitore, iDMateriale, qnt;        
        public LocalDateTime data;

        public Ordine(int iDOrdine, int iDFornitore, int iDMateriale, int qnt, LocalDateTime data) {
            this.iDOrdine = iDOrdine;
            this.iDFornitore = iDFornitore;
            this.iDMateriale = iDMateriale;
            this.qnt = qnt;
            this.data = data;
        }
        
        
        
        
        
    }
    
    
    public List<Ordine> vett;
    
    public Ordini(){
        vett= new ArrayList();
        
    }
    
    
    public void addOr(Ordine O) {
        vett.add(O);
    }

    public void ElUt(int iD) {
        vett.remove(iD);
    }

    public Ordine getByiD(int iD) {
        return vett.get(iD);
    }

    public List<Ordine> getList() {
        return vett;
    }
    
    public boolean Riempi() {
        vett = new ArrayList<Ordine>();
        boolean exists = false;
//        Statement stmt = null;
//        Connection con = null;
        JSONArray jsarray = null;

        try {
            jsarray = SERVER.readJsonaFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/request.php?tipo=O");
            
            if (jsarray.getJSONObject(0).get("Esito").equals("V")) {               
               exists=true;
                for (int i = 1; i < jsarray.length(); i++) {
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    vett.add(new Ordine(jsarray.getJSONObject(i).getInt("idOrdine"), jsarray.getJSONObject(i).getInt("idFornitore"), jsarray.getJSONObject(i).getInt("idMateriale"), jsarray.getJSONObject(i).getInt("Quantita"), LocalDateTime.parse((jsarray.getJSONObject(i).getString("DatOrdine")),form)));
                    
                }
            } else if (jsarray.getJSONObject(0).get("Esito").equals("F")) {
                System.out.println("Non c’è niente in Ordinazioni");
            }

        } catch (IOException ex) {
            //Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
           // Logger.getLogger(Utenti.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return exists;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import Magazzino.Materiali;
import Utenti.Utenti;
import Utenti.Utenti.Utente;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.*;

/**
 *
 * @author MAALFING
 */
public class SERVER {
    
    
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
    
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
    
    
    public static JSONArray readJsonaFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONArray json = new JSONArray(jsonText);
      return json;
    } finally {
      is.close();
    }
  } 
    
    public synchronized static String POSTData(String URL, String Param) throws IOException, InterruptedException {
        HttpURLConnection con = null;                
        byte[] postData = Param.getBytes(StandardCharsets.UTF_8);

        try {

            URL myurl = new URL(URL);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString();

        } finally {

            con.disconnect();
        }
    

    }
    
    
    public static Utente getUtenteByiD(int iD) {

        JSONObject jsarray = null;

        try {
            jsarray = SERVER.readJsonFromUrl("http://jeanmonnetlucamarco.altervista.org/HPAzienda/richUtentebyiD.php?iD="+iD);
            //System.out.println(jsarray);
            if (jsarray.get("Esito").equals("V")) {
                for (int i = 1; i < jsarray.length(); i++) {
                    return new Utenti().new Utente(jsarray.getInt("iD"), jsarray.getString("Nome"), jsarray.getString("Cognome"), jsarray.getString("e-mail"), jsarray.getString("nome_utente"), jsarray.getString("Tipo"), jsarray.getString("Telefono"));

                }
            } else if (jsarray.get("Esito").equals("F")) {
                System.out.println("Non c’è niente in utenti");
            }

        } catch (IOException ex) {
            System.out.println(ex);
        } catch (JSONException ex) {
             System.out.println(ex);
        }
        return new Utenti().new Utente(-1,"","","","","","");
    }
    
    
    public static  String getMd5(String input)
    {
        try {
  
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

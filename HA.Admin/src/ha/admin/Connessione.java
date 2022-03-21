/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Bondesan_Luca
 */
public class Connessione {
    public static Connection con()
    {
                Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hpa-utenti","root","");
            JOptionPane.showMessageDialog(null, "Connected");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}

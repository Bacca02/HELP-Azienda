/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova.connessione.database;

import java.sql.*;

/**
 *
 * @author soggiu_marco
 */
public class ProvaConnessioneDatabase {

    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection con = null;
    Statement stmt = null;
    /**
     * @param args the command line arguments
     */
    
    public void login(){
        con=ConnessioneBD.con();
    }
    
    public void main(String[] args) throws ClassNotFoundException {
        System.out.println("Inizio");
        login();
        
    }
    
}

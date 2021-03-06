
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.SwingConstants;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author baccaglini_christian
 */
public class HALogin extends JFrame {

    HALogin login = this;
    //Statement stmt = null;
    //Connection con = null;
    JTextField textField_utente;
    JPasswordField passwordField_password;
    JButton start_button;

    public static void main(String[] args) {
        HALogin login = new HALogin();
        //IUtente i = new IUtente();
    }

    @SuppressWarnings("unchecked")

    public HALogin() {
        //Elimina i bordi
        setUndecorated(true);
        //Imposto la finestra al massimo
        setSize(350, 500);
        this.setLocationRelativeTo(null);
        //setExtendedState(HAAdmin.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//--------------------------------------------------------------------------------------  
        //Colore di sfondo
        Color c = new Color(211, 245, 255);
        this.getContentPane().setBackground(c);
        //Bordo
        c = new Color(150, 245, 255);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, c));
        this.setLayout(null);
//--------------------------------------------------------------------------------------
        start_button = new JButton();

        try {
             BufferedImage img = ImageIO.read(new File("img/start.png"));
            start_button.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HALogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        start_button.setFocusable(false);
        start_button.setBounds(this.getWidth() / 2 - 55, 400, 100, 39);
        start_button.setOpaque(false);
        start_button.setContentAreaFilled(false);
        start_button.setBorderPainted(false);
        start_button.setVisible(true);
        start_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(start_button);
//--------------------------------------------------------------------------------------

        JButton exit = new JButton();
        exit.setFocusable(false);
        try {
           BufferedImage img = ImageIO.read(new File("img/x.png"));
            exit.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        exit.setBounds(280, 5, 55, 40);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setVisible(true);
        this.add(exit);

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("E dai");
                System.exit(0);
            }
        });
//--------------------------------------------------------------------------------------
        Font f = new Font("Verdana", Font.BOLD, 18);
        Font f1 = new Font("Verdana", Font.PLAIN, 20);

        //Label visualizzazione utente
        JLabel label_utente = new JLabel("Utente", SwingConstants.CENTER);
        JLabel label_password = new JLabel("Password", SwingConstants.CENTER);
        label_utente.setBounds(100, 150, 135, 30);
        label_password.setBounds(100, 250, 135, 30);
        label_utente.setVisible(true);
        label_password.setVisible(true);
        label_utente.setFont(f);
        label_password.setFont(f);
        this.add(label_utente);
        this.add(label_password);
//--------------------------------------------------------------------------------------
        //textPane visualizzazione utente
        textField_utente = new JTextField();
        passwordField_password = new JPasswordField();

        textField_utente.setHorizontalAlignment(textField_utente.CENTER);
        passwordField_password.setHorizontalAlignment(passwordField_password.CENTER);

        textField_utente.setBounds(60, 180, 215, 30);
        passwordField_password.setBounds(60, 280, 215, 30);

        textField_utente.setVisible(true);
        passwordField_password.setVisible(true);

        textField_utente.setFont(f1);
        passwordField_password.setFont(f1);

        textField_utente.setOpaque(true);
        passwordField_password.setOpaque(true);

        textField_utente.setBorder(null);
        passwordField_password.setBorder(null);

        this.add(textField_utente);
        this.add(passwordField_password);

        //-----------------------------------------------------------------------------------
        //EVENTO D'INVIO
        KeyListener KL;
        KL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    Accesso();

                }
            }

            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        passwordField_password.addKeyListener(KL);
        textField_utente.addKeyListener(KL);

//--------------------------------------------------------------------------------------
        start_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Accesso();
            }
        });

        this.setVisible(true);
    }

    public void Accesso() {
        Connection con = null;
        Caricamento C = new Caricamento(this);
        C.start();
        //prendo il valore della text box e lo salvo 
        String value = textField_utente.getText();
        boolean corretto = false, admin = false, trovato = false;

        //try {
        //con = ConnessioneBD.con();
        //stmt = con.createStatement();
        //String sql = "SELECT * FROM `utenti`;";
        JSONObject json = null;
        int iD = -1;

        try {
            json = new JSONObject(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/accesso.php", "name=" + value + "&pass=" + SERVER.getMd5(passwordField_password.getText())));
            System.out.println(json);
            String esito = json.getString("Esito");

            if (esito.equals("V")) {
                String tipo = json.getString("Tipo");
                iD = json.getInt("iD");
                corretto = true;
                if (tipo.equals("A")) {
                    admin = true;
                }
            } else {
                String motivo = json.getString("Motivo");
                JOptionPane.showMessageDialog(null, motivo, "ERRORE LOGIN", 0);
                this.setCursor(0);
                start_button.setCursor(new Cursor(0));
            }

        } catch (IOException ex) {
            Logger.getLogger(HALogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(HALogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HALogin.class.getName()).log(Level.SEVERE, null, ex);
        }

//            try {
//                ResultSet rs = stmt.executeQuery(sql);
//
//                while (rs.next() || !trovato) {
//                    String nome = rs.getString("Nome");
//                    System.out.println(rs.getString("Nome"));
//                    if (nome.equals(value)) {
//                        trovato = true;
//                        System.out.println(value + " esiste");
//                        System.out.println(rs.getString("Password"));
//                        if (passwordField_password.getText().equals(rs.getString("Password"))) {
//                            corretto = true;
//                            if (rs.getString("Tipo").equals("admin")) {
//                                admin = true;
//                            }
//
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Password errata");
//                        }
//
//                    } else {
//                        System.out.println(value + " non esiste");
//                        //JOptionPane.showMessageDialog(null, "Utente inesistente");
//                    }
//                }
//                if (!corretto && !trovato) {
//                    JOptionPane.showMessageDialog(null, "Utente inesistente");
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(HALogin.class
//                        .getName()).log(Level.SEVERE, null, ex);
//            }
        if (corretto && admin) {
            HAAdmin fStartAdmin = new HAAdmin(Integer.toString(iD));
            fStartAdmin.setVisible(true);
            login.setVisible(false);
        } else if (corretto) {
            HAUtente fStartUtente = new HAUtente(Integer.toString(iD));
            fStartUtente.setVisible(true);
            login.setVisible(false);
        }

        System.out.println(SERVER.getMd5(passwordField_password.getText()) + "|" + textField_utente.getText() + "|");
        System.out.println("Nome utente: " + textField_utente.getText());
//                    if (textField_utente.getText().equals("user")) {
//                        HAUtente fStartUtente = new HAUtente(textField_utente.getText());
//                        fStartUtente.setVisible(true);
//                        login.setVisible(false);
//                    } else {
//                        System.out.println("prova");
//                        HAAdmin fStartAdmin = new HAAdmin(textField_utente.getText());
//                        fStartAdmin.setVisible(true);
//                        login.setVisible(false);
//                    }
//        } catch (SQLException ex) {
//            Logger.getLogger(HALogin.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public class Caricamento extends Thread {

        public HALogin L;

        public Caricamento(HALogin L) {
            this.L = L;
        }

        @Override
        public void run() {
            L.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            L.start_button.setCursor(new Cursor(Cursor.WAIT_CURSOR));

        }

    }

}

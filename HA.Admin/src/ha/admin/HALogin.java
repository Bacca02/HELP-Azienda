/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author baccaglini_christian
 */
public class HALogin extends JFrame {

    HALogin login = this;
    Statement stmt = null;
    Connection con = null;
    JTextField textField_utente;
    JPasswordField passwordField_password;
    Image img;

    public static void main(String[] args) {
        HALogin login = new HALogin();

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
        JButton start_button = new JButton();

        try {
            img = ImageIO.read(getClass().getResource("img/start.png"));
            start_button.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HALogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        start_button.setFocusable(false);
        start_button.setBounds(this.getWidth()/2-55, 400, 100, 39);
        start_button.setOpaque(false);
        start_button.setContentAreaFilled(false);
        start_button.setBorderPainted(false);
        start_button.setVisible(true);
        this.add(start_button);
//--------------------------------------------------------------------------------------
        JButton exit = new JButton();
        exit.setFocusable(false);
        try {
            img = ImageIO.read(getClass().getResource("img/x.png"));
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
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        //Label visualizzazione utente
        JLabel label_utente = new JLabel("Utente", SwingConstants.CENTER);
        JLabel label_password = new JLabel("Password", SwingConstants.CENTER);
        label_utente.setBounds(100, 150, 135, 30);
        label_password.setBounds(100, 250, 135, 30);
        label_utente.setVisible(true);
        label_password.setVisible(true);
        label_utente.setFont(font1);
        label_password.setFont(font1);
        this.add(label_utente);
        this.add(label_password);
//--------------------------------------------------------------------------------------
        //textPane visualizzazione utente
        textField_utente = new JTextField("Marco");
        passwordField_password = new JPasswordField("1234");

        textField_utente.setHorizontalAlignment(textField_utente.CENTER);
        passwordField_password.setHorizontalAlignment(passwordField_password.CENTER);

        textField_utente.setBounds(60, 180, 215, 30);
        passwordField_password.setBounds(60, 280, 215, 30);

        textField_utente.setVisible(true);
        passwordField_password.setVisible(true);

        textField_utente.setFont(font1);
        passwordField_password.setFont(font1);

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

        //prendo il valore della text box e lo salvo 
        String value = textField_utente.getText();
        boolean corretto = false, admin = false, trovato = false;

        try {
            con = ConnessioneBD.con();
            stmt = con.createStatement();

            String sql = "SELECT * FROM `utenti`;";
            try {
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next() || !trovato) {
                    String nome = rs.getString("Nome");
                    System.out.println(rs.getString("Nome"));
                    if (nome.equals(value)) {
                        trovato = true;
                        System.out.println(value + " esiste");
                        System.out.println(rs.getString("Password"));
                        if (passwordField_password.getText().equals(rs.getString("Password"))) {
                            corretto = true;
                            if (rs.getString("Tipo").equals("admin")) {
                                admin = true;
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Password errata");
                        }

                    } else {
                        System.out.println(value + " non esiste");
                        //JOptionPane.showMessageDialog(null, "Utente inesistente");
                    }
                }
                if (!corretto && !trovato) {
                    JOptionPane.showMessageDialog(null, "Utente inesistente");
                }

            } catch (SQLException ex) {
                Logger.getLogger(HALogin.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            if (corretto && admin) {
                HAAdmin fStartAdmin = new HAAdmin(textField_utente.getText());
                fStartAdmin.setVisible(true);
                login.setVisible(false);
            } else if (corretto) {
                HAUtente fStartUtente = new HAUtente(textField_utente.getText());
                fStartUtente.setVisible(true);
                login.setVisible(false);
            }

            System.out.println(passwordField_password.getPassword() + "|" + textField_utente.getText() + "|");
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
        } catch (SQLException ex) {
            Logger.getLogger(HALogin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}

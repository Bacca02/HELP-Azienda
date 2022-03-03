/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author baccaglini_christian
 */
public class HALogin extends JFrame {

    HALogin login = this;

    public static void main(String[] args) {
        HALogin login = new HALogin();

    }

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
        JButton start_button = new JButton("Start");
        start_button.setFocusable(false);
        start_button.setBounds(125, 400, 85, 30);
        start_button.setVisible(true);
        this.add(start_button);
//--------------------------------------------------------------------------------------
        JButton exit = new JButton();
        exit.setFocusable(false);
        try {
            Image img = ImageIO.read(getClass().getResource("img/x.png"));
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
        JTextField textField_utente = new JTextField();
        JPasswordField passwordField_password = new JPasswordField();

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

//--------------------------------------------------------------------------------------
        start_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(passwordField_password.getPassword() + "|" + textField_utente.getText()+"|");
                if (textField_utente.getText() == "ciao") {
                    HAUtente fStartUtente = new HAUtente(textField_utente.getText());
                    fStartUtente.setVisible(true);
                    login.setVisible(false);
                } else {
                    System.out.println("prova");
//                    HAAdmin fStartAdmin = new HAAdmin(textField_utente.getText());
//                    fStartAdmin.setVisible(true);
//                    login.setVisible(false);
                }

            }
        });
        this.setVisible(true);

    }

}

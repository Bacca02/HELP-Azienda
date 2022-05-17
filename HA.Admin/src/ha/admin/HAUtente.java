/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import Magazzino.JMagazzino;
import Richieste.Richieste;
import Magazzino.Materiali;
import Richieste.JRichieste;
import Richieste.JRichiesteUtenti;
import Utenti.Utenti;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author baccaglini_christian
 */
public final class HAUtente extends JFrame {
//Inserire prodotti rimuovere
//Gestione utenti
//Andamento magazzino
//Richieste  

    JTextField nome = new JTextField("nome");
    JTextField cognome = new JTextField("cognome");
    JTextField email = new JTextField("email");
    JTextField nomeUtente = new JTextField("nomeUtente");
    JComboBox tipo = new JComboBox();
    public Utenti.Utente nUtente;
    JPasswordField password = new JPasswordField();
    JTextField telefono = new JTextField("telefono");
//    Materiali vettM = null;
//    Richieste vettR = null;
    JRichiesteUtenti JR = new JRichiesteUtenti(this);
    public JMagazzino JM = new JMagazzino(this);

    public HAUtente(String nomeUtente) {
        System.out.println("Utente");
        HAUtente utente= this;
        
        nUtente = SERVER.getUtenteByiD(Integer.parseInt(nomeUtente));
        
        this.add(JM.scrollp_magazzino);        
        this.add(JR.scrollp_richieste);
        
        JR.panel_richieste.setVisible(false);
        JM.panel_magazzino.setVisible(true);
        JM.scrollp_magazzino.setVisible(true);
        JR.scrollp_richieste.setVisible(false);
        // p2.setVisible(true);
        //Elimina i bordi
        setUndecorated(true);
        //Imposto la finestra al massimo
        setSize(1300, 800);
        this.setLocationRelativeTo(null);
        //setExtendedState(HAAdmin.MAXIMIZED_BOTH);

        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//---------------------------------------------------------------------------------------------------------
        //Colore di sfondo
        Color c = new Color(211, 245, 255);
        this.getContentPane().setBackground(c);
        //Bordo
        c = new Color(150, 245, 255);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, c));

        this.setLayout(null);
                //Bottone logout---------------------------------------------------------------------------------------------------------   
        JButton logout = new JButton();
        logout.setFocusable(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/logout.png"));
            logout.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        logout.setBounds(20, 50, 40, 40);
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setVisible(true);
        this.add(logout);

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //fStartAdmin.setVisible(true);
                HALogin login = new HALogin();
                login.setVisible(true);
                utente.dispose();
            }
        });
//---------------------------------------------------------------------------------------------------------
        JButton exit = new JButton();
        exit.setFocusable(false);
        try {
           BufferedImage img = ImageIO.read(new File("img/x.png"));
            exit.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        exit.setBounds(1230, 5, 55, 40);
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
//---------------------------------------------------------------------------------------------------------

        JButton btn_magazzino = new JButton();
        btn_magazzino.setFocusable(false);
        btn_magazzino.setBounds(30, 100, 150, 91);
        btn_magazzino.setVisible(true);
        //Rende il bottone invisibile
        btn_magazzino.setOpaque(false);
        btn_magazzino.setContentAreaFilled(false);
        btn_magazzino.setBorderPainted(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/btnMagazzino.png"));
            btn_magazzino.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.add(btn_magazzino);

        JButton btn_segnalazioni = new JButton();
        btn_segnalazioni.setFocusable(false);
        btn_segnalazioni.setBounds(30, 200, 150, 91);
        btn_segnalazioni.setVisible(true);
                     //Rende il bottone invisibile
        btn_segnalazioni.setOpaque(false);
        btn_segnalazioni.setContentAreaFilled(false);
        btn_segnalazioni.setBorderPainted(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/btnRichieste.png"));
            btn_segnalazioni.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.add(btn_segnalazioni);
//---------------------------------------------------------------------------------------------------------
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        JLabel label_utente = new JLabel("Utente: " + nomeUtente);
        label_utente.setBounds(20, 15, 200, 30);
        label_utente.setVisible(true);
        label_utente.setFont(font1);
        this.add(label_utente);

        btn_magazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                PSegnalazioni.setVisible(false);
//                PMagazzino.setVisible(true);
                JR.panel_richieste.setVisible(false);
                JM.panel_magazzino.setVisible(true);
                JM.scrollp_magazzino.setVisible(true);
                JR.scrollp_richieste.setVisible(false);
                
            }
        });
        btn_segnalazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                PMagazzino.setVisible(false);
//                PSegnalazioni.setVisible(true);               
                
                JM.scrollp_magazzino.setVisible(false);
                JR.scrollp_richieste.setVisible(true);
                JR.panel_richieste.setVisible(true);
                JM.panel_magazzino.setVisible(false);
            }
        });
        this.setVisible(true);

    }
//---------------------------------------------------------------------------------------------------------

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import Magazzino.JMagazzino;
import Richieste.Richieste;
import Magazzino.Materiali;
import Magazzino.Materiali.Materiale;
import Ordini.JOrdini;
import Richieste.JRichieste;
import Richieste.Richieste.Richiesta;
import Utenti.JDipendenti;
import Utenti.Utenti;
import Utenti.Utenti.Utente;
import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author baccaglini_christian
 */
public final class HAAdmin extends JFrame {
//Inserire prodotti rimuovere
//Gestione utenti
//Andamento magazzino
//Richieste  

    JTextField nome = new JTextField("nome");
    JTextField cognome = new JTextField("cognome");
    JTextField email = new JTextField("email");
    JTextField nomeUtente = new JTextField("nomeUtente");
    JComboBox tipo = new JComboBox();
    JPasswordField password = new JPasswordField();
    JTextField telefono = new JTextField("telefono");
    JScrollPane scrollp_ordini, scrollp_richieste, scrollp_magazzino, scrollp_dipendenti;
    //Connection con = null;
    //Statement stmt = null;
    //Richieste vettR = null;
    JRichieste JR = new JRichieste();
    public JMagazzino JM = new JMagazzino(this);
    public Fornitori F= new Fornitori();
    public Utente nUtente;
    JOrdini JO = new JOrdini(this);    
    JDipendenti JD = new JDipendenti(this);
    Materiali vettM = null;
    JPanel p1 /*= panel_ordini()*/;
    JPanel p2 /*= panel_richieste();*/;
    JPanel p3 /*= panel_magazzino()*/;
    JPanel p4 /*= panel_dipendenti()*/;
    JPanel p1_1 /*= panel_btnOrdini()*/;
    JPanel aggiungi_dip /*= aggiungi_dipendente()*/;

    public HAAdmin(String nomeUtente) {
//        con = ConnessioneBD.con();
//        try {
//            stmt = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("Admin");     
                
        this.add(JM.scrollp_magazzino);
        this.add(JO.scrollp_ordini);
        this.add(JR.scrollp_richieste);
        JO.panel_ordini.setVisible(true);
        JR.panel_richieste.setVisible(false);
        JM.panel_magazzino.setVisible(false);
        JD.panel_dipendenti.setVisible(false);
        JO.panel_btn_ordini.setVisible(true);
        JD.aggiungi_dip.setVisible(false);
        JO.scrollp_ordini.setVisible(true);
        JM.scrollp_magazzino.setVisible(false);
        JR.scrollp_richieste.setVisible(false);
        JD.scrollp_dipendenti.setVisible(false);
        JM.panel_btn_magazzino.setVisible(false);
        JM.panel_nuovo_prodotto.setVisible(false);
        JD.panel_btn_dipendenti.setVisible(false);
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
//Bottone exit---------------------------------------------------------------------------------------------------------
        JButton exit = new JButton();
        exit.setFocusable(false);
        try {
            Image img = ImageIO.read(getClass().getResource("img/x.png"));
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
//Bottoni operazioni---------------------------------------------------------------------------------------------------------
        JButton btn_ordini = new JButton();
        Image img;
        try {
            img = ImageIO.read(getClass().getResource("img/ordini.png"));
            btn_ordini.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn_ordini.setFocusable(false);
        btn_ordini.setBounds(30, 150, 150, 58);
        //Rende il bottone invisibile
        btn_ordini.setOpaque(false);
        btn_ordini.setContentAreaFilled(false);
        btn_ordini.setBorderPainted(false);
        btn_ordini.setVisible(true);
        this.add(btn_ordini);

        JButton btn_richieste = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("img/richieste.png"));
            btn_richieste.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        btn_richieste.setFocusable(false);
        btn_richieste.setBounds(30, 250, 150, 58);
        //Rende il bottone invisibile
        btn_richieste.setOpaque(false);
        btn_richieste.setContentAreaFilled(false);
        btn_richieste.setBorderPainted(false);
        btn_richieste.setVisible(true);
        this.add(btn_richieste);

        JButton btn_magazzino = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("img/magazzino.png"));
            btn_magazzino.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        btn_magazzino.setFocusable(false);
        btn_magazzino.setBounds(30, 350, 150, 58);
        //Rende il bottone invisibile
        btn_magazzino.setOpaque(false);
        btn_magazzino.setContentAreaFilled(false);
        btn_magazzino.setBorderPainted(false);
        btn_magazzino.setVisible(true);
        this.add(btn_magazzino);

        JButton btn_dipendenti = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("img/dipendenti.png"));
            btn_dipendenti.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        btn_dipendenti.setFocusable(false);
        btn_dipendenti.setBounds(30, 450, 150, 58);
        //Rende il bottone invisibile
        btn_dipendenti.setOpaque(false);
        btn_dipendenti.setContentAreaFilled(false);
        btn_dipendenti.setBorderPainted(false);
        btn_dipendenti.setVisible(true);
        this.add(btn_dipendenti);
//---------------------------------------------------------------------------------------------------------
        nUtente = SERVER.getUtenteByiD(Integer.parseInt(nomeUtente));
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        JLabel label_utente = new JLabel("Admin: " + nUtente.nome);
        label_utente.setBounds(20, 15, 200, 30);
        label_utente.setVisible(true);
        label_utente.setFont(font1);
        this.add(label_utente);

        btn_ordini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("ordini");
                JO.panel_ordini.setVisible(true);
                JR.panel_richieste.setVisible(false);
                JM.panel_magazzino.setVisible(false);
                JD.panel_dipendenti.setVisible(false);
                JO.panel_btn_ordini.setVisible(true);
                JO.panel_crea_ordine.setVisible(false);
                JD.aggiungi_dip.setVisible(false);
                JO.scrollp_ordini.setVisible(true);
                JM.scrollp_magazzino.setVisible(false);
                JR.scrollp_richieste.setVisible(false);
                JD.scrollp_dipendenti.setVisible(false);
                JM.panel_btn_magazzino.setVisible(false);
                JM.panel_nuovo_prodotto.setVisible(false);
                JD.panel_btn_dipendenti.setVisible(false);
                JO.repaint(JO.panel_ordini);
            }
        });
        btn_richieste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // p.setVisible(false);
                JO.panel_ordini.setVisible(false);
                JR.panel_richieste.setVisible(true);
                JM.panel_magazzino.setVisible(false);
                JD.panel_dipendenti.setVisible(false);
                JO.panel_btn_ordini.setVisible(false);
                JD.aggiungi_dip.setVisible(false);
                JO.panel_crea_ordine.setVisible(false);
                System.out.println("richieste");
                //p3.setVisible(false);
                JO.scrollp_ordini.setVisible(false);
                JM.scrollp_magazzino.setVisible(false);
                JR.scrollp_richieste.setVisible(true);
                JD.scrollp_dipendenti.setVisible(false);
                JM.panel_btn_magazzino.setVisible(false);
                JM.panel_nuovo_prodotto.setVisible(false);
                JD.panel_btn_dipendenti.setVisible(false);
                JR.repaint(JR.panel_richieste);
            }
        });
        btn_magazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                JO.panel_ordini.setVisible(false);
                JR.panel_richieste.setVisible(false);
                JM.panel_magazzino.setVisible(true);
                JD.panel_dipendenti.setVisible(false);
                JO.panel_btn_ordini.setVisible(false);
                JD.aggiungi_dip.setVisible(false);
                JO.panel_crea_ordine.setVisible(false);
                JO.scrollp_ordini.setVisible(false);
                JM.scrollp_magazzino.setVisible(true);
                JR.scrollp_richieste.setVisible(false);
                JD.scrollp_dipendenti.setVisible(false);
                JM.panel_btn_magazzino.setVisible(true);
                JM.panel_nuovo_prodotto.setVisible(false);
                JD.panel_btn_dipendenti.setVisible(false);
                JM.repaint(JM.panel_magazzino);
            }
        });

        btn_dipendenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("dipendenti");
                JO.panel_ordini.setVisible(false);
                JR.panel_richieste.setVisible(false);
                JM.panel_magazzino.setVisible(false);
                JD.panel_dipendenti.setVisible(true);
                JO.panel_btn_ordini.setVisible(false);
                JD.aggiungi_dip.setVisible(false);
                JO.panel_crea_ordine.setVisible(false);
                JO.scrollp_ordini.setVisible(false);
                JM.scrollp_magazzino.setVisible(false);
                JR.scrollp_richieste.setVisible(false);
                JD.scrollp_dipendenti.setVisible(true);
                JM.panel_btn_magazzino.setVisible(false);
                JM.panel_nuovo_prodotto.setVisible(false);
                JD.panel_btn_dipendenti.setVisible(true);
                JD.repaint(JD.panel_dipendenti);
            }
        });
        this.setVisible(true);

    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height) {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();
        return resizedImage;
    }
}

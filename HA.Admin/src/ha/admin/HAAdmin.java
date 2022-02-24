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
import javax.swing.JPanel;

/**
 *
 * @author baccaglini_christian
 */
public final class HAAdmin extends JFrame {
//Inserire prodotti rimuovere
//Gestione utenti
//Andamento magazzino
//Richieste  

    public HAAdmin(String nomeUtente) {

        JPanel p = panel_ordini();
        JPanel p2 = panel_richieste();
        JPanel p3 = panel_magazzino();
  
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
//---------------------------------------------------------------------------------------------------------
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
//---------------------------------------------------------------------------------------------------------
        JButton btn_ordini = new JButton("ordini");
        btn_ordini.setFocusable(false);
        btn_ordini.setBounds(50, 100, 120, 50);
        btn_ordini.setVisible(true);
        this.add(btn_ordini);

        JButton btn_richieste = new JButton("richieste");
        btn_richieste.setFocusable(false);
        btn_richieste.setBounds(50, 200, 120, 50);
        btn_richieste.setVisible(true);
        this.add(btn_richieste);

        JButton btn_magazzino = new JButton("magazzino");
        btn_magazzino.setFocusable(false);
        btn_magazzino.setBounds(50, 300, 120, 50);
        btn_magazzino.setVisible(true);
        this.add(btn_magazzino);
//---------------------------------------------------------------------------------------------------------
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        JLabel label_utente = new JLabel("Utente: " + nomeUtente);
        label_utente.setBounds(20, 15, 200, 30);
        label_utente.setVisible(true);
        label_utente.setFont(font1);
        this.add(label_utente);

        btn_ordini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("ordini");
                p.setVisible(true);
                p2.setVisible(false);
                p3.setVisible(false);
//                JButton btn_ordini = new JButton("ordini");
//                btn_ordini.setFocusable(false);
//                btn_ordini.setBounds(50, 100, 120, 50);
//                btn_ordini.setVisible(true);
//                p.add(btn_ordini);
            }
        });
        btn_richieste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // p.setVisible(false);
                p.setVisible(false);
                p2.setVisible(true);
                p3.setVisible(false);
                System.out.println("richieste");
                //p3.setVisible(false);

            }
        });
        btn_magazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                p.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(true);
            }
        });

        this.setVisible(true);

    }

    public JPanel panel_ordini() {
        JPanel p = new JPanel();
        p.setBackground(Color.green);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        JButton btn_ordini = new JButton("ordini");
 
        btn_ordini.setBounds(0, 0, 120, 50);
        btn_ordini.setVisible(true);
        p.add(btn_ordini);
        return p;
    }

    public JPanel panel_richieste() {
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        JButton btn_ordini = new JButton("richieste");
 
        btn_ordini.setBounds(0, 0, 120, 50);
        btn_ordini.setVisible(true);
        p.add(btn_ordini);
        return p;
    }

    public JPanel panel_magazzino() {
        JPanel p = new JPanel();
        p.setBackground(Color.red);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        JButton btn_ordini = new JButton("magazzino");
        
        btn_ordini.setBounds(0, 0, 120, 50);
        btn_ordini.setVisible(true);
        p.add(btn_ordini);
        return p;
    }
}

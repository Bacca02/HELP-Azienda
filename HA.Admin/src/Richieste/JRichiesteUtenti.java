/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Richieste;

import ha.admin.HAAdmin;
import ha.admin.SERVER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author baccaglini_christian
 */
public class JRichiesteUtenti {

    public JScrollPane scrollp_richieste;
    public JPanel panel_richieste;
    public boolean stato;

    public JRichiesteUtenti() {
        panel_richieste = panel_richieste();
        stato = false;
    }

    public JPanel panel_richieste() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(155, 225, 242)); //AZZURRO MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        scrollp_richieste = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_richieste.setBounds(200, 60, 1050, 680);
        scrollp_richieste.setBorder(null);
        JButton invia = new JButton();
        invia.setBounds(375, 550, 120, 73);
        //Rende il bottone invisibile
        invia.setOpaque(false);
        invia.setContentAreaFilled(false);
        invia.setBorderPainted(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/invia.png"));
            invia.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        invia.setVisible(true);
        p.add(invia);
        JButton leMieSeganalazioni = new JButton();
        leMieSeganalazioni.setBounds(555, 550, 120, 73);

        //Rende il bottone invisibile
        leMieSeganalazioni.setOpaque(false);
        leMieSeganalazioni.setContentAreaFilled(false);
        leMieSeganalazioni.setBorderPainted(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/lista.png"));
            leMieSeganalazioni.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        leMieSeganalazioni.setVisible(true);
        p.add(leMieSeganalazioni);

        Font font1 = new Font("Verdana", Font.PLAIN, 14);
        JTextArea fieldTesto = new JTextArea();
        fieldTesto.setBounds(275, 100, 500, 400);
        fieldTesto.setVisible(true);
        fieldTesto.setLineWrap(true);
        fieldTesto.setFont(font1);
        fieldTesto.setBorder(new LineBorder(Color.black, 4));
        fieldTesto.setWrapStyleWord(true);
        fieldTesto.setBorder(BorderFactory.createCompoundBorder(fieldTesto.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        p.add(fieldTesto);

        leMieSeganalazioni.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (!stato) {
                    panel_mie_richieste().setVisible(true);
                    stato = true;
                }
            }
        });

        invia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        return p;

    }

    public JFrame panel_mie_richieste() {
        JFrame p = new JFrame();
        //Elimina i bordi
        p.setUndecorated(true);
        //Imposto la finestra al massimo
        p.setSize(500, 700);
        p.setLocationRelativeTo(null);
        //setExtendedState(HAAdmin.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color c = new Color(211, 245, 255);
        p.getContentPane().setBackground(c);
        //Bordo
        c = new Color(150, 245, 255);
        p.getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, c));
        p.setLayout(null);

        JButton exit = new JButton();
        exit.setFocusable(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/x.png"));
            exit.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        exit.setBounds(430, 5, 55, 40);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setVisible(true);
        p.add(exit);

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p.setVisible(false);
                stato = false;
            }
        });
        return p;
    }

}

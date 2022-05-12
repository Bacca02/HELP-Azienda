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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author baccaglini_christian
 */
public class JRichiesteUtenti {
    
    public JScrollPane scrollp_richieste;
    public JPanel panel_richieste;
    
    public JRichiesteUtenti() {
        panel_richieste = panel_richieste();
    }
    
    public JPanel panel_richieste() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //BLU MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        scrollp_richieste = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_richieste.setBounds(200, 60, 1050, 680);
        JButton invia = new JButton("Invia");
        invia.setBounds(475, 500, 100, 50);
        invia.setVisible(true);
        p.add(invia);
        JButton leMieSeganalazioni = new JButton("LeMieSegnalazioni");
        leMieSeganalazioni.setBounds(700, 500, 100, 50);
        leMieSeganalazioni.setVisible(true);
        p.add(leMieSeganalazioni);
        
        JTextArea fieldTesto = new JTextArea();
        fieldTesto.setBounds(200, 50, 650, 400);
        fieldTesto.setVisible(true);
        fieldTesto.setLineWrap(true);
        fieldTesto.setWrapStyleWord(true);
        p.add(fieldTesto);
        
        leMieSeganalazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_mie_richieste().setVisible(true);
                //p.setVisible(false);
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
        
        JButton exit = new JButton("X");
        exit.setFocusable(false);
        Image img;
//        try {
//            img = ImageIO.read(getClass().getResource("../img/x.png"));
//            exit.setIcon(new ImageIcon(img));
//        } catch (IOException ex) {
//            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
        exit.setBounds(445, 5, 55, 40);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setVisible(true);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p.setVisible(false);
            }
        });
        return p;
    }
    
}

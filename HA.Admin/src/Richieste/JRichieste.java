/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Richieste;

import ha.admin.HAAdmin;
import ha.admin.SERVER;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author MAALFING
 */
public class JRichieste {
    public JScrollPane scrollp_richieste;
    public JPanel panel_richieste;
    Richieste vettR = null;
    public JRichieste() {
        panel_richieste = panel_richieste();
    }
    
    
    
    
    public JPanel panel_richieste() {
        vettR = new Richieste();
        JPanel p = new JPanel();

        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //BLU MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        scrollp_richieste = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_richieste.setBounds(200, 60, 1050, 680);
        if (vettR.Riempi()) {
            p.setPreferredSize(new Dimension(2000, (200 * vettR.getList().size()) + 30));
            boolean prova = false;
            for (int i = 0; i < vettR.getList().size(); i++) {
                prova = !prova;
                p.add(panel_richieste(i, prova, vettR.getList().get(i)));

            }
        } else {
            p.setPreferredSize(new Dimension(2000, 200 * vettR.getList().size()));
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }
    
    public JPanel panel_richieste(int i, boolean prova, Richieste.Richiesta R) {

        JPanel p = new JPanel();
        Image img;
        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);

        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240)); //BLU MIGLIORE
        }
        
        JButton btnEseguito, btnPrendiInCarico;
        btnEseguito = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/cestino.png"));
            btnEseguito.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        btnEseguito.setBounds(910, 70, 50, 50);
        btnEseguito.setVisible(true);
        //Rende il bottone invisibile
        btnEseguito.setOpaque(false);
        btnEseguito.setContentAreaFilled(false);
        btnEseguito.setBorderPainted(false);
        p.add(btnEseguito);

        btnPrendiInCarico = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/spunta.png"));
            btnPrendiInCarico.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnPrendiInCarico.setBounds(830, 70, 50, 50);
        btnPrendiInCarico.setVisible(true);
        //Rende il bottone invisibile
        btnPrendiInCarico.setOpaque(false);
        btnPrendiInCarico.setContentAreaFilled(false);
        btnPrendiInCarico.setBorderPainted(false);
        p.add(btnPrendiInCarico);
        String myString = "<html><p> " + R.testo + " </p></html>";
        JLabel labelRichiesta = new JLabel(myString);
        labelRichiesta.setBounds(40, 40, 750, 130);
        labelRichiesta.setBackground(new Color(244, 121, 121)); //ROSSO
        labelRichiesta.setVisible(true);
        p.add(labelRichiesta);
        JLabel labelUtente = new JLabel("Utente: " + vettR.RichUtente(Integer.parseInt(R.Mittente)).nome);
        labelUtente.setBounds(10, 10, 700, 20);
        labelUtente.setVisible(true);
        p.add(labelUtente);

        btnPrendiInCarico.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Prendi in carico");
                //Prendi in carico
            }
        });
        btnEseguito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(JOptionPane.showConfirmDialog(null, "Sei sicuro?", "ATTENZIONE", 0)==0){
                    System.out.println("Sì");
                    try {
                        SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RR&iD="+R.iD);
                    } catch (IOException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else  {
                    System.out.println("No");
                }
                System.out.println("Eseguito");
                //Elimina dalla lista 
            }
        });

        return p;
    }
    
}

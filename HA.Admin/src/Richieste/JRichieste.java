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
import java.awt.image.BufferedImage;
import java.io.File;
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

    HAAdmin H;

    public JRichieste(HAAdmin H) {
        panel_richieste = panel_richieste();
        this.H = H;

    }

    public JPanel panel_richieste() {
        vettR = new Richieste();
        JPanel p = new JPanel();

        p.setLayout(null);
        p.setBackground(new Color(155, 225, 242)); //AZZURRO MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        scrollp_richieste = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_richieste.setBounds(200, 60, 1050, 680);
        scrollp_richieste.setBorder(null);
        if (vettR.Riempi()) {
            p.setPreferredSize(new Dimension(2000, (200 * vettR.getList().size()) + 30));
            boolean prova = false;
            for (int i = 0; i < vettR.getList().size(); i++) {
                prova = !prova;

                if (vettR.RichUtente(Integer.parseInt(vettR.getList().get(i).Mittente)).iD != -1) {
                    p.add(panel_richieste(i, vettR.getList().get(i)));
                } else {
                    try {
                        System.out.println("RIMOZIONE..." + SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "&TipoI=RR&iD=" + (vettR.getList().get(i).iD)));
//vettR.ElRic(vettR.getList().get(i).iD);
                    } catch (IOException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            p.setPreferredSize(new Dimension(2000, 200 * vettR.getList().size()));
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }

    public JPanel panel_richieste(int i, Richieste.Richiesta R) {
        JLabel presoInCarico = new JLabel("");
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);

        Font font1 = new Font("Verdana", Font.BOLD, 16);
        Font font2 = new Font("Verdana", Font.PLAIN, 12);
        p.setBackground(new Color(134, 201, 240)); //BLU MIGLIORE

        JButton btnEseguito, btnPrendiInCarico;
        btnEseguito = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("img/cestino.png"));
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
            BufferedImage img = ImageIO.read(new File("img/spunta.png"));
            btnPrendiInCarico.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnPrendiInCarico.setBounds(830, 70, 50, 50);
        if (presoInCarico.getText().equals("")) {
            btnPrendiInCarico.setVisible(true);
        } else {
            btnPrendiInCarico.setVisible(false);
        }
        //Rende il bottone invisibile
        btnPrendiInCarico.setOpaque(false);
        btnPrendiInCarico.setContentAreaFilled(false);
        btnPrendiInCarico.setBorderPainted(false);
        p.add(btnPrendiInCarico);
        String myString = "<html><p> " + R.testo + " </p></html>";
        JLabel labelRichiesta = new JLabel(myString);
        labelRichiesta.setBounds(20, 50, 800, 130);
        //labelRichiesta.setBackground(new Color(244, 121, 121)); //ROSSO
        labelRichiesta.setVisible(true);
        labelRichiesta.setFont(font2);
        //labelRichiesta.setOpaque(true);
        p.add(labelRichiesta);
        JLabel labelUtente = new JLabel("Utente: " + vettR.RichUtente(Integer.parseInt(R.Mittente)).nome);
        labelUtente.setFont(font1);
        labelUtente.setBounds(10, 10, 700, 20);
        labelUtente.setVisible(true);
        p.add(labelUtente);
//-----------------------------------------------------------------------------------

        if (!R.attiva) {
            btnPrendiInCarico.setVisible(true);
            btnPrendiInCarico.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Prendi in carico");
                    try {
                        System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=AR&iD=" + R.iD + "&att=" + !R.attiva + "&des=" + H.nUtente.iD));
                        presoInCarico.setBounds(10, 30, 700, 20);

                        presoInCarico.setText("Preso in carico da: " + vettR.RichUtente(Integer.parseInt(R.destinatario)).nome);
                        presoInCarico.setVisible(true);
                        p.add(presoInCarico);
                        btnPrendiInCarico.setVisible(false);
                        //Prendi in carico
                    } catch (IOException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else {
            presoInCarico.setBounds(10, 30, 700, 20);

            presoInCarico.setText("Preso in carico da: " + vettR.RichUtente(Integer.parseInt(R.destinatario)).nome);
            presoInCarico.setFont(font1);
            presoInCarico.setVisible(true);
            p.add(presoInCarico);
            btnPrendiInCarico.setVisible(false);

            //Prendi in carico
        }

        btnEseguito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (R.attiva) {

                    if (Integer.parseInt(R.destinatario) == (H.nUtente.iD)) {
                        if (JOptionPane.showConfirmDialog(null, "Sei sicuro?", "ATTENZIONE", 0) == 0) {
                            //System.out.println("Sì");
                            try {
                                SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RR&iD=" + R.iD);
                                panel_richieste.setVisible(false);
                                repaint(panel_richieste);
                                panel_richieste.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println("Eseguito");
                        //Elimina dalla lista 
                    } else {
                        JOptionPane.showMessageDialog(null, "La richiesta può essere eliminata solo da chi l'ha presa in carico\nPer assistenza contattare l'amministratore");
                    }
                } else {
                    try {
                        SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RR&iD=" + R.iD);
                        panel_richieste.setVisible(false);
                        repaint(panel_richieste);
                        panel_richieste.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JRichieste.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });

        return p;
    }

    public void repaint(JPanel p) {
        p.removeAll();
        if (vettR.Riempi()) {
            p.setPreferredSize(new Dimension(2000, (200 * vettR.getList().size()) + 30));
            boolean prova = false;
            for (int i = 0; i < vettR.vett.size(); i++) {
                p.add(panel_richieste(i, vettR.vett.get(i)));
            }
        } else {
            p.setPreferredSize(new Dimension(2000, 200 * vettR.getList().size()));
            Font font1 = new Font("Verdana", Font.PLAIN, 14);
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setFont(font1);
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }
    }

}

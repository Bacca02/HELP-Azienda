/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazzino;

import Magazzino.Materiali.Materiale;
import ha.admin.HAAdmin;
import static ha.admin.HAAdmin.resizeImage;
import ha.admin.SERVER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.*;
import org.json.JSONObject;

/**
 *
 * @author MAALFING
 */
public class JMagazzino {

    Materiali vettM = null;
    public JScrollPane scrollp_magazzino;
    public JPanel p3;
    JLabel labelQuantita;

    public JMagazzino() {
        p3 = panel_magazzino();
    }

    public JPanel panel_magazzino, panel_btn_magazzino, panel_nuovo_prodotto;
    public HAAdmin H;
    public JTextField textField_quantita, textField_materiale, textField_marca, textField_posizione, textField_img;

    public JMagazzino(HAAdmin H) {
        this.H = H;
        panel_magazzino = panel_magazzino();
        panel_btn_magazzino = panel_btn_magazzino();
        panel_nuovo_prodotto = panel_nuovo_prodotto();
    }

    //"TipoI=M&Materiale=" + M.Materiale + "&Marca=" + M.Marca + "&Posizione=" + M.Posizione + "&Path=" + M.IPath + "&quantita=" + M.Quantita;
    public boolean IM(Materiale M) {
        try {

            JSONObject json = new JSONObject(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=M&Materiale=" + M.Materiale + "&Marca=" + M.Marca + "&Posizione=" + M.Posizione + "&Path=" + M.IPath + "&quantita=" + M.Quantita));
            if (json.get("Esito").equals("V")) {
                return true;

            } else {
                JOptionPane.showMessageDialog(null, json.get("Motivo"), "ERRORE", 0);
            }
        } catch (IOException ex) {
            Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
    
    public String getNM(int iD) {
        for (int i = 0; i < vettM.getList().size(); i++) {
            if (vettM.getList().get(i).iD==iD) {
                return vettM.getList().get(i).Materiale;
            }
        }
        return "";
    }

    public JPanel panel_magazzino() {
        vettM = new Materiali();
        JPanel p = new JPanel();
        JLabel oggetto = new JLabel("Oggetto", SwingConstants.CENTER);
        JLabel marca = new JLabel("Marca", SwingConstants.CENTER);
        JLabel locazione = new JLabel("Locazione", SwingConstants.CENTER);
        JLabel quantita = new JLabel("Quantità", SwingConstants.CENTER);
        JLabel immagine = new JLabel("Immagine", SwingConstants.CENTER);

        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO
        p.setBounds(200, 60, 1050, 680);

        scrollp_magazzino = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_magazzino.setBounds(200, 60, 1050, 680);

        if (vettM.Riempi()) {
            boolean prova = false;
            p.setPreferredSize(new Dimension(2000, (200 * vettM.getList().size()) + 90));
            for (int i = 0; i < vettM.getList().size(); i++) {
                prova = !prova;
                p.add(panel_prodotto(i, prova, vettM.getList().get(i)));

                oggetto.setOpaque(true);
                oggetto.setBackground(Color.white);
                oggetto.setBounds(20, 10, 250, 70);
                oggetto.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, 0, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
                oggetto.setVisible(true);
                p.add(oggetto);

                marca.setOpaque(true);
                marca.setBackground(Color.white);
                marca.setBounds(270, 10, 200, 70);
                marca.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
                marca.setVisible(true);
                p.add(marca);

                locazione.setOpaque(true);
                locazione.setBackground(Color.white);
                locazione.setBounds(470, 10, 180, 70);
                locazione.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
                locazione.setVisible(true);
                p.add(locazione);

                quantita.setOpaque(true);
                quantita.setBackground(Color.white);
                quantita.setBounds(650, 10, 120, 70);
                quantita.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
                quantita.setVisible(true);
                p.add(quantita);

                immagine.setOpaque(true);
                immagine.setBackground(Color.white);
                immagine.setBounds(770, 10, 250, 70);
                immagine.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
                immagine.setVisible(true);
                p.add(immagine);
            }
        } else {
            p.setPreferredSize(new Dimension(2000, 200 * vettM.getList().size()));
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }

    public JPanel panel_prodotto(int i, boolean prova, Materiali.Materiale M) {

        JPanel p = new JPanel();
        Image img;
        p.setLayout(null);
        p.setBounds(20, 90 + (200 * i), 1000, 190);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }

        JLabel labelNomeProdotto = new JLabel(M.Materiale, SwingConstants.CENTER);
        labelNomeProdotto.setBounds(0, 50, 250, 100);
        labelNomeProdotto.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelNomeProdotto.setVisible(true);
        p.add(labelNomeProdotto);

        JLabel labelTipoProdotto = new JLabel(M.Marca, SwingConstants.CENTER);
        labelTipoProdotto.setBounds(250, 50, 200, 100);
        labelTipoProdotto.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelTipoProdotto.setVisible(true);
        p.add(labelTipoProdotto);

        JLabel labelSezione = new JLabel(M.Posizione, SwingConstants.CENTER);
        labelSezione.setBounds(450, 50, 180, 100);
        labelSezione.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelSezione.setVisible(true);
        p.add(labelSezione);

        labelQuantita = new JLabel(M.Quantita + "", SwingConstants.CENTER);
        labelQuantita.setBounds(630, 50, 120, 100);
        labelQuantita.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelQuantita.setVisible(true);
        p.add(labelQuantita);

        JButton btn_meno = new JButton();
        JButton btn_piu = new JButton();
        btn_meno.setVisible(true);
        btn_piu.setVisible(true);
        btn_meno.setBounds(655, 120, 30, 30);
        btn_piu.setBounds(695, 120, 30, 30);
        btn_piu.setContentAreaFilled(false);
        btn_piu.setBorderPainted(false);
        btn_piu.setVisible(true);
        btn_meno.setContentAreaFilled(false);
        btn_meno.setBorderPainted(false);
        btn_meno.setVisible(true);

        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/meno.png"));
            btn_meno.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("../ha/admin/img/piu.png"));
            btn_piu.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        p.add(btn_meno);
        p.add(btn_piu);

        btn_meno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                M.Quantita--;
                System.out.println("Controllo-"+M.iD+" "+M.Quantita);
                if (M.Quantita == -1) {
                    M.Quantita++;
                    
                } else {
                    try {
                        System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MM&iD=" + M.iD + "&qnt=" + "-1"));
                    } catch (IOException ex) {
                        Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("meno " + M.Quantita);
                    labelQuantita.setText(Integer.toString(M.Quantita));

                    //Diminuisci prodotto
                }
            }
        });
        btn_piu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                M.Quantita++;
                System.out.println("Controllo+"+M.iD+" "+M.Quantita);
                try {
                    System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MM&iD=" + M.iD + "&qnt=" + "1"));
                } catch (IOException ex) {
                    Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("I: "+vettM.vett.get(i).Quantita);
                System.out.println("piu " + M.Quantita);
                labelQuantita.setText(Integer.toString(M.Quantita));
                //Aumenta prodotto

            }
        });
        JLabel labelRichiesta = new JLabel("", SwingConstants.CENTER);
        labelRichiesta.setBounds(750, 50, 280, 100);
        labelRichiesta.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelRichiesta.setVisible(true);

        labelRichiesta.setFocusable(false);
        try {
            img = ImageIO.read(new URL(M.IPath));
            labelRichiesta.setIcon((new ImageIcon((resizeImage((BufferedImage) (img), 1, 100, 100)))));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelRichiesta.setOpaque(false);
        //labelRichiesta.setContentAreaFilled(false);
        // labelRichiesta.setBorderPainted(false);
        p.add(labelRichiesta);
        return p;
    }

    public JPanel panel_btn_magazzino() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        p.setBounds(50, 640, 150, 100);
        H.add(p);
        JButton btn = new JButton("Nuovo prodotto");
        btn.setBounds(10, 10, 140, 80);
        btn.setVisible(true);
        p.add(btn);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Btn");
                panel_nuovo_prodotto.setVisible(true);
                panel_btn_magazzino.setVisible(false);
                panel_magazzino.setVisible(false);
            }
        });
        return p;
    }

    public JPanel panel_nuovo_prodotto() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(149, 238, 189)); //VERDE MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        H.add(p);

        JLabel quantita = new JLabel("Quantita");
        quantita.setVisible(true);
        quantita.setBounds(0, 0, 200, 30);
        p.add(quantita);
        textField_quantita = new JTextField();
        textField_quantita.setVisible(true);
        textField_quantita.setBounds(0, 30, 200, 30);
        p.add(textField_quantita);

        JLabel labelMateriale = new JLabel("Materiale");
        labelMateriale.setVisible(true);
        labelMateriale.setBounds(0, 60, 200, 30);
        p.add(labelMateriale);

        textField_materiale = new JTextField();
        textField_materiale.setBounds(0, 90, 200, 30);
        textField_materiale.setVisible(true);
        p.add(textField_materiale);

        JLabel labelMarca = new JLabel("Marca");
        labelMarca.setVisible(true);
        labelMarca.setBounds(0, 120, 200, 30);
        p.add(labelMarca);

        textField_marca = new JTextField();
        textField_marca.setBounds(0, 150, 200, 30);
        textField_marca.setVisible(true);
        p.add(textField_marca);

        JLabel labelPosizione = new JLabel("Posizione");
        labelPosizione.setVisible(true);
        labelPosizione.setBounds(0, 180, 200, 30);
        p.add(labelPosizione);

        textField_posizione = new JTextField();
        textField_posizione.setBounds(0, 210, 200, 30);
        textField_posizione.setVisible(true);
        p.add(textField_posizione);

        JLabel labelImg = new JLabel("Immagine");
        labelImg.setVisible(true);
        labelImg.setBounds(0, 240, 200, 30);
        p.add(labelImg);

        textField_img = new JTextField();
        textField_img.setBounds(0, 270, 200, 30);
        textField_img.setVisible(true);
        p.add(textField_img);

        JButton btnAggiungiOrdine = new JButton("Add Ordine");
        btnAggiungiOrdine.setVisible(true);
        btnAggiungiOrdine.setBounds(0, 320, 150, 30);
        p.add(btnAggiungiOrdine);

        btnAggiungiOrdine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                IM(vettM.new Materiale(textField_materiale.getText(),textField_marca.getText(),textField_posizione.getText(),-1, textField_img.getText(), Integer.parseInt(textField_quantita.getText())));
                
                System.out.println("Nuovo ordine eseguito");
                System.out.println(textField_materiale.getText());
            }
        });
        return p;

    }
}

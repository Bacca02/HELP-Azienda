/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazzino;

import Magazzino.Materiali.Materiale;
import ha.admin.HAAdmin;
import static ha.admin.HAAdmin.resizeImage;
import ha.admin.HAUtente;
import ha.admin.SERVER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
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
    //JLabel labelQuantita;

    public JMagazzino() {
        p3 = panel_magazzino();
    }

    public JPanel panel_magazzino, panel_btn_magazzino, panel_nuovo_prodotto;
    public HAAdmin H;
    public HAUtente HU;
    public JTextField textField_quantita, textField_materiale, textField_marca, textField_posizione, textField_img;

    public JMagazzino(HAAdmin H) {
        this.H = H;
        HU = null;
        panel_magazzino = panel_magazzino();
        panel_btn_magazzino = panel_btn_magazzino();
        panel_nuovo_prodotto = panel_nuovo_prodotto();
    }

    public JMagazzino(HAUtente HU) {
        this.HU = HU;
        H = null;
        panel_magazzino = panel_magazzino();
        //panel_btn_magazzino = panel_btn_magazzino();
        //panel_nuovo_prodotto = panel_nuovo_prodotto();
    }

    //"TipoI=M&Materiale=" + M.Materiale + "&Marca=" + M.Marca + "&Posizione=" + M.Posizione + "&Path=" + M.IPath + "&quantita=" + M.Quantita;
    public boolean IM(Materiale M) {
        try {

            JSONObject json = new JSONObject(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=M&Materiale=" + M.Materiale + "&Marca=" + M.Marca + "&Posizione=" + M.Posizione + "&Path=" + M.IPath + "&quantita=" + M.Quantita));
            if (json.get("Esito").equals("V")) {
                vettM.Riempi();
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
            if (vettM.getList().get(i).iD == iD) {
                return vettM.getList().get(i).Materiale;
            }
        }
        return "";
    }

    public JPanel panel_magazzino() {
        vettM = new Materiali();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(155, 225, 242)); //AZZURRO
        p.setBounds(200, 60, 1050, 680);

        scrollp_magazzino = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_magazzino.setBounds(200, 60, 1050, 680);
        scrollp_magazzino.setBorder(null);

        if (vettM.Riempi()) {
            boolean prova = false;
            p.setPreferredSize(new Dimension(2000, (200 * vettM.getList().size()) + 10));
            for (int i = 0; i < vettM.getList().size(); i++) {
                prova = !prova;
                p.add(panel_prodotto(i, prova, vettM.getList().get(i)));

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
        Font f = new Font("Verdana", Font.BOLD, 16);
        Font f1 = new Font("Verdana", Font.PLAIN, 16);

        JPanel p = new JPanel();
        JLabel oggetto = new JLabel("Oggetto", SwingConstants.CENTER);
        JLabel marca = new JLabel("Marca", SwingConstants.CENTER);
        JLabel locazione = new JLabel("Locazione", SwingConstants.CENTER);
        JLabel quantita = new JLabel("Quantità", SwingConstants.CENTER);
        JLabel immagine = new JLabel("Immagine", SwingConstants.CENTER);
        JLabel elimina = new JLabel("Elimina", SwingConstants.CENTER);

        oggetto.setFont(f);
        marca.setFont(f);
        locazione.setFont(f);
        quantita.setFont(f);
        immagine.setFont(f);
        elimina.setFont(f);
        p.setLayout(null);
        p.setBounds(20, 10 + (200 * i), 1000, 190);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }
        Color sfondo = new Color(211, 245, 255);
        Color linee = new Color(134, 201, 240);
        oggetto.setOpaque(true);
        oggetto.setBackground(sfondo);
        oggetto.setBounds(0, 0, 250, 40);
        oggetto.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -4), BorderFactory.createLineBorder(linee, 6)));
        oggetto.setVisible(true);
        p.add(oggetto);

        marca.setOpaque(true);
        marca.setBackground(sfondo);
        marca.setBounds(250, 0, 200, 40);
        marca.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -4), BorderFactory.createLineBorder(linee, 6)));
        marca.setVisible(true);
        p.add(marca);

        locazione.setOpaque(true);
        locazione.setBackground(sfondo);
        locazione.setBounds(450, 0, 180, 40);
        locazione.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -4), BorderFactory.createLineBorder(linee, 6)));
        locazione.setVisible(true);
        p.add(locazione);

        quantita.setOpaque(true);
        quantita.setBackground(sfondo);
        quantita.setBounds(630, 0, 120, 40);
        quantita.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -4), BorderFactory.createLineBorder(linee, 6)));
        quantita.setVisible(true);
        p.add(quantita);

        if (H != null) {
            immagine.setOpaque(true);
            immagine.setBackground(sfondo);
            immagine.setBounds(750, 0, 150, 40);
            immagine.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -4), BorderFactory.createLineBorder(linee, 6)));
            immagine.setVisible(true);
            p.add(immagine);

            elimina.setOpaque(true);
            elimina.setBackground(sfondo);
            elimina.setBounds(900, 0, 95, 40);
            elimina.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -4), BorderFactory.createLineBorder(linee, 6)));
            elimina.setVisible(true);
            p.add(elimina);

        } else {
            immagine.setOpaque(true);
            immagine.setBackground(sfondo);
            immagine.setBounds(750, 0, 250, 40);
            immagine.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), BorderFactory.createLineBorder(linee, 6)));
            immagine.setVisible(true);
            p.add(immagine);
        }

        JLabel labelNomeProdotto = new JLabel(M.Materiale, SwingConstants.CENTER);
        labelNomeProdotto.setFont(f1);
        labelNomeProdotto.setBounds(0, 50, 250, 100);
        labelNomeProdotto.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelNomeProdotto.setVisible(true);
        p.add(labelNomeProdotto);

        JLabel labelTipoProdotto = new JLabel(M.Marca, SwingConstants.CENTER);
        labelTipoProdotto.setFont(f1);
        labelTipoProdotto.setBounds(250, 50, 200, 100);
        labelTipoProdotto.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelTipoProdotto.setVisible(true);
        p.add(labelTipoProdotto);

        JLabel labelSezione = new JLabel(M.Posizione, SwingConstants.CENTER);
        labelSezione.setFont(f1);
        labelSezione.setBounds(450, 50, 180, 100);
        labelSezione.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelSezione.setVisible(true);
        p.add(labelSezione);

        JLabel labelQuantita = new JLabel(M.Quantita + "", SwingConstants.CENTER);
        labelQuantita.setFont(f1);
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
            BufferedImage img = ImageIO.read(new File("img/meno.png"));
            btn_meno.setIcon(new ImageIcon(img));

        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BufferedImage img = ImageIO.read(new File("img/piu.png"));
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
                System.out.println("Controllo-" + M.iD + " " + M.Quantita);
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
                System.out.println("Controllo+" + M.iD + " " + M.Quantita);
                try {
                    System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MM&iD=" + M.iD + "&qnt=" + "1"));
                } catch (IOException ex) {
                    Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("I: " + vettM.vett.get(i).Quantita);
                System.out.println("piu " + M.Quantita);
                labelQuantita.setText(Integer.toString(M.Quantita));
                //Aumenta prodotto

            }
        });
        JLabel labelImmagineMagazzino = new JLabel("", SwingConstants.CENTER);
        if (H != null) {
            labelImmagineMagazzino.setBounds(710, 60, 240, 100);
        } else {
            labelImmagineMagazzino.setBounds(735, 60, 280, 100);
        }

        labelImmagineMagazzino.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelImmagineMagazzino.setVisible(true);

        labelImmagineMagazzino.setFocusable(false);

        try {
            Image img = ImageIO.read(new URL(M.IPath));
            labelImmagineMagazzino.setIcon((new ImageIcon((resizeImage((BufferedImage) (img), 1, 100, 100)))));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelImmagineMagazzino.setOpaque(false);
        //labelRichiesta.setContentAreaFilled(false);
        // labelRichiesta.setBorderPainted(false);
        p.add(labelImmagineMagazzino);

        if (H != null) {
            JButton btnElimina = new JButton();
            btnElimina.setBounds(900, 80, 100, 40);
            btnElimina.setVisible(true);
            btnElimina.setContentAreaFilled(false);
            btnElimina.setBorderPainted(false);
            btnElimina.setVisible(true);
             btnElimina.setFocusable(false);
            p.add(btnElimina);
            try {
                BufferedImage img = ImageIO.read(new File("img/cestino40.png"));
                btnElimina.setIcon(new ImageIcon(img));

            } catch (IOException ex) {
                Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            btnElimina.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    System.out.println("Riga 303");
                    if (JOptionPane.showConfirmDialog(null, "Sei sicuro?", "ATTENZIONE", 0) == 0) {
                        try {
                            String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RM&iD=" + M.iD);
                            System.out.println(json);

                            panel_magazzino.setVisible(false);
                            repaint(panel_magazzino);
                            panel_magazzino.setVisible(true);
                            //Elimina
                        } catch (IOException ex) {
                            Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JMagazzino.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
        }

        return p;
    }

    public JPanel panel_btn_magazzino() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(211, 245, 255)); //AZZURRO BASE MIGLIORE
        p.setBounds(50, 640, 150, 100);
        if (H == null) {
            HU.add(p);
        } else {
            H.add(p);
        }
        JButton btn = new JButton();
        //Rende il bottone invisibile
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/piuMagazzino.png"));
            btn.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn.setBounds(10, 10, 140, 80);
        p.add(btn);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Btn");
                panel_nuovo_prodotto.setVisible(true);
                panel_btn_magazzino.setVisible(false);
                panel_magazzino.setVisible(false);
                scrollp_magazzino.setVisible(false);

            }
        });
        return p;
    }

    public JPanel panel_nuovo_prodotto() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(155, 225, 242)); //AZZURRO MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        H.add(p);
        Font f = new Font("Verdana", Font.BOLD, 16);
        Font f1 = new Font("Verdana", Font.PLAIN, 14);

        JLabel quantita = new JLabel("Quantita");
        quantita.setFont(f);
        quantita.setVisible(true);
        quantita.setBounds((p.getWidth() / 2) - 100, 100, 200, 30);
        p.add(quantita);
        textField_quantita = new JTextField();
        textField_quantita.setVisible(true);
        textField_quantita.setBounds((p.getWidth() / 2) - 100, 130, 200, 30);
        textField_quantita.setBorder(null);
        textField_quantita.setFont(f1);
        p.add(textField_quantita);

        JLabel labelMateriale = new JLabel("Materiale");
        labelMateriale.setFont(f);
        labelMateriale.setVisible(true);
        labelMateriale.setBounds((p.getWidth() / 2) - 100, 180, 200, 30);
        p.add(labelMateriale);

        textField_materiale = new JTextField();
        textField_materiale.setBounds((p.getWidth() / 2) - 100, 210, 200, 30);
        textField_materiale.setVisible(true);
        textField_materiale.setBorder(null);
        textField_materiale.setFont(f1);
        p.add(textField_materiale);

        JLabel labelMarca = new JLabel("Marca");
        labelMarca.setFont(f);
        labelMarca.setVisible(true);
        labelMarca.setBounds((p.getWidth() / 2) - 100, 250, 200, 30);
        p.add(labelMarca);

        textField_marca = new JTextField();
        textField_marca.setBounds((p.getWidth() / 2) - 100, 280, 200, 30);
        textField_marca.setVisible(true);
        textField_marca.setBorder(null);
        textField_marca.setFont(f1);
        p.add(textField_marca);

        JLabel labelPosizione = new JLabel("Posizione");
        labelPosizione.setFont(f);

        labelPosizione.setVisible(true);
        labelPosizione.setBounds((p.getWidth() / 2) - 100, 320, 200, 30);
        p.add(labelPosizione);

        textField_posizione = new JTextField();
        textField_posizione.setBounds((p.getWidth() / 2) - 100, 350, 200, 30);
        textField_posizione.setVisible(true);
        textField_posizione.setBorder(null);
        textField_posizione.setFont(f1);
        p.add(textField_posizione);

        JLabel labelImg = new JLabel("Immagine");
        labelImg.setFont(f);
        labelImg.setVisible(true);
        labelImg.setBounds((p.getWidth() / 2) - 100, 390, 200, 30);
        p.add(labelImg);

        textField_img = new JTextField();
        textField_img.setBounds((p.getWidth() / 2) - 100, 420, 200, 30);
        textField_img.setVisible(true);
        textField_img.setBorder(null);
        textField_img.setFont(f1);
        p.add(textField_img);

        JButton btnAggiungiProdotto = new JButton();
        btnAggiungiProdotto.setFont(f);
        btnAggiungiProdotto.setVisible(true);
        btnAggiungiProdotto.setBounds((p.getWidth() / 2) - 60, 510, 120, 73);
        //Rende il bottone invisibile
        btnAggiungiProdotto.setOpaque(false);
        btnAggiungiProdotto.setContentAreaFilled(false);
        btnAggiungiProdotto.setBorderPainted(false);
        try {
            BufferedImage img = ImageIO.read(new File("img/invia.png"));
            btnAggiungiProdotto.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        };
        p.add(btnAggiungiProdotto);

        btnAggiungiProdotto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                IM(vettM.new Materiale(textField_materiale.getText(), textField_marca.getText(), textField_posizione.getText(), -1, textField_img.getText(), Integer.parseInt(textField_quantita.getText())));
                        p.setVisible(false);
                        scrollp_magazzino.setVisible(true);
                        panel_magazzino.setVisible(true);
                        panel_btn_magazzino.setVisible(true);
                System.out.println("Nuovo ordine eseguito");
                System.out.println(textField_materiale.getText());
            }
        });
        return p;

    }

    public void repaint(JPanel p) {
        p.removeAll();
        if (vettM.Riempi()) {

            for (int i = 0; i < vettM.vett.size(); i++) {
                p.add(panel_prodotto(i, true, vettM.vett.get(i)));
            }
        } else {
            p.setPreferredSize(new Dimension(2000, 200 * vettM.getList().size()));
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }
    }
}

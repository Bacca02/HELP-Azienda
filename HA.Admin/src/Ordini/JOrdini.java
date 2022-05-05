/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordini;

import Magazzino.JMagazzino;
import Magazzino.Materiali;
import Ordini.Ordini.Ordine;
import Fornitori.Fornitori;
import Fornitori.JFornitori;
import ha.admin.HAAdmin;
import static ha.admin.HAAdmin.resizeImage;
import ha.admin.SERVER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.json.JSONObject;

/**
 *
 * @author MAALFING
 */
public class JOrdini {

    public JScrollPane scrollp_ordini;
    public JPanel panel_ordini, panel_btn_ordini;
    public HAAdmin H;
    public JPanel panel_crea_ordine;
    public boolean aperto;
    
    public Fornitori F;
    JFornitori JF;
    Ordini Os;

    public JOrdini(HAAdmin H) {
        this.H = H;
        F = H.F;
        
        F.Riempi();
        JF= new JFornitori(this);
        Os = new Ordini();
        Os.Riempi();
        aperto=false;
        panel_ordini = panel_ordini();
        panel_btn_ordini = panel_btnOrdini();
        panel_crea_ordine = panel_crea_ordine();
        panel_crea_ordine.setVisible(false);

    }

    public JPanel panel_ordini() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(149, 238, 189)); //VERDE MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        p.setPreferredSize(new Dimension(2000, 2000));
        scrollp_ordini = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_ordini.setBounds(200, 60, 1050, 680);
        //this.add(scrollp);
        boolean prova = false;
        for (int i = 0; i < Os.vett.size(); i++) {
            prova = !prova;
            p.add(panel_shop(i, prova, Os.vett.get(i)));
        }
        return p;
    }

    public JPanel panel_shop(int i, boolean prova, Ordine O) {

        JPanel p = new JPanel();
        Font f =new Font("Verdana",Font.BOLD,14);
        Font f1 =new Font("Verdana",Font.PLAIN,16);
        JLabel quantita = new JLabel("Quantita", SwingConstants.CENTER);
        JLabel marca = new JLabel("Marca", SwingConstants.CENTER);
        JLabel materiale = new JLabel("Materiale", SwingConstants.CENTER);
        JLabel data = new JLabel("Data", SwingConstants.CENTER);
        JLabel elimina = new JLabel("Elimina", SwingConstants.CENTER);
        quantita.setFont(f);
        marca.setFont(f);
        materiale.setFont(f);
        data.setFont(f);
        elimina.setFont(f);
        Image img;
        p.setLayout(null);
        p.setBounds(20, 20 + (100 * i), 1000, 95);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }
        //------------------------------------------------------------------------------------------------------------------
        quantita.setOpaque(true);
            quantita.setBackground(Color.white);
        quantita.setBounds(0, 0, 210, 30);
        quantita.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, 0, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        quantita.setVisible(true);
        p.add(quantita);

        marca.setOpaque(true);
        marca.setBackground(Color.white);
        marca.setBounds(210, 0, 240, 30);
        marca.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        marca.setVisible(true);
        p.add(marca);

        materiale.setOpaque(true);
        materiale.setBackground(Color.white);
        materiale.setBounds(450, 0, 250, 30);
        materiale.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        materiale.setVisible(true);
        p.add(materiale);

        data.setOpaque(true);
        data.setBackground(Color.white);
        data.setBounds(700, 0, 230, 30);
        data.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        data.setVisible(true);
        p.add(data);

        elimina.setOpaque(true);
        elimina.setBackground(Color.white);
        elimina.setBounds(930, 0, 70, 30);
        elimina.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        elimina.setVisible(true);
        p.add(elimina);
//--------------------------------------------------------------------------------------------------------------------
        JLabel labelQuantita = new JLabel(Integer.toString(O.qnt), SwingConstants.CENTER);
        labelQuantita.setFont(f1);
        labelQuantita.setBounds(10, 20, 200, 80);
        labelQuantita.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelQuantita.setVisible(true);
        p.add(labelQuantita);

        JLabel labelFornitore = new JLabel(F.getNomebyiD(O.iDFornitore), SwingConstants.CENTER);
        labelFornitore.setFont(f1);
        labelFornitore.setBounds(210, 20, 240, 80);
        labelFornitore.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelFornitore.setVisible(true);
        p.add(labelFornitore);

        JLabel labelMateriale = new JLabel(H.JM.getNM(O.iDMateriale), SwingConstants.CENTER);
        labelMateriale.setFont(f1);
        labelMateriale.setBounds(450, 20, 250, 80);
        labelMateriale.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelMateriale.setVisible(true);
        p.add(labelMateriale);

        JLabel labelData = new JLabel(O.data.toString(), SwingConstants.CENTER);
        labelData.setFont(f1);
        labelData.setBounds(700, 20, 230, 80);
        labelData.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelData.setVisible(true);
        p.add(labelData);

        JButton btnElimina = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/cestino40.png"));
            btnElimina.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnElimina.setBounds(940, 35, 50, 50);
        btnElimina.setVisible(true);
        btnElimina.setOpaque(false);
        btnElimina.setContentAreaFilled(false);
        btnElimina.setBorderPainted(false);
        p.add(btnElimina);
        btnElimina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Ordine eliminato");

                if (JOptionPane.showConfirmDialog(null, "Sei sicuro?", "ATTENZIONE", 0) == 0) {
                    try {
                        SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RO&iD=" + O.iDOrdine);
                        Os.Riempi();
                        panel_ordini.setVisible(false);
                        repaint(panel_ordini);
                        panel_ordini.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
        return p;
    }

    public JPanel panel_btnOrdini() {

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(149, 238, 189)); //VERDE MIGLIORE
        p.setBounds(50, 640, 150, 100);
        H.add(p);
        JButton btn = new JButton("Nuovo ordine");
        btn.setBounds(10, 10, 140, 80);
        btn.setVisible(true);
        p.add(btn);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Btn");
                panel_crea_ordine.setVisible(true);
                panel_ordini.setVisible(false);
                panel_btn_ordini.setVisible(false);
                scrollp_ordini.setVisible(false);
            }
        });
        return p;
    }

    public JComboBox comboFornitore;
    public JComboBox comboMateriale;
    public JTextField textField_quantita;
    public JLabel dataOrdine;

    public JPanel panel_crea_ordine() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(149, 238, 189)); //VERDE MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        H.add(p);
        Font f = new Font("Verdana", Font.BOLD, 14);
        Font f1 = new Font("Verdana", Font.PLAIN, 16);

        JLabel quantita = new JLabel("Quantita");
        quantita.setFont(f);
        quantita.setVisible(true);
        quantita.setBounds(0, 0, 200, 30);
        p.add(quantita);
        textField_quantita = new JTextField();
        textField_quantita.setVisible(true);
        textField_quantita.setBounds(0, 30, 200, 30);
        textField_quantita.setFont(f);
        p.add(textField_quantita);

        //Creare due vettori di stringhe con dentro l'idFornitore e idMateriale
        Materiali M = new Materiali();
        M.Riempi();
        String[] fornitori = null;
        String[] materiali = M.getNomi();

        JLabel labelFornitore = new JLabel("Fornitore");
        labelFornitore.setFont(f);
        labelFornitore.setVisible(true);
        labelFornitore.setBounds(0, 60, 200, 30);
        p.add(labelFornitore);

        comboFornitore = new JComboBox(F.getNomi());
        comboFornitore.addItem("Gestisci...");
        comboFornitore.setBounds(0, 90, 200, 30);
        comboFornitore.setVisible(true);
        comboFornitore.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (comboFornitore.getSelectedIndex()==comboFornitore.getItemCount()-1&&!aperto) {
                    JF.setVisible(true);
                    aperto=true;
                }
            }
        });
        p.add(comboFornitore);

        JLabel labelMateriali = new JLabel("Materiali");
        labelMateriali.setFont(f);
        labelMateriali.setVisible(true);
        labelMateriali.setBounds(0, 120, 200, 30);
        p.add(labelMateriali);

        comboMateriale = new JComboBox(materiali);
        comboMateriale.setBounds(0, 150, 200, 30);
        comboMateriale.setVisible(true);
        comboMateriale.setFont(f);
        p.add(comboMateriale);

        //YYYY-MM-DD hh:mm:ss
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String formattedDate = data.format(form);
        System.out.println(formattedDate);
        dataOrdine = new JLabel(formattedDate);
        dataOrdine.setVisible(true);
        dataOrdine.setBounds(0, 180, 200, 30);
        dataOrdine.setFont(f);
        
        p.add(dataOrdine);
        JButton btnAggiungiOrdine = new JButton("Add Ordine");
        btnAggiungiOrdine.setFont(f);
        btnAggiungiOrdine.setVisible(true);
        btnAggiungiOrdine.setBounds(0, 210, 150, 30);
        p.add(btnAggiungiOrdine);

        btnAggiungiOrdine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    JSONObject json = new JSONObject(ha.admin.SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=O&idFornitore=" + Integer.toString(F.vett.get(comboFornitore.getSelectedIndex()).iD) + "&idMateriale=" + M.vett.get(comboFornitore.getSelectedIndex()).iD + "&Quantita=" + textField_quantita.getText() + "&Data=" + dataOrdine.getText()));

                    if (json.get("Esito").equals("V")) {
                        System.out.println("FATTO!");
                        Os.Riempi();
                    } else {
                        JOptionPane.showMessageDialog(null, json.get("Motivo"), "ERRORE", 0);
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("Nuovo ordine eseguito");

            }
        });
        return p;

    }

    public void repaint(JPanel p) {
        p.removeAll();
        for (int i = 0; i < Os.vett.size(); i++) {
            p.add(panel_shop(i, true, Os.vett.get(i)));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordini;

import Magazzino.Materiali;
import ha.admin.Fornitori;
import ha.admin.HAAdmin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

    public JOrdini(HAAdmin H) {
        this.H = H;
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
        for (int i = 0; i < 10; i++) {
            prova = !prova;
            p.add(panel_shop(i, prova));
        }
        return p;
    }

    public JPanel panel_shop(int i, boolean prova) {

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240)); //BLU MIGLIORE
        }
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

        JLabel quantita = new JLabel("Quantita");
        quantita.setVisible(true);
        quantita.setBounds(0, 0, 200, 30);
        p.add(quantita);
        textField_quantita = new JTextField();
        textField_quantita.setVisible(true);
        textField_quantita.setBounds(0, 30, 200, 30);
        p.add(textField_quantita);

        //Creare due vettori di stringhe con dentro l'idFornitore e idMateriale
        Fornitori F = new Fornitori();
        Materiali M = new Materiali();
        M.Riempi();
        F.Riempi();
        String[] fornitori = null;
        String[] materiali = M.getNomi();

        JLabel labelFornitore = new JLabel("Fornitore");
        labelFornitore.setVisible(true);
        labelFornitore.setBounds(0, 60, 200, 30);
        p.add(labelFornitore);

        comboFornitore = new JComboBox(F.getNomi());
        comboFornitore.setBounds(0, 90, 200, 30);
        comboFornitore.setVisible(true);
        p.add(comboFornitore);

        JLabel labelMateriali = new JLabel("Materiali");
        labelMateriali.setVisible(true);
        labelMateriali.setBounds(0, 120, 200, 30);
        p.add(labelMateriali);

        comboMateriale = new JComboBox(materiali);
        comboMateriale.setBounds(0, 150, 200, 30);
        comboMateriale.setVisible(true);
        p.add(comboMateriale);

        //YYYY-MM-DD hh:mm:ss
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = data.format(form);
        System.out.println(formattedDate);
        dataOrdine = new JLabel(formattedDate);
        dataOrdine.setVisible(true);
        dataOrdine.setBounds(0, 180, 200, 30);
        p.add(dataOrdine);
        JButton btnAggiungiOrdine = new JButton("Add Ordine");
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
                    } else {
                        JOptionPane.showMessageDialog(null, json.get("Motivo"), "ERRORE", 0);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("Nuovo ordine eseguito");

            }
        });
        return p;

    }

}

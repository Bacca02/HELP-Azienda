/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fornitori;

import Fornitori.Fornitori.Fornitore;
import Ordini.JOrdini;
import ha.admin.SERVER;
import java.awt.*;
import java.util.List;
import java.awt.HeadlessException;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author MAALFING
 */
public class JFornitori extends JFrame {

    public JOrdini JO;
    public List<Fornitore> vett;
    //public JPanel p;
    public Panel fpanel = new Panel();
    public ScrollPane scp;

    public JFornitori(JOrdini JO) {
        setSize(500, 500);
        this.JO = JO;
        this.setTitle("Gestione dei fornitori...");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JO.aperto = false;
            }

        });
        //setUndecorated(true);
        this.setLocationRelativeTo(null);
        //p = new JPanel();
        //p.setBounds(0, 0, this.getWidth(), this.getHeight() - 70);

        riempiPanel();

        //p.setVisible(true);
        Color c = new Color(211, 245, 255);
        this.getContentPane().setBackground(c);
        this.setLayout(null);
        //this.add(p);
        Button B1 = new Button("Chiudi");

        B1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JO.aperto = false;
                dispose();
            }

        });

        B1.setBounds(5, this.getHeight() - 60, 100, 25);

        this.add(B1);
        Button B2 = new Button("Nuovo");

        B2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Frame Fm = AddFor();
                Fm.setVisible(true);
            }

        });

        B2.setBounds(this.getWidth() - 115, this.getHeight() - 60, 100, 25);
        this.add(B2);

    }

    public void riempiPanel() {

        JO.F.Riempi();
        vett = JO.F.vett;
        //fpanel = new Panel();
        //fpanel.removeAll();
        fpanel.setLayout(new GridLayout(vett.size(), 0));
        scp = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        //scp.setLayout(null);
        scp.setSize(this.getWidth() - 10, this.getHeight() - 70);

        for (int i = 0; i < vett.size(); i++) {
            

            Fornitore F = vett.get(i);
            Panel singf = AddFornToPanel(F);
            fpanel.add(singf);

        }
        scp.add(fpanel);
        this.add(scp);
    }

    public Frame AddFor() {
        Frame FMod = new Frame("Crea nuovo fornitore");
        FMod.setSize(300, 300);
        FMod.setLayout(null);
        //FMod.setUndecorated(true);
        FMod.setLocationRelativeTo(null);
        Panel p = new Panel();
        TextField nome = new TextField("Nome");
        TextField tel = new TextField("Telefono");
        TextField ind = new TextField("Indirizzo");

        FMod.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FMod.dispose();
            }

        });

        Label lnom = new Label("Nome:");
        lnom.setBounds(10, 20, 200, 10);
        Label ltel = new Label("Telefono:");
        ltel.setBounds(10, 65, 200, 10);
        Label lind = new Label("Indirizzo:");
        lind.setBounds(10, 110, 200, 10);

        nome.setBounds(10, 35, 200, 25);
        tel.setBounds(10, 80, 200, 25);
        ind.setBounds(10, 125, 200, 25);

        p.setBounds(0, 10, FMod.getWidth(), FMod.getHeight() - 50);
        p.setLayout(null);
        //p.setBackground(Color.red);
        p.add(lnom);
        p.add(nome);
        p.add(ltel);
        p.add(tel);
        p.add(lind);
        p.add(ind);

        Button bnt1 = new Button("Crea");
        Button bnt2 = new Button("Annulla");

        bnt1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=F&iD=" + "&Nome=" + nome.getText() + "&Telefono=" + tel.getText() + "&Ind=" + ind.getText()));

                    //FA APPARIRE IL NUOVO FORNITORE NELLA LISTA
                    fpanel.add(AddFornToPanel(new Fornitori().new Fornitore(vett.size() - 1, vett.get(vett.size() - 1).iD, nome.getText(), tel.getText(), ind.getText())));
                    JO.panel_crea_ordine.setVisible(false);
                    repaint(fpanel);
                    JO.panel_crea_ordine.setVisible(true);
                    FMod.dispose();

                    //scp.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        bnt2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FMod.dispose();
            }

        });

        bnt1.setBounds(FMod.getWidth() - 110, FMod.getHeight() - 35, 100, 25);
        bnt2.setBounds(10, FMod.getHeight() - 35, 100, 25);
        FMod.add(bnt1);
        FMod.add(bnt2);
        FMod.add(p);
        return FMod;
    }

    public Frame ModFor(Fornitore F) {
        Frame FMod = new Frame("Modifica " + F.nome);
        FMod.setSize(300, 300);
        FMod.setLayout(null);
        //FMod.setUndecorated(true);
        FMod.setLocationRelativeTo(null);
        Panel p = new Panel();
        TextField nome = new TextField(F.nome);
        TextField tel = new TextField(F.telefono);
        TextField ind = new TextField(F.indirizzo);

        FMod.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FMod.dispose();
            }

        });

        Label lnom = new Label("Nome:");
        lnom.setBounds(10, 20, 200, 10);
        Label ltel = new Label("Telefono:");
        ltel.setBounds(10, 65, 200, 10);
        Label lind = new Label("Indirizzo:");
        lind.setBounds(10, 110, 200, 10);

        nome.setBounds(10, 35, 200, 25);
        tel.setBounds(10, 80, 200, 25);
        ind.setBounds(10, 125, 200, 25);

        p.setBounds(0, 10, FMod.getWidth(), FMod.getHeight() - 50);
        p.setLayout(null);
        //p.setBackground(Color.red);
        p.add(lnom);
        p.add(nome);
        p.add(ltel);
        p.add(tel);
        p.add(lind);
        p.add(ind);

        Button bnt1 = new Button("Salva");
        Button bnt2 = new Button("Annulla");

        bnt1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MF&iD=" + F.iD + "&Nome=" + nome.getText() + "&Telefono=" + tel.getText() + "&Ind=" + ind.getText()));

                    //FA SALVARE IL FORNITORE SELEZIONATO E CAMBIA LO STATO SIA NEL FORM PRECEDENTE CHE NEL JORDINI
                    fpanel.remove(F.num - 1);
                    fpanel.setVisible(false);
                    JO.panel_crea_ordine.setVisible(false);
                    fpanel.add(AddFornToPanel(new Fornitori().new Fornitore(F.num, F.iD, nome.getText(), tel.getText(), ind.getText())), F.num - 1);
                    FMod.dispose();
                    repaint(fpanel);
                    JO.panel_crea_ordine.setVisible(true);
                    fpanel.setVisible(true);
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        bnt2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FMod.dispose();
            }

        });

        bnt1.setBounds(FMod.getWidth() - 110, FMod.getHeight() - 35, 100, 25);
        bnt2.setBounds(10, FMod.getHeight() - 35, 100, 25);
        FMod.add(bnt1);
        FMod.add(bnt2);
        FMod.add(p);
        return FMod;
    }

    public Panel AddFornToPanel(Fornitore F) {
        Panel singf = new Panel();

        Label L = new Label(F.nome);
        Button btn1 = new Button("Modifica");
        Button btn2 = new Button("Elimina");

        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Frame Fm = ModFor(F);
                Fm.setVisible(true);
            }

        });

        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Sei sicuro d'eliminare " + F.nome, "Attenzione", 0, 1) == 0) {

                        System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RF&iD=" + F.iD));
                        
                        fpanel.remove(F.num-1);
                        JO.panel_crea_ordine.setVisible(false);
                        repaint(fpanel);
                        JO.panel_crea_ordine.setVisible(true);
                        
                    }

                    //ELIMINA SIA DAL FORM PRECEDENTE SIA DAL JORDINI IL FORNITORE SELEZIONATO
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        singf.add(L);
        singf.add(btn1);
        singf.add(btn2);
        System.out.println("AGGIUNTO");

        return singf;

    }
    public void repaint(Panel P){
        this.setVisible(false);
        JO.F.Riempi();

        JO.repaintComboBox();
        JO.aperto=false;
        
        
        vett = JO.F.vett;
        fpanel.removeAll();
        P.setLayout(new GridLayout(vett.size(), 0));
        

        for (int i = 0; i < vett.size(); i++) {
            

            Fornitore F = vett.get(i);
            Panel singf = AddFornToPanel(F);
            P.add(singf);

        }
        this.setVisible(true);
    }
    

}

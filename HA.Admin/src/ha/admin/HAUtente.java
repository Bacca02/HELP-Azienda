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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author baccaglini_christian
 */
public final class HAUtente extends JFrame {
//Inserire prodotti rimuovere
//Gestione utenti
//Andamento magazzino
//Richieste  

    JTextField nome = new JTextField("nome");
    JTextField cognome = new JTextField("cognome");
    JTextField email = new JTextField("email");
    JTextField nomeUtente = new JTextField("nomeUtente");
    JComboBox tipo = new JComboBox();
    JPasswordField password = new JPasswordField();
    JTextField telefono = new JTextField("telefono");
    Materiali vettM = null;
    Richieste vettR = null;

    public HAUtente(String nomeUtente) {
        System.out.println("Utente");

        JPanel PMagazzino = panel_magazzino();
        JPanel PSegnalazioni = panel_segnalazioni();
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

        JButton btn_magazzino = new JButton("magazzino");
        btn_magazzino.setFocusable(false);
        btn_magazzino.setBounds(50, 300, 120, 50);
        btn_magazzino.setVisible(true);
        this.add(btn_magazzino);

        JButton btn_segnalazioni = new JButton("segnalazioni");
        btn_segnalazioni.setFocusable(false);
        btn_segnalazioni.setBounds(50, 500, 120, 50);
        btn_segnalazioni.setVisible(true);
        this.add(btn_segnalazioni);
//---------------------------------------------------------------------------------------------------------
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        JLabel label_utente = new JLabel("Utente: " + nomeUtente);
        label_utente.setBounds(20, 15, 200, 30);
        label_utente.setVisible(true);
        label_utente.setFont(font1);
        this.add(label_utente);

        btn_magazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                PSegnalazioni.setVisible(false);
                PMagazzino.setVisible(true);
            }
        });
        btn_segnalazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("segnalazioni");
                PMagazzino.setVisible(false);
                PSegnalazioni.setVisible(true);

            }
        });
        this.setVisible(true);

    }
//---------------------------------------------------------------------------------------------------------

    public JPanel panel_magazzino() {
        vettM = new Materiali();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO
        p.setBounds(200, 60, 1050, 680);
        this.add(p);

        if (vettM.Riempi()) {
            boolean prova = false;
            for (int i = 0; i < vettM.getList().size(); i++) {
                prova = !prova;

                p.add(panel_prodotto(i, prova, vettM.getList().get(i)));
            }
        } else {
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }

    public JPanel panel_prodotto(int i, boolean prova, Materiali.Materiale M) {

        JPanel p = new JPanel();

        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }

        JLabel labelNomeProdotto = new JLabel(M.Materiale);
        labelNomeProdotto.setBounds(50, 50, 100, 100);
        labelNomeProdotto.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelNomeProdotto.setVisible(true);
        p.add(labelNomeProdotto);

        JLabel labelTipoProdotto = new JLabel(M.Marca);
        labelTipoProdotto.setBounds(200, 50, 100, 100);
        labelTipoProdotto.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelTipoProdotto.setVisible(true);
        p.add(labelTipoProdotto);

        JLabel labelRichiesta = new JLabel("Immagine");
        labelRichiesta.setBounds(800, 50, 100, 100);
        labelRichiesta.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelRichiesta.setVisible(true);

        labelRichiesta.setFocusable(false);
        try {
            Image img = ImageIO.read(getClass().getResource(M.IPath));
            labelRichiesta.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelRichiesta.setOpaque(false);
        //labelRichiesta.setContentAreaFilled(false);
        // labelRichiesta.setBorderPainted(false);
        p.add(labelRichiesta);
        return p;
    }

    //PanelMagazzino------------------------------------------------------------------------------------------------------------
    public JPanel panel_segnalazioni() {
        vettR = new Richieste();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        if (vettR.Riempi()) {
            boolean prova = false;
            for (int i = 0; i < vettR.getList().size(); i++) {
                prova = !prova;

               // p.add(panel_segnalazione(i, prova, vettR.getList().get(i)));
            }
        } else {
            JLabel tmp = new JLabel("Non c'è nulla");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }

    public JPanel panel_segnalazione(int i, boolean prova, Materiali.Materiale M) {

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }
        return p;
    }
}

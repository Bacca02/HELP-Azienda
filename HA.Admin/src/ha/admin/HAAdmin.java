/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.sql.*;
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
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author baccaglini_christian
 */
public final class HAAdmin extends JFrame {
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
    Connection con = null;
    Statement stmt = null;

    public HAAdmin(String nomeUtente) {
        con = ConnessioneBD.con();
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Admin");
        JPanel p1 = panel_ordini();
        JPanel p2 = panel_richieste();
        JPanel p3 = panel_magazzino();
        JPanel p4 = panel_dipendenti();
        JPanel p1_1 = panel_btnOrdini();
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(true);
        p1_1.setVisible(false);

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
        JButton btn_ordini = new JButton("ordini");
        btn_ordini.setFocusable(false);
        btn_ordini.setBounds(50, 100, 120, 50);
        btn_ordini.setVisible(true);
        this.add(btn_ordini);

        JButton btn_richieste = new JButton("richieste");
        btn_richieste.setFocusable(false);
        btn_richieste.setBounds(50, 200, 120, 50);
        btn_richieste.setVisible(true);
        this.add(btn_richieste);

        JButton btn_magazzino = new JButton("magazzino");
        btn_magazzino.setFocusable(false);
        btn_magazzino.setBounds(50, 300, 120, 50);
        btn_magazzino.setVisible(true);
        this.add(btn_magazzino);

        JButton btn_dipendenti = new JButton("dipendenti");
        btn_dipendenti.setFocusable(false);
        btn_dipendenti.setBounds(50, 400, 120, 50);
        btn_dipendenti.setVisible(true);
        this.add(btn_dipendenti);
//---------------------------------------------------------------------------------------------------------
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        JLabel label_utente = new JLabel("Admin: " + nomeUtente);
        label_utente.setBounds(20, 15, 200, 30);
        label_utente.setVisible(true);
        label_utente.setFont(font1);
        this.add(label_utente);

        btn_ordini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("ordini");
                p1.setVisible(true);
                p2.setVisible(false);
                p3.setVisible(false);
                p4.setVisible(false);
                p1_1.setVisible(true);
//                JButton btn_ordini = new JButton("ordini");
//                btn_ordini.setFocusable(false);
//                btn_ordini.setBounds(50, 100, 120, 50);
//                btn_ordini.setVisible(true);
//                p.add(btn_ordini);
            }
        });
        btn_richieste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // p.setVisible(false);
                p1.setVisible(false);
                p2.setVisible(true);
                p3.setVisible(false);
                p4.setVisible(false);
                p1_1.setVisible(false);
                System.out.println("richieste");
                //p3.setVisible(false);

            }
        });
        btn_magazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                p1.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(true);
                p4.setVisible(false);
                p1_1.setVisible(false);
            }
        });

        btn_dipendenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                p1.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(false);
                p4.setVisible(true);
                p1_1.setVisible(false);
            }
        });
        this.setVisible(true);

    }
//----------------------------------------------------------------------------------------

    public JPanel panel_ordini() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.green);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);

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
            p.setBackground(Color.blue);
        }
        return p;
    }

    public JPanel panel_btnOrdini() {

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.green);
        p.setBounds(50, 640, 150, 100);
        this.add(p);
        JButton btn = new JButton();
        btn.setBounds(10, 10, 140, 80);
        btn.setVisible(true);
        p.add(btn);
        return p;
    }
//----------------------------------------------------------------------------------------

    public JPanel panel_richieste() {
        Richieste vett= new Richieste();
        vett.Riempi();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.red);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        boolean prova = false;
        for (int i = 0; i < vett.getList().size(); i++) {
            prova = !prova;
            p.add(panel_richieste(i, prova, vett.getList().get(i).testo));
            System.out.println(vett.getList().get(i).testo);
        }
        return p;
    }

    public JPanel panel_richieste(int i, boolean prova, String txt) {

        JPanel p = new JPanel();

        p.setLayout(null);
        p.setBounds(20, 20 + (200 * 0), 1000, 190);
       
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(Color.blue);
        }
        JButton btnEseguito, btnPrendiInCarico;
        btnEseguito = new JButton("E");

        btnEseguito.setBounds(800, 85, 50, 50);
        btnEseguito.setVisible(true);
        p.add(btnEseguito);

        btnPrendiInCarico = new JButton("P");
        btnPrendiInCarico.setBounds(860, 85, 50, 50);
        btnPrendiInCarico.setVisible(true);
        p.add(btnPrendiInCarico);

        JLabel labelRichiesta = new JLabel(txt/*"Domanda da parte dell'utente aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"*/);
        labelRichiesta.setBounds(40, 40, 700, 100);
        labelRichiesta.setBackground(Color.red);
        labelRichiesta.setVisible(true);
        p.add(labelRichiesta);
        JLabel labelUtente = new JLabel("Utente: ");
        labelUtente.setBounds(10, 10, 700, 20);
        labelUtente.setVisible(true);
        p.add(labelUtente);
        return p;
    }
//----------------------------------------------------------------------------------------

    public JPanel panel_magazzino() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.red);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);

        boolean prova = false;
        for (int i = 0; i < 10; i++) {
            prova = !prova;

            p.add(panel_prodotto(i, prova));
        }

        return p;
    }

    public JPanel panel_prodotto(int i, boolean prova) {

        JPanel p = new JPanel();

        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(Color.blue);
        }

        JLabel labelNomeProdotto = new JLabel("Prodotto");
        labelNomeProdotto.setBounds(50, 50, 100, 100);
        labelNomeProdotto.setBackground(Color.red);
        labelNomeProdotto.setVisible(true);
        p.add(labelNomeProdotto);

        JLabel labelTipoProdotto = new JLabel("Tipo");
        labelTipoProdotto.setBounds(200, 50, 100, 100);
        labelTipoProdotto.setBackground(Color.red);
        labelTipoProdotto.setVisible(true);
        p.add(labelTipoProdotto);

        JLabel labelRichiesta = new JLabel("Immagine");
        labelRichiesta.setBounds(800, 50, 100, 100);
        labelRichiesta.setBackground(Color.red);
        labelRichiesta.setVisible(true);

        labelRichiesta.setFocusable(false);
        try {
            Image img = ImageIO.read(getClass().getResource("img/x.png"));
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
//----------------------------------------------------------------------------------------

    public JPanel panel_dipendenti() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.red);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);

        JButton btn = new JButton("aggiungi dipendente");
        btn.setBounds((p.getWidth() / 2) - 60, 550, 120, 50);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("aggiunto utente");
                String sql = "INSERT INTO `utenti` (`iD`, `Nome`, `Cognome`, `e-mail`, `nome_utente`, `Password`, `Tipo`, `Telefono`) VALUES (NULL, '" + nome.getText() + "', '" + cognome.getText() + "', '" + email.getText() + "', '" + nomeUtente.getText() + "', '" + password.getText() + "', '" + tipo.getSelectedItem().toString() + "', '" + telefono.getText() + "');";
                try {
                    //esequo la query e ne salvo il risultato
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Utente creato");
                    nome.setText("Nome");
                    cognome.setText("Cognome");
                    email.setText("E-mail");
                    nomeUtente.setText("Nome utente");
                    password.setText("");
                    telefono.setText("Telefono");
//                p1.setVisible(false);
//                p2.setVisible(false);
//                p3.setVisible(false);
//                p4.setVisible(true);
//                p1_1.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn.setVisible(true);
        p.add(btn);

//        JLabel label_nome = new JLabel("ciao");
//        label_nome.setBounds(50, 100, 150, 200);
//        label_nome.setBackground(Color.white);
//        label_nome.setOpaque(true);
//        label_nome.setVisible(true);
//        p.add(label_nome);
        tipo.addItem("Admin");
        tipo.addItem("Utente");

        nome.setBounds((p.getWidth() / 2) - 125, 150, 250, 30);
        cognome.setBounds((p.getWidth() / 2) - 125, 200, 250, 30);
        email.setBounds((p.getWidth() / 2) - 125, 250, 250, 30);
        nomeUtente.setBounds((p.getWidth() / 2) - 125, 300, 170, 30);
        tipo.setBounds(570, 300, 80, 30);
        password.setBounds((p.getWidth() / 2) - 125, 350, 250, 30);
        telefono.setBounds((p.getWidth() / 2) - 125, 400, 250, 30);

        nome.setVisible(true);
        cognome.setVisible(true);
        email.setVisible(true);
        nomeUtente.setVisible(true);
        tipo.setVisible(true);
        password.setVisible(true);
        telefono.setVisible(true);

        p.add(nome);
        p.add(cognome);
        p.add(email);
        p.add(nomeUtente);
        p.add(tipo);
        p.add(password);
        p.add(telefono);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(nome.getText() + " " + cognome.getText() + " " + email.getText() + " " + nomeUtente.getText() + " " + tipo.getSelectedItem().toString() + " " + password.getPassword() + " " + telefono.getText());
            }
        });
        return p;
    }
}

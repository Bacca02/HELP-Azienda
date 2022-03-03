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

    public HAUtente(String nomeUtente) {
        System.out.println("Utente");
        JPanel p = panel_ordini();
        JPanel p2 = panel_richieste();
        JPanel p3 = panel_magazzino();
        JPanel p4 = panel_dipendenti();

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
        JLabel label_utente = new JLabel("Utente: " + nomeUtente);
        label_utente.setBounds(20, 15, 200, 30);
        label_utente.setVisible(true);
        label_utente.setFont(font1);
        this.add(label_utente);

        btn_ordini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("ordini");
                p.setVisible(true);
                p2.setVisible(false);
                p3.setVisible(false);
                p4.setVisible(false);
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
                p.setVisible(false);
                p2.setVisible(true);
                p3.setVisible(false);
                p4.setVisible(false);
                System.out.println("richieste");
                //p3.setVisible(false);

            }
        });
        btn_magazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                p.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(true);
                p4.setVisible(false);
            }
        });

        btn_dipendenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("magazzino");
                p.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(false);
                p4.setVisible(true);
            }
        });
        this.setVisible(true);

    }

    public JPanel panel_ordini() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.green);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        JButton btn = new JButton("ordini");

        btn.setBounds(0, 0, 120, 50);
        btn.setVisible(true);
        p.add(btn);
        return p;
    }

    public JPanel panel_richieste() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.white);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        JButton btn = new JButton("richieste");

        btn.setBounds(0, 0, 120, 50);
        btn.setVisible(true);
        p.add(btn);
        return p;
    }

    public JPanel panel_magazzino() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.red);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);
        JButton btn = new JButton("magazzino");

        btn.setBounds(0, 0, 120, 50);
        btn.setVisible(true);
        p.add(btn);
        return p;
    }

    public JPanel panel_dipendenti() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.red);
        p.setBounds(200, 60, 1050, 680);
        this.add(p);

        JButton btn = new JButton("aggiungi dipendente");
        btn.setBounds((p.getWidth() / 2) - 60, 550, 120, 50);
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
                System.out.println(nome.getText()+" "+cognome.getText()+" "+email.getText()+" "+nomeUtente.getText()+" "+tipo.getSelectedItem().toString()+" "+password.getPassword()+" "+telefono.getText());
            }
        });
        return p;
    }
}

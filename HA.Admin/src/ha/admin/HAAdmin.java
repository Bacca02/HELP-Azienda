/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import ha.admin.Materiali.Materiale;
import ha.admin.Richieste.Richiesta;
import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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
    JScrollPane scrollp_ordini, scrollp_richieste, scrollp_magazzino;
    Connection con = null;
    Statement stmt = null;
    Richieste vettR = null;
    Materiali vettM = null;

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
        this.add(scrollp_magazzino);
        this.add(scrollp_ordini);
        this.add(scrollp_richieste);
        p1.setVisible(true);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
        p1_1.setVisible(true);
        scrollp_ordini.setVisible(true);
        scrollp_magazzino.setVisible(false);
        scrollp_richieste.setVisible(false);

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
//Bottone exit---------------------------------------------------------------------------------------------------------
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
//Bottoni operazioni---------------------------------------------------------------------------------------------------------
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
                scrollp_ordini.setVisible(true);
                scrollp_magazzino.setVisible(false);
                scrollp_richieste.setVisible(false);

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
                scrollp_ordini.setVisible(false);
                scrollp_magazzino.setVisible(false);
                scrollp_richieste.setVisible(true);

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
                scrollp_ordini.setVisible(false);
                scrollp_magazzino.setVisible(true);
                scrollp_richieste.setVisible(false);

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
                scrollp_ordini.setVisible(false);
                scrollp_magazzino.setVisible(false);
                scrollp_richieste.setVisible(false);
            }
        });
        this.setVisible(true);

    }
//Panel Ordini + shop----------------------------------------------------------------------------------------

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
        this.add(p);
        JButton btn = new JButton();
        btn.setBounds(10, 10, 140, 80);
        btn.setVisible(true);
        p.add(btn);
        return p;
    }
//Panel richieste ----------------------------------------------------------------------------------------

    public JPanel panel_richieste() {
        vettR = new Richieste();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //BLU MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        scrollp_richieste = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_richieste.setBounds(200, 60, 1050, 680);
        if (vettR.Riempi()) {
             p.setPreferredSize(new Dimension(2000, (200*vettR.getList().size())+30));
            boolean prova = false;
            for (int i = 0; i < vettR.getList().size(); i++) {
                prova = !prova;
                p.add(panel_richieste(i, prova, vettR.getList().get(i)));

            }
        } else {
             p.setPreferredSize(new Dimension(2000, 200*vettR.getList().size()));
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }

    public JPanel panel_richieste(int i, boolean prova, Richiesta R) {

        JPanel p = new JPanel();

        p.setLayout(null);
        p.setBounds(20, 20 + (200 * i), 1000, 190);

        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240)); //BLU MIGLIORE
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

        JLabel labelRichiesta = new JLabel(R.testo/*"Domanda da parte dell'utente aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"*/);
        labelRichiesta.setBounds(40, 40, 700, 100);
        labelRichiesta.setBackground(new Color(244, 121, 121)); //ROSSO
        labelRichiesta.setVisible(true);
        p.add(labelRichiesta);
        JLabel labelUtente = new JLabel("Utente: " + vettR.RichUtente(Integer.parseInt(R.Mittente)).nome);
        labelUtente.setBounds(10, 10, 700, 20);
        labelUtente.setVisible(true);
        p.add(labelUtente);
        return p;
    }
//Panel magazzino----------------------------------------------------------------------------------------

    public JPanel panel_magazzino() {
        vettM = new Materiali();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO
        p.setBounds(200, 60, 1050, 680);
       
        scrollp_magazzino = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_magazzino.setBounds(200, 60, 1050, 680);

        if (vettM.Riempi()) {
            boolean prova = false;
             p.setPreferredSize(new Dimension(2000, (200*vettM.getList().size())+50));
            for (int i = 0; i < vettM.getList().size(); i++) {
                prova = !prova;
                p.add(panel_prodotto(i, prova, vettM.getList().get(i)));
                
            }
        } else {
             p.setPreferredSize(new Dimension(2000, 200*vettM.getList().size()));
            JLabel tmp = new JLabel("Non c'è nulla da visualizzare");
            tmp.setBounds(10, 10, 200, 100);
            p.add(tmp);
        }

        return p;
    }

    public JPanel panel_prodotto(int i, boolean prova, Materiale M) {

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

                JLabel labelSezione = new JLabel(M.Posizione);
        labelSezione.setBounds(300, 50, 100, 100);
        labelSezione.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelSezione.setVisible(true);
        p.add(labelSezione);
        
        
        JLabel labelQuantita = new JLabel(M.Quantita+"");
        labelQuantita.setBounds(400, 50, 100, 100);
        labelQuantita.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelQuantita.setVisible(true);
        p.add(labelQuantita);
        
        
        JLabel labelRichiesta = new JLabel("Immagine");
        labelRichiesta.setBounds(800, 50, 100, 100);
        labelRichiesta.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelRichiesta.setVisible(true);

        labelRichiesta.setFocusable(false);
        try {
            Image img = ImageIO.read(new URL(M.IPath));
            labelRichiesta.setIcon((new ImageIcon((resizeImage((BufferedImage)(img), 1, 100, 100)))));
        } catch (IOException ex) {
            Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelRichiesta.setOpaque(false);
        //labelRichiesta.setContentAreaFilled(false);
        // labelRichiesta.setBorderPainted(false);
        p.add(labelRichiesta);
        return p;
    }
//Panel dipendenti----------------------------------------------------------------------------------------

    public JPanel panel_dipendenti() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
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
    public static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height) {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();
        return resizedImage;
    }
}

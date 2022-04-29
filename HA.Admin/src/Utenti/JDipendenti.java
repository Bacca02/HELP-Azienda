/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import Ordini.JOrdini;
import Utenti.Utenti.Utente;
import ha.admin.HAAdmin;
import ha.admin.HALogin;
import ha.admin.SERVER;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.json.*;

/**
 *
 * @author MAALFING
 */
public class JDipendenti {

    public JTextField nome = new JTextField("nome");
    public JTextField cognome = new JTextField("cognome");
    public JTextField email = new JTextField("email");
    public JTextField nomeUtente = new JTextField("nomeUtente");
    public JComboBox tipo = new JComboBox();
    public JPasswordField password = new JPasswordField();
    public JTextField telefono = new JTextField("telefono");
    public JScrollPane scrollp_dipendenti;
    public HAAdmin H;
    public JPanel aggiungi_dip, panel_dipendenti, panel_btn_dipendenti;
    Utenti vettU;

    public JDipendenti(HAAdmin H) {
        this.H = H;
        vettU = new Utenti();
        vettU.Riempi();
        panel_dipendenti = panel_dipendenti();
        aggiungi_dip = aggiungi_dipendente();
        panel_btn_dipendenti = panel_btn_utente();

    }

    public JPanel panel_dipendenti() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        p.setPreferredSize(new Dimension(2000, 2000));
        scrollp_dipendenti = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollp_dipendenti.setBounds(200, 60, 1050, 680);
        H.add(scrollp_dipendenti);
        boolean prova = false;
        for (int i = 0; i < vettU.vett.size(); i++) {
            prova = !prova;
            p.add(dati_utente(i, prova, vettU.vett.get(i)));
        }
        return p;
    }

    public JPanel aggiungi_dipendente() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        p.setBounds(200, 60, 1050, 680);
        H.add(p);

        JButton btn = new JButton("aggiungi dipendente");
        btn.setBounds((p.getWidth() / 2) - 60, 550, 120, 50);

        btn.setVisible(true);
        p.add(btn);

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
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    JSONObject json = null;
                    json = new JSONObject(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=U&Nome=" + nome.getText() + "&Cognome=" + cognome.getText() + "&Email=" + email.getText() + "&nomeUtente=" + nomeUtente.getText() + "&Tipo=" + tipo.getSelectedItem().toString() + "&Password=" + SERVER.getMd5(password.getText()) + "&Telefono=" + telefono.getText()));

                    if (json.get("Esito").equals("V")) {
                        JOptionPane.showMessageDialog(null, "Utente creato");
                        vettU.Riempi();

                        nome.setText("Nome");
                        cognome.setText("Cognome");
                        email.setText("E-Mail");
                        password.setText("Password");
                        telefono.setText("Telefono");
                        nomeUtente.setText("Nome utente");
                    } else {
                        JOptionPane.showMessageDialog(null, json.get("Motivo"), "ERRORE", 0);
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(nome.getText() + " " + cognome.getText() + " " + email.getText() + " " + nomeUtente.getText() + " " + tipo.getSelectedItem().toString() + " " + password.getPassword() + " " + telefono.getText());
            }
        });

        return p;
    }

    public JPanel dati_utente(int i, boolean prova, Utente U) {
        JPanel p = new JPanel();
        JLabel nome = new JLabel("Nome", SwingConstants.CENTER);
        JLabel cognome = new JLabel("Cognome", SwingConstants.CENTER);
        JLabel username = new JLabel("Username", SwingConstants.CENTER);
        JLabel tipo = new JLabel("Tipo", SwingConstants.CENTER);
        JLabel email = new JLabel("Email", SwingConstants.CENTER);
        JLabel telefono = new JLabel("Telefono", SwingConstants.CENTER);
        JLabel resetPassword = new JLabel("Reset", SwingConstants.CENTER);
        JLabel elimina = new JLabel("Elimina", SwingConstants.CENTER);
        Image img;
        p.setLayout(null);
        p.setBounds(20, 20 + (100 * i), 1000, 90);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }
//---------------------------------------------------------------------------------       
        nome.setOpaque(true);
        nome.setBackground(Color.white);
        nome.setBounds(0, 0, 160, 30);
        nome.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, 0, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        nome.setVisible(true);
        p.add(nome);

        cognome.setOpaque(true);
        cognome.setBackground(Color.white);
        cognome.setBounds(160, 0, 150, 30);
        cognome.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        cognome.setVisible(true);
        p.add(cognome);

        username.setOpaque(true);
        username.setBackground(Color.white);
        username.setBounds(310, 0, 200, 30);
        username.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        username.setVisible(true);
        p.add(username);

        tipo.setOpaque(true);
        tipo.setBackground(Color.white);
        tipo.setBounds(510, 0, 50, 30);
        tipo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        tipo.setVisible(true);
        p.add(tipo);

        email.setOpaque(true);
        email.setBackground(Color.white);
        email.setBounds(560, 0, 200, 30);
        email.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        email.setVisible(true);
        p.add(email);

        telefono.setOpaque(true);
        telefono.setBackground(Color.white);
        telefono.setBounds(760, 0, 100, 30);
        telefono.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        telefono.setVisible(true);
        p.add(telefono);

        resetPassword.setOpaque(true);
        resetPassword.setBackground(Color.white);
        resetPassword.setBounds(860, 0, 70, 30);
        resetPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        resetPassword.setVisible(true);
        p.add(resetPassword);

        elimina.setOpaque(true);
        elimina.setBackground(Color.white);
        elimina.setBounds(930, 0, 70, 30);
        elimina.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(-4, -4, -4, 0), BorderFactory.createLineBorder(Color.BLACK, 4)));;
        elimina.setVisible(true);
        p.add(elimina);

        //Nome Cognome NomeUtente Tipo
        //Email ResettaPassword Telefono
//-----------------------------------------------------------------------------------------------------------
        JLabel labelNome = new JLabel(U.nome, SwingConstants.CENTER);
        labelNome.setBounds(10, 40, 150, 40);
        labelNome.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelNome.setVisible(true);
        p.add(labelNome);

        JLabel labelCognome = new JLabel(U.cognome, SwingConstants.CENTER);
        labelCognome.setBounds(160, 40, 150, 40);
        labelCognome.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelCognome.setVisible(true);
        p.add(labelCognome);

        JLabel labelNomeUtente = new JLabel(U.username, SwingConstants.CENTER);
        labelNomeUtente.setBounds(310, 40, 200, 40);
        labelNomeUtente.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelNomeUtente.setVisible(true);
        p.add(labelNomeUtente);

        JLabel labelTipo = new JLabel(U.tipo, SwingConstants.CENTER);
        labelTipo.setBounds(510, 40, 50, 40);
        labelTipo.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelTipo.setVisible(true);
        p.add(labelTipo);

        JLabel labelEmail = new JLabel(U.email, SwingConstants.CENTER);
        labelEmail.setBounds(560, 40, 200, 40);
        labelEmail.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelEmail.setVisible(true);
        p.add(labelEmail);

        JLabel labelTelefono = new JLabel(U.telefono, SwingConstants.CENTER);
        labelTelefono.setBounds(760, 40, 100, 40);
        labelTelefono.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelTelefono.setVisible(true);
        p.add(labelTelefono);

        JButton btnPassword = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/reset.png"));
            btnPassword.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnPassword.setBounds(860, 40, 70, 40);
        btnPassword.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        btnPassword.setVisible(true);
        btnPassword.setOpaque(false);
        btnPassword.setContentAreaFilled(false);
        btnPassword.setBorderPainted(false);
        p.add(btnPassword);
        btnPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Resettata");
                try {
                    String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RPU&iD=" + Integer.toString(U.iD));
                    System.out.println("JSON: " + json);
                } catch (IOException ex) {
                    Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JButton btnElimina = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/cestino40.png"));
            btnElimina.setIcon(new ImageIcon(img));

        } catch (IOException ex) {
            Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnElimina.setBounds(930, 40, 70, 40);
        btnElimina.setVisible(true);
        btnElimina.setOpaque(false);
        btnElimina.setContentAreaFilled(false);
        btnElimina.setBorderPainted(false);
        p.add(btnElimina);

        btnElimina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ((SERVER.getUtenteByiD(U.iD).tipo.equals("Admin") || SERVER.getUtenteByiD(U.iD).tipo.equals("Utente")) && SERVER.getUtenteByiD(H.nUtente.iD).tipo.equals("SAdmin")) {
                    System.out.println("PUOI FARLO");
                    if (JOptionPane.showConfirmDialog(null, "Sei sicuro?", "ATTENZIONE", 0) == 0) {
                        try {
                            String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RU&iD=" + Integer.toString(U.iD));
                            vettU.Riempi();
                            panel_dipendenti.setVisible(false);
                            repaint(panel_dipendenti);
                            panel_dipendenti.setVisible(true);
                            System.out.println(json);
                            JOptionPane.showMessageDialog(null, "Utente eliminato");
                        } catch (IOException ex) {
                            Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Dipendente eliminato");
                    }
                } else if (SERVER.getUtenteByiD(U.iD).tipo.equals("Utente") && SERVER.getUtenteByiD(H.nUtente.iD).tipo.equals("Admin")) {
                    System.out.println("PUOI FARLO");
                    if (JOptionPane.showConfirmDialog(null, "Sei sicuro?", "ATTENZIONE", 0) == 0) {
                        try {
                            String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RU&iD=" + Integer.toString(U.iD));
                            vettU.Riempi();
                            panel_dipendenti.setVisible(false);
                            repaint(panel_dipendenti);
                            panel_dipendenti.setVisible(true);
                            System.out.println(json);
                            JOptionPane.showMessageDialog(null, "Utente eliminato");
                        } catch (IOException ex) {
                            Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Dipendente eliminato");
                    }
                } else {
                    System.out.println("NON PUOI FARLO");
                    JOptionPane.showMessageDialog(null, "Impossibile eliminare l'utente, non si dispone di abbastanza privilegi", "ERRORE", 0);
                }
                
            }
        });
        return p;

    }

    public JPanel panel_btn_utente() {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        p.setBounds(50, 640, 150, 100);
        H.add(p);
        JButton btn = new JButton("Add dipendente");
        btn.setBounds(10, 10, 140, 80);
        btn.setVisible(true);
        p.add(btn);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scrollp_dipendenti.setVisible(false);
                panel_dipendenti.setVisible(false);
                panel_btn_dipendenti.setVisible(false);
                aggiungi_dip.setVisible(true);
            }
        });
        return p;
    }

    public void repaint(JPanel p) {
        p.removeAll();
        for (int i = 0; i < vettU.vett.size(); i++) {
            p.add(dati_utente(i, true, vettU.vett.get(i)));
        }
    }

}

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
        vettU=new Utenti();
        vettU.Riempi();
        panel_dipendenti = panel_dipendenti();
        aggiungi_dip = aggiungi_dipendente();
        panel_btn_dipendenti = panel_btn_utente();

    }

    public String POSTUtente() throws IOException, InterruptedException {
        HttpURLConnection con = null;
        String url = "http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php";
        String urlParameters = "TipoI=U&Nome=" + nome.getText() + "&Cognome=" + cognome.getText() + "&Email=" + email.getText() + "&nomeUtente=" + nomeUtente.getText() + "&Tipo=" + tipo.getSelectedItem().toString() + "&Password=" + SERVER.getMd5(password.getText()) + "&Telefono=" + telefono.getText();
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString();

        } finally {

            con.disconnect();
        }

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

                    json = new JSONObject(POSTUtente());

                    if (json.get("Esito").equals("true")) {
                        JOptionPane.showMessageDialog(null, "Utente creato");
                    } else {
                        JOptionPane.showMessageDialog(null, json.get("Motivo"), "ERRORE", 0);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);

                } catch (InterruptedException ex) {
                    Logger.getLogger(JDipendenti.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(nome.getText() + " " + cognome.getText() + " " + email.getText() + " " + nomeUtente.getText() + " " + tipo.getSelectedItem().toString() + " " + password.getPassword() + " " + telefono.getText());
            }
        });

        return p;
    }

    public JPanel dati_utente(int i, boolean prova, Utente U) {
        JPanel p = new JPanel();
        Image img;
        p.setLayout(null);
        p.setBounds(20, 20 + (60 * i), 1000, 55);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240));
        }
        //Nome Cognome NomeUtente Tipo
        //Email ResettaPassword Telefono
        JLabel labelNome = new JLabel(U.nome, SwingConstants.CENTER);
        labelNome.setBounds(10, 0, 150, 55);
        labelNome.setBackground(new Color(244, 121, 121)); //ROSSO MIGLIORE
        labelNome.setVisible(true);
        p.add(labelNome);

        JLabel labelCognome = new JLabel(U.cognome, SwingConstants.CENTER);
        labelCognome.setBounds(160, 0, 150, 55);
        labelCognome.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelCognome.setVisible(true);
        p.add(labelCognome);

        JLabel labelNomeUtente = new JLabel(U.username, SwingConstants.CENTER);
        labelNomeUtente.setBounds(310, 0, 200, 55);
        labelNomeUtente.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelNomeUtente.setVisible(true);
        p.add(labelNomeUtente);

        JLabel labelTipo = new JLabel(U.tipo, SwingConstants.CENTER);
        labelTipo.setBounds(510, 0, 30, 55);
        labelTipo.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelTipo.setVisible(true);
        p.add(labelTipo);

        JLabel labelEmail = new JLabel(U.email, SwingConstants.CENTER);
        labelEmail.setBounds(540, 0, 220, 55);
        labelEmail.setBackground(new Color(244, 121, 121));//ROSSO MIGLIORE
        labelEmail.setVisible(true);
        p.add(labelEmail);

        JLabel labelTelefono = new JLabel(U.telefono, SwingConstants.CENTER);
        labelTelefono.setBounds(750, 0, 150, 55);
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
        btnPassword.setBounds(900, 10, 40, 40);
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
            }
        });
        JButton btnElimina = new JButton();
        try {
            img = ImageIO.read(getClass().getResource("../ha/admin/img/cestino40.png"));
            btnElimina.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(JOrdini.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnElimina.setBounds(950, 10, 40, 40);
        btnElimina.setVisible(true);
        btnElimina.setOpaque(false);
        btnElimina.setContentAreaFilled(false);
        btnElimina.setBorderPainted(false);
        p.add(btnElimina);
        
        btnElimina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Dipendente eliminato");
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
}

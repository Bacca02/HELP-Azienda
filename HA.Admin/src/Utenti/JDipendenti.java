/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;


import ha.admin.HAAdmin;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
    public JPanel aggiungi_dip;
    public JPanel p4;

    public JDipendenti(HAAdmin H) {
        this.H = H;
        p4 = panel_dipendenti();
        aggiungi_dip = aggiungi_dipendente();
    }

    public String POSTUtente() throws IOException, InterruptedException {
        HttpURLConnection con = null;
        String url = "http://jeanmonnetlucamarco.altervista.org/HPAzienda/insert.php";
        String urlParameters = "TipoI=U&Nome="+nome.getText() + "&Cognome=" + cognome.getText() + "&Email=" + email.getText() + "&nomeUtente=" + nomeUtente.getText() + "&Tipo=" + tipo.getSelectedItem().toString() + "&Password=" + password.getPassword() + "&Telefono=" + telefono.getText();
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
        JButton btn = new JButton("aggiungi");
        btn.setBounds((p.getWidth() / 2) - 60, 550, 120, 50);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p.setVisible(false);
                aggiungi_dip.setVisible(true);
                scrollp_dipendenti.setVisible(false);
            }
        });
        boolean prova = false;
        for (int i = 0; i < 30; i++) {
            prova = !prova;
            p.add(dati_utente(i, prova));
        }
        btn.setVisible(true);
        p.add(btn);

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
                    JSONObject json= null;
                    json= new JSONObject(POSTUtente());
                    
                    if (json.get("Esito").equals("true")) {
                        JOptionPane.showMessageDialog(null, "Utente creato");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, json.get("Motivo"),"ERRORE",0);
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

    public JPanel dati_utente(int i, boolean prova) {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(20, 20 + (50 * i), 1000, 50);
        if (prova == false) {
            p.setBackground(Color.white);
        } else {
            p.setBackground(new Color(134, 201, 240)); //BLU MIGLIORE
        }
        return p;
    }
}


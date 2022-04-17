/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import ha.admin.HAAdmin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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

//        btn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("aggiunto utente");
//                String sql = "INSERT INTO `utenti` (`iD`, `Nome`, `Cognome`, `e-mail`, `nome_utente`, `Password`, `Tipo`, `Telefono`) VALUES (NULL, '" + nome.getText() + "', '" + cognome.getText() + "', '" + email.getText() + "', '" + nomeUtente.getText() + "', '" + password.getText() + "', '" + tipo.getSelectedItem().toString() + "', '" + telefono.getText() + "');";
//                try {
//                    stmt.executeUpdate(sql);
//                    JOptionPane.showMessageDialog(null, "Utente creato");
//                    nome.setText("Nome");
//                    cognome.setText("Cognome");
//                    email.setText("E-mail");
//                    nomeUtente.setText("Nome utente");
//                    password.setText("");
//                    telefono.setText("Telefono");
//                    p.setVisible(false);
//                    p4.setVisible(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(HAAdmin.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });

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
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

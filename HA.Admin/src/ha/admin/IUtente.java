/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author baccaglini_christian
 */
public class IUtente extends JFrame {

    public IUtente() {
        //Elimina i bordi
        setUndecorated(true);
        //Imposto la finestra al massimo
        setSize(350, 500);
        this.setLocationRelativeTo(null);
        //setExtendedState(HAAdmin.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBounds(50, 50, 400, 500);
        this.setLayout(null);
        //setSize(400, 500);
        Color c = new Color(211, 245, 255);
        this.getContentPane().setBackground(c);
        //Bordo
        c = new Color(150, 245, 255);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, c));
        JLabel impostazioni = new JLabel("IMPOSTAZIONI", SwingConstants.CENTER);
        impostazioni.setBounds((400 / 2) - 50, 10, 100, 30);
        impostazioni.setVisible(true);
        this.add(impostazioni);

        JLabel nomeUtente = new JLabel("Nome", SwingConstants.CENTER);
        nomeUtente.setBounds((400 / 2) - 50, 30, 100, 30);
        nomeUtente.setVisible(true);
        this.add(nomeUtente);

        String[] petStrings = {"Cambia password", "Cambia nome"};
        JComboBox comboScelta = new JComboBox(petStrings);
        comboScelta.setBounds(45, 60, 130, 20);
        //petList.setSelectedIndex(4);
        //petList.addActionListener(petList);
        comboScelta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (comboScelta.getSelectedIndex()==0) {
                    panelCambioPassword(true);
                    panelCambioNome(false);
                }else if (comboScelta.getSelectedIndex()==1) {
                    panelCambioPassword(false);
                    panelCambioNome(true);
                }
            }
        }
        );
        
        
        comboScelta.setVisible(true);
        this.add(comboScelta);

        //panelCambioPassword(false);
        //panelCambioNome(true);
        
        
        JPanel panelUsername = new JPanel();

        panelUsername.setBounds(45, 60, 300, 380);
        panelUsername.setVisible(false);
        this.add(panelUsername);

        this.setVisible(true);
    }

    public JPanel panelCambioPassword(boolean stato) {
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(null);
        panelPassword.setBounds(45, 60, 300, 380);
        panelPassword.setVisible(stato);
        this.add(panelPassword);
        panelPassword.setLayout(null);
        JLabel vecchiaPassword = new JLabel("Vecchia password");
        JLabel nuovaPassword = new JLabel("Nuova password");
        JLabel reinserisciPassword = new JLabel("Reinserisci password");
        vecchiaPassword.setBounds(45, 50, 200, 30);
        nuovaPassword.setBounds(45, 150, 200, 30);
        reinserisciPassword.setBounds(45, 250, 200, 30);
        vecchiaPassword.setVisible(stato);
        nuovaPassword.setVisible(stato);
        reinserisciPassword.setVisible(stato);
        panelPassword.add(vecchiaPassword);
        panelPassword.add(nuovaPassword);
        panelPassword.add(reinserisciPassword);

        JTextField fieldVecchiaPassword = new JTextField();
        JTextField fieldNuovaPassword = new JTextField();
        JTextField fieldReinserisciPassword = new JTextField();

        fieldVecchiaPassword.setBounds(45, 80, 200, 30);
        fieldNuovaPassword.setBounds(45, 180, 200, 30);
        fieldReinserisciPassword.setBounds(45, 280, 200, 30);

        fieldVecchiaPassword.setVisible(stato);
        fieldNuovaPassword.setVisible(stato);
        fieldReinserisciPassword.setVisible(stato);

        panelPassword.add(fieldVecchiaPassword);
        panelPassword.add(fieldNuovaPassword);
        panelPassword.add(fieldReinserisciPassword);
        JButton annulla = new JButton("annulla");
        
        annulla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
            
        });
        
        JButton conferma = new JButton("conferma");
        annulla.setBounds(20, 340, 100, 20);
        annulla.setVisible(stato);

        conferma.setBounds(180, 340, 100, 20);
        conferma.setVisible(stato);
        panelPassword.add(annulla);
        panelPassword.add(conferma);
        return panelPassword;
    }
    
     public JPanel panelCambioNome(boolean stato) {
        JPanel panelNome = new JPanel();
        panelNome.setLayout(null);
        panelNome.setBounds(45, 60, 300, 380);
        panelNome.setVisible(stato);
        this.add(panelNome);
        panelNome.setLayout(null);
        
        JLabel nuovoNome = new JLabel("Nuovo nome");
        JLabel password = new JLabel("Password");
        nuovoNome.setBounds(45, 50, 200, 30);
        password.setBounds(45, 150, 200, 30);
        
        nuovoNome.setVisible(stato);
        password.setVisible(stato);

        panelNome.add(nuovoNome);
        panelNome.add(password);


        JTextField fieldNuovoNome = new JTextField();
        JTextField fieldPassword = new JTextField();

        fieldNuovoNome.setBounds(45, 80, 200, 30);
        fieldPassword.setBounds(45, 180, 200, 30);

        fieldNuovoNome.setVisible(stato);
        fieldPassword.setVisible(stato);

        panelNome.add(fieldNuovoNome);
        panelNome.add(fieldPassword);
        
        JButton annulla = new JButton("annulla");
        annulla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
            
        });
        JButton conferma = new JButton("conferma");
        annulla.setBounds(20, 340, 100, 20);
        annulla.setVisible(stato);

        conferma.setBounds(180, 340, 100, 20);
        conferma.setVisible(stato);
        panelNome.add(annulla);
        panelNome.add(conferma);
        
        
        conferma.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        
                annulla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        return panelNome;
    }
}

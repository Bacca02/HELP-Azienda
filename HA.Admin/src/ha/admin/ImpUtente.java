/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ha.admin;

import Utenti.Utenti;
import Utenti.Utenti.Utente;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author MAALFING
 */
public class ImpUtente extends javax.swing.JFrame {

    /**
     * Creates new form ImpUtente
     */
    //HAAdmin H;
    Utente U;
    Utenti vettU;
    int stato;
    public AttivaPulsante AP;

    public ImpUtente() {
        initComponents();
    }

    public ImpUtente(Utente U, Utenti vettU, int stato) {
        initComponents();
        Color c = new Color(211, 245, 255);
        getContentPane().setBackground(c);
        c = new Color(150, 245, 255);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, c));

        this.U = U;
        this.stato = stato;

        this.vettU = vettU;
        jLabel1.setText("<html><p><center><b>IMPOSTAZIONE DELL'UTENTE</b><br>" + U.nome + "</center></p></html>");
        label1.setText("Vecchia password:");
        label2.setText("Nuova password:");
        label3.setText("Reinserisci nuova password:");
        label1.show(true);
        label2.show(true);
        //label3.show(false);
        textField1.setVisible(true);
        textField2.setVisible(true);
        //textField3.setVisible(false);
        choice1.select(1);
        AP = new AttivaPulsante(this);

//        if (stato == 0) {
//            
//                textField1.setVisible(false);
//                label1.setVisible(false);
//                label2.setVisible(true);
//                textField2.setVisible(true);
//            
//        }
    }

    public void AvviaTread() {
        AP = new AttivaPulsante(this);
        AP.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        button1 = new java.awt.Button();
        choice1 = new java.awt.Choice();
        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        button2 = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setLocation(new java.awt.Point(150, 150));
        setUndecorated(true);
        setResizable(false);

        button1.setLabel("Conferma");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        choice1.add("Cambia nome");
        choice1.add("Cambia password");
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });

        textField3.setText("");
        textField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField3ActionPerformed(evt);
            }
        });

        label1.setBackground(new Color(211, 245, 255));
        label1.setText("label1");

        label2.setBackground(new Color(211, 245, 255));
        label2.setText("label1");

        label3.setBackground(new Color(211, 245, 255));
        label3.setText("label1");

        label4.setBackground(new Color(211, 245, 255));
        label4.setText("Seleziona un'azione");

        button2.setLabel("Annulla");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new Color(211, 245, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("IMPOSTAZIONE DELL'UTENTE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(68, 68, 68)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField3ActionPerformed

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        if (choice1.getSelectedIndex() == 1) {
            // JOptionPane.showMessageDialog(null, "Hai selezionato 1");
            if (stato == 0) {
                textField1.setVisible(false);
                label1.setVisible(false);
                label2.setVisible(true);
                textField2.setVisible(true);
                Button B = new Button("Reimposta password");
                
                B.setBounds(10, 10, 100, 20);
                B.setVisible(true);
            }
            label1.setText("Vecchia password:");
            label2.setText("Nuova password:");
            label3.setText("Reinserisci nuova password:");
//            label1.show(true);
//            label2.show(true);
            label3.show(true);
//            textField1.setVisible(true);
//            textField2.setVisible(true);
            textField3.setVisible(true);

        } else if (choice1.getSelectedIndex() == 0) {
            // JOptionPane.showMessageDialog(null, "Hai selezionato 0");
            if (stato == 0) {
                textField2.setVisible(false);
                label1.setVisible(true);
                label2.setVisible(false);
                textField1.setVisible(true);
            }
            label1.setText("Nuovo nome:");
            label2.setText("Password:");
//            label1.show(true);
//            label2.show(true);
            label3.show(false);
//            textField1.setVisible(true);
//            textField2.setVisible(true);
            textField3.setVisible(false);
        }
    }//GEN-LAST:event_choice1ItemStateChanged

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        AP.vai = false;
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");

    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:

        if (stato == 0) {

            if (choice1.getSelectedIndex() == 0) {

                try {
                    String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MNU&iD=" + U.iD + "&Nome=" + textField1.getText());
                    JSONObject js = new JSONObject(json);
                    if (js.getString("Esito").equals("V")) {
                        JOptionPane.showMessageDialog(null, "Nome modificato con successo", "HPAzienda", 1);
                        vettU.getByiD(U.iD).nome = textField1.getText();
                    }
                    System.out.println("CAMBIO NOME " + json);

                } catch (IOException ex) {
                    Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (choice1.getSelectedIndex() == 1) {

                try {
                    String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MPU&iD=" + U.iD + "&Pass=" + SERVER.getMd5(textField2.getText()));
                    JSONObject js = new JSONObject(json);
                    if (js.getString("Esito").equals("V")) {
                        JOptionPane.showMessageDialog(null, "Password modificata con successo", "HPAzienda", 1);
                    }
                    System.out.println("CAMBIO PASSWORD " + json);

                } catch (IOException ex) {
                    Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (stato == 1) {
            if (choice1.getSelectedIndex() == 0) {

                JSONObject js = null;
                try {
                    js = new JSONObject(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/accesso.php", "name=" + U.nome + "&pass=" + SERVER.getMd5(textField2.getText())));
                } catch (IOException ex) {
                    Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println(js);

                if (js.getString("Esito").equals("V")) {
                    try {
                        String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MNU&iD=" + U.iD + "&Nome=" + textField1.getText());
                        js = new JSONObject(json);
                        if (js.getString("Esito").equals("V")) {
                            JOptionPane.showMessageDialog(null, "Nome modificato con successo", "HPAzienda", 1);
                            vettU.getByiD(U.iD).nome = textField1.getText();
                        }
                        System.out.println("CAMBIO NOME " + json);

                    } catch (IOException ex) {
                        Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    String motivo = js.getString("Motivo");
                    JOptionPane.showMessageDialog(null, motivo, "ERRORE LOGIN", 0);
                }

            } else if (choice1.getSelectedIndex() == 1) {

                JSONObject js = null;
                if (!(textField2.getText().equals(textField3.getText()))) {
                    JOptionPane.showMessageDialog(null, "Le password non coincidono", "HPAzienda", 0);
                } else {
                    try {
                        js = new JSONObject(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/accesso.php", "name=" + U.nome + "&pass=" + SERVER.getMd5(textField1.getText())));
                    } catch (IOException ex) {
                        Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println(js);

                    if (js.getString("Esito").equals("V")) {
                        try {
                            String json = SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=MPU&iD=" + U.iD + "&Pass=" + SERVER.getMd5(textField2.getText()));
                            js = new JSONObject(json);
                            if (js.getString("Esito").equals("V")) {
                                JOptionPane.showMessageDialog(null, "Password modificata con successo", "HPAzienda", 1);
                            }
                            System.out.println("CAMBIO PASSWORD " + json);

                        } catch (IOException ex) {
                            Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ImpUtente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        String motivo = js.getString("Motivo");
                        JOptionPane.showMessageDialog(null, motivo, "ERRORE LOGIN", 0);
                    }

                }
            }
        }
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
    }//GEN-LAST:event_button1ActionPerformed

    public class AttivaPulsante extends Thread {

        public ImpUtente IU;
        public boolean vai = true;

        public AttivaPulsante(ImpUtente IU) {
            this.IU = IU;
        }

        @Override
        public void run() {
            while (vai) {

                if (stato == 0) {
                    if (choice1.getSelectedIndex() == 0) {
                        if (IU.textField1.getText().equals("")) {
                            IU.button1.setEnabled(false);
                        } else {
                            IU.button1.setEnabled(true);
                        }
                    } else if (choice1.getSelectedIndex() == 1) {
                        if (IU.textField2.getText().equals("") || IU.textField3.getText().equals("")) {
                            IU.button1.setEnabled(false);
                        } else {
                            IU.button1.setEnabled(true);
                        }
                    }
                } else if (stato == 1) {

                    if (choice1.getSelectedIndex() == 0) {
                        if (IU.textField1.getText().equals("") || IU.textField2.getText().equals("")) {
                            IU.button1.setEnabled(false);
                        } else {
                            IU.button1.setEnabled(true);
                        }
                    } else if (choice1.getSelectedIndex() == 1) {
                        if (IU.textField1.getText().equals("") || IU.textField2.getText().equals("") || IU.textField3.getText().equals("")) {
                            IU.button1.setEnabled(false);
                        } else {
                            IU.button1.setEnabled(true);
                        }
                    }
                }
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImpUtente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImpUtente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImpUtente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImpUtente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImpUtente().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Choice choice1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    public java.awt.TextField textField1;
    public java.awt.TextField textField2;
    public java.awt.TextField textField3;
    // End of variables declaration//GEN-END:variables
}

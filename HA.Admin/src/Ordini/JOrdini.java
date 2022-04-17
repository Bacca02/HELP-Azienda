/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordini;

import ha.admin.HAAdmin;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author MAALFING
 */
public class JOrdini {
    public JScrollPane scrollp_ordini;
    public JPanel p1, p1_1;
    public HAAdmin H;

    public JOrdini(HAAdmin H) {
        this.H=H;
        p1 = panel_ordini();
        p1_1= panel_btnOrdini();
        
    }
    
    
    
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
        H.add(p);
        JButton btn = new JButton();
        btn.setBounds(10, 10, 140, 80);
        btn.setVisible(true);
        p.add(btn);
        return p;
    }
}

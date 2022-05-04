/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fornitori;

import Fornitori.Fornitori.Fornitore;
import Ordini.JOrdini;
import ha.admin.SERVER;
import java.awt.*;
import java.util.List;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author MAALFING
 */
public class JFornitori extends JFrame {

    public JOrdini JO;
    public List<Fornitore> vett;

    public JFornitori(JOrdini JO) {
        setSize(500, 500);
        this.JO = JO;
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                JO.aperto=false;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //setUndecorated(true);
        this.setLocationRelativeTo(null);
        JPanel p= new JPanel();
        p.setBounds(0,0,this.getWidth(), this.getHeight()-70);
        vett=JO.F.vett;
        
        
        Panel fpanel = new Panel();
        fpanel.setLayout(new GridLayout(vett.size(),0));
        
        for (int i = 0; i < vett.size(); i++) {
            Panel singf=new Panel();
            
            Fornitore F =vett.get(i);
            Label L = new Label(F.nome);
            Button btn1=new Button("Modifica");
            Button btn2=new Button("Elimina");
            
            
            btn2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        System.out.println(SERVER.POSTData("http://jeanmonnetlucamarco.altervista.org/HPAzienda/multinsert.php", "TipoI=RF&iD="+F.iD));
                        JO.F.Riempi();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(JFornitori.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            
            singf.add(L);
            singf.add(btn1);
            singf.add(btn2);
            fpanel.add(singf);
        }
        ScrollPane scp = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scp.setSize(p.getWidth()-10, p.getHeight());
        scp.add(fpanel);
        p.add(scp);
        
        p.setVisible(true);
        
        Color c = new Color(211, 245, 255);
        this.getContentPane().setBackground(c);
        this.setLayout(null);
        this.add(p);
        Button B1 = new Button("Chiudi");
        
        B1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JO.aperto=false;
                dispose();
            }
        
        });
        
        B1.setBounds(5, this.getHeight()-60 , 100, 25);
        
        this.add(B1);
        Button B2 = new Button("Nuovo");
        
        B2.setBounds(this.getWidth()-115, this.getHeight()-60 , 100, 25);
        this.add(B2);

    }

}

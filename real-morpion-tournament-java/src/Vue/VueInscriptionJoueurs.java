/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.Observable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
/**
 *
 * @author grosa
 */
public class VueInscriptionJoueurs extends Observable{
    
    private JFrame window;
    private JButton retirer;
    private JButton rdj;
    private JButton ajouter;
    private JButton start;
    private JTextField nom;
    
    
    
    public VueInscriptionJoueurs(){
    
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        JPanel mainPanel=new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        
        JPanel leftPan=new JPanel();
        mainPanel.add(leftPan,BorderLayout.WEST);
        
        
        JPanel topleftPan=new JPanel();
        leftPan.add(topleftPan,BorderLayout.NORTH);
        
        
               
        //left part of the view with names
        
        JLabel lab1=new JLabel("Liste des inscrits:");
        topleftPan.add(lab1);
        
        
        
        JPanel downleftPan=new JPanel();
        leftPan.add(downleftPan,BorderLayout.SOUTH);
        
        //button in the bottom left
        retirer=new JButton("Retirer");
        downleftPan.add(retirer);
        
        JPanel midleftPan=new JPanel();
        leftPan.add(midleftPan,BorderLayout.CENTER);
        
        //list of name plates in the left
        DefaultListModel namelist=new DefaultListModel();
        
        JList names=new JList(namelist);
        
        namelist.addElement("dai2");
        namelist.addElement("dais");
        namelist.addElement("dai3");
        namelist.addElement("daih");
        
        midleftPan.add(names);
        


        
        

        
        //right part of the view with Options
        JPanel rightPan=new JPanel();
        mainPanel.add(rightPan,BorderLayout.EAST);
        
        
        
        JPanel toprightPan=new JPanel(new GridLayout(3,2));
        rightPan.add(toprightPan,BorderLayout.NORTH);
       
        
        
        //informations on the top right
        JLabel lbl;
        nom=new JTextField();
 
                lbl=new JLabel("Inscrivez les joueurs");
                toprightPan.add(lbl);
                
     
                lbl=new JLabel("");
                toprightPan.add(lbl);
                
       
                lbl=new JLabel("Ecrivez le nom du joueurs:");
                toprightPan.add(lbl);
                
             
                toprightPan.add(nom);
                
           
                lbl=new JLabel("Choisissez l'interface addaptée");
                toprightPan.add(lbl);
                
              
                lbl=new JLabel("à son age:");
                toprightPan.add(lbl);
               
        
        
        
        
        JPanel midrightPan=new JPanel();
        rightPan.add(midrightPan,BorderLayout.CENTER);
        
        // for sorting the interface by age
        JSlider sld=new JSlider();
        midrightPan.add(sld);
        
        
        // for the buttons on the bottom right
        JPanel downrightPan=new JPanel(new GridLayout(2,3));
        rightPan.add(downrightPan,BorderLayout.SOUTH);
        
             
         JLabel lbl2;
         ajouter=new JButton("Ajouter ce joueur");
         rdj=new JButton("Règeles du jeu");
         start=new JButton("Commencer");
        
            
   
                lbl2=new JLabel("");
                downrightPan.add(lbl2);
   
                lbl2=new JLabel("");
                downrightPan.add(lbl2);
  
               
                downrightPan.add(ajouter);
   
                lbl2=new JLabel("");
                downrightPan.add(lbl2);
       
                
                downrightPan.add(rdj);
           
                downrightPan.add(start);
        
        
    }
    
        public void afficher() {
        this.window.setVisible(true);
    }

    private JPanel getCellule(int i) {
        JPanel panelCellule = new JPanel();
        return panelCellule ;
    }

    public static void main(String [] args) {
        VueInscriptionJoueurs myihm = new VueInscriptionJoueurs();
        myihm.afficher();
   }   
    
}

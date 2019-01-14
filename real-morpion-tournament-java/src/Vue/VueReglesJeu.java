/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Utilitaires.Enums.EAction;
import Utilitaires.Messages.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
        
/**
 *
 * @author grosa
 */
public class VueReglesJeu extends Observable {
    private JFrame fenetre;
    private final int defaultWidth=400;
    private JButton fermer=new JButton("Fermer");
    private JButton suivant=new JButton("Suivant");
    
    public VueReglesJeu() {
        fenetre = new JFrame();
        fenetre.setTitle("Regles du Jeu");
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        fenetre.setSize(defaultWidth, (int)(defaultWidth*1.4));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        
        JPanel panePrincipale=new JPanel(new BorderLayout());
        
        JPanel paneImage=new JPanel(new GridLayout(2,1));
        //================met image ici==========================
        JPanel paneAvecImage=new JPanel();
        //===================================================
        JPanel paneAvecButton=new JPanel(new BorderLayout());
        
        paneImage.add(paneAvecImage);
        
        paneImage.add(paneAvecButton);
        
        paneAvecButton.add(fermer,BorderLayout.SOUTH);
        paneAvecButton.add(new JLabel(""),BorderLayout.CENTER);
        //====================== met les regle ici=================
        JPanel paneRegle=new JPanel(new BorderLayout());
        JPanel paneTitre=new JPanel();
        JPanel paneText=new JPanel();
        JPanel paneSuivent=new JPanel();
        paneTitre.add(new JLabel("Titre"));
        paneText.add(new JLabel(""));
        paneSuivent.add(suivant);
        
        paneRegle.add(paneTitre,BorderLayout.NORTH);
        paneRegle.add(paneText,BorderLayout.CENTER);
        paneRegle.add(paneSuivent,BorderLayout.SOUTH);
        
        panePrincipale.add(paneRegle,BorderLayout.CENTER);
        panePrincipale.add(paneImage,BorderLayout.WEST);
        
        
        fenetre.add(panePrincipale);
        
        
        fermer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              setChanged();
              notifyObservers(new Message(EAction.FERMER));
              clearChanged();
            }
        });
        
        
    }
    
 public void afficherFenetre(boolean afficher) {
        fenetre.setVisible(afficher);
    }
 
 

    
}

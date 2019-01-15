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
import javax.swing.JTextArea;
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
    private JTextArea regle=new JTextArea(5,20);
    
    private JLabel titre;
    public VueReglesJeu() {
        fenetre = new JFrame();
        fenetre.setTitle("Regles du Jeu");
        // Définit la taille de la fenêtre en pixels
        fenetre.setSize(defaultWidth, (int)(defaultWidth*0.7));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        
        JPanel panePrincipale=new JPanel(new BorderLayout());
        
        JPanel paneImage=new JPanel(new GridLayout(2,1));
        //================met image ici==========================
        JPanel paneAvecImage=new JPanel();
        //===================================================
        JPanel paneAvecButton=new JPanel(new BorderLayout());
        
        paneImage.add(paneAvecImage);
        paneImage.setBorder(new MatteBorder(0, 0, 1, 1, Color.GRAY));
        paneImage.add(paneAvecButton);
        
        paneAvecButton.add(fermer,BorderLayout.SOUTH);
        paneAvecButton.add(new JLabel(""),BorderLayout.CENTER);
        //====================== met les regle ici=================
        JPanel paneRegle=new JPanel(new BorderLayout());
        JPanel paneTitre=new JPanel();
        JPanel paneText=new JPanel();
        JPanel paneSuivent=new JPanel();
        titre = new JLabel();
        paneTitre.add(titre);
        
        regle.setText("Le but du jeu de morpion est\n"+" d’avoir un alignement en ligne\n"+" ou en diagonale de trois symboles\n"+" identiques. Pour cela, lorsque\n"+" c’est votre tour (votre nom et\n"+" votre symbole est écrit en haut\n"+" du plateau de jeu lorsque c’est votre\n"+" tour), cliquez sur une des cases\n"+" du plateau pour poser votre symbole.\n");
        paneText.add(regle);
        paneSuivent.add(suivant);
        
        paneRegle.add(paneTitre,BorderLayout.NORTH);
        paneRegle.add(paneText,BorderLayout.CENTER);
        paneRegle.add(paneSuivent,BorderLayout.SOUTH);
        
        panePrincipale.add(paneRegle,BorderLayout.CENTER);
        panePrincipale.add(paneImage,BorderLayout.WEST);
        
        
        fenetre.add(panePrincipale);
        
        
        suivant.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              setChanged();
              notifyObservers(new Message(EAction.CONTINUER));
              clearChanged();
            }
        });
        
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
 
 public void afficherRegleMorpion(){
     titre.setText("Regle du Morpion");
     regle.setText("Le but du jeu de morpion est\n"+" d’avoir un alignement en ligne\n"+" ou en diagonale de trois symboles\n"+" identiques. Pour cela, lorsque\n"+" c’est votre tour (votre nom et\n"+" votre symbole est écrit en haut\n"+" du plateau de jeu lorsque c’est votre\n"+" tour), cliquez sur une des cases\n"+" du plateau pour poser votre symbole.\n");
 
    }

 public void afficherRegleTournoi(){
     titre.setText("Regle du Tournoi");
     regle.setText("Pour le tournoi, l’ensemble des\n"+"joueurs joue contre tous les autres\n"+" une fois. Pour suivre le déroulement\n"+" du tournoi, observez le panneau \n"+"“Confrontations” à droite. Le but\n"+" du tournoi est d’avoir le plus de points.\n"+" A chaque égalité, les adversaires\n"+" gagnent 1 points chacuns, et\n"+" à chaque victoire, le gagnant gagne\n"+" trois points. Le panneau “Classement”\n"+" se trouve à gauche.\n");


    } 

    
}

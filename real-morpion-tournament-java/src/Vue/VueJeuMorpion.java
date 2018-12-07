/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.util.Observer;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
        
/**
 *
 * @author grosa
 */
public class VueJeuMorpion extends Observable {
    
    private JFrame fenetre;
    private final int defaultWidth = 400;
    
    private JLabel nomJoueurCourant;
    private JPanel iconeSymboleActuel;
    
    public VueJeuMorpion() {
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        fenetre.setSize(defaultWidth, (int)(defaultWidth*1.4)-30); //Ratio 1:1.618
        fenetre.setTitle("Grille de jeu");
        
        JPanel agencementFenetre = new JPanel(new BorderLayout(0, 10));
        
        JPanel panelNord = new JPanel(new BorderLayout(0, 10));
        JPanel panelPresentationJoueur = new JPanel(new BorderLayout(0, 0));
        
        JLabel texteTourDe = new JLabel("Tour de :", SwingConstants.CENTER);
        nomJoueurCourant = new JLabel("Jean Jacques", SwingConstants.CENTER);
        iconeSymboleActuel = new JPanel();
        
        JPanel barre = new JPanel();
        barre.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        
        panelPresentationJoueur.add(texteTourDe, BorderLayout.WEST);
        panelPresentationJoueur.add(nomJoueurCourant, BorderLayout.CENTER);
        panelPresentationJoueur.add(iconeSymboleActuel, BorderLayout.EAST);
        
        panelNord.add(panelPresentationJoueur, BorderLayout.CENTER);
        panelNord.add(barre, BorderLayout.SOUTH);
        
        
        JPanel pannelGrille = new JPanel(new GridLayout(3, 3));
        pannelGrille.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton("_");
            button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
            
            button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers("s");
                clearChanged();
            }});
            
            pannelGrille.add(button);
        }
        
        
        JPanel pannelBoutons = new JPanel(new BorderLayout(0, 0));
        
        JButton boutonAnnuller = new JButton("Corriger le tour précédent");
        JButton boutonRegles = new JButton("Règles du Jeu");
        
        pannelBoutons.add(boutonAnnuller, BorderLayout.WEST);
        pannelBoutons.add(boutonRegles, BorderLayout.EAST);
        
        
        agencementFenetre.add(panelNord, BorderLayout.NORTH);
        agencementFenetre.add(pannelGrille, BorderLayout.CENTER);
        agencementFenetre.add(pannelBoutons, BorderLayout.SOUTH);
        
        fenetre.add(agencementFenetre);
    }
    
    public void setNomJoueurCourant(String nomJ) {
        nomJoueurCourant.setText(nomJ);
    }
    
    public void afficherFenetre(boolean afficher) {
        fenetre.setVisible(afficher);
    }
}

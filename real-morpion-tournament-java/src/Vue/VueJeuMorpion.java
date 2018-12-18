/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Utilitaires.Enums.EAction;
import Utilitaires.Enums.EEtatCase;
import Utilitaires.Enums.MyButton;
import Utilitaires.Messages.MClicCase;
import Utilitaires.Messages.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private JLabel iconeSymboleActuel;
    
    private MyButton bCases[][];
    
    public VueJeuMorpion() {
        fenetre.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        bCases = new MyButton[3][3];
        
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
        iconeSymboleActuel = new JLabel();
        
        JPanel barre = new JPanel();
        barre.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        
        panelPresentationJoueur.add(texteTourDe, BorderLayout.WEST);
        panelPresentationJoueur.add(nomJoueurCourant, BorderLayout.CENTER);
        panelPresentationJoueur.add(iconeSymboleActuel, BorderLayout.EAST);
        
        panelNord.add(panelPresentationJoueur, BorderLayout.CENTER);
        panelNord.add(barre, BorderLayout.SOUTH);
        
        
        JPanel pannelGrille = new JPanel(new GridLayout(3, 3));
        pannelGrille.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        for (int ligne = 2; ligne >= 0; ligne--) {
            for (int colonne = 0; colonne < 3; colonne++) {
                MyButton button = new MyButton("_", ligne, colonne);
                button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();

                        notifyObservers(new MClicCase(EAction.CLIC_CASE, ((MyButton) e.getSource()).getLigne(), ((MyButton) e.getSource()).getColonne()));
                        clearChanged();
                    }});
                bCases[ligne][colonne] = button;
                pannelGrille.add(button);
            }
        }
        
        
        JPanel pannelBoutons = new JPanel(new BorderLayout(0, 0));
        
        JButton boutonAnnuller = new JButton("Corriger le tour précédent");
        boutonAnnuller.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(new Message(EAction.RETOUR));
                        clearChanged();
                    }});
        JButton boutonRegles = new JButton("Règles du Jeu");
        boutonRegles.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(new Message(EAction.REGLES_JEU));
                        clearChanged();
                    }});
        
        pannelBoutons.add(boutonAnnuller, BorderLayout.WEST);
        pannelBoutons.add(boutonRegles, BorderLayout.EAST);
        
        
        agencementFenetre.add(panelNord, BorderLayout.NORTH);
        agencementFenetre.add(pannelGrille, BorderLayout.CENTER);
        agencementFenetre.add(pannelBoutons, BorderLayout.SOUTH);
        
        fenetre.add(agencementFenetre);
    }
    
    public void setEtatCase(EEtatCase etat, int ligne, int colonne) {
        
        switch (etat) {
            case VIDE:
                bCases[ligne][colonne].setText("_");
                break;
            case CROIX:
                bCases[ligne][colonne].setText("X");
                break;
            case ROND:
                bCases[ligne][colonne].setText("O");
                break;
        }
    }
    
    public void setNomJoueurCourant(String nomJ) {
        nomJoueurCourant.setText(nomJ);
    }
    
    public void setSymboleJoueurCourant(EEtatCase etat) {
        switch (etat) {
            case CROIX:
                iconeSymboleActuel.setText("X");
                break;
            case ROND:
                iconeSymboleActuel.setText("O");
                break;
        }
    }
    
    public void afficherFenetre(boolean afficher) {
        fenetre.setVisible(afficher);
    }
}

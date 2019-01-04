/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Utilitaires.Enums.EAction;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import Utilitaires.Messages.MInscriptionJoueurs;
import Utilitaires.Messages.Message;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

/**
 *
 * @author grosa
 */
public class VueInscriptionJoueurs extends Observable{
    
    private JFrame fenetre;
    
    private JButton boutonAjouter;
    private JButton boutonRetirer;
    private JButton boutonReglesJeu;
    private JButton boutonCommencer;
    private JTextField textFieldNom;
    private JList listeNomsJoueurs;
    private DefaultListModel nomsListModel;
    
    
    public VueInscriptionJoueurs() {
        fenetre = new JFrame("Inscrire les joueurs");
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setResizable(true);
        
        JPanel pannelPrincipal = new JPanel(new BorderLayout(10, 0));
        fenetre.add(pannelPrincipal);
        
        // =========== PANNEL DE GAUCHE ==========
        JPanel pannelGauche = new JPanel(new BorderLayout(10, 10));
        pannelPrincipal.add(pannelGauche, BorderLayout.WEST);
        pannelGauche.setBorder(new MatteBorder(0, 0, 0, 1, Color.GRAY));
        
        // ----- Haut --------
        JLabel labelListeInscrits = new JLabel("Liste des inscrits:");
        pannelGauche.add(labelListeInscrits, BorderLayout.NORTH);
        
        // ----- Centre ------
        nomsListModel = new DefaultListModel();
        nomsListModel.setSize(1);
        listeNomsJoueurs = new JList(nomsListModel);
        
        JScrollPane midleftPan = new JScrollPane(listeNomsJoueurs);
      
        pannelGauche.add(midleftPan, BorderLayout.CENTER);
        
        // ----- Bas ------
        boutonRetirer = new JButton("Retirer");
        pannelGauche.add(boutonRetirer, BorderLayout.SOUTH);
        
        // =========== PANNEL DE DROITE ==========
        JPanel pannelDroite = new JPanel(new BorderLayout(0, 20));
        pannelPrincipal.add(pannelDroite);
        
        // --- Pannel haut ----
        JPanel pannelDroitHaut = new JPanel(new BorderLayout());
        pannelDroite.add(pannelDroitHaut, BorderLayout.NORTH);
        
        // Sous-pannel haut haut:
        JLabel labelInscrivezJoueurs = new JLabel("Inscrivez les joueurs");
        labelInscrivezJoueurs.setFont(new Font("Arial", Font.BOLD, 25));
        pannelDroitHaut.add(labelInscrivezJoueurs, BorderLayout.NORTH);
        
        // Sous-panel haut centre:
        JPanel sousPanelDroitHautCentre = new JPanel(new BorderLayout(10, 30));
        pannelDroitHaut.add(sousPanelDroitHautCentre, BorderLayout.CENTER);
        
        sousPanelDroitHautCentre.add(new JLabel("Ecrivez le nom du joueurs:"), BorderLayout.WEST);
        
        textFieldNom = new JTextField(20);
        sousPanelDroitHautCentre.add(textFieldNom, BorderLayout.EAST);
        
        // Sous panel haut bas
        JPanel sousPanelDroitHautBas = new JPanel(new BorderLayout(10, 20));
        pannelDroitHaut.add(sousPanelDroitHautBas, BorderLayout.SOUTH);
        
        JLabel labelInterfaceAdaptee = new JLabel("Choisissez l'interface "
                                                  + "adaptée à son âge:");
        sousPanelDroitHautBas.add(labelInterfaceAdaptee, BorderLayout.NORTH);
        
        JSlider curseurAge = new JSlider(0,3);
        curseurAge.setPaintLabels(true);
        
        Hashtable position = new Hashtable();
        position.put(0, new JLabel("Enfant"));
        position.put(1, new JLabel("Ado"));
        position.put(2, new JLabel("Adulte"));
        position.put(3, new JLabel("Senior"));
        
        curseurAge.setLabelTable(position);
      
        JPanel panelCurseur = new JPanel();
        panelCurseur.add(curseurAge);
        
        sousPanelDroitHautBas.add(curseurAge, BorderLayout.CENTER);
        
        boutonAjouter = new JButton("Ajouter ce joueur");
        boutonAjouter.setSize(80, 10);
        sousPanelDroitHautBas.add(boutonAjouter, BorderLayout.SOUTH);
        
        // --------- Panel Bas -----------
        JPanel downrightPan = new JPanel(new GridLayout(1,3,5,5));
        pannelDroite.add(downrightPan, BorderLayout.SOUTH);
         
        boutonReglesJeu = new JButton("Règles du jeu");
        
        boutonCommencer = new JButton("Commencer");
        
        downrightPan.add(new JPanel());
        downrightPan.add(boutonReglesJeu);
        downrightPan.add(boutonCommencer);
        
        // Création des actions:
        boutonRetirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                ArrayList<String> listeJoueursSelectionnes = new ArrayList(listeNomsJoueurs.getSelectedValuesList());
                notifyObservers(new MInscriptionJoueurs(EAction.SUPPRIMER, listeJoueursSelectionnes));
                clearChanged();
            }
         });
        
        boutonAjouter.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               setChanged();
               ArrayList <String> noms = new ArrayList();
               noms.add(textFieldNom.getText());
               MInscriptionJoueurs ins = new MInscriptionJoueurs(EAction.AJOUTER, noms);
               notifyObservers(ins);
               clearChanged();
            }
        });
        
        boutonReglesJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(EAction.REGLES_JEU));
                clearChanged();
            }
        });
        
        boutonCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(EAction.CONTINUER));
                clearChanged();
            }
        });
    }
    
    public void afficherFenetre(boolean aff) {
        fenetre.setVisible(aff);
        textFieldNom.requestFocusInWindow();
    }
        
    public void addJoueur(String nom) {
        nomsListModel.addElement(nom);
        textFieldNom.setText("");
        textFieldNom.requestFocusInWindow();
    }
    
    public void removeJoueur(String nom) {
        nomsListModel.removeElement(nom);
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return fenetre;
    }
    
}

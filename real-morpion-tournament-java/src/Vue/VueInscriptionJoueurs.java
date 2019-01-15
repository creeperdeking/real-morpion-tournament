/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Utilitaires.Enums.EAction;
import Utilitaires.Enums.ECategorieAge;
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
import java.awt.Image;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 *
 * @author grosa
 */
public class VueInscriptionJoueurs extends Observable{
    
    private JFrame fenetre;
    
    private JButton boutonRetirer;
    private JButton boutonReglesJeu;
    private JButton boutonCommencer;
    
    private JButton bEnfant;
    private JButton bAdo;
    private JButton bAdulte;
    private JButton bSenior;
    
    private JTextField textFieldNom;
    private JList listeNomsJoueurs;
    private DefaultListModel nomsListModel;
    
    private int imgSize = 30;
    private String path = "src/images/";
    
    private ImageIcon enfant = new ImageIcon(new ImageIcon(path+"enfant.png").getImage().getScaledInstance(imgSize, imgSize*2, Image.SCALE_DEFAULT));
    private ImageIcon ado = new ImageIcon(new ImageIcon(path+"ado.png").getImage().getScaledInstance(imgSize, imgSize*2, Image.SCALE_DEFAULT));
    private ImageIcon adulte = new ImageIcon(new ImageIcon(path+"adulte.png").getImage().getScaledInstance(imgSize, imgSize*2, Image.SCALE_DEFAULT));
    private ImageIcon senior = new ImageIcon(new ImageIcon(path+"senior.png").getImage().getScaledInstance(imgSize, imgSize*2, Image.SCALE_DEFAULT));
    
    public VueInscriptionJoueurs() {
        fenetre = new JFrame("Inscrire les joueurs");
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(650, 300);
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
        sousPanelDroitHautCentre.setBorder(new EmptyBorder(10,0,0,0));
        pannelDroitHaut.add(sousPanelDroitHautCentre, BorderLayout.CENTER);
        
        sousPanelDroitHautCentre.add(new JLabel("Ecrivez le nom du joueurs:"), BorderLayout.WEST);
        
        textFieldNom = new JTextField(20);
        sousPanelDroitHautCentre.add(textFieldNom, BorderLayout.EAST);
        
        // Sous panel haut bas
        JPanel sousPanelDroitHautBas = new JPanel(new BorderLayout(10, 20));
        pannelDroitHaut.add(sousPanelDroitHautBas, BorderLayout.SOUTH);
        
        JLabel labelInterfaceAdaptee = new JLabel("Choisissez l'interface "
                                                  + "adaptée à son âge:");
        labelInterfaceAdaptee.setBorder(new EmptyBorder(10,0,0,0));
        sousPanelDroitHautBas.add(labelInterfaceAdaptee, BorderLayout.NORTH);
        
        JPanel panelCurseur = new JPanel(new GridLayout(1,4));
        
        bEnfant = new JButton("Enfant");
        bEnfant.setIcon(enfant);
        bAdo = new JButton("Ado");
        bAdo.setIcon(ado);
        bAdulte = new JButton("Adulte");
        bAdulte.setIcon(adulte);
        bSenior = new JButton("Senior");
        bSenior.setIcon(senior);
        
        panelCurseur.add(bEnfant);
        panelCurseur.add(bAdo);
        panelCurseur.add(bAdulte);
        panelCurseur.add(bSenior);
        
        sousPanelDroitHautBas.add(panelCurseur, BorderLayout.CENTER);
        
        
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
        
        bEnfant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MInscriptionJoueurs(EAction.AJOUTER, textFieldNom.getText(), ECategorieAge.ENFANT));
                clearChanged();
            }
         });
        
        bAdo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MInscriptionJoueurs(EAction.AJOUTER, textFieldNom.getText(), ECategorieAge.ADO));
                clearChanged();
            }
         });
        
        bAdulte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MInscriptionJoueurs(EAction.AJOUTER, textFieldNom.getText(), ECategorieAge.ADULTE));
                clearChanged();
            }
         });
        
        bSenior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MInscriptionJoueurs(EAction.AJOUTER, textFieldNom.getText(), ECategorieAge.SENIOR));
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

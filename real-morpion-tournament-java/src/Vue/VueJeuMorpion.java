/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Utilitaires.Enums.EAction;
import Utilitaires.Enums.ECategorieAge;
import Utilitaires.Enums.EEtatCase;
import Utilitaires.Enums.MyButton;
import Utilitaires.Messages.MBouge;
import Utilitaires.Messages.MClicCase;
import Utilitaires.Messages.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
        
/**
 *
 * @author grosa
 */
public class VueJeuMorpion extends Observable implements ComponentListener{
    
    private JFrame fenetre;
    private final int defaultWidth = 400;
    
    private Point position = new Point();
    
    private JLabel nomJoueurCourant;
    private JLabel iconeSymboleActuel;
    
    private MyButton bCases[][];
    
    private ECategorieAge categorieAge;
    
    private int imgSize = 70;
    private String path = "src/images/";
    private ImageIcon image_croix;
    private ImageIcon image_rond;
    
    private ImageIcon croix_enfant = new ImageIcon(new ImageIcon(path+"croix_enfant.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon croix_ado = new ImageIcon(new ImageIcon(path+"croix_ado.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon croix_adulte = new ImageIcon(new ImageIcon(path+"croix_adulte.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon croix_senior = new ImageIcon(new ImageIcon(path+"croix_senior.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon rond_enfant = new ImageIcon(new ImageIcon(path+"rond_enfant.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon rond_ado = new ImageIcon(new ImageIcon(path+"rond_ado.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon rond_adulte = new ImageIcon(new ImageIcon(path+"rond_adulte.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon rond_senior = new ImageIcon(new ImageIcon(path+"rond_senior.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon vide = new ImageIcon(new ImageIcon(path+"vide.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    
    public VueJeuMorpion() {
        bCases = new MyButton[3][3];
        
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        fenetre.setSize(defaultWidth, (int)(defaultWidth*1.4)-30); //Ratio 1:1.618
        fenetre.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        position = fenetre.getLocation();
        fenetre.setTitle("Grille de jeu");
        
        JPanel agencementFenetre = new JPanel(new BorderLayout(0, 10));
        
        JPanel panelNord = new JPanel(new BorderLayout(0, 10));
        JPanel panelPresentationJoueur = new JPanel(new BorderLayout(0, 0));
        
        JLabel texteTourDe = new JLabel("Tour de :", SwingConstants.CENTER);
        texteTourDe.setFont(new Font("Arial", Font.BOLD, 20));
        nomJoueurCourant = new JLabel("Jean Jacques", SwingConstants.CENTER);
        nomJoueurCourant.setFont(new Font("Arial", Font.BOLD, 15));
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
                MyButton button = new MyButton(vide, ligne, colonne);
                button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();

                        notifyObservers(new MClicCase(EAction.CLIC_CASE, ((MyButton) e.getSource()).getLigne(), ((MyButton) e.getSource()).getColonne()));
                        clearChanged();
                    }});

                button.addMouseListener(new MouseListener() {
                    public void mouseEntered(MouseEvent e) {
                        notifyObservers(new MClicCase(EAction.HOVER, ((MyButton) e.getSource()).getLigne(), ((MyButton) e.getSource()).getColonne()));
                    }
                    public void mouseExited(MouseEvent e) {
                        notifyObservers(new MClicCase(EAction.EXIT_HOVER, ((MyButton) e.getSource()).getLigne(), ((MyButton) e.getSource()).getColonne()));
                    }
                    public void mouseReleased(MouseEvent e) {}
                    public void mousePressed(MouseEvent e) {}
                    public void mouseClicked(MouseEvent e) {}
                    });
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
        
        fenetre.addComponentListener(this);
    }
    
    @Override
    public void componentMoved(ComponentEvent arg0) {
        setChanged();
        notifyObservers(new MBouge(EAction.BOUGE, getPosition().x - position.x, getPosition().y - position.y));
        position = fenetre.getLocation();
        clearChanged();
    }
    
    public void setHover(EEtatCase symboleJoueur, int ligne, int colonne) {
        
    }
    
    public void stopHover(int ligne, int colonne) {
        
    }
    
    @Override
    public void componentResized(ComponentEvent arg0) {}
    @Override
    public void componentShown(ComponentEvent arg0) {}
    @Override
    public void componentHidden(ComponentEvent arg0) {}
    
    public void setPosition(int x, int y) {
        fenetre.setLocation(x, y);
        position = fenetre.getLocation();
    }
    
    public Point getPosition() {
        return fenetre.getLocation();
    }
    
    public void setEtatCase(EEtatCase etat, int ligne, int colonne) {
        
        switch (etat) {
            case VIDE:
                bCases[ligne][colonne].setIcon(vide);
                break;
            case CROIX:
                bCases[ligne][colonne].setIcon(image_croix);
                break;
            case ROND:
                bCases[ligne][colonne].setIcon(image_rond);
                break;
        }
    }
    
    public void setNomJoueurCourant(String nomJ) {
        nomJoueurCourant.setText(nomJ);
    }
    
    public void setSymboleJoueurCourant(EEtatCase etat) {
        switch (etat) {
            case CROIX:
                iconeSymboleActuel.setIcon(image_croix);
                break;
            case ROND:
                iconeSymboleActuel.setIcon(image_rond);
                break;
        }
    }
    
    public void setCategorieAge(ECategorieAge catAge) {
        categorieAge = catAge;
        switch (catAge) {
            case ENFANT:
                image_croix = croix_enfant;
                image_rond = rond_enfant;
                break;
            case ADO:
                image_croix = croix_ado;
                image_rond = rond_ado;
                break;
            case ADULTE:
                image_croix = croix_adulte;
                image_rond = rond_adulte;
                break;
            case SENIOR:
                image_croix = croix_senior;
                image_rond = rond_senior;
                break;
            default:
                break;
        }
    }
    
    public void reinitialiserGrille() {
        for (int ligne = 2; ligne >= 0; ligne--) {
            for (int colonne = 0; colonne < 3; colonne++) {
                bCases[ligne][colonne].setIcon(vide);
            }
        }
    }
    
    public void afficherFenetre(boolean afficher) {
        fenetre.setVisible(afficher);
    }

    /**
     * @return the defaultWidth
     */
    public int getDefaultWidth() {
        return defaultWidth;
    }
}

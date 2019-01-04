/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Observable;
import javax.swing.JScrollPane;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;

/**
 *
 * @author grosa
 */
public class VueConfrontations extends Observable {
    private JFrame fenetre;
    private JLabel labelNbMatchs;
    private ArrayList<JLabel> iconesMatchs = new ArrayList();
    private int matchActuel = 0;
    private int nbMatchsTotal;
    private JScrollPane scrollPane;
    
    private String path = "src/images/";
    private int imgSize = 100;
    private ImageIcon depart_bleu = new ImageIcon(new ImageIcon(path+"depart_bleu.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon depart_blanc = new ImageIcon(new ImageIcon(path+"depart_blanc.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon milieu_bleu = new ImageIcon(new ImageIcon(path+"milieu_bleu.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon milieu_blanc = new ImageIcon(new ImageIcon(path+"milieu_blanc.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon fin_bleu = new ImageIcon(new ImageIcon(path+"fin_bleu.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
    private ImageIcon fin_blanc = new ImageIcon(new ImageIcon(path+"fin_blanc.png").getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));

    public VueConfrontations(ArrayList<String[]> confrontations) {
        nbMatchsTotal = confrontations.size();
        
        fenetre = new JFrame("Confrontations");
        
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(250, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setResizable(true);
        
        JPanel framePrincipale = new JPanel(new BorderLayout());
        
        
        JPanel panelHaut = new JPanel(new BorderLayout());
        JLabel labelEnTete = new JLabel("Nombre de matchs restant: ");
        
        labelNbMatchs = new JLabel(String.valueOf(nbMatchsTotal));
        
        panelHaut.add(labelEnTete, BorderLayout.WEST);
        panelHaut.add(labelNbMatchs, BorderLayout.EAST);
        
        framePrincipale.add(panelHaut, BorderLayout.NORTH);
        
        int nbLignes = nbMatchsTotal;
        if (nbLignes < 6)
            nbLignes = 6;
        JPanel panelScrollPan = new JPanel(new GridLayout(nbLignes, 1));
        
        for (String[] match : confrontations) {
            JPanel panelLigne = new JPanel(new BorderLayout());
            JLabel nomsJoueurs = new JLabel(match[0]+" vs "+match[1]);
            nomsJoueurs.setHorizontalAlignment(JLabel.CENTER);
            JLabel iconeMatch = new JLabel(milieu_blanc);
            if (match == confrontations.get(0))
                iconeMatch = new JLabel(depart_bleu);
            else if (match == confrontations.get(confrontations.size()-1))
                iconeMatch = new JLabel(fin_blanc);

            iconesMatchs.add(iconeMatch);
            panelLigne.add(nomsJoueurs, BorderLayout.CENTER);
            panelLigne.add(iconeMatch, BorderLayout.EAST);
            panelScrollPan.add(panelLigne);
        }
        for (int c = 0; c < 6 - nbMatchsTotal; c++) {
            panelScrollPan.add(new JPanel());
        }
        scrollPane = new JScrollPane(panelScrollPan);
        framePrincipale.add(scrollPane, BorderLayout.CENTER);
        
        fenetre.add(framePrincipale);
    }
    
    public void setPosition(int x, int y) {
        fenetre.setLocation(x, y);
    }
    
    public Point getPosition() {
        return fenetre.getLocation();
    }
    
    public void afficherFenetre(boolean aff) {
        fenetre.setVisible(aff);
    }
    
    public void prochainMatch() {
        if (matchActuel == nbMatchsTotal - 1)
            iconesMatchs.get(matchActuel).setIcon(fin_bleu);
        else if (matchActuel == 0)
            iconesMatchs.get(matchActuel).setIcon(depart_bleu);
        else
            iconesMatchs.get(matchActuel).setIcon(milieu_bleu);
        if (matchActuel > 0) {
            if (matchActuel == 1)
                iconesMatchs.get(matchActuel-1).setIcon(depart_blanc);
            else
                iconesMatchs.get(matchActuel-1).setIcon(milieu_blanc);
        }
        
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
        int nbLignes = nbMatchsTotal;
        if (nbLignes < 6) {
            nbLignes = 6;
        }
        scrollBar.setValue((scrollBar.getMaximum() / nbLignes ) * (matchActuel -1));
        
        labelNbMatchs.setText(String.valueOf(nbMatchsTotal - matchActuel));
                
        matchActuel ++;
    }
}

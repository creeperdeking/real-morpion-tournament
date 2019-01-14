/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author grosa
 */
public class VueClassement extends Observable {
    private HashMap<String,Integer> listeNomScore=new HashMap<>();

    private final int defaultWidth=400;
    private final JFrame fenetre;
    private ArrayList<JLabel> labelDeNoms;
    private ArrayList<JLabel> labelDeScores;
    private JLabel labelDeNom;
    private JLabel labelDeScore;
    
    public VueClassement(ArrayList<String> noms){
          
        for(String  nom : noms){
            listeNomScore.put(nom,0);
        }
        
        
        
        
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        fenetre.setSize(defaultWidth, (int)(defaultWidth*1.6));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
       
        JPanel mainPanel=new JPanel();
         //=====================partie gauche ou il y a la liste de noms============================
        JPanel panelCentre=new JPanel(new BorderLayout());
        //liste de nom
        JPanel panelListeNom=new JPanel(new GridLayout(10,1));
        labelDeNoms=new ArrayList<>();    
        for (int i=1;i<=10;i++){
            labelDeNom=new JLabel();
            labelDeNoms.add(labelDeNom);
            panelListeNom.add(labelDeNom);
         }
        //=========================partie droit ou il y a la liste de scores===========================
        JPanel panelEast=new JPanel(new BorderLayout());
        JPanel score=new JPanel();
        score.add(new JLabel("Scores"));

        //liste de score
        JPanel panelListeScore=new JPanel(new GridLayout(10,1));
        
        labelDeScores=new ArrayList<>();
       
        
        for (int i=1;i<=10;i++){
            labelDeScore=new JLabel();
            panelListeScore.add(labelDeScore);
            labelDeScores.add(labelDeScore);
        }
        
        JPanel joueur=new JPanel();
        joueur.add(new JLabel("Joueurs"));
        joueur.setBorder(new MatteBorder(0, 0,1, 0, Color.GRAY));
        panelCentre.add(joueur,BorderLayout.NORTH);
        panelCentre.add(panelListeNom,BorderLayout.CENTER);
        panelCentre.setPreferredSize(new Dimension(220,660));     
        panelCentre.setBorder(new MatteBorder(0,0,0 ,1, Color.GRAY));      
        mainPanel.add(panelCentre,BorderLayout.CENTER);
        
        JPanel scores=new JPanel();
        scores.add(new JLabel("Scores"));
        panelEast.add(scores,BorderLayout.NORTH);
        panelEast.add(panelListeScore,BorderLayout.CENTER);
        panelEast.setPreferredSize(new Dimension(110,660));
        scores.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        mainPanel.add(panelEast,BorderLayout.EAST);
        
        this.actualiserListe(); 
        
        fenetre.add(mainPanel);
        
        
      
     }
    
    
    
    public void setScoreJoueur(String nom,Integer score){
        listeNomScore=new HashMap<String,Integer>();
        this.labelDeNom=new JLabel(nom);
        this.labelDeScore=new JLabel(score.toString());
       
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(listeNomScore.entrySet());
        //==============avant trié===================
        for(int i=0;i<infoIds.size();i++){
            String id=infoIds.get(i).toString();
            
        }
        
        //=============trieé par le score=========================
       Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
             @Override
             public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
             return (o2.getValue() - o1.getValue()); 
        //return (o1.getKey()).toString().compareTo(o2.getKey());
          }
        }); 
       //=================apré trié par le score========================
            for(int i=0;i<infoIds.size();i++){
                String id=infoIds.get(i).toString();
            
            }
       
    }
    
    public void actualiserListe(){
        int cptr = 0;
        for (String nom : listeNomScore.keySet()){
            JLabel labelNom = labelDeNoms.get(cptr);
            labelNom.setText(nom);
            JLabel labelScore=labelDeScores.get(cptr);
            labelScore.setText(listeNomScore.get(nom).toString());
            cptr ++;
        }
    }
    
    
    
    public void setPosition(int x, int y) {
        fenetre.setLocation(x, y);
    }
    
    public int getDefaultWidth() {
        return defaultWidth;
    }    
    
    
    public void afficherFenetre(boolean aff) {
        fenetre.setVisible(aff);
    }

}

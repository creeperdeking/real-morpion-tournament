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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author grosa
 */
public class VueClassement extends Observable {
    private HashMap<String,Integer> listeNomScore=new HashMap<>();

    private final int defaultWidth=320;
    private final JFrame fenetre;
    private ArrayList<JLabel> labelDeNoms;
   
    private ArrayList<JLabel> labelDeScores;
   
    

    private JButton recommencer=new JButton("Recommencer partie");
    private JButton fermer=new JButton("Redémarrer jeu");

    public VueClassement(ArrayList<String> noms){
          
        for(String  nom : noms){
            listeNomScore.put(nom,0);
        }
        
        fenetre = new JFrame("Classement");
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        fenetre.setSize(defaultWidth, (int)(defaultWidth*1.6));
       
        JPanel mainPanel=new JPanel(new BorderLayout());
         //=====================partie gauche ou il y a la liste de noms============================
        JPanel panelCentre=new JPanel(new BorderLayout());
        //liste de nom
        JPanel panelListeNom=new JPanel(new GridLayout(10,1));
        labelDeNoms=new ArrayList<>();    
        for (int i=1;i<=10;i++){
            JLabel labelDeNom=new JLabel();
            labelDeNom.setHorizontalAlignment(SwingConstants.RIGHT);
            labelDeNom.setBorder(new EmptyBorder(0,0,0,10));
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
            JLabel labelDeScore=new JLabel();
            labelDeScore.setBorder(new EmptyBorder(0,10,0,0));
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
        
       
        
        JPanel panelBas=new JPanel(new BorderLayout());
        JPanel panelButtonCommencer=new JPanel();
        JPanel panelButtonQuitter=new JPanel();
        
        
        panelButtonCommencer.add(recommencer);
        
            
        
        panelButtonQuitter.add(fermer);
        
        fermer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              setChanged();
              notifyObservers(new Message(EAction.FERMER));
              clearChanged();
            }
        });
        
        recommencer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              setChanged();
              notifyObservers(new Message(EAction.SUPPRIMER));
              clearChanged();
            }
        });
        
        panelBas.add(panelButtonCommencer,BorderLayout.CENTER);
        panelBas.add(panelButtonQuitter,BorderLayout.EAST);
        
        
        mainPanel.add(panelBas,BorderLayout.SOUTH);
        
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
    
    public void center() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
    }
    
    public void setScoreJoueur(String nom,Integer score){
        
        listeNomScore.put(nom, score);
        this.actualiserListe();
    }
    
    public static <K, V extends Comparable< ? super V>> Map<K, V>
    sortMapByValues(final Map <K, V> mapToSort)
    {
        List<Map.Entry<K, V>> entries =
            new ArrayList<Map.Entry<K, V>>(mapToSort.size());  

        entries.addAll(mapToSort.entrySet());

        Collections.sort(entries,
                         new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(
                   final Map.Entry<K, V> entry1,
                   final Map.Entry<K, V> entry2)
            {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });      

        Map<K, V> sortedMap = new LinkedHashMap<K, V>();      

        for (Map.Entry<K, V> entry : entries)
        {
            sortedMap.put(entry.getKey(), entry.getValue());

        }      

        return sortedMap;

    }
    
    public void actualiserListe(){
        int ctr=0;
        
        Map<String,Integer> mape = sortMapByValues(listeNomScore);
        ArrayList<Map.Entry<String,Integer>> array = new ArrayList(mape.entrySet());
        Collections.reverse(array);
        for(Map.Entry<String,Integer> entry : array){
           
            labelDeNoms.get(ctr).setText(entry.getKey());
            labelDeScores.get(ctr).setText(entry.getValue().toString());
           
            ctr++;
        }
        
    }
    
    public Point getPosition() {
        return fenetre.getLocation();
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

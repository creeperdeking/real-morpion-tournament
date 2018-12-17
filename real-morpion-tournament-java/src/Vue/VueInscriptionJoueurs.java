/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Utilitaires.Enums.EAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import Utilitaires.Messages.MInscriptionJoueurs;
import Utilitaires.Messages.Message;
import java.util.ArrayList;
import org.w3c.dom.NameList;
/**
 *
 * @author grosa
 */
public class VueInscriptionJoueurs extends Observable{
    
    private JFrame window;
    private JButton retirer;
    private JButton rdj;
    private JButton ajouter;
    private JButton start;
    private JTextField nom;
    private DefaultListModel namelist;
    
    
    
    public VueInscriptionJoueurs(){
    
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(750, 200);
        
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 0));
        
        // ----- Left Pan -----
        JPanel leftPan = new JPanel(new BorderLayout(10, 10));
        
        JLabel topleftPan = new JLabel("Liste des inscrits:");
        leftPan.add(topleftPan, BorderLayout.NORTH);
        
        //list of name plates in the left
        namelist = new DefaultListModel();
        JList names = new JList(namelist);
        
        
        
        namelist.addElement("dai2");
        namelist.addElement("dais");
        namelist.addElement("dai3");
        namelist.addElement("daih");
        namelist.addElement("daih");
        namelist.addElement("daih");
        namelist.addElement("daih");
        namelist.addElement("daih");
        namelist.addElement("daih");
        namelist.addElement("daih");
        namelist.addElement("daih");
        
        JScrollPane midleftPan = new JScrollPane(names);
        
        leftPan.add(midleftPan, BorderLayout.CENTER);
        
        retirer = new JButton("Retirer");
         
        names.getSelectedValuesList();
         
        retirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                ArrayList <String> nomliste=new ArrayList<>();
                
                notifyObservers();
                clearChanged();
            }
         });
         
         
        leftPan.add(retirer, BorderLayout.SOUTH);
       
        mainPanel.add(leftPan, BorderLayout.WEST);
        
        // ----- Right Pan -----
        JPanel rightPan = new JPanel(new BorderLayout());
        
        
        JPanel toprightPan = new JPanel(new GridLayout(2,2));
        JLabel lbl=new JLabel("Inscrivez les joueurs");
        lbl.setFont(new Font("Arial", Font.BOLD, 25));
        
        toprightPan.add(lbl);
        toprightPan.add(new JLabel(""));
        toprightPan.add(new JLabel("Ecrivez le nom du joueurs:"));
        
        nom= new JTextField(20);
        toprightPan.add(nom);
        rightPan.add(toprightPan,BorderLayout.NORTH);
        
        JPanel midrightPan = new JPanel(new BorderLayout());
        midrightPan.add(new JLabel("Choisissez l'interface adaptée à son âge:"), BorderLayout.NORTH);
        
        // for adapting the appearancve of the board to the age
     
        
        JLabel sldlabel=new JLabel();
        
        JSlider sld = new JSlider(0,3);
        sld.setPaintLabels(true);
        
        Hashtable position = new Hashtable();
        position.put(0, new JLabel("Enfant"));
        position.put(1, new JLabel("Ado"));
        position.put(2, new JLabel("Adulte"));
        position.put(3, new JLabel("Senior"));
        
        
        sld.setLabelTable(position);
      
        JPanel sldContainer = new JPanel();
        sldContainer.add(sld);
        
        midrightPan.add(sldContainer);
        
        rightPan.add(midrightPan,BorderLayout.CENTER);
        
        
        
        
        // for the buttons on the bottom right
        JPanel downrightPan = new JPanel(new GridLayout(2,3,5,5));
        rightPan.add(downrightPan, BorderLayout.SOUTH);
        
         ajouter=new JButton("Ajouter ce joueur");
         
         ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                ArrayList <String> noms=new ArrayList<>();
                noms.add(nom.getText());
                MInscriptionJoueurs ins=new MInscriptionJoueurs(EAction.AJOUTER, noms);
                notifyObservers(ins);
                clearChanged();
            }
         });
         
         rdj=new JButton("Règles du jeu");
         rdj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(EAction.REGLES_JEU));
                clearChanged();
            }
         });
         
   
         
         start=new JButton("Commencer");
         start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(EAction.CONTINUER));
                clearChanged();
            }
         });
        
        downrightPan.add(new JLabel());
        downrightPan.add(new JLabel());
        downrightPan.add(ajouter);
        downrightPan.add(new JLabel());
        downrightPan.add(rdj);
        downrightPan.add(start);
                
        mainPanel.add(rightPan, BorderLayout.CENTER);
        
        window.add(mainPanel);
    }
    
        public void afficher() {
        this.window.setVisible(true);
    }

    private JPanel getCellule(int i) {
        JPanel panelCellule = new JPanel();
        return panelCellule ;
    }
    
    public void addJoueur(String nom) {
        namelist.addElement(nom);
    }

    public static void main(String [] args) {
        VueInscriptionJoueurs myihm = new VueInscriptionJoueurs();
        myihm.afficher();
   }   
    
}

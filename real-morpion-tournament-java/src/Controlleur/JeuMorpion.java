/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Modele.Grille;
import Modele.Joueur;
import Utilitaires.Enums.EAction;
import Utilitaires.Enums.EEtatCase;
import Utilitaires.Messages.MClicCase;
import Utilitaires.Messages.MInscriptionJoueurs;
import Utilitaires.Messages.Message;
import java.util.Observer;
import java.util.Observable;


import Vue.VueInscriptionJoueurs;
import Vue.VueReglesJeu;
import Vue.VueJeuMorpion;
import Vue.VueClassement;
import Vue.VueConfrontations;
import java.util.ArrayList;
        
/**
 *
 * @author grosa
 */

public class JeuMorpion implements Observer {
    private VueJeuMorpion vueJeuMorpion;
    private VueReglesJeu  vueReglesJeu;
    private VueInscriptionJoueurs vueInscriptionJoueurs;
    private Grille grille;
    
    private ArrayList<Joueur> joueurs;
    
    public JeuMorpion() {
        vueJeuMorpion = new VueJeuMorpion();
        vueReglesJeu = new VueReglesJeu();
        vueInscriptionJoueurs = new VueInscriptionJoueurs();
        
        vueJeuMorpion.addObserver(this);
        vueReglesJeu.addObserver(this);
        vueInscriptionJoueurs.addObserver(this);
        
        grille = new Grille();
        joueurs = new ArrayList();
    }
    
    public void lancerJeu() {
        vueJeuMorpion.afficherFenetre(true);
    }
    
    @Override
    public void update(Observable arg0, Object arg1) {
        
        if(arg1 instanceof Message) {
            if(arg1 instanceof MInscriptionJoueurs) {
                if (arg0 instanceof VueInscriptionJoueurs) {
                    switch (((MInscriptionJoueurs)arg1).getAction()) {
                        case AJOUTER:
                            String nomJoueur = ((MInscriptionJoueurs) arg1).getNomJoueurs().get(0);
                            joueurs.add(new Joueur(nomJoueur));
                        case SUPPRIMER:
                            ArrayList<String> nomJoueurs =((MInscriptionJoueurs) arg1).getNomJoueurs();
                            for (String nom : nomJoueurs) {
                                for (Joueur j : joueurs) {
                                    if (j.getIdentifiant().equals(nom))
                                        joueurs.remove(j);
                                }
                            }
                    }
                }
            }
            else if(arg1 instanceof MClicCase) {
                if (arg0 instanceof VueJeuMorpion) {
                    MClicCase mes = (MClicCase)arg1;
                    //if ()
                    grille.setEtatCase(EEtatCase.VIDE, mes.getLigne(), mes.getColonne());
                }
            }
            else { // Si il s'agit d'un message normal
                if (arg0 instanceof VueInscriptionJoueurs) {
                    
                }
                else if (arg0 instanceof VueReglesJeu) {
                    //vueReglesJeu.
                }
                else if (arg0 instanceof VueJeuMorpion) {
                    
                }
                else if (arg0 instanceof VueClassement) {
                    
                }
                else if (arg0 instanceof VueConfrontations) {
                    
                }
            }
        }
    }
}

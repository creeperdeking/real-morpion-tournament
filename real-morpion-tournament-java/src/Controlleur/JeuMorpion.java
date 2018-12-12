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
import javax.swing.JOptionPane;
        
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
    private Joueur joueurCourant;
    
    private boolean retour_arriere_possible;
    private int ancienne_ligne, ancienne_colonne;
    
    public JeuMorpion(Joueur j1, Joueur j2) {
        grille = new Grille();
        joueurs = new ArrayList();
        
        joueurs.add(j1);
        joueurs.add(j2);
        
        vueJeuMorpion = new VueJeuMorpion();
        vueReglesJeu = new VueReglesJeu();
        vueInscriptionJoueurs = new VueInscriptionJoueurs();
        
        vueJeuMorpion.addObserver(this);
        vueReglesJeu.addObserver(this);
        vueInscriptionJoueurs.addObserver(this);
        
        joueurCourant = j2;
        prochainTour(); // Le joueur courant deviens j1
    }
    
    public void lancerJeu() {
        vueJeuMorpion.afficherFenetre(true);
    }
    
    public void prochainTour() {
        EEtatCase gagne = grille.chercherSiEtatFinal();
        if (gagne != null) {
            switch (gagne) {
                case VIDE:
                    JOptionPane.showMessageDialog(null, "Egalité", "Information", JOptionPane.OK_OPTION);
                    break;
                case CROIX:
                    JOptionPane.showMessageDialog(null, "Croix gagne!", "Information", JOptionPane.OK_OPTION);
                    break;
                case ROND:
                    JOptionPane.showMessageDialog(null, "Rond gagne!", "Information", JOptionPane.OK_OPTION);
                    break;
                default:
                    break;
            }
        }
        if (joueurCourant == joueurs.get(0))
            joueurCourant = joueurs.get(1);
        else
            joueurCourant = joueurs.get(0);
        
        vueJeuMorpion.setNomJoueurCourant(joueurCourant.getIdentifiant());
        int numeroJoueurCourant = joueurs.indexOf(joueurCourant);
        if (numeroJoueurCourant == 0)
            vueJeuMorpion.setSymboleJoueurCourant(EEtatCase.CROIX);
        else if(numeroJoueurCourant == 1)
            vueJeuMorpion.setSymboleJoueurCourant(EEtatCase.ROND);
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
                    int numeroJoueurCourant = joueurs.indexOf(joueurCourant);
                    if (grille.getEtatCase(mes.getLigne(), mes.getColonne()) == EEtatCase.VIDE) {
                        
                        if (numeroJoueurCourant == 0) {
                            grille.setEtatCase(EEtatCase.CROIX, mes.getLigne(), mes.getColonne());
                            vueJeuMorpion.setEtatCase(EEtatCase.CROIX, mes.getLigne(), mes.getColonne());
                        }
                        else if(numeroJoueurCourant == 1) {
                            grille.setEtatCase(EEtatCase.ROND, mes.getLigne(), mes.getColonne());
                            vueJeuMorpion.setEtatCase(EEtatCase.ROND, mes.getLigne(), mes.getColonne());
                        }
                        ancienne_ligne = mes.getLigne();
                        ancienne_colonne = mes.getColonne();
                        prochainTour();
                    }
                }
            }
            else { // Si il s'agit d'un message normal
                Message msg = (Message)arg1;
                if (arg0 instanceof VueInscriptionJoueurs) {
                    
                }
                else if (arg0 instanceof VueReglesJeu) {
                    
                }
                else if (arg0 instanceof VueJeuMorpion) {
                    if (msg.getAction() == EAction.RETOUR) {
                        if (ancienne_ligne >= 0)
                            vueJeuMorpion.setEtatCase(EEtatCase.VIDE, ancienne_ligne, ancienne_colonne);
                    }
                }
                else if (arg0 instanceof VueClassement) {
                    
                }
                else if (arg0 instanceof VueConfrontations) {
                    
                }
            }
        }
    }
}

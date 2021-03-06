/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Modele.Grille;
import Modele.Joueur;
import Utilitaires.Enums.EAction;
import Utilitaires.Enums.ECategorieAge;
import Utilitaires.Enums.EEtatCase;
import Utilitaires.Messages.MBouge;
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
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JOptionPane;
        
/**
 *
 * @author grosa
 */

public class JeuMorpion implements Observer {
    private VueJeuMorpion         vueJeuMorpion = new VueJeuMorpion();
    private VueReglesJeu          vueReglesJeu = new VueReglesJeu();
    private VueInscriptionJoueurs vueInscriptionJoueurs = new VueInscriptionJoueurs();
    private VueConfrontations vueConfrontation;
    private VueClassement vueClassement;
    private Grille grille = new Grille();
    
    private ArrayList<Joueur> joueurs = new ArrayList();
    private Joueur[] adversairesCourant = new Joueur[2];
    private int numeroJoueurCourant;
    private ArrayList<Joueur[]> matchs = new ArrayList();
    private int indiceMatchActuel = -1;
    
    private boolean retour_arriere_possible = false;
    private int ancienne_ligne, ancienne_colonne;
    
    public JeuMorpion() {
        vueJeuMorpion.addObserver(this);
        vueReglesJeu.addObserver(this);
        vueInscriptionJoueurs.addObserver(this);
    }
    
    public void lancerJeu() {
        ajouterJoueur("A", ECategorieAge.ADO);
        ajouterJoueur("B", ECategorieAge.ENFANT);
        ajouterJoueur("C", ECategorieAge.ADULTE);
        ajouterJoueur("D", ECategorieAge.SENIOR);
        vueInscriptionJoueurs.afficherFenetre(true);
    }
    
    private void genererMatchs() {
        for (Joueur joueur : joueurs) {
            for (Joueur adversaire : joueurs) {
                if (adversaire == joueur)
                    continue;
                Joueur paire1[] = {joueur, adversaire};
                matchs.add(paire1);
            }
            Collections.shuffle(matchs);
        }
    }
    
    private void prochainMatch() {
        indiceMatchActuel ++;
        grille.reinitialiserGrille();
        vueJeuMorpion.reinitialiserGrille();
        if (indiceMatchActuel < matchs.size()) {
            adversairesCourant = matchs.get(indiceMatchActuel);
            
            numeroJoueurCourant = 1;
            prochainTour(); // Le joueur courant deviens j1
            retour_arriere_possible = false;
            vueConfrontation.prochainMatch();
        }
        else {
            finTournoi();
        }
    }
    
    public void finTournoi() {
        JOptionPane.showMessageDialog(null, "Fin du tournoi!", "Information", JOptionPane.OK_OPTION);
        vueClassement.center();
        vueJeuMorpion.afficherFenetre(false);
        vueConfrontation.afficherFenetre(false);
    }
    
    public void prochainTour() {
        retour_arriere_possible = true;
       
        EEtatCase gagne = grille.chercherSiEtatFinal();
        if (gagne != null) {
            if (gagne == EEtatCase.VIDE) {
                JOptionPane.showMessageDialog(null, "Egalité: +1 Points chacun!", "Information", JOptionPane.OK_OPTION);
                adversairesCourant[0].addScore(1);
                adversairesCourant[1].addScore(1);
                vueClassement.setScoreJoueur(adversairesCourant[0].getIdentifiant(), adversairesCourant[0].getScore());
                vueClassement.setScoreJoueur(adversairesCourant[1].getIdentifiant(), adversairesCourant[1].getScore());
            }
            else {
                Joueur gagnant = adversairesCourant[gagne.getNumero()];
                JOptionPane.showMessageDialog(null, gagnant.getIdentifiant()+" gagne ce match! +3 Points pour lui!", "Information", JOptionPane.OK_OPTION);
                gagnant.addScore(3);
                vueClassement.setScoreJoueur(adversairesCourant[gagne.getNumero()].getIdentifiant(), adversairesCourant[gagne.getNumero()].getScore());
            }
            prochainMatch();
        }
        else
            joueurSuivant();
        
        actualiserAffichageJoueur();
    }
    
    private void joueurSuivant() {
        if (numeroJoueurCourant == 0)
            numeroJoueurCourant = 1;
        else
            numeroJoueurCourant = 0;
    }
        
    private void annulerTourPrecedent() {
        retour_arriere_possible = false;

        vueJeuMorpion.setEtatCase(EEtatCase.VIDE, ancienne_ligne, ancienne_colonne);
        grille.setEtatCase(EEtatCase.VIDE, ancienne_ligne, ancienne_colonne);

        joueurSuivant();

        actualiserAffichageJoueur();
    }
    
    private void actualiserAffichageJoueur() {
        String nomJoueurCourant = adversairesCourant[numeroJoueurCourant].getIdentifiant();
        vueJeuMorpion.setCategorieAge(adversairesCourant[numeroJoueurCourant].getCategorieAge());
        vueJeuMorpion.setNomJoueurCourant(nomJoueurCourant);
        
        if (numeroJoueurCourant == 0)
            vueJeuMorpion.setSymboleJoueurCourant(EEtatCase.CROIX);
        else if(numeroJoueurCourant == 1)
            vueJeuMorpion.setSymboleJoueurCourant(EEtatCase.ROND);
    }
    
    private boolean ajouterJoueur(String nomJoueur, ECategorieAge catAge) {
        boolean existeDeja = false;
        for (Joueur j : joueurs) {
            if (j.getIdentifiant().equals(nomJoueur))
                existeDeja = true;
        }
                            
        if (!existeDeja) {
            joueurs.add(new Joueur(nomJoueur, catAge));
            vueInscriptionJoueurs.addJoueur(nomJoueur);
        }
        
        return !existeDeja;
    }
    
    private void supprimerJoueurs(ArrayList<String> nomJoueurs) {
        for (String nom : nomJoueurs) {
            System.out.println(nom);
            ArrayList<Joueur> listeJ = new ArrayList(joueurs);
            for (Joueur j : listeJ) {
                if (j.getIdentifiant().equals(nom)) {
                    joueurs.remove(j);
                    vueInscriptionJoueurs.removeJoueur(nom);
                }
            }
        }
    }
    
    private boolean gererClicCase(int ligne, int colonne) {
        boolean caseVide = grille.getEtatCase(ligne, colonne) == EEtatCase.VIDE;
        
        if (caseVide) {
            EEtatCase symboleJoueur = EEtatCase.getCase(numeroJoueurCourant);
            if (numeroJoueurCourant == 1)
                symboleJoueur = EEtatCase.ROND;
            
            grille.setEtatCase(symboleJoueur, ligne, colonne);
            vueJeuMorpion.setEtatCase(symboleJoueur, ligne, colonne);
            
            ancienne_ligne = ligne;
            ancienne_colonne = colonne;
            prochainTour();
        }
        return caseVide;
    }
    
    private boolean gererHover(int ligne, int colonne) {
        boolean caseVide = grille.getEtatCase(ligne, colonne) == EEtatCase.VIDE;
        
        if (caseVide) {
            EEtatCase symboleJoueur = EEtatCase.getCase(numeroJoueurCourant);
            if (numeroJoueurCourant == 1)
                symboleJoueur = EEtatCase.ROND;
            
            vueJeuMorpion.setHover(symboleJoueur, ligne, colonne);
        }
        return caseVide;
    }
    
    private boolean gererStopHover(int ligne, int colonne) {
        boolean caseVide = grille.getEtatCase(ligne, colonne) == EEtatCase.VIDE;
        
        if (caseVide) {
            vueJeuMorpion.stopHover(ligne, colonne);
        }
        return caseVide;
    }
    
    
    private void commencerTournoi() {
        
        vueInscriptionJoueurs.afficherFenetre(false);
        genererMatchs();
        
        ArrayList<String> noms=new ArrayList<>();
        for(Joueur j:joueurs){
            String nom=j.getIdentifiant();
            noms.add(nom);
            //vueClassement.setScoreJoueur(nom, ListeNomScore.get(nom).intValue());
        }

        ArrayList<String[]> confrontations = new ArrayList();
        for (Joueur joueurs[] : matchs) {
            String conf[] = {joueurs[0].getIdentifiant(), joueurs[1].getIdentifiant()};
            confrontations.add(conf);
        }
        vueConfrontation = new VueConfrontations(confrontations);
        
        vueClassement=new VueClassement(noms);
        
        Point positionVueJeu = vueJeuMorpion.getPosition();
        vueConfrontation.setPosition(positionVueJeu.x + vueJeuMorpion.getDefaultWidth(), positionVueJeu.y);
        vueConfrontation.afficherFenetre(true);
        vueConfrontation.addObserver(this);
        
        vueClassement.setPosition(positionVueJeu.x - vueClassement.getDefaultWidth(), positionVueJeu.y);
        vueClassement.afficherFenetre(true);
        vueClassement.addObserver(this);
        
        vueJeuMorpion.afficherFenetre(true);
        prochainMatch();
    }
    
    private void lancerReglesJeu() {
        vueReglesJeu.afficherFenetre(true);
        vueReglesJeu.afficherRegleMorpion();
    }
    
    private void fermerReglesJeu(){
        vueReglesJeu.afficherFenetre(false);
    
    }
    
    private void relancerJeu(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sûr de voulour revenir à l'écran d'inscription des joueurs?","Attention!",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            vueJeuMorpion.afficherFenetre(false);
            vueReglesJeu.afficherFenetre(false);
            vueConfrontation.afficherFenetre(false);
            vueClassement.afficherFenetre(false);
            
            vueJeuMorpion = new VueJeuMorpion();
            vueReglesJeu = new VueReglesJeu();
            vueInscriptionJoueurs = new VueInscriptionJoueurs();
            grille = new Grille();

            joueurs = new ArrayList();
            adversairesCourant = new Joueur[2];
            matchs = new ArrayList();
            indiceMatchActuel = -1;

            retour_arriere_possible = false;

            vueJeuMorpion.addObserver(this);
            vueReglesJeu.addObserver(this);
            vueInscriptionJoueurs.addObserver(this);

            lancerJeu();
        }
        
    }
    
    private void relancerPartie() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sûr de voulour relancer la partie?","Attention!",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            vueJeuMorpion.afficherFenetre(false);
            vueReglesJeu.afficherFenetre(false);
            vueInscriptionJoueurs.afficherFenetre(false);
            vueConfrontation.afficherFenetre(false);
            vueClassement.afficherFenetre(false);
            
            matchs = new ArrayList();
            indiceMatchActuel = -1;
            
            commencerTournoi();
        }
    }
    
    private void prochainPaneRegle(){
        vueReglesJeu.afficherRegleTournoi();
    }
    
    @Override
    public void update(Observable arg0, Object arg1) {
        if(arg1 instanceof Message) {
            if(arg1 instanceof MInscriptionJoueurs) {
                EAction action = ((MInscriptionJoueurs)arg1).getAction();
                
                switch (action) {
                    case AJOUTER:
                        String nomJoueur = ((MInscriptionJoueurs) arg1).getNomJoueurs().get(0);
                        ECategorieAge catAge = ((MInscriptionJoueurs) arg1).getCatAge();
                        if (!nomJoueur.equals("")) {
                            if (!ajouterJoueur(nomJoueur, catAge))
                                JOptionPane.showMessageDialog(vueInscriptionJoueurs.getWindow(), "Deux joueurs ne peuvent pas avoir le même nom!");
                        }
                        else
                            JOptionPane.showMessageDialog(vueInscriptionJoueurs.getWindow(), "Un joueur doit avoir un nom!");
                        break;
                    case SUPPRIMER:
                        ArrayList<String> nomJoueurs = ((MInscriptionJoueurs) arg1).getNomJoueurs();
                        supprimerJoueurs(nomJoueurs);
                        break;
                }
            }
            else if(arg1 instanceof MClicCase) {
                MClicCase mes = (MClicCase)arg1;
                EAction action = mes.getAction();
                
                switch (action) {
                    case CLIC_CASE:
                        gererClicCase(mes.getLigne(), mes.getColonne());
                        break;
                    case HOVER:
                        gererHover(mes.getLigne(), mes.getColonne());
                        break;
                    case EXIT_HOVER:
                        gererStopHover(mes.getLigne(), mes.getColonne());
                        break;
                }
            }
            else if(arg1 instanceof MBouge) {
                MBouge mes = (MBouge)arg1;
                Point posVueConfrontation = vueConfrontation.getPosition();
                Point posVueClassement = vueClassement.getPosition();
                vueClassement.setPosition(posVueClassement.x+mes.getDeltaX(), posVueClassement.y+mes.getDeltaY());
                vueConfrontation.setPosition(posVueConfrontation.x+mes.getDeltaX(), posVueConfrontation.y+mes.getDeltaY());
            }
            else { // Si il s'agit d'un message normal
                Message msg = (Message)arg1;
                if (arg0 instanceof VueInscriptionJoueurs) {
                    switch(msg.getAction()){
                        case REGLES_JEU:
                            lancerReglesJeu();
                            break;
                        case CONTINUER:
                            if (joueurs.size() < 2)
                               JOptionPane.showMessageDialog(null, "Ajoutez au moins deux joueurs à votre tournoi", "Information", JOptionPane.OK_OPTION);
                            else
                               commencerTournoi();
                            break;
                   }
                }
 
                else if (arg0 instanceof VueReglesJeu) {
           
                    switch(msg.getAction()){
                        case FERMER:
                            fermerReglesJeu();
                        case CONTINUER:
                            prochainPaneRegle();
                        break;
                    }
                    
                }
                else if (arg0 instanceof VueJeuMorpion) {
                    switch(msg.getAction()) {
                        case RETOUR:
                            if (retour_arriere_possible)
                                annulerTourPrecedent();
                            break;
                        case REGLES_JEU:
                            lancerReglesJeu();
                    }
                }
                else if (arg0 instanceof VueClassement) {
                    switch(msg.getAction()){
                        case FERMER:
                            relancerJeu();
                            break;
                        case SUPPRIMER:
                            relancerPartie();
                            break;
                    }
                }
                else if (arg0 instanceof VueConfrontations) {
                    
                }
            }
        }
    }
}

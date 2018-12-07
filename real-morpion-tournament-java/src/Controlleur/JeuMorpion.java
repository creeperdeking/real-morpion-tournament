/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import java.util.Observer;
import java.util.Observable;


import Vue.VueInscriptionJoueurs;
import Vue.VueReglesJeu;
import Vue.VueJeuMorpion;
        
/**
 *
 * @author grosa
 */

public class JeuMorpion implements Observer {
    private VueJeuMorpion vueJeuMorpion;
    private VueReglesJeu  vueReglesJeu;
    private VueInscriptionJoueurs vueInscriptionJoueurs;
    
    public JeuMorpion() {
        vueJeuMorpion = new VueJeuMorpion();
        vueReglesJeu = new VueReglesJeu();
        vueInscriptionJoueurs = new VueInscriptionJoueurs();
    }
    
    public void lancerJeu() {
        vueJeuMorpion.afficherFenetre(true);
    }
    
    @Override
    public void update(Observable arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

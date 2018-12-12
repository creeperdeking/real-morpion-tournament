/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controlleur.JeuMorpion;
import Modele.Joueur;

/**
 *
 * @author grosa
 */
public class RealMorpionTournament {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JeuMorpion jeu = new JeuMorpion(new Joueur("Dai"), new Joueur("Alexis"));
        jeu.lancerJeu();
    }
    
}

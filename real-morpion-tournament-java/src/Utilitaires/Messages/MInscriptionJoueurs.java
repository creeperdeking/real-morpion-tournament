/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Messages;

import Utilitaires.Enums.EActions;
import java.util.ArrayList;

/**
 *
 * @author grosa
 */
public class MInscriptionJoueurs extends Message {
    private ArrayList<String> nomJoueurs;
    
    public MInscriptionJoueurs(EActions action, ArrayList<String> nomJoueurs) {
        super(action);
        
        this.nomJoueurs = nomJoueurs;
    }
    
    public void addJoueur(String nomJoueur) {
        nomJoueurs.add(nomJoueur);
    }

    /**
     * @return the nomJoueurs
     */
    public ArrayList<String> getNomJoueurs() {
        return nomJoueurs;
    }
    
}

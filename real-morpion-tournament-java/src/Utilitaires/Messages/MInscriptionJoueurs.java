/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Messages;

import Utilitaires.Enums.EAction;
import Utilitaires.Enums.ECategorieAge;
import java.util.ArrayList;

/**
 *
 * @author grosa
 */
public class MInscriptionJoueurs extends Message {
    private ArrayList<String> nomJoueurs = new ArrayList();
    private ECategorieAge catAge;
    
    public MInscriptionJoueurs(EAction action, ArrayList<String> nomJoueurs) {
        super(action);
        
        this.nomJoueurs = nomJoueurs;
    }
    
    public MInscriptionJoueurs(EAction action, String nomJoueur, ECategorieAge catAge) {
        super(action);
        
        this.nomJoueurs.add(nomJoueur);
        this.catAge = catAge;
    }
    
    public void addJoueur(String nomJoueur) {
        nomJoueurs.add(nomJoueur);
    }
    
    public ECategorieAge getCatAge() {
        return catAge;
    }

    /**
     * @return the nomJoueurs
     */
    public ArrayList<String> getNomJoueurs() {
        return nomJoueurs;
    }
    
}

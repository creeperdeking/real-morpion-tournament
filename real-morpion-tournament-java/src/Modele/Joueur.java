/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Utilitaires.Enums.ECategorieAge;

/**
 *
 * @author grosa
 */
public class Joueur {
    private String identifiant;
    private ECategorieAge categorieAge;
    
    private int nbPoints;
    
    public Joueur(String identifiant) {
        this.identifiant = identifiant;
        
        nbPoints = 0;
    }

    /**
     * @return the identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * @return the categorieAge
     */
    public ECategorieAge getCategorieAge() {
        return categorieAge;
    }
}

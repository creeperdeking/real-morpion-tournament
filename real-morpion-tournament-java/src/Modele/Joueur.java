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
    
    private int nbPoints = 0;
    
    public Joueur(String identifiant, ECategorieAge categorieAge) {
        this.identifiant = identifiant;
        this.categorieAge = categorieAge;
    }
    
    public void addScore(int points) {
        nbPoints += points;
    }
    
    public int getScore(){
        return nbPoints;
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Messages;

import Utilitaires.Enums.EActions;

/**
 *
 * @author grosa
 */
public class MClicCase extends Message {
    // Lignes et colonnes de la grille, numérotés à partir de 0
    private int ligne, colonne;
    
    public MClicCase(EActions action, int ligne, int colonne) {
        super(action);
        
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * @return la ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * @return la colonne
     */
    public int getColonne() {
        return colonne;
    }
}

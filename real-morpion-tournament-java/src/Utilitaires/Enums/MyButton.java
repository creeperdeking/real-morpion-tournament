/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Enums;

import javax.swing.JButton;

/**
 *
 * @author grosa
 */
public class MyButton extends JButton {
    private int ligne, colonne ;
    
    public MyButton(String label, int ligne, int colonne) {
        super(label) ;
        this.ligne = ligne ;
        this.colonne = colonne ;
    }

    /**
     * @return the ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * @return the colonne
     */
    public int getColonne() {
        return colonne;
    }
}

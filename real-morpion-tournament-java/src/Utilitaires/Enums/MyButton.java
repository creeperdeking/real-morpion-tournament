/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Enums;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author grosa
 */
public class MyButton extends JButton {
    private int ligne, colonne ;
    
    public MyButton(ImageIcon image, int ligne, int colonne) {
        super(image);
        this.ligne = ligne;
        this.colonne = colonne;
        this.setBackground(new Color(236, 204, 179));
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

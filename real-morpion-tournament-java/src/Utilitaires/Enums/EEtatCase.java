/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Enums;

/**
 *
 * @author grosa
 */
public enum EEtatCase {
    CROIX(0),
    ROND(1),
    VIDE(2);
    
    private int numero;
    
    private EEtatCase(int num) {
        numero = num;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public static EEtatCase getCase(int numero) {
        switch (numero) {
            case 0:
                return CROIX;
            case 1:
                return ROND;
            case 2:
                return VIDE;
            default:
                return null;
        }
    }
}

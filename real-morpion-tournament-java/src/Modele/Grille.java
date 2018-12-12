/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Utilitaires.Enums.EEtatCase;

/**
 *
 * @author grosa
 */
public class Grille {
    // Grille d'EEtatCase de 3 par 3
    private EEtatCase cases[][];
    
    public Grille() {
        cases = new EEtatCase[3][3];
        effacerCases();
    }
    
    public Grille(Grille grille) {
        cases = new EEtatCase[3][3];
        for (int ligne = 0; ligne != 3; ligne++) {
            for (int colonne = 0; colonne != 3; colonne++) {
                cases[ligne][colonne] = grille.getEtatCase(ligne, colonne);
            }
        }
    }
    
    public void effacerCases() {
        for (int ligne = 0; ligne != 3; ligne++) {
            for (int colonne = 0; colonne != 3; colonne++) {
                cases[ligne][colonne] = EEtatCase.VIDE;
            }
        }
    }
    
    public void setEtatCase(EEtatCase etat, int ligne, int colonne) {
        cases[ligne][colonne] = etat;
    }
    
    public EEtatCase getEtatCase(int ligne, int colonne) {
        return cases[ligne][colonne];
    }
    
    // Cette fonction parcourt la grille à la recherche
    // d'un symbole vaincqeur
    // Retourne: null si pas de fin de partie
    //           EEtatCase.CROIX si la croix gagne
    //           EEtatCase.ROND si le rond gagne
    //           EEtatCase.VIDE si egalite
    public EEtatCase chercherSiEtatFinal() {
        EEtatCase valeurRetour = null;
        
        // On teste en colonne
        for (int x = 0; x != 3; x++) {
            EEtatCase etatPremier = cases[x][0];
            if (etatPremier != EEtatCase.VIDE) {
                boolean gagne = true;
                for (int y = 1; y != 3; y++) {
                    if (cases[x][y] != etatPremier)
                        gagne = false;
                }
                if (gagne)
                    valeurRetour = etatPremier;
            }
        }
        
        if (valeurRetour != null)
            return valeurRetour;
        
        // On teste en ligne
        for (int y = 0; y < 3; y++) {
            EEtatCase etatPremier = cases[0][y];
            if (etatPremier != EEtatCase.VIDE) {
                boolean gagne = true;
                for (int x = 1; x < 3; x++) {
                    if (cases[x][y] != etatPremier)
                        gagne = false;
                }
                if (gagne)
                    valeurRetour = etatPremier;
            }
        }
        
        if (valeurRetour != null)
            return valeurRetour;
        
        //Diagonale décroisante:
        EEtatCase etatPremier = cases[0][0];
        if (etatPremier != EEtatCase.VIDE) {
            boolean gagne = true;
            for (int i = 1; i < 3; i++) {
                if (cases[i][i] != etatPremier)
                    gagne = false;
            }
            if (gagne)
                    valeurRetour = etatPremier;
        }
        
        if (valeurRetour != null)
            return valeurRetour;
        
        //Diagonale croissante:
        etatPremier = cases[0][2];
        if (etatPremier != EEtatCase.VIDE) {
            boolean gagne = true;
            for (int i = 1; i < 3; i++) {
                if (cases[i][2-i] != etatPremier)
                    gagne = false;
            }
            if (gagne)
                    valeurRetour = etatPremier;
        }
        
        if (valeurRetour != null)
            return valeurRetour;
        
        // On teste si la grille est pleine
        boolean pleine = true;
        for (int x = 0; x < 3; x++) {
            for (int y = 1; y < 3; y++) {
                if (cases[x][y] == EEtatCase.VIDE)
                    pleine = false;
            }
        }
        
        if (pleine)
            valeurRetour = EEtatCase.VIDE; // Egalité
        
        return valeurRetour;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4_fontaine_royer;

/**
 *
 * @author Hugo
 */
public class grille {
    cellule cellules[][]= new cellule[6][7];
  grille() {
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                cellules[i][j] = new cellule();
            }
        }
    }


  boolean colonne_remplie(int ind_colonne) {
    // methode ajoutée par rapport à l'énoncé
    // retourne vrai si la colonne d'indice ind_colonne estremplie
    // retourne faux sinon
        return (cellules[5][ind_colonne].CurrentToken != null);
    }

        
    boolean ajouterJetonDansColonne(joueur joueurCourant, int ind_colonne) {
        // si la colonne est remplie, on s'arrete et on retourne false
        if (colonne_remplie(ind_colonne)) return false;
        
        // on recherche l'indice de la ligne où ajouter le jeton
        // forcement cet inddice existe 
        int i = 0;
        while (cellules[i][ind_colonne].CurrentToken != null) {
            i++;
        }

        // on récupére un jeton dans la liste des jetons du joueur 
        jeton un_jeton = joueurCourant.retirerJeton();
        // on ajoute le jeton dans la case en question
        cellules[i][ind_colonne].CurrentToken = un_jeton;
        // on récupère un potentiel désintegrateur
        if (cellules[i][ind_colonne].presenceDesintegrateur()) {
            cellules[i][ind_colonne].recupererDesintegrateur();
            joueurCourant.nombreDesintegrateurs++;
        }
        // on active le potentiel trou noir
        if (cellules[i][ind_colonne].presenceTrouNoir()) {
           cellules[i][ind_colonne].activerTrouNoir();
        }
        return true;
    }
    
  
    
    void activer_trounoir(int column) { // ressort vrai si il y a un desing
        int i = 5;
        while (cellules[i][column].CurrentToken == null) {
            i--;
            if (i == 0) {
                break;
            }
        }
        if (i >= 0 && i < 6) {
            cellules[i][column].activerTrouNoir();
        }
        

    }
    
    
 
    boolean etreRemplie() {
        // renvoie vrai si la grille est remplie.
        // il nous suffit de tester si chaque colonne est remplie
        int compteur = 0;
        int i=0;
        // tant que la colonne i est remplie, on incrémente i 
        // et on passe a la colonne suivante 
        while (i!=6 && colonne_remplie(i)) { i++;}
        return (i==6); // si i=6 on a toutes les colonens de remplies 
    }
    
    
    void viderGrille() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                cellules[i][j].CurrentToken = null;
                cellules[i][j].BlackHole = false;
                cellules[i][j].desintegrateur = false;
            }
        }
    }

    
    void afficherGrilleSurConsole() {
 
        // boucle inversée : on affiche d'abord la ligne du haut
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                if(cellules[i][j].BlackHole){
                    System.out.print("\u001B[0m T ");
                }
                  else if (cellules[i][j].desintegrateur) {
                    System.out.print("\u001B[0m D ");
                } else if (cellules[i][j].CurrentToken == null) {
                    System.out.print("\u001B[0m N ");
                } else {
                    System.out.print(cellules[i][j].CurrentToken);
                }
            }
            System.out.println(" " + (i+1));
        }
        for(int i=0; i<7;i++){
            System.out.print(" " + (i+1) + " ");
        }
        System.out.println();
    }

    
    
    boolean celluleOccupee(int ligne, int column) {
        if(cellules[ligne][column].CurrentToken != null) {
            return true;
        }
        return false;
    }

    String lireCouleurDuJeton(int ligne, int column) {
        return cellules[ligne][column].lireCouleurDuJeton();
    }

    boolean etreGagnantePourJoueur(joueur un_joueur) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (cellules[i][j] != null && cellules[i][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i][j + 1] != null && cellules[i][j + 1].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i][j + 2] != null && cellules[i][j + 2].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i][j + 3] != null && cellules[i][j + 3].lireCouleurDuJeton().equals(un_joueur.PlayerColor)) {
                    return true;
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (cellules[i][j] != null && cellules[i][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i + 1][j] != null && cellules[i + 1][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i + 2][j] != null && cellules[i + 2][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i + 3][j] != null && cellules[i + 3][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)) {
                    return true;
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (cellules[i][j] != null && cellules[i][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i + 1][j + 1] != null && cellules[i + 1][j + 1].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i + 2][j + 2] != null && cellules[i + 2][j + 2].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i + 3][j + 3] != null && cellules[i + 3][j + 3].lireCouleurDuJeton().equals(un_joueur.PlayerColor)) {
                    return true;
                }

            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (cellules[i][j] != null && cellules[i][j].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i - 1][j + 1] != null && cellules[i - 1][j + 1].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i - 2][j + 2] != null && cellules[i - 2][j + 2].lireCouleurDuJeton().equals(un_joueur.PlayerColor)
                        && cellules[i - 3][j + 3] != null && cellules[i - 3][j + 3].lireCouleurDuJeton().equals(un_joueur.PlayerColor)) {
                    return true;
                }

            }
        }

        return false;

    }

    Boolean placerTrouNoir(int ligne, int colonne) {
        if (!cellules[ligne][colonne].BlackHole) {
            cellules[ligne][colonne].BlackHole = true;
            return true;
        }
        return false;
    }

    Boolean supprimerJeton(int ligne, int colonne) {
        if (cellules[ligne][colonne].CurrentToken == null) {
            return false;
        }
        cellules[ligne][colonne].CurrentToken = null;
        return true;
    }

    void tasserGrille() {
        for (int i = 0; i < 7; i++) {
           tasserColonne(i);
        }
    }
    
    
       void tasserColonne(int colonne) {
        for (int i = 0; i < 6; i++) {
            if (i == 5) {
                cellules[i][colonne].CurrentToken = null;
            } else {
                if (cellules[i][colonne].CurrentToken  == null) {
                  cellules[i][colonne].CurrentToken = cellules[i + 1][colonne].CurrentToken;
                  cellules[i + 1][colonne].CurrentToken=null;
                }
            }

        }
    }

    jeton recupererJeton(int ligne, int colonne) {
        jeton t = cellules[ligne][colonne].recupererJeton();
        cellules[ligne][colonne].supprimerJeton();
        return t;
    }

    Boolean placerDesintegrateur(int ligne, int colonne) {
        if (!cellules[ligne][colonne].desintegrateur) {
            cellules[ligne][colonne].desintegrateur = true;
            return true;
        }
        return false;
    }

}
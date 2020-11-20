/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4_fontaine_royer;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class partie {
joueur Listejoueurs[] = new joueur[2];
joueur joueurCourant;
grille grilleDeJeu = new grille();
jeton jetonCourant;

void attribuerCouleursAuxJoueurs() {
        Random r = new Random();
        boolean couleur;
        couleur = r.nextBoolean();
        if (couleur) {
            Listejoueurs[0].PlayerColor = "Rouge";
            Listejoueurs[1].PlayerColor = "Jaune";
        } else {
            Listejoueurs[0].PlayerColor = "Jaune";
            Listejoueurs[1].PlayerColor = "Rouge";
        }
    }

    joueur ProchainJoueur(joueur un_joueur) {
        if (Listejoueurs[0] == joueurCourant) {
            return Listejoueurs[1];
        }
        return Listejoueurs[0];
    }

    void initialiserPartie() {
        //Mise en place de la grille
        grilleDeJeu.viderGrille();

        //Création des joueurs
        Scanner sc = new Scanner(System.in);
        System.out.println("Choix du pseudo du J1 :");
        joueur J1 = new joueur(sc.nextLine());
        System.out.println("Choix du pseudo du J2 :");
        joueur J2 = new joueur(sc.nextLine());
        Listejoueurs[0] = J1;
        Listejoueurs[1] = J2;

        attribuerCouleursAuxJoueurs();

        System.out.println(J1.Nom + " est de couleur " + J1.PlayerColor);
        System.out.println(J2.Nom + " est de couleur " + J2.PlayerColor);

        // On donne des jetons aux joueurs
        for (int i = 0; i < 21; i++) {

            jeton J = new jeton(Listejoueurs[0].PlayerColor);

            J1.ajouterJeton(J);

            J2.ajouterJeton(new jeton(J2.PlayerColor));
        }

        // Determine qui est le premier joueur
        Random r = new Random();
        boolean le_premier = r.nextBoolean();
        if (le_premier) {
            joueurCourant = Listejoueurs[0];
        } else {
            joueurCourant = Listejoueurs[1];
        }

        // Génération des 5 trous noirs et de 2 désintégrateurs sur les trou noirs
        int compteur = 0;
        for (int i = 0; i < 5; i++) {
            int ligne_trou_noir = r.nextInt(6);
            int colonne_trou_noir = r.nextInt(7);
            if (compteur < 2) {
                if (!grilleDeJeu.placerDesintegrateur(ligne_trou_noir, colonne_trou_noir)) {
                    compteur--;
                }
                compteur = compteur + 1;
            }
            if (!grilleDeJeu.placerTrouNoir(ligne_trou_noir, colonne_trou_noir)) {
                i--;
            }
        }

        // On place les trois derniers désintégrateurs
        for (int i = 0; i < 3; i++) {
            int ligne_désin = r.nextInt(6);
            int colonne_désin = r.nextInt(7);
            if (!grilleDeJeu.placerDesintegrateur(ligne_désin, colonne_désin) || grilleDeJeu.cellules[ligne_désin][colonne_désin].presenceTrouNoir()) {
                i--;
            }
        }

        grilleDeJeu.afficherGrilleSurConsole();

    }

    int menu_joueur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1) Jouer un Jeton");
        System.out.println("2) Récuperer un Jeton");
        System.out.println("3) Désintégrer un Jeton");
        int choix = sc.nextInt();
        while (choix > 3 || choix < 1) {
            System.out.println("Erreur : Entrer un choix qui existe :");
            choix = sc.nextInt();
        }
        return choix;
    }

    void jouerJeton() {
        Scanner sc = new Scanner(System.in);
        boolean resultatAction;
        System.out.println("Veuillez saisir une colonne :");
        int colonne = sc.nextInt() - 1;
        while (colonne > 6 || colonne < 0) {
            System.out.println("Erreur : veuillez saisir une colonne :");
            colonne = sc.nextInt() - 1;
        }

        resultatAction = grilleDeJeu.ajouterJetonDansColonne(joueurCourant, colonne);
        while (!resultatAction) {
            System.out.println("La collone est pleine veuillez saisir une autre colonne :");
            colonne = sc.nextInt() - 1;
            resultatAction = grilleDeJeu.ajouterJetonDansColonne(joueurCourant, colonne);
        }

    }

    boolean recup_jeton() {
        int colonne;
        int ligne;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir les coordonnées du jeton a récup :");
        System.out.println("Veuillez saisir la colonne :");
        colonne = sc.nextInt() - 1;
        while (colonne > 6 || colonne < 0) {
            System.out.println("Erreur : veuillez saisir une colonne valide :");
            colonne = sc.nextInt() - 1;
        }
        System.out.println("Veuillez saisir la ligne :");
        ligne = sc.nextInt() - 1;
        while (ligne > 5 || ligne < 0) {
            System.out.println("Erreur : veuillez saisir une ligne valide :");
            ligne = sc.nextInt() - 1;
        }
        if (grilleDeJeu.cellules[ligne][colonne].CurrentToken != null && grilleDeJeu.cellules[ligne][colonne].lireCouleurDuJeton().equals(joueurCourant.PlayerColor)) {
            //G.recupererJeton(ligne, colonne);
            joueurCourant.ajouterJeton(grilleDeJeu.recupererJeton(ligne, colonne));
            grilleDeJeu.tasserGrille();
            //JoueurCourant.ajouterJeton(G.Cellules[ligne][colonne].recupererJeton());

            return true;
        } else {
            return false;
        }
    }

    boolean désing_jeton() {
        if (joueurCourant.nombreDesintegrateurs == 0) {
            return false;
        }
        int colonne;
        int ligne;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir les coordonnées du jeton a désintégrer :");
        System.out.println("Veuillez saisir la colonne :");
        colonne = sc.nextInt() - 1;
        while (colonne > 6 || colonne < 0) {
            System.out.println("Erreur : veuillez saisir une colonne valide :");
            colonne = sc.nextInt() - 1;
        }
        System.out.println("Veuillez saisir la ligne :");
        ligne = sc.nextInt() - 1;
        while (ligne > 5 || ligne < 0) {
            System.out.println("Erreur : veuillez saisir une ligne valide :");
            ligne = sc.nextInt() - 1;
        }

        if (grilleDeJeu.cellules[ligne][colonne].CurrentToken != null && !grilleDeJeu.cellules[ligne][colonne].lireCouleurDuJeton().equals(joueurCourant.PlayerColor)) {
            grilleDeJeu.supprimerJeton(ligne, colonne);
            grilleDeJeu.tasserGrille();
            joueurCourant.utiliserDesintegrateur();
            return true;
        } else {
            return false;
        }
    }

    boolean tour_de_jeux() {
        System.out.println("C'est a " + joueurCourant.Nom + " de jouer (" + joueurCourant.PlayerColor + ")");
        System.out.println("Il vous reste " + joueurCourant.nombreJetonsRestant + " jetons");
        System.out.println("Il vous reste " + joueurCourant.nombreDesintegrateurs + " désintégrateurs");
        int choix = menu_joueur();
        switch (choix) {
            case 1:
                jouerJeton();
                return true;
            //break;
            case 2:
                if (!recup_jeton()) {
                    System.out.println("Vous avez soit saisi un jeton qui n'est pas le vôtre ou un endroit sans jeton");
                    return false;
                }

                break;
            case 3:
                if (!désing_jeton()) {
                    System.out.println("Vous avez soit saisi un jeton qui est le vôtre ou vous n'avez pas de désintégrateur");
                    return false;
                }
                break;
        }
        return true;
    }

    void debuterPartie() {
        initialiserPartie();
        Scanner sc = new Scanner(System.in);

        do {
            while (!tour_de_jeux()) {
                System.out.println("Recommencez votre tour");
            }
            grilleDeJeu.afficherGrilleSurConsole();

            joueurCourant = ProchainJoueur(joueurCourant);

        } while (grilleDeJeu.etreGagnantePourJoueur(Listejoueurs[0]) != true && grilleDeJeu.etreGagnantePourJoueur(Listejoueurs[1]) != true && grilleDeJeu.etreRemplie() != true);

        if (grilleDeJeu.etreGagnantePourJoueur(Listejoueurs[0]) == true && grilleDeJeu.etreGagnantePourJoueur(Listejoueurs[1]) == true) {
            System.out.println("C'est " + joueurCourant.Nom + " qui a gagné !");
        } else {
            System.out.println("C'est " + ProchainJoueur(joueurCourant).Nom + " qui a gagné !");
        }

    }

}
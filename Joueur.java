/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4_fontaine_royer;

import java.util.Random;

/**
 *
 * @author groyer
 */
public class Joueur {
    String Nom; // nom	du joueur
    String PlayerColor;// 	couleur	affectée au joueur
    Jeton [] ListeJetons = new Jeton [21]; //tableau décrivant	les jetons encore en possession	du	joueur
    int nombreDesintegrateurs;//	nombre	de	désintégrateurs	actuellement	en	possession
    int nombreJetonsRestant; //nombre de jetons	 restant en possession du joueur,correspondant à la taille effective de ListeJetons	

public Joueur (String Nickname){
Nom=Nickname;
}
public void affecterCouleur (String color){
PlayerColor=color;
}
public boolean ajouterJeton (Jeton CurrentToken){
if (nombreJetonsRestant<21){
        ListeJetons[nombreJetonsRestant]=CurrentToken;
        nombreJetonsRestant++;
return true;
}else {
return false;
}
}
public void obtenirDesintegrateur (){
   nombreDesintegrateurs++; 
}
public void utiliserDesintegrateur (){
    if (nombreDesintegrateurs>0){
        nombreDesintegrateurs--;
        System.out.println("Désintégrateur utilisé, reste "+nombreDesintegrateurs+"desintégrateur ");
} else { 
    System.out.println("Plus de désintégrateur disponible, tu joues à quoi là ?");
}
}
}


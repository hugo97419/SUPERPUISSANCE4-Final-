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
public class joueur {
    String Nom; // nom	du joueur
    String PlayerColor;// 	couleur	affectÃ©e au joueur
    jeton [] ListeJetons = new jeton [21]; //tableau dÃ©crivant	les jetons encore en possession	du	joueur
    int nombreDesintegrateurs;//	nombre	de	dÃ©sintÃ©grateurs	actuellement	en	possession
    int nombreJetonsRestant; //nombre de jetons	 restant en possession du joueur,correspondant Ã  la taille effective de ListeJetons	

public joueur (String Nickname){
Nom=Nickname;
}
public void affecterCouleur (String color){
PlayerColor=color;
}
public boolean ajouterJeton (jeton CurrentToken){
if (nombreJetonsRestant<21){
        ListeJetons[nombreJetonsRestant]=CurrentToken;
        nombreJetonsRestant++;
return true;
}else {
return false;
}
}
 jeton retirerJeton(){
        nombreJetonsRestant = nombreJetonsRestant-1;
        return ListeJetons[nombreJetonsRestant];
    }

public void obtenirDesintegrateur (){
   nombreDesintegrateurs++; 
}
public void utiliserDesintegrateur (){
    if (nombreDesintegrateurs>0){
        nombreDesintegrateurs--;
        System.out.println("DÃ©sintÃ©grateur utilisÃ©, reste "+nombreDesintegrateurs+"desintÃ©grateur ");
} else { 
    System.out.println("Plus de dÃ©sintÃ©grateur disponible, tu joues Ã  quoi lÃ  ?");
}
}
}
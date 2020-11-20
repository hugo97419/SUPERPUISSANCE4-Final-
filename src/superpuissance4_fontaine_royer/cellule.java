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
public class cellule {
   jeton CurrentToken;
   boolean BlackHole;
   boolean desintegrateur;
   
 cellule(){
        CurrentToken = null; // pas de jeton a la creation de la cellule
        BlackHole = false ; // pas de trou noir 
        desintegrateur = false ; // pas de desintegrateur 
        
    }
    
    boolean affecterJeton(jeton un_jeton){
        if(CurrentToken == null){
            CurrentToken = un_jeton;
            return true;
        }
        return false;
    }
    
    // permet de récupérer le jeton de la cellule.
    // on renvoie une référence vers le jeton, et on l'enleve de la cellule.
    // si la cellule ne contenait pas de jeton, on renvoie juste null
    jeton recupererJeton(){
        jeton jetonRetour = CurrentToken;
        CurrentToken  = null;
        return jetonRetour;
    }
    
    
    boolean supprimerJeton(){
        if(CurrentToken == null){
            return false;
        }
        CurrentToken = null;
        return true;
    }
    
    // renvoie la couleur du jeton affecté a la cellule, ou le mot "vide" sinon
    String lireCouleurDuJeton(){
        if(CurrentToken == null){
            return "vide";
        }
        return CurrentToken.PlayerColor;
    }
    
    // place un trou noir sur la cellule et renvoie vrai
    // ou renvoie faux si le trou noir était déja placé
    Boolean placerTrouNoir(){
        if(BlackHole){
            return false;
        }
        BlackHole = true;
        return true;
    }
    
    Boolean presenceTrouNoir(){
        return BlackHole;
    }
    
    Boolean activerTrouNoir(){
        if(BlackHole){
            CurrentToken = null;
            BlackHole = false;
            System.out.println("Votre pion c'est fait engloutir dans le trou noir !");
            return true;
        }
        return false;
    }
    
    Boolean placerDesintegrateur(){
        if(desintegrateur){
            return false;
        }
        desintegrateur = true;
        return true;
    }
    
    Boolean presenceDesintegrateur(){
        return desintegrateur;
    }
    
    Boolean recupererDesintegrateur(){
        if(presenceDesintegrateur()){
            desintegrateur = false;
            return true;
        }
        return false;
    }
    
    
}
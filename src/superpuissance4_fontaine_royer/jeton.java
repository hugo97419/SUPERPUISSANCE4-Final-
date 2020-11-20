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
public class jeton {
    String PlayerColor ;
    
    
    public jeton (String playercolor) {
        PlayerColor = playercolor;
       
    }
    
    @Override
    public String toString() {
        String Readcolor ;
        Readcolor = "La couleur du joueur est " +PlayerColor;
        return Readcolor ;
    }
}
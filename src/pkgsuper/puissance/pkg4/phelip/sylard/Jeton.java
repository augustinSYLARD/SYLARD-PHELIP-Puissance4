/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper.puissance.pkg4.phelip.sylard;

/**
 *
 * @author asylard
 */
public class Jeton {

    String Couleur;//contient la couleur du jeton
    int a;
    public Jeton (String couleur) {//constructeur de la class Jeton qui donne au jeton la couleur donner en parametre
        Couleur=couleur;
        a=1;
    }
    public String lireCouleur() {//cette fonction permet de lire la couleur d'un jeton
        return Couleur;
    }
}

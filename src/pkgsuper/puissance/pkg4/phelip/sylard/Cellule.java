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
public class Cellule {
    
    Jeton jetonCourant;//jetonCourant contiant le jeton qui se trouve dans la cellule
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule() {//constructeur de la class Cellule
        jetonCourant=null;//on initialise jeton courant pour dire qu'il n'y a pas de jeton dans la cellule de base
        trouNoir=false;
        desintegrateur=false;
    }
    
    public boolean affecterJeton(Jeton jeton) {//permet, si il n'y pas de jeton dans la cellule, d'y placer le jeton mit en parametre
        if (jetonCourant==null) {
            jetonCourant=jeton;
            return true;//si sa marche renvoit true
        }
        else {
            return false;//si sa ne marche pas renvoit false
        }
    }
    
    public Jeton recupererJeton() {//supprime le jeton de la cellule et renvoit sa référence
        Jeton jeton=jetonCourant;//recupere le jeton dans la variable jetonCourant
        supprimerJeton();//vide la variable jetonCourant
        return jeton;//renvoit le jeton qui a été supprimer
    }
    
    public boolean supprimerJeton() {//permet de supprimer un jeton si il y en a un dans la cellule.
        if (jetonCourant==null) {//si il n'y a pas de jeton
            return false;//renvoit false
        }
        else {//si il y en a un
            jetonCourant=null;//le supprime
            return true;//puis renvoit true
        }
    }
    
    public boolean placerTrouNoir() {
        if (trouNoir=true) {
            return false;
        }
        trouNoir=true;
        return true;
    }
    
    public boolean placerDesintegrateur() {
        if (desintegrateur==false) {
            desintegrateur=true;
            return true;
        }
        return false;
    }
    
    public boolean presenceTroueNoir() {
        if (trouNoir==true) {
            return true;
        }
        return false;
    }
    
    public boolean presenceDesintegrateur(){
        if (desintegrateur==true) {
            return true;
        }
        return false;
    }
    
    public String lireCouleurDuJeton() {//renvoit l'attribue couleur du jeton
        return jetonCourant.Couleur;
    }
    
    public boolean recupererDesintegrateur() {
        if (desintegrateur==true) {
            desintegrateur=false;
            return true;
        }
        return false;
    }
    
    public boolean activerTrouNoir() {
        if (trouNoir==true) {
            jetonCourant=null;
            trouNoir=false;
            return true;
        }
        return false;
    }
}

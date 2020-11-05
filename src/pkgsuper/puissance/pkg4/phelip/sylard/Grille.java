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
public class Grille {

    Cellule Cellules[][];//possede un tableau appeler Cellules a deux dimensions contenant des Cellules

    public Grille() {//constructeur de la class d'objet Grille
        Cellules= new Cellule[6][7];//initilise la grille comme un tableau a deux dimensions de 6 lignes et 7 colonnes
        for(int i=0; i<6;i++) {
            for(int j=0; j<7; j++) {
                Cellules[i][j] = new Cellule();//initiliser chaque Cellule de la grille
            }
        }
    }

    public boolean ajouterJetonDansColonne(Jeton jeton, int numcolonne) {//permet d'ajouter le jeton mis en parametre dans la colonne mise en parametre
        for (int i=0; i<6;i++) {
            if (Cellules[i][numcolonne].jetonCourant==null) {//Pour chaque cellules de la colonne, verifie si elle est vide
                if (Cellules[i][numcolonne].presenceTroueNoir()==false) {
                    Cellules[i][numcolonne].affecterJeton(jeton);//si elle l'est, alors il place le jeton a l'interieur
                    return true;//et renvoit true pour terminer la fonction et confirmer que sa a fonctionner
                }
                else {
                    Cellules[i][numcolonne].affecterJeton(jeton);
                    Cellules[i][numcolonne].activerTrouNoir();
                    return true;
                }
            }//sinon passe a la cellule au dessus
        }//si aucune des 6 cellules de la colonnes n'est vide, alors elle est plein et la fonction renvoit false
        return false;//renvoit false et met fin a la fonction
    }
    
    public boolean etreRemplie() {//verifie si la grille et remplie
        for (int i=0; i<6;i++) {
            for (int j=0; j<7;j++) {//pour chaque cellule de la grille,
                if (Cellules[i][j].jetonCourant==null) {//si la celulle est vide
                    return false;//renvoit false
                }//sinon continue pour chaque autre cellules
            }
        }//si aucune cellule n'est vide, alors la grille est renplit
        return true;//renvoit true et met fin a la fonction
    }
    
    public void viderGrille() {//permet de vider la grille
        for (int i=0; i<6;i++) {
            for (int j=0; j<7;j++) {//pour chaque cellule de la grille,
                Cellules[i][j].jetonCourant=null;//efface le jeton qui se trouve dans la cellule
            }
        }
    }
    
    public void afficherGrilleSurConsole() {//permet d'afficher la grille dans la console
        for (int i=5; i>=0;i--) {//pour chaque ligne de haut en bas de la grille,
            System.out.print("|");//affiche en debut de ligne un trait du "tableau"
            for (int j=0; j<7;j++) {//pour chaque élément de la ligne,
                if (Cellules[i][j].jetonCourant==null) {//si il n'ya pas de jeton
                    if (Cellules[i][j].presenceTroueNoir()==true) {//s'il y a un trou noir
                        System.out.print("*");//affiche une étoile s'il y a un trou noir
                        System.out.print("|");//affiche un autre trait du "tableau"
                    }
                    else {//s'il n'y a pas de trou noir
                        if (Cellules[i][j].presenceDesintegrateur()==true) {//s'il y a un désintégrateur
                            System.out.print("/");//affiche un espace
                            System.out.print("|");//affiche un autre trait du "tableau"
                        }
                    }
                }
                else {//sinon, (elle n'est pas vide)
                    if (Cellules[i][j].jetonCourant.Couleur=="rouge") {//si elle est rouge
                        System.out.print("+");//affiche un + si le jeton est rouge
                        System.out.print("|");//affiche un autre trait du "tableau"
                    }
                    else {
                        System.out.print("-");//affiche un - si le jeton est jaune
                        System.out.print("|");//affiche un autre trait du "tableau"
                    }
                }
            }
            System.out.println();//renvoit a la ligne a chaque fin de ligne
        }
    }
    
    public boolean celluleOccupee(int numligne, int numcolonne) {//verifie si une cellule est occuper
        if (Cellules[numligne][numcolonne].jetonCourant==null) {//si la cellule est occuper,
            return false;//renvoit false
        }
        else {//sinon
            return true;//renvoit true
        }
    }
    
    public String lireCouleurDuJeton(int numligne, int numcolonne) {//permet de lire la couleur du jeton
        return Cellules[numligne][numcolonne].lireCouleurDuJeton();//renvoit la couleur du jeton qui se trouve dans la Cellule dont les coordonner sont les entier mis en parametre
    }
    
    public boolean etreGagnantePourJoueur(Joueur joueur) { //A optimiser
        int a;
        for(int i=0;i<6;i++) {//verifie si une ligne comporte 4 jeton a la suite
            a=0;
            for(int j=0;j<7;j++) {
                if (Cellules[i][j].jetonCourant!=null ) {//modifier a se niveau a la
                    if (lireCouleurDuJeton(i,j)==joueur.Couleur) {
                        a++;
                        if (a==4) {
                            return true;
                        }
                    }
                    else {
                        a=0;
                    }
                }
                else {
                    a=0;
                }
            }
        }
        for(int j=0;j<7;j++) {//verifie si une colonne comporte 4 jeton a la suite
            a=0;
            for(int i=0;i<6;i++) {
                if (Cellules[i][j].jetonCourant!=null) {
                    if (lireCouleurDuJeton(i,j)==joueur.Couleur) {
                        a++;
                        if (a==4) {
                            return true;
                        }
                    }
                    else {
                        a=0;
                    }
                }
                else {
                    a=0;
                }
            }
        }
        for(int i=0;i<3;i++) {//verifie si 4 élément sont en diagonale a la suite de droite a gauche et de haut en bas
            for(int j=0;j<4;j++) {
                if (Cellules[i][j].jetonCourant!=null&&Cellules[i+1][j+1].jetonCourant!=null&&Cellules[i+2][j+2].jetonCourant!=null&&Cellules[i+3][j+3].jetonCourant!=null) {
                    if (lireCouleurDuJeton(i,j)==joueur.Couleur) {
                        if (lireCouleurDuJeton(i+1,j+1)==joueur.Couleur) {
                            if (lireCouleurDuJeton(i+2,j+2)==joueur.Couleur) {
                                if (lireCouleurDuJeton(i+3,j+3)==joueur.Couleur) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<3;i++) {//verifie si 4 élément sont en diagonale a la suite de gauche a droite et de haut en bas
            for(int j=3;j<7;j++) {
                if (Cellules[i][j].jetonCourant!=null&&Cellules[i+1][j-1].jetonCourant!=null&&Cellules[i+2][j-2].jetonCourant!=null&&Cellules[i+3][j-3].jetonCourant!=null) {
                    if (lireCouleurDuJeton(i,j)==joueur.Couleur) {
                        if (lireCouleurDuJeton(i+1,j-1)==joueur.Couleur) {
                            if (lireCouleurDuJeton(i+2,j-2)==joueur.Couleur) {
                                if (lireCouleurDuJeton(i+3,j-3)==joueur.Couleur) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void tasserGrille(int numcolonne) {
        for (int i=0; i<5; i++) {
            if (Cellules[i][numcolonne].jetonCourant==null) {
                if (Cellules[i+1][numcolonne].jetonCourant!=null) {
                    Cellules[i][numcolonne].jetonCourant=Cellules[i+1][numcolonne].jetonCourant;
                    Cellules[i+1][numcolonne].jetonCourant=null;
                }
            }
        }
    }
    
    public boolean colonneRemplie(int numcolonne) {//verifie si la colonne est renplit
        for(int i=0;i<6;i++) {//pour chaque ligne
            if (Cellules[i][numcolonne].jetonCourant==null) {//si la cellule est vide,
                return false;//renvoit false
            }
        }//si aucun n'est vide, alors la colonne est remplie
        return true;//renvoi true
    }
    
    public boolean placerTrouNoir(int numligne, int numcolonne) {
        if (Cellules[numligne][numcolonne].trouNoir==false) {
            Cellules[numligne][numcolonne].trouNoir=true;
            return true;
        }
        return false;
    }
    
    public boolean placerDesintegrateur(int numligne, int numcolonne) {
        if (Cellules[numligne][numcolonne].desintegrateur==false) {
            Cellules[numligne][numcolonne].desintegrateur=true;
            return true;
        }
        return false;
    }
    
    public boolean supprimerJeton(int numligne,int numcolonne) {
        if (Cellules[numligne][numcolonne].jetonCourant!=null) {
            Cellules[numligne][numcolonne].supprimerJeton();
            return true;
        }
        else {
            return false;
        }
    }
    
    public Jeton recupererJeton(int numligne,int numcolonne) {
        return Cellules[numligne][numcolonne].recupererJeton();
    }
}
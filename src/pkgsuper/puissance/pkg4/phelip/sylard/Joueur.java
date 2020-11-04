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
public class Joueur {

    String Nom;//on crée la variable Nom contenant le nom du joueur
    String Couleur;//on crée la variable Couleur contenant la couleur du joueur
    Jeton ListeJetons[];//on crée la liste des jeton que possede le joueur
    int nombreJetonsRestants;//on crée aussi la variable contenant le nombre de jeton restant au  joueur
    
    public Joueur(String nom) {//le constructeur de la classe d'objet Joueur
        Nom=nom;//on donne au Nom du joueur la variable donner ne parametre lors de sont initialisation
        ListeJetons = new Jeton[21];//on défini la dimension de la liste de jeton sur 21, il y aura donc maximum 21 jetons
        for (int i=0; i<21; i++) {//pour chaque emplacement dans liste de jeton on y place un jeton de couleur du joueur
            ListeJetons[i]= new Jeton(Couleur);
        }
        nombreJetonsRestants=0;//on definit le nombre de jetons sur 0 car au debut aucun participant na de jeton pour éviter tout problem
    }
    
    public void affecterCouleur(String couleur) {//cette fonction donne au joueur la couleur donner en parametre
        Couleur=couleur;
    }
    
    public void ajouterJeton (Jeton jeton) {//cette fonction permet de rajouter le jeton donner en parametre a la listre de jeton et augmente le nombre de jeton du joueur a 1
        ListeJetons[nombreJetonsRestants]=jeton;
        nombreJetonsRestants++;
    }
}

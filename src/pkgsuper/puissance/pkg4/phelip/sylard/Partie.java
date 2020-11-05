/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper.puissance.pkg4.phelip.sylard;

import java.util.Random;//on importe la bibliotheque de Scanne
import java.util.Scanner;

/**
 *
 * @author asylard
 */
public class Partie {

    Joueur ListeJoueurs[];//on crée la liste comportant les joueurs
    Joueur joueurCourant;//on crée la variable joueur Courant qui va permettre
    Grille grille;//on crée la grille qui va contenir les cellules et les jetons
    
    public Partie() {//Dans le constructeur
        ListeJoueurs = new Joueur[2];//on initialise deux joueurs dans la liste des joueurs
        joueurCourant=null;//on intialise a la variable joueurCourant
        grille = new Grille();//et on initialise la grille
    }
    
    public void attribuerCouleursAuxJoueurs() {//cette fonction permet d'attribuer les couleur aux joueurs
        Random r = new Random();
        int variablealéatoire = r.nextInt(2);//on crée une variablealéatoire qui va contenir  un nombre entre 0 et 1
        if (variablealéatoire==0) {//si la variable est 0
            ListeJoueurs[0].affecterCouleur("rouge");//le premier joueur sera rouge
            ListeJoueurs[1].affecterCouleur("jaune");//le deuxieme sera jaune
        }
        else {//sinon inverse les couleurs
            ListeJoueurs[0].affecterCouleur("jaune");//le premier joueur sera jaune
            ListeJoueurs[1].affecterCouleur("rouge");//le deuxieme sera rouge
        }
    }//on utilise pour cela les fonction affecterCouleur() qui modifie l'attribut Couleur des joueurs
    
    public void initialiserPartie() {//cette fonction va permettre de réaliser les préparatif avant une partie
        grille.viderGrille();//on vide la grille avant de l'utiliser par sécurtité
        Jeton jetonJoueur1 = new Jeton(ListeJoueurs[0].Couleur);//on initialise deux sorte de jetons, pour chacun des joueur en fonction de leur couleur
        Jeton jetonJoueur2 = new Jeton(ListeJoueurs[1].Couleur);
        for (int i=0; i<21; i++) {//puis on attribut ces deux type de jeton au deux joueur
            ListeJoueurs[0].ajouterJeton(jetonJoueur1);//le joueur 1
            ListeJoueurs[1].ajouterJeton(jetonJoueur2);//et le joueur 2
        }
        ListeJoueurs[0].nombreJetonsRestants=21;//on initialise enfin le nombres de jetons restants aux joueur car on vien de donner 21 jeton aux joueurs
        ListeJoueurs[1].nombreJetonsRestants=21;
        
        Random r = new Random();//on crée une classe Random()
        for (int i=0; i<5; i++) {//on place 5 trou noir
            int numligne = r.nextInt(6);//on choisie aléatoirement
            int numcolonne = r.nextInt(7);
            grille.placerTrouNoir(numligne, numcolonne);
        }
    }
    
    public void debuterPartie() {
        Scanner saisieUtilisateur = new Scanner(System.in);//on crée la variable saisieUtilisateur qui va saisir les valeur des Joueurs
        String nom;//on crée la variable nom qui va contenir les nom des joueur pendant un cour instant
        Random r = new Random();
        int variablealéatoire = r.nextInt(2);//on crée une variablealéatoire qui va contenir  un nombre entre 0 et 1
        if (variablealéatoire==0) {
            System.out.println("Saisissez le nom du joueur 1: ");//on demande au joueurs 1 de donner son nom
            nom = saisieUtilisateur.next();//on saisie le nom du joueur dans la variable nom
            ListeJoueurs[0] = new Joueur(nom);//on initialise l'objet joueur avec son nom
            System.out.println("Saisissez le nom du joueur 2: ");//on demande au joueurs 2 de donner son nom
            nom = saisieUtilisateur.next();//on saisie le nom du joueur dans la variable nom
            ListeJoueurs[1] = new Joueur(nom);//on initialise l'objet joueur avec son nom
        }
        else {
            System.out.println("Saisissez le nom du premier joueur: ");//on demande au joueurs 1 de donner son nom
            nom = saisieUtilisateur.next();//on saisie le nom du joueur dans la variable nom
            ListeJoueurs[1] = new Joueur(nom);//on initialise l'objet joueur avec son nom
            System.out.println("Saisissez le nom du deuxieme joueur: ");//on demande au joueurs 2 de donner son nom
            nom = saisieUtilisateur.next();//on saisie le nom du joueur dans la variable nom
            ListeJoueurs[0] = new Joueur(nom);//on initialise l'objet joueur avec son nom
        }
        
        attribuerCouleursAuxJoueurs();//on appelle ensuite la fonction attribuerCouleursAuxJoueurs() pour donner la couleur au joueur qui vienne d'être crée
        initialiserPartie();//on prépare enfin la partie avec les parametre de initiale d'une partie de puissance 4 grâce a la fonction initialiserPartie()
        
        int numtour=0;//on initialise le nombre de tour a 0 dans la variable numtour, qui va contenir le nombre de tour qui se sont produit depuis le debut
        String test;//on créer la variable test qui va servir a savoir si le tour du joueur respect les regle
        boolean finDeLaPartie=false;
        while(finDeLaPartie==false) {//on répete le code qui va suivre tant que l'un des deux joueur n'a pas gagné, ou que la grille est pleine
            joueurCourant=ListeJoueurs[numtour%2];//Le joueurCourant, donc celui qui joue change entre les deux joueurs suivant si le nombre de tour est pair ou impair
            test="";//on attribu "" a test a chaque tour
            while (test!="ok") {//tant que le test n'est pas "ok" on demande a l'utilisateur de séléctionner une colonne
                System.out.println(" 1 2 3 4 5 6 7");//on affiche les numéros des colonnes
                grille.afficherGrilleSurConsole();//on affiche la grille
                System.out.println("Au tour de "+joueurCourant.Nom);//un indique quel joueur doit jouer
                int numLigne=0;
                int numColonne=0;
                int choix=0;
                System.out.println("Que souhaiter vous faire ?");
                System.out.println("1) Placer un jeton");
                System.out.println("2) Récupérer un jeton");
                choix = saisieUtilisateur.nextInt();
                if (choix==1) {
                    while (numColonne<1||numColonne>7) {
                        System.out.println("Dans quel colonne souhaiter vous mettre un jeton?");//on demande au joueur dans quel colonne il souhaite jouer
                        System.out.println("Entrer le numéro de la colonne souhaiter.(entre 1 et 7)");//on lui demande de saisir le numéro souhaité
                        numColonne = saisieUtilisateur.nextInt();//on attribut a la variable numcolonne, le numéro que vient de rentrer le joueur
                    }
                    if (grille.colonneRemplie(numColonne-1)==false) {//Si la colonne n'est pas remplie alors
                        grille.ajouterJetonDansColonne(joueurCourant.ListeJetons[joueurCourant.nombreJetonsRestants-1], numColonne-1);//on ajoute un jeton dans la colonne souhaiter
                        joueurCourant.ListeJetons[joueurCourant.nombreJetonsRestants-1]=null;//on retire un jeton au joueur
                        joueurCourant.nombreJetonsRestants--;
                        test="ok";//le test est alors ok, et la boucle s'arrette
                    }
                    else {//sinon on demander de saisir une autre colonne
                        System.out.println("la colonne est pleine, veuillez saisir une autre colonne.");
                    }
                }
                else {
                    if (choix==2) {
                        System.out.println("Quel jeton souhaiter vous récuperer ?");
                        while (numColonne<1||numColonne>7) {
                            System.out.println("Entrer la colonne du jeton que vous souhaiter récuperer");
                            numColonne=saisieUtilisateur.nextInt();
                        }
                        while (numLigne<1||numLigne>6) {
                            System.out.println("Entrer la ligne du jeton que vous souhaiter récuperer");
                            numLigne=saisieUtilisateur.nextInt();
                        }
                        if (grille.Cellules[numLigne-1][numColonne-1].jetonCourant!=null) {
                            if (grille.Cellules[numLigne-1][numColonne-1].lireCouleurDuJeton()==joueurCourant.Couleur) {
                                grille.recupererJeton(numLigne-1, numColonne-1);
                                grille.tasserGrille(numColonne-1);
                            }
                            else {
                                System.out.println("Ce jeton n'est pas a vous !!!");
                            }
                        }
                    }
                }
            }
            numtour++;//on augmente le nombre de tour qui c'est produit de 1
            if (grille.etreGagnantePourJoueur(ListeJoueurs[0])==true)//si le joueur 1 a gagné on donne à finDeLaPartie la valeur true
                finDeLaPartie=true;
            else {//sinon
                if (grille.etreGagnantePourJoueur(ListeJoueurs[1])==true) {//si le joueur 2 a gagné on donne à finDeLaPartie la valeur true
                    finDeLaPartie=true;
                }
                else {//sinon
                    if (grille.etreRemplie()==true) {//si la grille est pleine on donne à finDeLaPartie la valeur true
                        finDeLaPartie=true;
                    }
                }
            }
        }
        
        grille.afficherGrilleSurConsole();//on affiche une derniere fois la grille
        
        if (grille.etreGagnantePourJoueur(ListeJoueurs[0])==true) {//si le joueur 1 a gagné
            System.out.println(ListeJoueurs[0].Nom+" a Gagné !!!!");//on affiche un message de felicitation avec son nom
            System.out.println("Bravo à toi!");
        }
        else {
            if (grille.etreGagnantePourJoueur(ListeJoueurs[1])==true) {//si le joueur 2 a gagné
                System.out.println(ListeJoueurs[1].Nom+" a Gagné !!!");//on affiche un message de felicitation avec son nom
                System.out.println("Bravo à toi!");
            }
            else {//sinon c'est qu'il y a match nul
                System.out.println("Matche nul, vous etes très fort !!!");//on indique donc qu'il y a match nul
            }
        }
        System.out.println("il restait "+ListeJoueurs[0].nombreJetonsRestants+" jetons à "+ListeJoueurs[0].Nom);//on affiche aussi le nombre de jetons restants au deux joueurs
        System.out.println("et "+ListeJoueurs[1].nombreJetonsRestants+" jetons à "+ListeJoueurs[1].Nom);
    }
}
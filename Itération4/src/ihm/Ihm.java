package ihm;

import java.util.Scanner;

import static java.lang.Math.min;

public class Ihm {
    public int saisirNbreTas() {
        print("Veuillez choisir le numero du tas");
        return saisirEntier();
    }

    public String saisirNomJoueur() {
        print("Veuillez saisir le nom du joueur");
        return saisirString();
    }
    public int saisir1ou0(){
        boolean bool = true;
        int nbre =saisirEntier();
        while (bool){
            if (nbre==1){
                bool=false;
            }else if (nbre==0){
                bool=false;
            }
            else{
                print("Veuillez saisir 1 ou 0");
            }
        }
        return nbre;

    }
    public int choixJeu(){
        print ("Veuillez choisir un jeu : \n 1 pour le jeu de Nim ou 0 pour le jeu de Puissance4");
        return saisir1ou0();
    }
    public int choixIA() {
        int choix = 0;
        print("Veuillez choisir 1 pour jouer contre l'IA, 0 pour jouer à 2 joueurs");
        choix =saisir1ou0();
        return choix;
    }
    public int saisirNumTas() {
        return saisirEntier();
    }

    public int saisirNbreAllumette(int n) {
        print("Veuillez choisir le nbre d'allumette a retirer. Il doit etre inferieur a " + n);
        return saisirEntier();
    }

    public int saisirEntier() {
        boolean bool = true;
        int nbre = 0;
        Scanner sc = new Scanner(System.in);
        while (bool) {
            if (sc.hasNextInt()) {
                nbre = sc.nextInt();
                if (nbre < 0) {
                    print("Veuillez entrer un entier positif");
                } else {
                    bool = false;
                }
            } else {
                print("Un entier est attendu");
                sc.next();
            }
        }
        return nbre;
    }

    private String saisirString() {
        String s = "";
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            if (sc.hasNext()) {
                s = sc.next();
                bool = false;
            } else {
                print("Veuillez saisir une chaine de caractere valide");
            }
        }
        return s;
    }
    public int saisirNumColonne() {
        return saisirEntier();
    }

    public void formeCoup(String tas, String joueur) {
        print("Etat des tas \n" + tas + "\n" + joueur + " : à vous de jouer un coup dans deux case differentes dont la premiere" +
                " represente le numero du tas et la seconde le nombre d'allumette a retirer");
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void formeCoupPui4(String etatGrille, String nom, String jeton) {
        print("Etat de la grille \n"+etatGrille+"\n "+nom+" a vous jouer, votre jeton est de couleur "+jeton+"; Veuillez choisir le numero de la colonne");
    }

    public int saisirNbreAllumetteC(int n,int c) {
        print("Veuillez choisir le nbre d'allumette a retirer. Il doit etre inferieur a " + min(n, c));
        boolean bool = true;
        int x = 0;
        while (bool) {
            x = saisirEntier();
            if (x > c) {
                print("Veuillez rechoisir le nbre d'allumette a retirer. Il doit etre inferieur a " + min(n, c));
            } else {
                bool = false;
            }
        }
        return x;
    }
}

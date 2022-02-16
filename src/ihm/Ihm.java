package ihm;

import java.util.Scanner;

public class Ihm {

    public int saisirNbreTas() {
        print("Veuillez choisir le numero du tas");
        return saisirEntier();
    }

    public String saisirNomJoueur() {
        print("Veuillez saisir le nom du joueur");
        return saisirString();
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


    public void formeCoup(String n, String s) {
        print("Etat des tas \n" + n + "\n" + s + " : à vous de jouer un coup dans deux case differentes dont la premiere" +
                " represente le numero du tas et la seconde le nombre d'allumette a retirer");
    }

    public void print(String msg) {
        System.out.println(msg);
    }

}

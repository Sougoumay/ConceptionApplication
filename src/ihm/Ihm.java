package ihm;

import java.util.Scanner;

public class Ihm {

    public int saisirNbreTas() {
        int nbreTas=saisirEntier();
        return nbreTas;
    }

    public int saisirEntier()
    {
        Scanner sc = new Scanner(System.in);
        return 1;
    }

    public String saisirNomJoueur() {
        String joueur = saisirString();
        return joueur;
    }

    private String saisirString() {
        return "";
    }

    public int saisirNumTas() {
        return saisirEntier();
    }

    public int saisirNbreAllumette() {
        return saisirEntier();
    }

    public void formeCoup(String s)
    {
        System.out.println(s + " : à vous de jouer un coup");
    }
}

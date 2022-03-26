package ihm;
 

import java.util.Scanner;

public class Ihm {

        public String saisirNomJoueur() {
            print("Veuillez saisir le nom du joueur");
            return saisirString();
        }
        public int saisirNumColonne() {
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


        public void formeCoup(String etatGrille, String nom, String jeton) {
            print("Etat de la grille \n" + etatGrille + "\n" + nom+ " jeton = "+jeton+" : à vous de jouer un coup, Veuillez choisir un numéro de grille" );
        }

        public void print(String msg) {
            System.out.println(msg);
        }

    }

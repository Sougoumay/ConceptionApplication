package main;

import controleur.*;
import ihm.Ihm;
import modele.CoupInvalideException;

public class Main {

    public static void main(String[] args) throws CoupInvalideException {
	// write your code here
        Ihm ihm = new Ihm();
        ControleurJeu ctrl;
        int choix = ihm.choixJeu();
        int choixIA = ihm.choixIA();
        if (choix == 1) {
            ihm.print("---------------Bienvenue dans le jeu Nim----------------");
            if (choixIA==0) {
                ctrl=new ControleurContrainte(ihm);
                ctrl.jouer();
            }
            else {
                ctrl=new ControleurNimIA(ihm);
                ctrl.jouer();
            }
        }
        else if( choix == 0){
            ihm.print("--------------Bienvenue dans le jeu Puissance4-----------------");
            if (choixIA==0) {
                ctrl=new ControleurRotation(ihm);
                ctrl.jouer();
            }
            else {
                ctrl=new ControleurPui4IA(ihm);
                ctrl.jouer();
            }
        }
    }
}

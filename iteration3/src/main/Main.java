package main;

import controleur.*;
import ihm.Ihm;
import modele.CoupInvalideException;

public class Main {

    public static void main(String[] args) throws CoupInvalideException {
	// write your code here
        Ihm ihm = new Ihm();
        ControleurJeu ctrl = new ControleurJeu();
        boolean bool = true;

        while(bool){
            int choix = ihm.choixJeu();
            if (choix == 1) {
                ihm.print("---------------Bienvenue dans le jeu Nim----------------");
                ctrl.setJouer(new ControleurJeuNim(ihm));
                ctrl.lancerJeu();
                bool=false;
            }
            else if( choix == 2){
                ihm.print("--------------Bienvenue dans le jeu Puissance4-----------------");
                ctrl.setJouer(new RotationPuissance(ihm));
                ctrl.lancerJeu();
                bool=false;
            }
            else{
                ihm.print("Veuillez choisir 1 ou 2");
            }
        }

    }
}

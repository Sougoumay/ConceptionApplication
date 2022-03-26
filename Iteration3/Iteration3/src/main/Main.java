package main;

import controleur.*;
import ihm.Ihm;
import modele.CoupInvalideException;

public class Main {

    public static void main(String[] args) throws CoupInvalideException {
	// write your code here
        Ihm ihm = new Ihm();
        ControleurJeu ctrl;
        boolean bool = true;

        while(bool){
            int choix = ihm.choixJeu();
            if (choix == 1) {
                ihm.print("---------------Bienvenue dans le jeu Nim----------------");
                ctrl=new ControleurContrainte(ihm);
                ctrl.jouer();
                bool=false;
            }
            else if( choix == 2){
                ihm.print("--------------Bienvenue dans le jeu Puissance4-----------------");
                ctrl=new ControleurRotation(ihm);
                ctrl.jouer();
                bool=false;
            }
            else{
                ihm.print("Veuillez choisir 1 ou 2");
            }
        }

    }
}

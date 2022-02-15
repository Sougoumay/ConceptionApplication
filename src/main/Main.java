package main;

import controller.ControleurJeuNim;
import ihm.Ihm;
import modele.CoupInvalideException;

public class Main {

    public static void main(String[] args) throws CoupInvalideException {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim=new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}

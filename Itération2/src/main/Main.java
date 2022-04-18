package main;

import controller.ControleurJeuPuissance;
import ihm.Ihm;

public class Main {

    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuPuissance controleurJeuPuissance=new ControleurJeuPuissance(ihm);
        controleurJeuPuissance.jouer();
    }
}
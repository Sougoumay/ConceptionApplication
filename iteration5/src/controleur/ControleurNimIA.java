package controleur;

import ihm.Ihm;
import modele.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;


public class ControleurNimIA extends ControleurContrainte {


    public ControleurNimIA(Ihm ihm) {
        super(ihm);
    }


    public void nomJoueurs() {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new IANim("Ordinateur");
    }

    public void gameContinueWithContrainte () throws CoupInvalideException {
        while (!tas.partieTerminee()) {
            choixCoupNimC();
        }
    }

    public void gameContinueWithoutContrainte () throws CoupInvalideException {
        while (!tas.partieTerminee()) {
            choixCoup();
        }
    }

    public void choixCoupNimC() {
        try {
            int numTas;
            int nbreAllumette;

            if (nbreCoup[1] < nbreCoup[0]) {
                plateau = ((IANim) joueur2).gererCoup(plateau,1,choix);
                nbreCoup[1] = ((IANim) joueur2).getNbreCoup();
            } else {
                ihm.formeCoup(tas.toString(), joueur1.getNom());
                numTas = ihm.saisirNumTas();
                nbreAllumette = ihm.saisirNbreAllumetteC(tas.nbAllumettes(numTas), choix);
                coup = new CoupNim(numTas, nbreAllumette);
                enregistrerCoup(coup);
            }
        } catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoupNimC();
        }
    }

    public void choixCoup () {
        try {
            int numTas;
            int nbreAllumette;

            if (nbreCoup[1] < nbreCoup[0]) {
                plateau = ((IANim) joueur2).gererCoup(plateau,0,0);
                nbreCoup[1] = ((IANim) joueur2).getNbreCoup();
            } else {
                ihm.formeCoup(tas.toString(), joueur1.getNom());
                numTas = ihm.saisirNumTas();
                nbreAllumette = ihm.saisirNbreAllumette(tas.nbAllumettes(numTas));
                coup = new CoupNim(numTas, nbreAllumette);
                enregistrerCoup(coup);
            }

        } catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoup();
        }
    }
}


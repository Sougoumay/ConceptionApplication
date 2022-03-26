package controleur;

import ihm.Ihm;
import modele.CoupInvalideException;
import modele.CoupNim;

public class ControleurContrainte extends ControleurJeuNim {

    public ControleurContrainte(Ihm ihm) {
        super(ihm);
    }

    public void gameContinueWithContrainte(){
        while (!tas.partieTerminee()) {
            choixCoupNimC();
        }
    }

    public void choixCoupNimC(){
        try{
            int numTas;
            int nbreAllumette;

            if (nbreCoup[1] < nbreCoup[0]) {
                ihm.formeCoup(tas.toString(), joueur2.getNom());
            } else {
                ihm.formeCoup(tas.toString(), joueur1.getNom());
            }
            numTas = ihm.saisirNumTas();
            nbreAllumette = ihm.saisirNbreAllumetteC(tas.nbAllumettes(numTas), choix);
            coup = new CoupNim(numTas, nbreAllumette);
            enregistrerCoup(coup);
        } catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoupNimC();
        }
    }
}


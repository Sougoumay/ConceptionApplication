package controleur;

import modele.CoupInvalideException;

public class ControleurJeu {
    private Controleur ctrl;

    public void setJouer(Controleur controleur) {
        this.ctrl = controleur;
    }

    public void lancerJeu() throws CoupInvalideException {
        ctrl.jouer();
    }
}

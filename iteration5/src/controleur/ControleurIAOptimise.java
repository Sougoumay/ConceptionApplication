package controleur;

import ihm.Ihm;
import modele.*;

public class ControleurIAOptimise extends ControleurPui4IA{
    public ControleurIAOptimise(Ihm ihm) {
        super(ihm);
    }

    public void nomJoueurs() {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new IAOptimise("Ordinateur");
    }

    public void gameContinueWithoutContrainte() throws CoupInvalideException {
        while (!grille.partieTermine("rouge") && !grille.partieTermine("jaune") && !grille.GrillePleine()){
            if(nbreCoup[1]<nbreCoup[0]){
                plateau = ((IAOptimise) joueur2).strategieSansContrainteIA(plateau);
                nbreCoup[1] = ((IAOptimise) joueur2).getNbreCoup();
            } else {
                choixCoup();
            }
        }
    }
}

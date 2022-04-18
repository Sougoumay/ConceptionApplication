package controleur;

import ihm.Ihm;
import modele.*;

public class ControleurPui4IA extends ControleurRotation {
    public ControleurPui4IA(Ihm ihm) {
        super(ihm);
    }


    public void nomJoueurs() {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new IAPui4("Ordinateur");
    }

    public void gameContinueWithoutContrainte() throws CoupInvalideException {
        while (!grille.partieTermine("rouge") && !grille.partieTermine("jaune") && !grille.GrillePleine()){
            if(nbreCoup[1]<nbreCoup[0]){
                plateau = ((IAPui4) joueur2).strategieSansContrainteIA(plateau);
                nbreCoup[1] = ((IAPui4) joueur2).getNbreCoup();
            } else {
                choixCoup();
            }
        }
    }

    public void gameContinueWithContrainte() throws CoupInvalideException {
        while (!grille.partieTermine("rouge") && !grille.partieTermine("jaune") && !grille.GrillePleine()) {
            if(nbreCoup[1]<nbreCoup[0]) {
                if(nbRot[1]<=nbMaxRota){
                    if(((IAPui4) joueur2).rotation(grille)==1){
                        plateau = rotationDroite(grille);
                        nbRot[1] = ((IAPui4) joueur2).getNbRot();
                        nbreCoup[1] = ((IAPui4) joueur2).getNbreCoup();;
                    }else if(((IAPui4) joueur2).rotation(grille)==2){
                        plateau = rotationGauche(grille);
                        nbRot[1] = ((IAPui4) joueur2).getNbRot();
                        nbreCoup[1] = ((IAPui4) joueur2).getNbreCoup();
                    } else {
                        plateau = ((IAPui4) joueur2).strategieContrainteIA(plateau);
                        nbreCoup[1] = ((IAPui4) joueur2).getNbreCoup();
                    }
                } else {
                    plateau = ((IAPui4) joueur2).strategieContrainteIA(plateau);
                    nbreCoup[1] = ((IAPui4) joueur2).getNbreCoup();
                }
            }if(!grille.partieTermine("rouge") && !grille.partieTermine("jaune") && !grille.GrillePleine()){
                if(nbreCoup[0]==nbreCoup[1]) {
                    if(nbRot[0]<=nbMaxRota){
                        if(effectuerRotation()){
                            if(sensRotation()==1){
                                plateau = grille = ((IAPui4) joueur2).rotationDroite(grille);
                                nbreCoup[1] = ((IAPui4) joueur2).getNbreCoup();
                            } else {
                                plateau = grille = ((IAPui4) joueur2).rotationGauche(grille);
                            }
                            nbRot[0]++;
                            nbreCoup[0]++;
                        } else {
                            choixCoup();
                        }
                    } else {
                        choixCoup();
                    }
                }
            }
        }
    }

}

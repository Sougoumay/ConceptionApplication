package controleur;

import ihm.Ihm;

import modele.CoupInvalideException;
import modele.CoupNim;
import modele.Tas;

public abstract class ControleurJeuNim  extends ControleurJeu {
    Tas tas;
    int nbreTas;

    public ControleurJeuNim(Ihm ihm) {
        super(ihm);
    }


    public void initialiserJeu(){
        choixNbreTas();
        tas.initialiser();
        plateau=tas;
    }

    public void gameContinueWithoutContrainte() throws CoupInvalideException {
        while (!tas.partieTerminee()) {
            choixCoup();
        }
    }

    public void vainqueurPartie() {
        if (nbreCoup[1] == nbreCoup[0]) {
            joueur2.gagnePartie();
            nbrePartie++;
            ihm.print(joueur2.getNom() + " vous etes le vainqueur de la partie " + nbrePartie);
        } else {
            joueur1.gagnePartie();
            nbrePartie++;
            ihm.print(joueur1.getNom() + " vous etes le vainqueur de la partie " + nbrePartie);
        }
    }

    public void rejouerPartie() throws CoupInvalideException {
        nbreCoup[0]=nbreCoup[1]=0;
        int choix = choixRejouerPartie();
        if(choix==1) {
            tas.initialiser();
            plateau=tas;
            gameContinue();
            vainqueurPartie();
            rejouerPartie();
        } else {
            vainqueurJeu();
        }

    }

    public void choixNbreTas() {
        nbreTas = ihm.saisirNbreTas();
        tas = new Tas(nbreTas);
    }

    public void choixCoup() {
        try {
            int numTas;
            int nbreAllumette;

            if (nbreCoup[1] < nbreCoup[0]) {
                ihm.formeCoup(tas.toString(), joueur2.getNom());
            } else {
                ihm.formeCoup(tas.toString(), joueur1.getNom());
            }
            numTas = ihm.saisirNumTas();
            nbreAllumette = ihm.saisirNbreAllumette(tas.nbAllumettes(numTas));
            coup = new CoupNim(numTas, nbreAllumette);
            enregistrerCoup(coup);
        } catch (CoupInvalideException e){
            ihm.print("Erreur! " + e.getMessage());
            choixCoup();
        }

    }
    public void choixContrainte(){
        ihm.print("\nVoulez-vous integrer une contrainte de tirage ? Veuillez saisir un entier positif pour oui, 0 pour non");
        choix = ihm.saisirEntier();
    }
}

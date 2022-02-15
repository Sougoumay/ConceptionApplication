package controller;

import ihm.Ihm;
import modele.CoupInvalideException;
import modele.Joueur;
import modele.Tas;
import modele.CoupNim;

public class ControleurJeuNim {

    Ihm ihm;
    Tas tas;

    Joueur joueur1;
    Joueur joueur2;
    int[] nbreCoup = new int[2];

    public ControleurJeuNim(Ihm ihm) {

    }

    public void jouer() throws CoupInvalideException {
        choixNbreTas();
        nomJoueurs();
        while(!tas.partieTerminee())
        {
            choixCoupNim();
        }
    }

    public void choixNbreTas() {
        int nbreTas = ihm.saisirNbreTas();
        tas = new Tas(nbreTas);
    }

    public void nomJoueurs()
    {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new Joueur(ihm.saisirNomJoueur());
    }

    public void choixCoupNim()
    {
        int[] coup = new int[2];
        int numTas;
        int nbreAllumette;
        if(nbreCoup[1]<nbreCoup[0]) {
            ihm.formeCoup(joueur2.getNom());
            numTas = ihm.saisirNumTas();
            nbreAllumette = ihm.saisirNbreAllumette();
        } else {
            ihm.formeCoup(joueur1.getNom());
            numTas = ihm.saisirNumTas();
            nbreAllumette = ihm.saisirNbreAllumette();
        }
        coup[0]=numTas;
        coup[1]=nbreAllumette;
        enregistrerCoup(coup);
    }

    public void enregistrerCoup(int[] coup)
    {
        try {
            if(nbreCoup[1]<nbreCoup[0]) {
                tas.gererCoup(new CoupNim(coup[0], coup[1]));
                nbreCoup[1] = nbreCoup[1]+1;
            } else {
                tas.gererCoup(new CoupNim(coup[0], coup[1]));
                nbreCoup[0] = nbreCoup[0]+1;
            }
        } catch (CoupInvalideException e) {
            e.printStackTrace();
            choixCoupNim();
        }

    }


}

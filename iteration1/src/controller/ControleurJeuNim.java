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
    int nbreTas;
    int nbrePartie=0;
    int[] nbreCoup = {0, 0};

    public ControleurJeuNim(Ihm ihm){
        this.ihm = ihm;
    }

    public void jouer() {
        choixNbreTas();
        nomJoueurs();
        tas.initialiser();
        gameContinue();
        vainqueur();
        rejouerPartie();
    }
    
    public void gameContinue() {
        while (!tas.partieTerminee()) {
            choixCoupNim();
        }
    }
    
    public void vainqueur() {
        if (nbreCoup[1] < nbreCoup[0]) {
            joueur1.gagnePartie();
            nbrePartie++;
            ihm.print(joueur1.getNom() + " vous etes le vainqueur de la partie " + nbrePartie);
        } else {
            joueur2.gagnePartie();
            nbrePartie++;
            ihm.print(joueur2.getNom() + " vous etes le vainqueur de la partie " + nbrePartie);
        }
    }

    public void rejouerPartie() {
        nbreCoup[0]=nbreCoup[1]=0;
        boolean bool = true;
        ihm.print("\nVoulez-vous rejouer la partie? Veuillez saisir 1 pour oui, 0 pour non");
        int choix = ihm.saisirEntier();
        while(bool) {
            if(choix==1 || choix==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 ou 0 si vous voulez rejouer");
                choix = ihm.saisirEntier();
            }
        }
        if(choix==1) {
            tas = new Tas(nbreTas);
            tas.initialiser();
            gameContinue();
            vainqueur();
            rejouerPartie();
        } else {
            if(joueur2.getNbPartiesGagnees() < joueur1.getNbPartiesGagnees()) {
                ihm.print(joueur1.getNom() + " vous etes le vainqueur avec " + joueur1.getNbPartiesGagnees() +" partie(s) gagnée(s) contre " +
                        joueur2.getNbPartiesGagnees() + " pour" + joueur1.getNom());
            } else if(joueur1.getNbPartiesGagnees()< joueur2.getNbPartiesGagnees()) {
                ihm.print(joueur2.getNom() + " vous etes le vainqueur avec " + joueur2.getNbPartiesGagnees() +" partie(s) gagnée(s) contre " +
                        joueur1.getNbPartiesGagnees() + " pour" + joueur1.getNom());
            } else {
                ihm.print("ex aequo");
            }
        }
        
    }

    public void choixNbreTas() {
        nbreTas = ihm.saisirNbreTas();
        tas = new Tas(nbreTas);
    }

    public void nomJoueurs() {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new Joueur(ihm.saisirNomJoueur());
    }

    public void choixCoupNim() {
        int[] coup = new int[2];
        int numTas;
        int nbreAllumette;

        if (nbreCoup[1] < nbreCoup[0]) {
            ihm.formeCoup(tas.toString(), joueur2.getNom());
        } else {
            ihm.formeCoup(tas.toString(), joueur1.getNom());
        }
        numTas = ihm.saisirNumTas();
        while (nbreTas < numTas) {
            ihm.print("Veuillez saisir un numero de tas inferieur ou egal a " + nbreTas);
            numTas = ihm.saisirNumTas();
        }
        nbreAllumette = ihm.saisirNbreAllumette(tas.nbAllumettes(numTas));
        coup[0] = numTas;
        coup[1] = nbreAllumette;
        enregistrerCoup(coup);
    }

    public void enregistrerCoup(int[] coup) {
        try {
            if (nbreCoup[1] < nbreCoup[0]) {
                tas.gererCoup(new CoupNim(coup[0], coup[1]));
                nbreCoup[1] = nbreCoup[1] + 1;
            } else {
                tas.gererCoup(new CoupNim(coup[0], coup[1]));
                nbreCoup[0] = nbreCoup[0] + 1;
            }
        } catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoupNim();
        }

    }
}



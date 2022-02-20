package controller;

import ihm.Ihm;
import modele.CoupInvalideException;
import modele.Joueur;
import modele.Tas;
import modele.CoupNim;

public class ControleurJeuNim {

    Ihm ihm;
    Tas tas;

    Joueur[] lesJoueurs = new Joueur[2];
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
            lesJoueurs[0].gagnePartie();
            nbrePartie++;
            ihm.print(lesJoueurs[0].getNom() + " vous etes le vainqueur de la partie " + nbrePartie);
        } else {
            lesJoueurs[1].gagnePartie();
            nbrePartie++;
            ihm.print(lesJoueurs[1].getNom() + " vous etes le vainqueur de la partie " + nbrePartie);
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
            if(lesJoueurs[1].getNbPartiesGagnees() < lesJoueurs[0].getNbPartiesGagnees()) {
                ihm.print(lesJoueurs[0].getNom() + " vous etes le vainqueur avec " + lesJoueurs[0].getNbPartiesGagnees() +" partie(s) gagnée(s) contre " +
                        lesJoueurs[1].getNbPartiesGagnees() + " pour " + lesJoueurs[0].getNom());
            } else if(lesJoueurs[0].getNbPartiesGagnees()< lesJoueurs[1].getNbPartiesGagnees()) {
                ihm.print(lesJoueurs[1].getNom() + " vous etes le vainqueur avec " + lesJoueurs[1].getNbPartiesGagnees() +" partie(s) gagnée(s) contre " +
                        lesJoueurs[0].getNbPartiesGagnees() + " pour " + lesJoueurs[0].getNom());
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
        Joueur joueur1 = new Joueur(ihm.saisirNomJoueur());
        lesJoueurs[0]=joueur1;
        Joueur joueur2 = new Joueur(ihm.saisirNomJoueur());
        lesJoueurs[1]=joueur2;
    }

    public void choixCoupNim() {
        int numTas;
        int nbreAllumette;

        if (nbreCoup[1] < nbreCoup[0]) {
            ihm.formeCoup(tas.toString(), lesJoueurs[1].getNom());
        } else {
            ihm.formeCoup(tas.toString(), lesJoueurs[0].getNom());
        }
        numTas = ihm.saisirNumTas();
        while (nbreTas < numTas) {
            ihm.print("Veuillez saisir un numero de tas inferieur ou egal a " + nbreTas);
            numTas = ihm.saisirNumTas();
        }
        nbreAllumette = ihm.saisirNbreAllumette(tas.nbAllumettes(numTas));
        CoupNim coup = new CoupNim(numTas,nbreAllumette);
        enregistrerCoup(coup);
    }

    public void enregistrerCoup(CoupNim coup) {
        try {
            if (nbreCoup[1] < nbreCoup[0]) {
                tas.gererCoup(coup);
                nbreCoup[1] = nbreCoup[1] + 1;
            } else {
                tas.gererCoup(coup);
                nbreCoup[0] = nbreCoup[0] + 1;
            }
        } catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoupNim();
        }

    }
}



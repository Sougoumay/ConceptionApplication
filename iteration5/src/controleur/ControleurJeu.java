package controleur;

import ihm.Ihm;
import modele.Coup;
import modele.CoupInvalideException;
import modele.Joueur;
import modele.Plateau;

public abstract class ControleurJeu {
    Coup coup;
    Joueur joueur1;
    Joueur joueur2;
    Ihm ihm;
    int choix;
    int[] nbreCoup = {0,0};
    Plateau plateau;
    int nbrePartie = 0;

    public ControleurJeu(Ihm ihm){
        this.ihm = ihm;
    }

    public void jouer() throws CoupInvalideException {
        nomJoueurs();
        initialiserJeu();
        gameContinue();
        vainqueurPartie();
        rejouerPartie();
    }

    public abstract void initialiserJeu();

    public void gameContinue() throws CoupInvalideException {
        choixContrainte();
        if(choix>0){
            gameContinueWithContrainte();
        } else if(choix==0) {
            gameContinueWithoutContrainte();
        }
    }

    public void nomJoueurs() {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new Joueur(ihm.saisirNomJoueur());
    }

    public void enregistrerCoup(Coup coup) {
        try {
            if (nbreCoup[1] < nbreCoup[0]) {
                plateau.gererCoup(coup);
                nbreCoup[1]++;
            } else {
                plateau.gererCoup(coup);
                nbreCoup[0]++;
            }
        }
        catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoup();
        }
    }

    public void vainqueurJeu(){
        if(joueur2.getNbPartiesGagnees() < joueur1.getNbPartiesGagnees()) {
            ihm.print(joueur1.getNom() + " vous etes le vainqueur avec " + joueur1.getNbPartiesGagnees() +" partie(s) gagnée(s) contre " +
                    joueur2.getNbPartiesGagnees() + " pour " + joueur2.getNom());
        } else if(joueur1.getNbPartiesGagnees()< joueur2.getNbPartiesGagnees()) {
            ihm.print(joueur2.getNom() + " vous etes le vainqueur avec " + joueur2.getNbPartiesGagnees() +" partie(s) gagnée(s) contre " +
                    joueur1.getNbPartiesGagnees() + " pour " + joueur1.getNom());
        } else {
            ihm.print("ex aequo");
        }
    }

    public int choixRejouerPartie(){
        boolean bool = true;
        ihm.print("\nVoulez-vous rejouer la partie? Veuillez saisir 1 pour oui, 0 pour non");
        int choix = -1;
        while(bool) {
            choix = ihm.saisirEntier();
            if(choix==1 || choix==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 ou 0 si vous voulez rejouer");
            }
        }
        return choix;
    }

    public abstract void choixContrainte();

    public abstract void gameContinueWithContrainte() throws CoupInvalideException;

    public abstract void gameContinueWithoutContrainte() throws CoupInvalideException;

    public abstract void vainqueurPartie();

    public abstract void rejouerPartie() throws CoupInvalideException;

    public abstract void choixCoup();





}

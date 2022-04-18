package controller;

import ihm.Ihm;
import modele.CoupInvalideException;
import modele.Joueur;
import modele.Grille;
import modele.CoupPuissance;

public class ControleurJeuPuissance {

    Ihm ihm;
    Grille grille;


    Joueur joueur1;
    Joueur joueur2;
    int nbrePartie=1;

    public ControleurJeuPuissance(Ihm ihm){
        this.ihm = ihm;
    }

    /**
     *
     */
    public void jouer() {
        nomJoueurs();
        grille = new modele.Grille();
        gameContinue();
        vainqueur();
        rejouerPartie();
    }

    /**
     *
     */
    public void gameContinue() {
        while (!grille.partieTermine("jaune") && !grille.partieTermine("rouge") && !grille.GrillePleine()) {
            choixCoupPuissance(joueur1,"rouge");
            if(!grille.partieTermine("jaune") && !grille.partieTermine("rouge") && !grille.GrillePleine()){
                choixCoupPuissance(joueur2, "jaune");
            }
        }
    }

    public void vainqueur() {
        ihm.print(grille.toString());
        if(grille.GrillePleine()){
            nbrePartie++;
            ihm.print("Vous etes Ex-aequo");
            return;
        }
        if(grille.partieTermine("rouge")){
            joueur1.gagnePartie();
            ihm.print(joueur1.getNom()+" vous etes le vainqueur de la partie "+nbrePartie);
            nbrePartie++;
        }else{
            joueur2.gagnePartie();
            ihm.print(joueur2.getNom()+" vous etes le vainqueur de la partie "+nbrePartie);
            nbrePartie++;
        }
    }

    public void rejouerPartie() {
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
            grille = new Grille();
            gameContinue();
            rejouerPartie();
        } else {
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

    }
    public void nomJoueurs() {
        joueur1 = new Joueur(ihm.saisirNomJoueur());
        joueur2 = new Joueur(ihm.saisirNomJoueur());
    }

    public void choixCoupPuissance(Joueur j, String jeton) {
        ihm.formeCoup(grille.toString(),j.getNom(),jeton);
        int numColonne = ihm.saisirNumColonne();
        
        CoupPuissance coup = new CoupPuissance(numColonne,jeton);
        enregistrerCoup(coup,j);
    }

    public void enregistrerCoup(CoupPuissance coup, Joueur j)
    {
        try {
            grille.gererCoup(coup);
        }
        catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoupPuissance(j, coup.getJeton());
        }
    }

}

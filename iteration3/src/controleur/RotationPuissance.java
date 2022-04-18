package controleur;


import ihm.Ihm;
import modele.CoupInvalideException;
import modele.CoupPuissance;
import modele.GrilleRotation;
import modele.Joueur;

public class RotationPuissance extends ControleurJeuPuissance {

    GrilleRotation grilleRot;
    Ihm ihm = super.ihm;
    Joueur joueur1;
    Joueur joueur2;
    int nbPartie=0;



    public RotationPuissance(Ihm ihm) {
        super(ihm);
    }

    public void jouer() throws CoupInvalideException {
        nomJoueurs();
        grilleRot = new GrilleRotation();
        choixJeuPui4();
        rejouerPartieRotation();

    }

    public void choixJeuPui4() throws CoupInvalideException {
        super.grille = this.grilleRot;
        int c = choixRotation();
        if (c == 1) {
            gameContinueRotation();
        }
        else{
            gameContinue();
        }
    }

    public int choixRotation() {
        ihm.print("\nVoulez-vous integrer une contrainte de rotation? Veuillez saisir 1 pour oui, 0 pour non");
        boolean bool = true;
        int choix=-1;
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

    public boolean effectuerRotation(Joueur j) {
        ihm.print("L'etat de la grille \n"+grilleRot.toString());
        ihm.print("\n"+j.getNom()+ " voulez-vous effectuer une rotation? Veuillez saisir 1 pour oui, 0 pour non");
        boolean bool = true;
        int choix=-1;

        while(bool) {
            choix = ihm.saisirEntier();
            if(choix==1 || choix==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 ou 0 si vous voulez effectuer une rotation");
            }
        }

        return choix==1;
    }

    public int sensRotation() {
        ihm.print("\nVoulez-vous effectuer une rotation dans quel sens? Veuillez saisir 1 pour droite, 0 pour gauche");
        boolean bool = true;
        int choix = -1;
        while(bool) {
            choix = ihm.saisirEntier();
            if(choix==1 || choix==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 ou 0 pour choisir le sens de la rotation");
            }
        }
        return choix;
    }

    private void gameContinueRotation() throws CoupInvalideException {
        while (!grilleRot.partieTermine()) {
            if(grilleRot.GrillePleine()){
                nbPartie++;
                ihm.print("Ex-aequo");
                break;
            }

            joueur1 = super.joueur1;
            joueur2 = super.joueur2;
            if(effectuerRotation(joueur1)){
                if(sensRotation()==1){
                    grilleRot = grilleRot.rotationDroite();
                    super.grille = grilleRot;
                } else {
                    grilleRot.rotationGauche();
                    super.grille = grilleRot;
                }
            } else {
                choixCoupPuissance(joueur1, "rouge");
            }

            if(grilleRot.partieTermine()){
                super.grille = grilleRot;
                vainqueur(joueur1);
            } else {
                if(effectuerRotation(joueur2)){
                    if(sensRotation()==1){
                        grilleRot = grilleRot.rotationDroite();
                        super.grille = grilleRot;
                    } else {
                        grilleRot.rotationGauche();
                        super.grille = grilleRot;
                    }
                } else {
                    choixCoupPuissance(joueur2,"jaune");
                }

                if (grilleRot.partieTermine()) {
                    vainqueur(joueur2);
                }
            }

        }
    }

    /*private void choixCoupPuissance(Joueur j, String jeton) {
        ihm.formeCoup(grilleRot.toString(),j.getNom());
        int numColonne = ihm.saisirNumColonne();

        CoupPuissance coup = new CoupPuissance(numColonne,jeton);
        enregistrerCoupRotation(coup,j);
    }

    private void enregistrerCoupRotation(CoupPuissance coup, Joueur j) {
        try {
            grilleRot.gererCoup(coup);
        }
        catch (CoupInvalideException e) {
            ihm.print("Erreur! " + e.getMessage());
            choixCoupPuissance(j, coup.getJeton());
        }
    }*/

    public void rejouerPartieRotation() throws CoupInvalideException {

        ihm.print("\nVoulez-vous rejouer la partie? Veuillez saisir 1 pour oui, 0 pour non");
        boolean bool = true;
        int choix = ihm.saisirEntier();
        while(bool) {
            if(choix==1 || choix==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 ou 0 si vous voulez rejouer");
            }
        }
        if(choix==1) {
            grilleRot = new GrilleRotation();
            choixJeuPui4();
            rejouerPartieRotation();
        } else {
            joueur1 = super.joueur1;
            joueur2 = super.joueur2;
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

}
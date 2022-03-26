package controleur;

import ihm.Ihm;
import modele.CoupPuissance;
import modele.Grille;

public class ControleurJeuPuissance extends ControleurJeu {
    Grille grille;

    public ControleurJeuPuissance(Ihm ihm){
        super(ihm);
    }
    
    public void initialiserJeu(){
        plateau = grille = new Grille();

    }

    public void gameContinueWithoutContrainte() {
        while (!grille.partieTermine("jaune") && !grille.partieTermine("rouge") && !grille.GrillePleine()) {
            choixCoup();
            if(!grille.partieTermine("jaune") && !grille.partieTermine("rouge") && !grille.GrillePleine()){
                choixCoup();
            }
        }
    }

    public void vainqueurPartie() {
        ihm.print(grille.toString());
        if(grille.GrillePleine()){
            nbrePartie++;
            ihm.print("Vous etes Ex-aequo");
            return;
        }
        if(grille.partieTermine("rouge")){
            joueur1.gagnePartie();
            nbrePartie++;
            ihm.print(joueur1.getNom()+" vous etes le vainqueur de la partie "+nbrePartie);
        }else{
            joueur2.gagnePartie();
            nbrePartie++;
            ihm.print(joueur2.getNom()+" vous etes le vainqueur de la partie "+nbrePartie);
        }
    }

    public void rejouerPartie(){
        int choix = choixRejouerPartie();
        if(choix==1) {
            initialiserJeu();
            gameContinue();
            vainqueurPartie();
            rejouerPartie();
        } else {
            vainqueurJeu();
        }
    }

    public void choixCoup() {
        int numColonne = -1;
        String jeton = "";
        if(nbreCoup[1]<nbreCoup[0]){
            ihm.formeCoupPui4(grille.toString(),joueur2.getNom(), "jaune");
            numColonne = ihm.saisirNumColonne();
            jeton = "jaune";
        } else {
            ihm.formeCoupPui4(grille.toString(),joueur1.getNom(), "rouge");
            numColonne = ihm.saisirNumColonne();
            jeton = "rouge";
        }

        coup = new CoupPuissance(numColonne,jeton);
        enregistrerCoup(coup);
    }
    public void choixContrainte() {
        ihm.print("\nVoulez-vous integrer une contrainte de rotation? Veuillez saisir 1 pour oui, 0 pour non");
        boolean bool = true;
        while(bool) {
            choix = ihm.saisirEntier();
            if(choix==1 || choix==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 si vous voulez efffectuer une rotation et 0 sinon");
            }
        }
    }
}

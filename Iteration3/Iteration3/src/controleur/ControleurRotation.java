package controleur;

import ihm.Ihm;
import modele.Grille;

public class ControleurRotation extends ControleurJeuPuissance {
    private static int nbMaxRota = 4;
    int[] nbRot = {1,1};
    public ControleurRotation(Ihm ihm) {
        super(ihm);
    }

    public void gameContinueWithContrainte() {
        while (!grille.partieTermine("rouge") && !grille.partieTermine("jaune") && !grille.GrillePleine()) {
            if(nbRot[0]<=nbMaxRota){
                if(effectuerRotation()){
                    if(sensRotation()==1){
                        plateau = grille = rotationDroite(grille);
                    } else {
                        plateau = grille = rotationGauche(grille);
                    }
                    nbRot[0]++;
                    nbreCoup[0]++;
                } else {
                    choixCoup();
                }

            } else {
                choixCoup();
            }
            if(!grille.partieTermine("jaune") && !grille.partieTermine("rouge") && !grille.GrillePleine()){
                if(nbRot[1]<=nbMaxRota){
                    if(effectuerRotation()){
                        if(sensRotation()==1){
                            plateau = grille = rotationDroite(grille);
                        } else {
                            plateau = grille = rotationGauche(grille);
                        }
                        nbRot[1]++;
                        nbreCoup[1]++;
                    } else {
                        choixCoup();
                    }

                } else {
                    choixCoup();
                }
            }

        }
    }

    public boolean effectuerRotation() {
        ihm.print("L'etat de la grille \n"+grille.toString());
        if(nbreCoup[1]<nbreCoup[0])
            ihm.print("\n"+joueur2.getNom()+ " voulez-vous effectuer une rotation? Veuillez saisir 1 pour oui, 0 pour non");
        else
            ihm.print("\n"+joueur1.getNom()+ " voulez-vous effectuer une rotation? Veuillez saisir 1 pour oui, 0 pour non");
        boolean bool = true;
        int choixR=-1;

        while(bool) {
            choixR = ihm.saisirEntier();
            if(choixR==1 || choixR==0) {
                bool = false;
            } else {
                ihm.print("Veuillez saisir 1 ou 0 si vous voulez effectuer une rotation");
            }
        }
        return choixR==1;
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

    public Grille rotationDroite(Grille g) {
        Grille temp = new Grille();
        for(int i = 6; i>=0; i--){
            for(int j=6; j>=0; j--){
                if(g.getGrille()[i][j]=="jaune"){
                    String jeton = "jaune";
                    int numCol = 6-i;
                    for(int k=6; k>=0; k--){
                        if(temp.getGrille()[k][numCol]==null){
                            temp.setGrille(k,numCol,jeton);
                            break;
                        }
                    }
                } else if(g.getGrille()[i][j]=="rouge"){
                    String jeton = "rouge";
                    int numCol = 6-i;
                    for(int k=6; k>=0; k--){
                        if(temp.getGrille()[k][numCol]==null){
                            temp.setGrille(k,numCol,jeton);
                            break;
                        }
                    }
                }
            }
        }
        return temp;
    }

    public Grille rotationGauche(Grille g) {
        Grille temp = new Grille();
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j <= 6; j++) {
                if (g.getGrille()[i][j] == "jaune") {
                    String jeton = "jaune";
                    int numCol = i;
                    for (int k = 6; k >=0 ; k--) {
                        if (temp.getGrille()[k][numCol] == null) {
                            temp.setGrille(k,numCol,jeton);
                            break;
                        }
                    }
                } else if (g.getGrille()[i][j] == "rouge") {
                    String jeton = "rouge";
                    int numCol = 0+i;
                    for (int k = 6; k >=0 ; k--) {
                        if (temp.getGrille()[k][numCol] == null) {
                            temp.setGrille(k,numCol,jeton);
                            break;
                        }
                    }
                }
            }
        }
        return temp;
    }

}

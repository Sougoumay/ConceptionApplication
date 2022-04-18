package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IAPui4 extends IA{

    Map<Integer, List<Coup>> coupPossible = new TreeMap<>();
    private int nbRot;
    private static int nbMaxRota = 4;

    /**
     * @param nom du joueur à créer
     */
    public IAPui4(String nom)  {
        super(nom);
    }

    public int getNbRot() {
        return nbRot;
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

    public int rotation(Grille grille) {
        if (rotationDroite(grille).partieTermine("jaune")) {
            return 1;
        } else if(rotationGauche(grille).partieTermine("jaune")) {
            return 2;
        }
        return 0;
    }

    public void initialiserListeCoup() {
        for(int i = 1; i<=7; i++){
            List<Coup> liste = new ArrayList<>();
            coupPossible.put(i,liste);
        }
    }

    public void formerListeCoup(Grille grille) throws CoupInvalideException {
        initialiserListeCoup();
        for(int i =0 ;i<=6;i++){
            Coup coup = new CoupPuissance(i,"rouge");
            int colonne =((CoupPuissance)coup).getNumeroColonne();
            if(grille.getGrille()[0][colonne]==null) { //si la colonne n'est pas remplite
                grille.gererCoup(coup);
                if (grille.aligner("rouge", 4)) {
                    Coup coup1 = new CoupPuissance(i, "jaune");
                    coupPossible.get(6).add(coup1);
                    annulerCoup(i, "rouge",grille);
                } else if (grille.aligner("rouge", 3)) {
                    Coup coup1 = new CoupPuissance(i, "jaune");
                    coupPossible.get(4).add(coup1);
                    annulerCoup(i, "rouge",grille);
                } else if (grille.aligner("rouge", 2)) {
                    Coup coup1 = new CoupPuissance(i, "jaune");
                    coupPossible.get(2).add(coup1);
                    annulerCoup(i, "rouge",grille);
                } else annulerCoup(i, "rouge",grille);
                Coup coup2 = new CoupPuissance(i, "jaune");
                grille.gererCoup(coup2);
                if (grille.aligner("jaune", 4)) {
                    Coup coup3 = new CoupPuissance(i, "jaune");
                    coupPossible.get(7).add(coup3);
                    annulerCoup(i, "jaune",grille);
                } else if (grille.aligner("jaune", 3)) {
                    Coup coup3 = new CoupPuissance(i, "jaune");
                    coupPossible.get(5).add(coup3);
                    grille = annulerCoup(i, "jaune",grille);
                } else if (grille.aligner("jaune", 2)) {
                    Coup coup3 = new CoupPuissance(i, "jaune");
                    coupPossible.get(3).add(coup3);
                    grille = annulerCoup(i, "jaune",grille);
                } else grille = annulerCoup(i, "jaune",grille);
            }
        }
    }

    public Grille annulerCoup(int numColonne, String jeton, Grille grille){
        String[][] tab = grille.getGrille();
        for(int i=0; i<7; i++){
            if(tab[i][numColonne]==jeton){
                grille.setGrille(i,numColonne,null);
                return grille;
            }
        }
        return grille;
    }

    public Plateau strategieContrainteIA(Plateau plateau) throws CoupInvalideException {
        formerListeCoup(((Grille) plateau));
        for(int i = 7; i>=1; i--){
            List<Coup> listeCoup = coupPossible.get(i);
            for(Coup c : listeCoup){
                int col = ((CoupPuissance) c).getNumeroColonne();
                String jet = ((CoupPuissance) c).getJeton();
                plateau.gererCoup(c);
                if(i==7) {
                    plateau = annulerCoup(col,jet, ((Grille) plateau));
                    plateau.gererCoup(c);
                    nbreCoup++;
                    return plateau;
                } else if(i==6){
                    if(nbRot<nbMaxRota){
                        if(!(rotationGauche(((Grille) plateau)).partieTermine("rouge"))){
                            plateau = annulerCoup(col,jet,((Grille) plateau));
                            rotationGauche(((Grille) plateau));
                            nbreCoup++;
                            nbRot++;
                            return plateau;

                        }else if(!(rotationDroite(((Grille) plateau)).partieTermine("rouge"))){
                            plateau = annulerCoup(col,jet,((Grille) plateau));
                            rotationDroite(((Grille) plateau));
                            nbreCoup++;
                            nbRot++;
                            return plateau;
                        }else {
                            plateau = annulerCoup(col,jet,((Grille) plateau));
                            int numCol = ((CoupPuissance) c).getNumeroColonne();
                            Coup coupAux = new CoupPuissance(numCol,"jaune");
                            plateau.gererCoup(coupAux);
                            nbreCoup++;
                            return plateau;
                        }
                    }

                } else if(!(rotationGauche(((Grille) plateau)).partieTermine("rouge")) &&
                        !(rotationDroite(((Grille) plateau)).partieTermine("rouge"))){
                    if(i%2==0){
                        plateau = annulerCoup(col,jet,((Grille) plateau));
                        int numCol = ((CoupPuissance) c).getNumeroColonne();
                        Coup coupAux = new CoupPuissance(numCol,"jaune");
                        plateau.gererCoup(coupAux);
                        nbreCoup++;
                        return plateau;
                    } else {
                        plateau = annulerCoup(col,jet,((Grille) plateau));
                        plateau.gererCoup(c);
                        nbreCoup++;
                        return plateau;
                    }
                }
            }
        }

        return plateau;
    }

    public Plateau strategieSansContrainteIA(Plateau plateau) throws CoupInvalideException {
        formerListeCoup(((Grille) plateau));
        for(int i=7; i>=1; i--){
            List<Coup> listeCoup = coupPossible.get(i);
            for(Coup c : listeCoup){
                plateau.gererCoup(c);
                nbreCoup++;
                return plateau;
            }
        }

        return plateau;
    }
}

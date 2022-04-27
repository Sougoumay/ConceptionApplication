package modele;

import java.util.List;
import java.util.Random;

public class IAOptimise extends IAPui4{
    /**
     * @param nom du joueur à créer
     */
    public IAOptimise(String nom) {
        super(nom);
    }

    public Plateau strategieSansContrainteIA(Plateau plateau) throws CoupInvalideException {
        formerListeCoupOptimise(((Grille) plateau));
        for(int i=7; i>=1; i--){
            List<Coup> listeCoup = coupPossible.get(i);
            if(listeCoup.size()>0){
                Random x = new Random();
                Coup c = listeCoup.get(x.nextInt(listeCoup.size()));
                plateau.gererCoup(c);
                nbreCoup++;
                return plateau;
            }
        }
        return plateau;
    }

    /**
     *
     * @param grille c'est la grille dans laquelle on joue
     * @throws CoupInvalideException la fonction gerercoup lève une exception donc on utilise la signature de celle-ci
     */
    public void formerListeCoupOptimise(Grille grille) throws CoupInvalideException {
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
                    String[][] tab = grille.getGrille();
                    int ligne = dernierCoup(i,grille);
                    // On verifie si la colonne à gauche ou celle de la droite n'est pas occupée par l'adversaire avant
                    // d'ajouter le coup à la liste. Sinon le coup sera ignoré
                    // mais d'abord on prend en compte les cas ou la colonne actuelle est 0 ou 6
                    if(i==0){
                        if(tab[ligne][i+1]!="rouge"){
                            coupPossible.get(5).add(coup3);
                        }
                    } else if(i==6) {
                        if(tab[ligne][i-1]!="rouge"){
                            coupPossible.get(5).add(coup3);
                        }
                    } else {
                        if(tab[ligne][i-1]!="rouge" || tab[ligne][i+1]!="rouge"){
                            coupPossible.get(5).add(coup3);
                        }
                    }
                    grille = annulerCoup(i, "jaune",grille);
                } else if (grille.aligner("jaune", 2)) {
                    Coup coup3 = new CoupPuissance(i, "jaune");
                    String[][] tab = grille.getGrille();
                    // On obtient la ligne sur laquelle il a joué le dernier coup
                    int ligne = dernierCoup(i,grille);
                    // On verifie si les deux colonne à gauche ou celles de la droite ne sont pas occupées par
                    // l'adversaire avant d'ajouter le coup à la liste. Sinon le coup sera ignoré
                    // mais d'abord on prend en compte les cas ou la colonne actuelle est 0, 1, 5 ou 6
                    if(i==0) {
                        if((tab[ligne][i+1]!="rouge" && tab[ligne][i+2]!="rouge")){
                            coupPossible.get(5).add(coup3);
                        }
                    } else if(i==1){
                        if((tab[ligne][i-1]!="rouge" || (tab[ligne][i+1]!="rouge" && tab[ligne][i+2]!="rouge"))){
                            coupPossible.get(5).add(coup3);
                        }
                    } else if(i==5) {
                        if((tab[ligne][i-1]!="rouge" && tab[ligne][i-2]!="rouge") || tab[ligne][i+1]!="rouge" ){
                            coupPossible.get(5).add(coup3);
                        }
                    }else if(i==6){
                        if((tab[ligne][i-1]!="rouge" && tab[ligne][i-2]!="rouge")){
                            coupPossible.get(5).add(coup3);
                        }
                    } else {
                        if((tab[ligne][i-1]!="rouge" && tab[ligne][i-2]!="rouge") ||
                                (tab[ligne][i+1]!="rouge" && tab[ligne][i+2]!="rouge")){
                            coupPossible.get(5).add(coup3);
                        }
                    }

                    grille = annulerCoup(i, "jaune",grille);
                } else grille = annulerCoup(i, "jaune",grille);
            }
        }
    }

    /**
     * Danss cette fonction on obtient la ligne sur laquelle on a joué le dernier coup du test
     * pour pouvoir l'utiliser avant de tester si sur la même ligne il est possible de jouer
     * ou que l'adversiare a occupé la place
     * @param numColonne le numero due la colonne sur laquelle le dernier coup a été joué
     * @param grille on prend la grille en paramètre afin d'effectuer dessus les tests
     * @return on retourne la ligne obtenu par la test
     */
    public int dernierCoup(int numColonne,Grille grille){
        String[][] tab = grille.getGrille();
        int ligne = 0;
        for(int i=0; i<7; i++) {
            if (tab[i][numColonne] == "jaune") {
                ligne = i;
            }
        }
        return ligne;
    }
}

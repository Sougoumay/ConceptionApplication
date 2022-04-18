package modele;

public class Grille {


    private static int taille = 7;
    protected String[][] grille;

    public Grille(){
        grille = new String[taille][taille];
    }


    public boolean partieTermine()
    {
        return  aligner() == true;
    }

    public boolean aligner() {
        // on verifir les alignements horizontales
        for (int ligne = 0; ligne < taille; ligne++) {
            // pour chaque numero de ligne donne, on incremente le numero de colonne dont le debut est 0
            if (chercherAlignement(0, ligne, 1, 0)) {
                return true;
            }
        }

        // on verifie les alignements verticales
        for (int col = 0; col < taille; col++) {
            // pour chaque numero de lonne donne, on incremente le numero de la ligne dont le debut est 0
            if (chercherAlignement(col, 0, 0, 1)) {
                return true;
            }
        }

        // On cherche les diagonales en commencant par le haut du table tout en allant vers la droite,
        // on change a chaque appelle de la boucle la colonne
        for (int col = 0; col < taille; col++) {
            // Première diagonale ( \ )
            if (chercherAlignement(col, 0, 1, 1)) {
                return true;
            }
            // Deuxième diagonale ( / )
            if (chercherAlignement(col, 0, -1, 1)) {
                return true;
            }
        }

        // On cherche les diagonales en commencant par le haut du table tout en allant vers le bas,
        // on change a chaque appelle de la boucle la ligne
        for (int ligne = 0; ligne < taille; ligne++) {
            // Première diagonale ( / )
            if (chercherAlignement(0, ligne, 1, 1)) {
                return true;
            }
            // Deuxième diagonale ( \ )
            if (chercherAlignement(taille - 1, ligne, -1, 1)) {
                return true;
            }
        }
        // On n'a rien trouvé
        return false;
    }

    private boolean chercherAlignement(int colonne, int ligne, int modifColonne, int modifLigne) {
        String couleur = null;
        int compteur = 0;

        int colonneCourant = colonne;
        int ligneCourante = ligne;

        while ((colonneCourant >= 0) && (colonneCourant < taille) && (ligneCourante >= 0) && (ligneCourante < taille)) {
            if (grille[ligneCourante][colonneCourant] != couleur) {
                // Si la couleur change, on réinitialise le compteur
                couleur = grille[ligneCourante][colonneCourant];
                compteur = 1;
            } else {
                // Sinon on l'incrémente puisqu'on aurait la meme couleur en plus
                compteur++;
            }

            // On sort lorsque le compteur atteint 4 parcequ'on aurait trouve un alignement successive d'une couleur 4 fois
            if ((couleur != null) && (compteur == 4)) {
                return true;
            }

            // On passe à l'itération suivante
            colonneCourant += modifColonne;
            ligneCourante += modifLigne;
        }

        // Aucun alignement n'a été trouvé
        return false;
    }

    public boolean GrillePleine(){
        if (grille[0][0]!=null && grille[0][1]!=null && grille[0][2]!=null && grille[0][3]!=null && grille[0][4]!=null
                && grille[0][5]!=null && grille[0][6]!=null){
            return true;
        }
        return false;
    }

    public void gererCoup(CoupPuissance coup) throws CoupInvalideException {
        int colonne = coup.getNumeroColonne();
        String jeton = coup.getJeton();
        if(colonne<0 || colonne>=taille) {
            throw new CoupInvalideException("Coup invalide! Veuillez choisir un numero de colonne compris 0 et "+(taille-1));
        } else if(grille[0][colonne]!=null){
            throw new CoupInvalideException("Coup Invalide. La colonne "+colonne+" est rempli");
        }

        for(int i=taille-1; i>=0; i--){
            if(grille[i][colonne]==null){
                grille[i][colonne]=jeton;
                break;
            }
        }

    }

    public String toString(){
        String s = "  0  1  2  3  4  5  6 \n";

        for(int i=0; i<7; i++){
            s+="|";
            for(int j =0; j<7; j++){
                if(grille[i][j] == null) {
                    s+=" . ";
                } else if(grille[i][j]=="rouge"){
                    s+=" r ";
                } else if(grille[i][j]=="jaune"){
                    s+=" j ";
                }
            }
            s+="|\n";
        }

        return s;
    }

    public String[][] getGrille() {
        return grille;
    }

    public void setGrille(String[][] grille) {
        this.grille = grille;
    }
}

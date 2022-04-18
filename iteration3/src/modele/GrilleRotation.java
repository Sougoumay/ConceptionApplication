package modele;


import ihm.Ihm;

public class GrilleRotation extends Grille{

    private String[][] grille;

    public GrilleRotation(){
        super();
        this.grille = new String[7][7];
    }

    public void initialiserGrille() {
        grille = new String[7][7];
    }

    public GrilleRotation rotationDroite() {
        GrilleRotation temp = new GrilleRotation();
        initialiserGrille();
        for(int i = 6; i>0; i--){
            for(int j=6; j>0; j--){
                if(super.grille[i][j]=="jaune"){
                    String jeton = "jaune";
                    int numCol = 6-i;
                    for(int k=6; k>=0; k--){
                        if(temp.grille[k][numCol]==null){
                            temp.grille[k][numCol]=jeton;
                            break;
                        }
                    }
                } else if(super.grille[i][j]=="rouge"){
                    String jeton = "rouge";
                    int numCol = 6-i;
                    for(int k=6; k>=0; k--){
                        if(temp.grille[k][numCol]==null){
                            temp.grille[k][numCol]=jeton;
                            break;
                        }
                    }
                }
            }
        }
        Ihm ihm = new Ihm();
        ihm.print("On est toujours dans grilleRotation\n"+temp.toString());
        return temp;
    }

    public void rotationGauche(){

    }

    public void gererCoupRotation(CoupPuissance coup) throws CoupInvalideException {
        int colonne = coup.getNumeroColonne();
        String jeton = coup.getJeton();
        if(colonne<0 || colonne>=7) {
            throw new CoupInvalideException("Coup invalide! Veuillez choisir un numero de colonne compris 0 et "+6);
        } else if(grille[0][colonne]!=null){
            throw new CoupInvalideException("Coup Invalide. La colonne "+colonne+" est rempli");
        }

        for(int i=6; i>=0; i--){
            if(grille[i][colonne]==null){
                grille[i][colonne]=jeton;
                break;
            }
        }
    }
}

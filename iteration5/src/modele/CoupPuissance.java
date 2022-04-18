package modele;

public class CoupPuissance extends Coup {
    private int numeroColonne;
    private String jeton;

    public CoupPuissance(int numeroGrille, String jeton) {
        this.numeroColonne = numeroGrille;
        this.jeton = jeton;
    }

    public int getNumeroColonne() {
        return numeroColonne;
    }

    public String getJeton() {
        return jeton;
    }

}

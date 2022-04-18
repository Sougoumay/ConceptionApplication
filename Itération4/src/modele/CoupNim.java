package modele;

public class CoupNim extends Coup {
    private int numeroTas;
    private int nbAllumettes;

    /**
     * Constructeur permettant de créer un coup
     *
     * @param numeroTas        numéro de la ligne
     * @param nbAllumettes nombre d'allumettes à enlever
     */
    public CoupNim(int numeroTas, int nbAllumettes) {
        this.numeroTas = numeroTas;
        this.nbAllumettes = nbAllumettes;
    }

    /**
     * @return the ligne
     */
    public int getNumeroTas() {
        return numeroTas;
    }

    /**
     * @return the nbAllumettes
     */
    public int getNbAllumettes() {
        return nbAllumettes;
    }
}

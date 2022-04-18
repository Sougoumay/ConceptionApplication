package modele;

public class IA extends Joueur{

    protected int nbreCoup = 0 ;

    /**
     * @param nom du joueur à créer
     */
    public IA(String nom) {
        super(nom);
    }

    public int getNbreCoup() {
        return nbreCoup;
    }
}

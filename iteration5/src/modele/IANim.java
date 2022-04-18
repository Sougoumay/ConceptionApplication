package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IANim extends IA{

    private List<Coup> coupsSansContrainte = new ArrayList<>();
    private List<Coup> coupsAvecContrainte = new ArrayList<>();

    /**
     * @param nom du joueur à créer
     */
    public IANim(String nom) {
        super(nom);
    }

    public void formerlistecoup(Tas tas) throws CoupInvalideException {
        for (int i = 1; i <= tas.getLesTas().length; i++) {
            if (tas.nbAllumettes(i) > 0) {
                int nbAllumette = tas.nbAllumettes(i);
                Random y = new Random();
                int allumette = -1;
                Boolean bool = true;
                while (bool) {
                    allumette = y.nextInt(nbAllumette)+1;
                    if (allumette > 0) {
                        bool = false;
                    }
                }
                Coup coup1 = new CoupNim(i, allumette);
                coupsSansContrainte.add(coup1);
            }
        }
    }

    public void formerListeCoupContrainte(Tas tas, int choix) throws CoupInvalideException {
        for (int i = 1; i <= tas.getLesTas().length; i++) {
            if (tas.nbAllumettes(i) > 0) {
                int nbAllumette = tas.nbAllumettes(i);
                Random y = new Random();
                int allumette = -1;
                Boolean bool = true;
                while (bool) {
                    allumette = y.nextInt(nbAllumette+1);
                    if (allumette > 0 && allumette<=choix) {
                        bool = false;
                    }
                }
                Coup coup1 = new CoupNim(i, allumette);
                coupsAvecContrainte.add(coup1);
            }
        }
    }

    public Plateau gererCoup(Plateau plateau ,int choixJeu,int nbRotation) throws CoupInvalideException {
        if(choixJeu==0){
            coupsSansContrainte = new ArrayList<>();
            formerlistecoup((Tas) plateau);
            Random x = new Random();
            Coup c = coupsSansContrainte.get(x.nextInt(coupsSansContrainte.size()));
            plateau.gererCoup(c);
            nbreCoup++;
        } else {
            coupsAvecContrainte = new ArrayList<>();
            formerListeCoupContrainte((Tas) plateau, nbRotation);
            Random x = new Random();
            Coup c = coupsAvecContrainte.get(x.nextInt(coupsAvecContrainte.size()));
            int nbAllumette = ((CoupNim) c).getNbAllumettes();
            int indice = ((CoupNim) c).getNumeroTas();
            Random y = new Random();
            int allumette = -1;
            Boolean bool = true;
            while (bool) {
                allumette = y.nextInt(nbAllumette+1);
                if (allumette > 0) {
                    bool = false;
                }
            }

            Coup coup = new CoupNim(indice, allumette);
            plateau.gererCoup(coup);
            nbreCoup++;

        }

        return plateau;
    }
}

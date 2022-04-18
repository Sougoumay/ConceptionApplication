package controleur;

import modele.CoupInvalideException;

public interface Controleur {
    void jouer() throws CoupInvalideException;
}

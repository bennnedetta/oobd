package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;

public interface MezzoDAO {
    Mezzo getByDisponibilita(boolean disponibile);
    void update (Mezzo nuovoMezzo, String targa);
}

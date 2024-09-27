package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;

public interface OperatoreDAO {
    Operatore getByEmail(String email);
    boolean existCredenziali(String email, String password);
}

package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;

public interface OperatoreDAO {
    void create(Operatore operatore);
    void delete(Operatore operatore);
    void update(Operatore nuovoOperatore,String email);
    Operatore getByEmail(String email);
    boolean existCredenziali(String email, String password);
}

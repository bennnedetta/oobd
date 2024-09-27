package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;

public interface ClienteDAO {
    Cliente getByEmail(String email);
}

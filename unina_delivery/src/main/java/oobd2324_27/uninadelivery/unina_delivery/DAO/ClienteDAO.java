package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;

public interface ClienteDAO {
    void create(Cliente cliente);
    void delete(Cliente cliente);
    void update(Cliente nuovoCliente,String email);
    Cliente getByEmail(String email);
}

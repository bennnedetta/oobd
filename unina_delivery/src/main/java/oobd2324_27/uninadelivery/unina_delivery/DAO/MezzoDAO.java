package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;

import java.util.List;

public interface MezzoDAO {
    Mezzo getByTarga(String targa);
    List<Mezzo> getDisponibili();
    List<Mezzo> getDisponibili(Double pesoBase);
}

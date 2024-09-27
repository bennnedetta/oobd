package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Corriere;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.util.List;

public interface CorriereDAO {
    List<Corriere> getAvailableCorrieri();
    Corriere getById(int idcorriere);
}

package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.Entity.Corriere;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.util.List;

public interface CorriereDAO {
    void createCorriere(Corriere corriere) throws MyException; //Inserisce un nuovo corriere nel sistema.
    Corriere readCorriere(String matricola) throws MyException; //Restituisce il corriere con la matricola specificata.
    void updateCorriere(Corriere corriere) throws MyException; //Aggiorna le informazioni di un corriere esistente.
    void deleteCorriere(String matricola) throws MyException; //Rimuove il corriere con la matricola specificata.
    List<Corriere> getAllCorrieri() throws MyException; //Restituisce una lista di tutti i corrieri.
    List<Corriere> getAvailableCorrieri() throws MyException; //Restituisce una lista di tutti i corrieri disponibili.
}

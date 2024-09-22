package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;

import java.time.LocalDate;
import java.util.List;

public interface SpedizioneDAO {

    // Metodo per creare una nuova spedizione nel database
    void createSpedizione(Spedizione spedizione) throws MyException;

    // Metodo per eliminare una spedizione dal database utilizzando la data di spedizione
    void deleteSpedizione(LocalDate dataSpedizione) throws MyException;

    // Metodo per aggiornare una spedizione nel database
    void updateSpedizione(Spedizione spedizione) throws MyException;

    // Metodo per leggere una spedizione dal database utilizzando la data di spedizione
    Spedizione readSpedizione(LocalDate dataSpedizione) throws MyException;

    // Metodo per ottenere una lista di tutte le spedizioni dal database
    List<Spedizione> getAllSpedizioni() throws MyException;

    Spedizione getById(int numeroSpedizione) throws MyException;
}

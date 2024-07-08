package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Prodotto;

import java.util.List;

public interface ProdottoDAO {

    // Metodo per creare un nuovo prodotto nel database
    void createProdotto(Prodotto prodotto) throws MyException;

    // Metodo per eliminare un prodotto dal database utilizzando il suo identificatore (marca)
    void deleteProdotto(String marca) throws MyException;

    // Metodo per aggiornare un prodotto nel database
    void updateProdotto(Prodotto prodotto) throws MyException;

    // Metodo per leggere un prodotto dal database utilizzando il suo identificatore (marca)
    Prodotto readProdotto(String marca) throws MyException;

    // Metodo per ottenere una lista di tutti i prodotti dal database
    List<Prodotto> getAllProdotti() throws MyException;
}

package oobd2324_27.uninadelivery.unina_delivery.DAO;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Prodotto;

import java.util.List;

public interface ProdottoDAO {
    void createProdotto(Prodotto prodotto) throws MyException;
    void deleteProdotto(String marca) throws MyException;
    void updateProdotto(Prodotto prodotto) throws MyException;
    Prodotto readProdotto(String marca) throws MyException;
    List<Prodotto> getAllProdotti() throws MyException;
}

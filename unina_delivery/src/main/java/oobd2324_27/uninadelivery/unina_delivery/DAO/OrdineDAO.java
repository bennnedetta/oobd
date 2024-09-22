package oobd2324_27.uninadelivery.unina_delivery.DAO;

import javafx.scene.control.DatePicker;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;

import java.time.LocalDate;
import java.util.List;

public interface OrdineDAO {

    // Metodo per creare un nuovo ordine nel database
    void createOrdine(Ordine ordine) throws MyException;

    // Metodo per eliminare un ordine dal database utilizzando la data dell'ordine
    void deleteOrdine(LocalDate dataOrdine) throws MyException;

    // Metodo per aggiornare un ordine nel database
    void updateOrdine(Ordine ordine) throws MyException;

    // Metodo per leggere un ordine dal database utilizzando la data dell'ordine
    Ordine readOrdine(LocalDate dataOrdine) throws MyException;

    // Metodo per ottenere una lista di tutti gli ordini nel database
    List<Ordine> getAllOrdini() throws MyException;

    List<Ordine> getByData(LocalDate dataInizio, LocalDate dataFine) throws MyException;

    List<Ordine> getByCliente(LocalDate dataInizio, LocalDate dataFine,String clienteEmail) throws MyException;
}

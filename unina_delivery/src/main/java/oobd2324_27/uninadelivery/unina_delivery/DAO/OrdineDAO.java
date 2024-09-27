package oobd2324_27.uninadelivery.unina_delivery.DAO;

import javafx.scene.control.DatePicker;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;

import java.time.LocalDate;
import java.util.List;

public interface OrdineDAO {
    void updateOrdine(Ordine ordine);
    Ordine readOrdine(int idOrdine);
    List<Ordine> getAllOrdiniDaSpedire();
    List<Ordine> getAllOrdiniSpediti();
    List<Ordine> getByData(LocalDate dataInizio, LocalDate dataFine);
    List<Ordine> getByCliente(LocalDate dataInizio, LocalDate dataFine,String clienteEmail);
}

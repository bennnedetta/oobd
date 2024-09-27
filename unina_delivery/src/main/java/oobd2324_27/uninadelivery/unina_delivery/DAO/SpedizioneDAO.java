package oobd2324_27.uninadelivery.unina_delivery.DAO;

import javafx.scene.chart.PieChart;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface SpedizioneDAO {
    void createSpedizione(Spedizione spedizione) ;
    List<Spedizione> getAllSpedizioni()  ;
    Spedizione getById(int numeroSpedizione) ;
    Integer getId(Spedizione spedizione);
    int contaSpedizioni(int anno, int mese);
    HashMap<String, Integer> getNumeroSpedizioniPerData(LocalDate inizio, LocalDate fine);
    double getAvg(LocalDate dataInizio, LocalDate dataFine);
    int getMin(LocalDate dataInizio, LocalDate dataFine);
    int getMax(LocalDate dataInizio, LocalDate dataFine);


}

package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Spedizione {
    private String numeroSpedizione;
    private LocalDate dataSpedizione;
    private double pesoTotale;
    private LocalDate dataConsegna;
    private String statoSpedizione;
    private Operatore operatore;
    private Mezzo mezzo;
    private Corriere corriere;
    private List<Ordine> ordini= new ArrayList<>();

    public Spedizione(String numeroSpedizione,LocalDate dataSpedizione, double pesoTotale, LocalDate dataConsegna, String statoSpedizione, Operatore operatore, Mezzo mezzo, Corriere corriere, List<Ordine> ordini) throws MyException {
        setNumeroSpedizione(numeroSpedizione);
        setDataSpedizione(dataSpedizione);
        setPesoTotale(pesoTotale);
        setDataConsegna(dataConsegna);
        setDataSpedizione(dataSpedizione);
        setOperatore(operatore);
        setMezzo(mezzo);
        setCorriere(corriere);
        setOrdini(ordini);
    }

    public LocalDate getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione(LocalDate dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public double getPesoTotale() {
        return pesoTotale;
    }

    public void setPesoTotale(double pesoTotale) throws MyException{
        if(pesoTotale<0){
            throw new MyException("il peso di una spedizione non può essere negativo");
        }
        this.pesoTotale = pesoTotale;
    }

    public String getNumeroSpedizione() {
        return numeroSpedizione;
    }

    public void setNumeroSpedizione(String numeroSpedizione) {
        this.numeroSpedizione = numeroSpedizione;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public String getStatoSpedizione() {
        return statoSpedizione;
    }

    public void setStatoSpedizione(String statoSpedizione) {
        this.statoSpedizione = statoSpedizione;
    }

    public Operatore getOperatore() {
        return operatore;
    }

    public void setOperatore(Operatore operatore) {
        this.operatore = operatore;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }
    public Ordine getOrdini(int index) {
        return ordini.get(index);
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }
    public void addOrdini(Ordine ordini) {
        this.ordini.add(ordini);
    }
}

package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Mezzo {
    private String targa;
    private double pesoSupportato;
    private boolean disponibile;
    private List<Spedizione> spedizioni= new ArrayList<>();

    public Mezzo (String targa, double pesoSupportato, boolean disponibile) throws MyException {
        setTarga(targa);
        setPesoSupportato(pesoSupportato);
        setDisponibile(disponibile);
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) throws MyException {
        if(targa == null || targa.trim().isEmpty() || targa.length() != 6){
            throw new MyException("il campo targa non è stato valorizzato correttamente");
        }
        this.targa = targa;
    }

    public double getPesoSupportato() {
        return pesoSupportato;
    }

    public void setPesoSupportato(double pesoSupportato) throws MyException {
        if(pesoSupportato <0){
         throw new MyException ("Impossibile inserire un peso negativo");
        }
        this.pesoSupportato = pesoSupportato;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile= disponibile;
    }

    public List<Spedizione> getSpedizioni() {
        return spedizioni;
    }
    public Spedizione getSpedizioni(int index) {
        return spedizioni.get(index);
    }

    public void setSpedizioni(List<Spedizione> spedizioni) {
        this.spedizioni = spedizioni;
    }
    public void addSpedizioni(Spedizione spedizioni) {
        this.spedizioni.add(spedizioni);
    }
}

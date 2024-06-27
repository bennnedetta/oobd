package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.util.ArrayList;
import java.util.List;

public class Prodotto {
    private double peso;
    private String marca;
    private double prezzo;
    private List<Ordine> ordini= new ArrayList<>();

    public Prodotto(double peso, String marca, double prezzo) throws MyException {
        setPeso(peso);
        setMarca(marca);
        setPrezzo(prezzo);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) throws MyException {
        if(peso<0){
            throw new MyException("il peso di un prodotto non può essere negativo");
        }
        this.peso = peso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) throws MyException{
        if(marca==null || marca.trim().isEmpty()){
            throw new MyException("il campo marca dev'essere valorizzato");
        }
        this.marca = marca;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) throws MyException {
        if(prezzo<0){
            throw new MyException("il prezzo di un prodotto non può essere negativo");
        }
        this.prezzo = prezzo;
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

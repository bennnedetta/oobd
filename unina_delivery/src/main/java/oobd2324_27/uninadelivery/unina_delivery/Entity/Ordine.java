package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ordine {
    private LocalDate data;
    private double costoTotale;
    private double pesoTotale;
    private Spedizione spedizione;
    private List<Prodotto> prodotti= new ArrayList<>();
    private Cliente cliente;

    public Ordine(LocalDate data, double costoTotale, double pesoTotale, List<Prodotto> prodotti, Cliente cliente)throws MyException{
        setData(data);
        setCostoTotale(costoTotale);
        setPesoTotale(pesoTotale);
        setProdotti(prodotti);
        setCliente(cliente);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(double costoTotale) throws MyException{
        if(costoTotale<0){
            throw new MyException("il costo totale di un ordine non può essere negativo");
        }
        this.costoTotale = costoTotale;
    }

    public double getPesoTotale() {
        return pesoTotale;
    }

    public void setPesoTotale(double pesoTotale) throws MyException{
        if(pesoTotale<0){
            throw new MyException("il peso totale di un ordine non può essere negativo");
        }
        this.pesoTotale = pesoTotale;
    }

    public Spedizione getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(Spedizione spedizione) {
        this.spedizione = spedizione;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }
    public Prodotto getProdotti(int index) {
        return prodotti.get(index);
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
    public void addProdotti(Prodotto prodotti) {
        this.prodotti.add(prodotti);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

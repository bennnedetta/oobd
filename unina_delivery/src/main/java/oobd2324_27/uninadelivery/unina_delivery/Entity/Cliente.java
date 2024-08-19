package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cognome;
    private String via;
    private String email;
    private String recapitoTelefonico;
    private List<Ordine> ordini= new ArrayList<>();

    public Cliente(String nome, String cognome, String via, String email, String recapitoTelefonico) throws MyException {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setVia(via);
        setRecapitoTelefonico(recapitoTelefonico);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws MyException {
        if(nome==null || nome.trim().isEmpty()){
            throw new MyException("il campo nome dev'essere valorizzato");
        }
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) throws MyException{
        if(cognome==null || cognome.trim().isEmpty()){
            throw new MyException("il campo cognome dev'essere valorizzato");
        }
        this.cognome = cognome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via)throws MyException {
        if(via==null || via.trim().isEmpty()){
            throw new MyException("il campo via dev'essere valorizzato");
        }
        this.via = via;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws MyException{
        if(email==null || email.trim().isEmpty()){
            throw new MyException("il campo email dev'essere valorizzato");
        }
        this.email = email;
    }

    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }

    public void setRecapitoTelefonico(String recapitoTelefonico) throws MyException{
        if(recapitoTelefonico.length()!=10){
            throw new MyException("recapito telefonico non valido");
        }
        this.recapitoTelefonico = recapitoTelefonico;
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

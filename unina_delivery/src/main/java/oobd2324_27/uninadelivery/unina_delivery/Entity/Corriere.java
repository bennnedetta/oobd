package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.util.ArrayList;
import java.util.List;

public class Corriere {
    private String nome;
    private String cognome;
    private String recapitoTelefonico;
    private boolean disponibile;
    private int idcorriere;
    private List<Spedizione> spedizioni=new ArrayList<>();


    public Corriere(String nome, String cognome, String recapitoTelefonico, boolean disponibile, int idcorriere ) throws MyException {
        setNome(nome);
        setCognome(cognome);
        setRecapitoTelefonico(recapitoTelefonico);
        setDisponibile(disponibile);
        setIdcorriere(idcorriere);

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

    public void setCognome(String cognome) throws MyException {
        if(cognome==null || cognome.trim().isEmpty()){
            throw new MyException("il campo cognome dev'essere valorizzato");
        }
        this.cognome = cognome;
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

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public int getIdcorriere() {
        return idcorriere;
    }

    public void setIdcorriere(int idcorriere) throws MyException {
        this.idcorriere = idcorriere;
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

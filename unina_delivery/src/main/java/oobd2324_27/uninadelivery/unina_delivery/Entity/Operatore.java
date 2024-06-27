package oobd2324_27.uninadelivery.unina_delivery.Entity;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;

import java.util.ArrayList;
import java.util.List;

public class Operatore {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private List<Spedizione> spedizioni=new ArrayList<>();

    //questo è il costruttore viene usato solo quando faccio new.
    public Operatore(String nome, String cognome, String email, String password) throws MyException{
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws MyException {
        if(nome==null || nome.trim().isEmpty()){
            throw new MyException("Il nome deve essere valorizzato");
        }
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) throws MyException {
        if(cognome==null || cognome.trim().isEmpty()){
            throw new MyException("Il cognome deve essere valorizzato");
        }
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws MyException{
        if( email==null || email.trim().isEmpty()){
            throw new MyException("il campo email dev'essere valorizzato");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws MyException{
        if(password==null || password.trim().isEmpty()){
            throw new MyException("il campo password dev'essere valorizzato");
        }
        this.password = password;
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

    public void addSpedizione(Spedizione spedizione) {
        this.spedizioni.add(spedizione);
    }

}

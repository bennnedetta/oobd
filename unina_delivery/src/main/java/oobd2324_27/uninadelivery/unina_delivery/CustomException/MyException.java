package oobd2324_27.uninadelivery.unina_delivery.CustomException;

//questa classe mi serve solo per distinguere le mie eccezioni da altre.
public class MyException extends Exception {
    public MyException(String messaggio) {
        //this permette di utilizzare i metodi e gli attributi di questa classe mentre super
        //fa rifeimento alla classe padre. In questo caso Exception.
        super(messaggio);
    }

}

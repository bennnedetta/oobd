package oobd2324_27.uninadelivery.unina_delivery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OperatoreDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.OperatoreDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //launch();
        try {
            OperatoreDAO operatoreDAO= new OperatoreDAOImp();
            //Operatore operatore = operatoreDAO.getByEmail("lala");
            Operatore operatore=new Operatore("massi","gay","comete","123");
            operatoreDAO.create(operatore);
            operatoreDAO.delete(operatore);
            System.out.println(operatore.getNome());
        }catch (Exception eccezione){
          System.out.println(eccezione.getMessage());
        }
    }
}
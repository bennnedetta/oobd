package oobd2324_27.uninadelivery.unina_delivery.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OperatoreDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.OperatoreDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataLogin implements Initializable {
    @FXML
    private Button loginButton;

    @FXML
    private Label messaggioErrore;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void login(ActionEvent event) {
        OperatoreDAO operatoreDAO = new OperatoreDAOImp();
        try {
            if(usernameField.getText().isBlank() || passwordField.getText().isBlank()) {
                messaggioErrore.setVisible(true);
                messaggioErrore.setText("Valorizzare i campi");
            }else if(!operatoreDAO.existCredenziali(usernameField.getText(),passwordField.getText())){
                messaggioErrore.setVisible(true);
                messaggioErrore.setText("Credenziali errate");
            }
            else{
                Stage stage = (Stage) loginButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataPrincipale.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

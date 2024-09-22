package oobd2324_27.uninadelivery.unina_delivery.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OrdineDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.OrdineDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;
import oobd2324_27.uninadelivery.unina_delivery.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataNuovaSpedizione implements Initializable {
    @FXML
    private TableView<Ordine> tabellaOrdini= new TableView<>();

    @FXML
    private TableColumn<Ordine, Double> costoTotaleCol= new TableColumn<>("Costo Totale");

    @FXML
    private TableColumn<Ordine, LocalDate> dataCol= new TableColumn<>("Data");

    @FXML
    private TableColumn<Ordine, String> emailClienteCol= new TableColumn<>("Email Cliente");

    @FXML
    private TableColumn<Ordine, Integer> numeroOrdineCol= new TableColumn<>("Numero Ordine");

    @FXML
    private TableColumn<Ordine, Integer> numeroSpedzioneCol= new TableColumn<>("Numero Spedizione");

    @FXML
    private TableColumn<Ordine, Double> pesoTotaleCol= new TableColumn<>("Peso Totale");

    @FXML
    private Button aggiungiButton;

    @FXML
    private Label corriereLabel;

    @FXML
    private ChoiceBox<?> corrieriDisponibili;

    @FXML
    private ChoiceBox<?> mezziDisponibili;

    @FXML
    private Label numeroSpedizioneLabel;

    @FXML
    private Label pesoSupportatoLabel;

    @FXML
    private Label pesoTotaleLabel;

    @FXML
    void aggiungiSpedizione(ActionEvent event) {

    }

    @FXML
    void showCorrieriDisponibili(MouseEvent event) {

    }

    @FXML
    void showMezziDisponibili(MouseEvent event) {

    }
    @FXML
    void showHome(ActionEvent event) {
        try {
            Stage stage = (Stage) aggiungiButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataPrincipale.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showOrdini(ActionEvent event) {
        try {
            Stage stage = (Stage) aggiungiButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataOrdini.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showSpedizioni(ActionEvent event) {
        try {
            Stage stage = (Stage) aggiungiButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataSpedizioni.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void setTabella(){
        ObservableList<Ordine> ordini= FXCollections.observableArrayList();
        OrdineDAO ordineDAO= new OrdineDAOImp();
        try{
            ordini.addAll(ordineDAO.getAllOrdini());
            tabellaOrdini.setItems(ordini);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numeroOrdineCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        costoTotaleCol.setCellValueFactory(new PropertyValueFactory<>("costoTotale"));
        pesoTotaleCol.setCellValueFactory(new PropertyValueFactory<>("pesoTotale"));
        emailClienteCol.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getCliente().getEmail()));
        numeroSpedzioneCol.setCellValueFactory(dato -> new SimpleObjectProperty<>(dato.getValue().getSpedizione().getNumeroSpedizione()));

        setTabella();

    }
}

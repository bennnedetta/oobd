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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.SpedizioneDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.DAO.SpedizioneDAO;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;
import oobd2324_27.uninadelivery.unina_delivery.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataSpedizioni implements Initializable {

    @FXML
    private TableView<Spedizione> tabellaSpedizioni = new TableView<>();

    @FXML
    private TableColumn<Spedizione, String> numeroSpedizioneCol = new TableColumn<>("Numero Spedizione");

    @FXML
    private TableColumn<Spedizione, String> statoCol = new TableColumn<>("Stato");

    @FXML
    private TableColumn<Spedizione, LocalDate> dataSpedizioneCol = new TableColumn<>("Data Spedizione");

    @FXML
    private TableColumn<Spedizione, LocalDate> dataConsegnaCol = new TableColumn<>("Data Consegna");

    @FXML
    private TableColumn<Spedizione, Double> pesoTotaleCol = new TableColumn<>("Peso Totale");

    @FXML
    private TableColumn<Spedizione, String> targaMezzoCol = new TableColumn<>("Targa Mezzo");

    @FXML
    private TableColumn<Spedizione, Integer> corriereCol = new TableColumn<>("Corriere");

    @FXML
    private Button creaNuovaSpedizioneButton;

    @FXML
    private Button ricaricaTabellaButton;

    @FXML
    void creaNuovaSpedizione(ActionEvent event) {
        try {
            Stage stage = (Stage) creaNuovaSpedizioneButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataNuovaSpedizione.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mostraNuovaTabella(ActionEvent event) {
        setTabella();

    }

    @FXML
    void showOrdini(ActionEvent event) {
        try {
            Stage stage = (Stage) creaNuovaSpedizioneButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataOrdini.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showHome(ActionEvent event) {
        try {
            Stage stage = (Stage) creaNuovaSpedizioneButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataPrincipale.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTabella(){
        ObservableList<Spedizione> spedizioni = FXCollections.observableArrayList();
        SpedizioneDAO spedizioneDAO = new SpedizioneDAOImp();
        try {
            spedizioni.addAll(spedizioneDAO.getAllSpedizioni());
            tabellaSpedizioni.setItems(spedizioni);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numeroSpedizioneCol.setCellValueFactory(new PropertyValueFactory<>("numeroSpedizione"));
        statoCol.setCellValueFactory(new PropertyValueFactory<>("statoSpedizione"));
        dataSpedizioneCol.setCellValueFactory(new PropertyValueFactory<>("dataSpedizione"));
        dataConsegnaCol.setCellValueFactory(new PropertyValueFactory<>("dataConsegna"));
        pesoTotaleCol.setCellValueFactory(new PropertyValueFactory<>("pesoTotale"));
        targaMezzoCol.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getMezzo().getTarga()));
        corriereCol.setCellValueFactory(dato -> new SimpleObjectProperty<>(dato.getValue().getCorriere().getIdcorriere()));

        setTabella();

    }

}

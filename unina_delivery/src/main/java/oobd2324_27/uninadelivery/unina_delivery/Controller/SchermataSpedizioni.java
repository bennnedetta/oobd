package oobd2324_27.uninadelivery.unina_delivery.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataSpedizioni implements Initializable {

    @FXML
    private TableView<Spedizione> tabellaSpedizioni;

    @FXML
    private TableColumn<Spedizione, String> numeroSpedizioneCol;

    @FXML
    private TableColumn<Spedizione, String> statoCol;

    @FXML
    private TableColumn<Spedizione, LocalDate> dataSpedizioneCol;

    @FXML
    private TableColumn<Spedizione, LocalDate> dataConsegnaCol;

    @FXML
    private TableColumn<Spedizione, Double> pesoTotaleCol;

    @FXML
    private TableColumn<Spedizione, String> targaMezzoCol;

    @FXML
    private TableColumn<Spedizione, String> corriereCol;

    @FXML
    private Button creaNuovaSpedizioneButton;

    @FXML
    private Button ricaricaTabellaButton;

    @FXML
    void creaNuovaSpedizione(ActionEvent event) {

    }

    @FXML
    void mostraNuovaTabella(ActionEvent event) {

    }

    @FXML
    void showOrdini(MouseEvent event) {

    }

    @FXML
    void showSpedizioni(MouseEvent event) {

    }

    /*ObservableList<Spedizione> initialData(){
        Spedizione s1 = new Spedizione("1","10/10/1000","123","10/10/1000","conclusa","io","es123r","tu","1,2,3");
        Spedizione s2 = new Spedizione("2","10/10/1000","123","10/10/1000","conclusa","io","es123r","tu","1,2,3");
        return FXCollections.<Spedizione> observableArrayList(s1,s2);
    }
    */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

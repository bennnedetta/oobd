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
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.CorriereDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.MezzoDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OrdineDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.CorriereDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.MezzoDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.OrdineDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.SpedizioneDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.DAO.SpedizioneDAO;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Corriere;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;
import oobd2324_27.uninadelivery.unina_delivery.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private ChoiceBox<String> corrieriDisponibili;

    @FXML
    private ChoiceBox<String> mezziDisponibili;

    @FXML
    private Label numeroSpedizioneLabel;

    @FXML
    private Label pesoSupportatoLabel;

    @FXML
    private Label pesoTotaleLabel;

    @FXML
    private ListView<String> recapOrdini;


    @FXML
    void salvaSpedizione(ActionEvent event) {
        LocalDate dataSpedizione = LocalDate.now();
        LocalDate dataConsegna = LocalDate.now().plusWeeks(1);
        Double pesoTotale = Double.valueOf(pesoTotaleLabel.getText());
        String targaMezzo = mezziDisponibili.getSelectionModel().getSelectedItem().split("\\s+")[0];
        int corriere= Integer.parseInt(corrieriDisponibili.getSelectionModel().getSelectedItem().split("\\s+")[0]);
        MezzoDAO mezzoDAO = new MezzoDAOImp();
        CorriereDAO corriereDAO = new CorriereDAOImp();

        List<String> listaOrdini = recapOrdini.getItems();
        if(listaOrdini!=null){
            try {
                Spedizione spedizione = new Spedizione(dataSpedizione,pesoTotale,dataConsegna,"in transito",mezzoDAO.getByTarga(targaMezzo),corriereDAO.getById(corriere));
                SpedizioneDAO spedizioneDAO = new SpedizioneDAOImp();
                spedizioneDAO.createSpedizione(spedizione);
                for(String data : listaOrdini){
                    Ordine ordine = new OrdineDAOImp().readOrdine(Integer.parseInt(data.split("\\s+")[0]));
                    ordine.setSpedizione(spedizione);
                    OrdineDAO ordineDAO = new OrdineDAOImp();
                    ordineDAO.updateOrdine(ordine);
                }

                Stage stage = (Stage) aggiungiButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataSpedizioni.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }
        }


    }
    @FXML
    void logout(MouseEvent event) {
        try {
            Stage stage = (Stage) aggiungiButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void showCorrieriDisponibili() {
        List<Corriere> listacorrieri = new CorriereDAOImp().getAvailableCorrieri();
        String data = listacorrieri.get(0).getIdcorriere() + " - " + listacorrieri.get(0).getNome() + " " + listacorrieri.get(0).getCognome();
        corrieriDisponibili.setValue(data);
        for(Corriere corriere : listacorrieri){
            data = corriere.getIdcorriere() + " - " + corriere.getNome() + " " + corriere.getCognome();
            corrieriDisponibili.getItems().add(data);
        }
    }

    void showMezziDisponibili() {
        List<Mezzo> listamezzi = new MezzoDAOImp().getDisponibili();
        String data = listamezzi.get(0).getTarga() + " - " + listamezzi.get(0).getPesoSupportato() + " kg ";
        mezziDisponibili.setValue(data);
        for(Mezzo mezzo : listamezzi){
            data = mezzo.getTarga() + " - " + mezzo.getPesoSupportato() + " kg ";
            mezziDisponibili.getItems().add(data);
        }
    }
    void showMezziDisponibili(Double pesoBase) {
        mezziDisponibili.getItems().clear();
        List<Mezzo> listamezzi = new MezzoDAOImp().getDisponibili(pesoBase);
        String data = listamezzi.get(0).getTarga() + " - " + listamezzi.get(0).getPesoSupportato() + " kg ";
        mezziDisponibili.setValue(data);
        for(Mezzo mezzo : listamezzi){
            data = mezzo.getTarga() + " - " + mezzo.getPesoSupportato() + " kg ";
            mezziDisponibili.getItems().add(data);
        }
    }

    @FXML
    void aggiungiOrdine(ActionEvent event) {
        Ordine ordine = tabellaOrdini.getSelectionModel().getSelectedItem();
        if(ordine != null){
            String data= ordine.getNumero() + " - " + ordine.getPesoTotale() + " kg - " + ordine.getCliente().getEmail();
            Double totale = Double.parseDouble(pesoTotaleLabel.getText()) + ordine.getPesoTotale();
            String[] pesoSelezionato = mezziDisponibili.getSelectionModel().getSelectedItem().split("\\s+");
            Double pesoMezzo =  Double.parseDouble(pesoSelezionato[2]);
            recapOrdini.getItems().add(data);
            tabellaOrdini.getItems().remove(ordine);
            calcoloPeso(ordine.getPesoTotale());
            if(totale>pesoMezzo){
                showMezziDisponibili(totale);
            }
        }
    }

    void calcoloPeso(double peso){
        Double totale = Double.parseDouble(pesoTotaleLabel.getText()) + peso;
        pesoTotaleLabel.setText(totale.toString());
    }
    @FXML
    void rimuoviOrdine(ActionEvent event) {
        String data = recapOrdini.getSelectionModel().getSelectedItem();
        if(data != null){
            String[] dataSel = data.split("\\s+");
            Ordine ordine = new OrdineDAOImp().readOrdine(Integer.parseInt(dataSel[0]));
            tabellaOrdini.getItems().add(ordine);
            recapOrdini.getItems().remove(data);
            Double totale = Double.parseDouble(pesoTotaleLabel.getText()) - ordine.getPesoTotale();
            pesoTotaleLabel.setText(totale.toString());
            showMezziDisponibili(totale);
        }

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
            ordini.addAll(ordineDAO.getAllOrdiniDaSpedire());
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
        showMezziDisponibili();
        showCorrieriDisponibili();

    }
}

package oobd2324_27.uninadelivery.unina_delivery.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione.SpedizioneDAOImp;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;
import oobd2324_27.uninadelivery.unina_delivery.Main;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class SchermataPrincipale implements Initializable {
    @FXML
    private DatePicker dataFine = new DatePicker();

    @FXML
    private DatePicker dataInizio = new DatePicker();

    @FXML
    private PieChart graficoSpecifico;

    @FXML
    private LineChart<String, Integer> graficoSpedizioni;

    private XYChart.Series<String,Integer> numeroSpedizioni= new XYChart.Series<>();


    @FXML
    private Label ordineMenoProdottiLabel;


    @FXML
    private Label ordiniSpeditiLabel;

    @FXML
    private Label prodottiNellOrdineLabel;

    @FXML
    private Label valoreOrdineMenoProdotti;

    @FXML
    private Label valoreOrdiniSpediti;

    @FXML
    private Label valoreOrdinePiuProdotti;


    @FXML
    void logout(MouseEvent event) {
        try {
            Stage stage = (Stage) dataInizio.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataInizio.setValue(LocalDate.now().minusMonths(1));
        dataFine.setValue(LocalDate.now());
        List<XYChart.Data<String ,Integer>> dataList= new ArrayList<>();

        for(int i=1;i<=12;i++){
            int spedizioniMensili=new SpedizioneDAOImp().contaSpedizioni(dataInizio.getValue().getYear(), i);
            dataList.add(new XYChart.Data<>(Integer.toString(i),spedizioniMensili));
        }
        numeroSpedizioni.getData().addAll(dataList);
        graficoSpedizioni.getData().add(numeroSpedizioni);

        
    }
    void setChart(LocalDate datainizio,LocalDate datafine){
        HashMap<String, Integer> hashMapSpedizioni = new SpedizioneDAOImp().getNumeroSpedizioniPerData(datainizio,datafine);
        graficoSpecifico.getData().clear();
        for(Map.Entry<String, Integer> data : hashMapSpedizioni.entrySet()){
            String key = "Periodo: " + data.getKey() + " -> Spedizioni: " + data.getValue();
            graficoSpecifico.getData().add(new PieChart.Data(key, data.getValue()));
        }
    }





    @FXML
    void generateReport(ActionEvent event) {
        ordiniSpeditiLabel.setVisible(true);
        ordineMenoProdottiLabel.setVisible(true);
        prodottiNellOrdineLabel.setVisible(true);


       setChart(dataInizio.getValue(),dataFine.getValue());
       Double valore = new SpedizioneDAOImp().getAvg(dataInizio.getValue(),dataFine.getValue());
       valoreOrdiniSpediti.setText(Double.toString(valore));
       int min = new SpedizioneDAOImp().getMin(dataInizio.getValue(),dataFine.getValue());
       valoreOrdineMenoProdotti.setText(Integer.toString(min));
       int max = new SpedizioneDAOImp().getMax(dataInizio.getValue(),dataFine.getValue());
        valoreOrdinePiuProdotti.setText(Integer.toString(max));

        valoreOrdiniSpediti.setVisible(true);
        valoreOrdineMenoProdotti.setVisible(true);
        valoreOrdinePiuProdotti.setVisible(true);
    }

    @FXML
    void showOrdini(ActionEvent event) {
        try {
            Stage stage = (Stage) dataInizio.getScene().getWindow();
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
            Stage stage = (Stage) dataInizio.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/schermataSpedizioni.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
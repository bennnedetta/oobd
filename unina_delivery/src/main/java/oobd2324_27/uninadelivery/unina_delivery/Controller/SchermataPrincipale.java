package oobd2324_27.uninadelivery.unina_delivery.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static oobd2324_27.uninadelivery.unina_delivery.Database.Postgres.getConnection;

public class SchermataPrincipale implements Initializable {
    @FXML
    private DatePicker dataFine = new DatePicker();

    @FXML
    private DatePicker dataInizio = new DatePicker();

    @FXML
    private PieChart graficoCorrieri;
    //controlla
    private Connection connection;


    @FXML
    private LineChart<String, Integer> graficoSpedizioni;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


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
    private Label valoreProdottiNellOrdine;
    @FXML
    private Connection connexion;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataInizio.setValue(LocalDate.now());
        dataFine.setValue(LocalDate.now());
        //ordiniSpeditiLabel.setVisible(false);
        //ordineMenoProdottiLabel.setVisible(false);
        //prodottiNellOrdineLabel.setVisible(false);
        //valoreOrdiniSpediti.setVisible(false);
        //valoreOrdineMenoProdotti.setVisible(false);
        //valoreProdottiNellOrdine.setVisible(false);

        //xAxis.setTickLabelFill(Color.WHITE);
        
    }



    @FXML
    void generateReport(ActionEvent event) {
        ordiniSpeditiLabel.setVisible(true);
        ordineMenoProdottiLabel.setVisible(true);
        prodottiNellOrdineLabel.setVisible(true);
        valoreOrdiniSpediti.setText("5");
        valoreOrdineMenoProdotti.setText("001234");
        valoreProdottiNellOrdine.setText("1");
        valoreOrdiniSpediti.setVisible(true);
        valoreOrdineMenoProdotti.setVisible(true);
        valoreProdottiNellOrdine.setVisible(true);

    //TODO
    //valorizzazione del grafico a torta
    ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                    new PieChart.Data("A", 60),
                    new PieChart.Data("B", 25),
                    new PieChart.Data("C", 15),
                    new PieChart.Data("D", 20),
                    new PieChart.Data("E", 45)
            );

    graficoCorrieri.setData(pieChartData);

   /* String query="SELECT * FROM spedizioni WHERE data_consegna BETWEEN ? AND ?";
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    try{
        getConnection();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    */


    //valorizzazione del grafico con le linee
    xAxis.setTickLabelRotation(45);
    xAxis.setCategories(FXCollections.observableArrayList(
            "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"
    ));
    XYChart.Series<String,Integer> series = new XYChart.Series();
    series.getData().add(new XYChart.Data("gennaio",10));
    series.getData().add(new XYChart.Data("febbraio",50));
    series.getData().add(new XYChart.Data("marzo",75));
    series.getData().add(new XYChart.Data("aprile",10));
    series.getData().add(new XYChart.Data("maggio",80));


    graficoSpedizioni.getData().add(series);

    XYChart.Series<String,Integer> series2 = new XYChart.Series();
    series2.getData().add(new XYChart.Data("febbraio",90));
    series2.getData().add(new XYChart.Data("giugno",30));
    series2.getData().add(new XYChart.Data("dicembre",20));

    graficoSpedizioni.getData().add(series2);

    XYChart.Series<String,Integer> series3 = new XYChart.Series();
    series3.getData().add(new XYChart.Data("luglio",60));
    series3.getData().add(new XYChart.Data("agosto",40));

    graficoSpedizioni.getData().add(series3);

    }

    @FXML
    void showOrdini(ActionEvent event) {

    }

    @FXML
    void showSpedizioni(ActionEvent event) {

    }
}
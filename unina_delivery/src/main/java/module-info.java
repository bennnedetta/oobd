module oobd2324_27.uninadelivery.unina_delivery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    

    opens oobd2324_27.uninadelivery.unina_delivery to javafx.fxml;
    exports oobd2324_27.uninadelivery.unina_delivery;
}
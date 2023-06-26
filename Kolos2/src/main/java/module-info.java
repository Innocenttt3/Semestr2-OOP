module com.example.kolos2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.kolos2 to javafx.fxml;
    exports com.example.kolos2;
}
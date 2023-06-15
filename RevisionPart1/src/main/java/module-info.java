module com.example.revisionpart1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.revisionpart1 to javafx.fxml;
    exports com.example.revisionpart1;
}
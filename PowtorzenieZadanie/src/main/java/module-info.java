module pl.umcs.powtorzeniezadanie {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.umcs.powtorzeniezadanie to javafx.fxml;
    exports pl.umcs.powtorzeniezadanie;
}
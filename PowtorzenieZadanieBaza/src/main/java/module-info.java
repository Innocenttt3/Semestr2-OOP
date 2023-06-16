module pl.umcs.powtorzeniezadanie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;


    opens pl.umcs.powtorzeniezadanie to javafx.fxml;
    exports pl.umcs.powtorzeniezadanie;
}
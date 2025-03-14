module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires java.desktop;
    requires java.management;

    opens GUI to javafx.fxml;
    exports GUI;
    exports Main;
}
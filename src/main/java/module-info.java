module com.example.ejemplocrud {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.core;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.ejemplocrud to javafx.fxml;
    opens peliculas to javafx.fxml;
    exports com.example.ejemplocrud;
    exports peliculas;
}
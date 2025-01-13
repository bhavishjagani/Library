module com.library.library {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.library.library to javafx.fxml;
    exports com.library.library;
    exports controllers;
    opens controllers to javafx.fxml;
}
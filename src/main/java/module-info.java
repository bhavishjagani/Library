module com.library.library {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    // Open models package for JavaFX reflection
    opens com.library.library to javafx.fxml;
    opens models to javafx.base, javafx.fxml;
    exports com.library.library;
    exports controllers;
    opens controllers to javafx.fxml;
}